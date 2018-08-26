var interval;
new Vue({
    el: '#vpay',
    data: {
        isShowQrCode: false,
        isShowUnionPay: false,
        orderSn: orderSn,
        payStyle:''
    },
    methods: {
        alipay: function () {
            this.isShowQrCode = true;
            this.payStyle = '支付宝';
        },
        wechatPay: function () {
            this.isShowQrCode = true;
            this.payStyle = '微信';
        },
        quickPassPay: function () {
            this.isShowQrCode = true;
            this.payStyle = '云闪付';
        },
        unionPay: function () {
            this.isShowUnionPay = true;
            window.open("/pay/pc-gate-way/" + this.orderSn);
        },
        closeMask: function () {
            this.isShowQrCode=false;
        },

        makeCode: function (qrcodeEle, qrCode) {
            qrcodeEle.makeCode(qrCode);
        },
        commonQuestions: function () {
            location.href = '/question'
        },
        chooseOtherPayStyle: function () {
            this.isShowQrCode=false;
        },
        closeMaskUnion: function () {
            this.isShowUnionPay = false
        },
        paySuccess: function () {
            window.location.href = '/pay/success/' + this.orderSn+"?source="+source;
        },
        repeatPay: function () {
            window.location.reload(true);
        },
        initQrCodeEle: function () {
            var ele = document.getElementById("qrcode");
            $("#qrcode").empty();
            return new QRCode(ele, {width: 200, height: 200});
        },
        qrCodePay: function () {
            var _self = this;
            _self.$http.post("/api/v0/pay/qr-code/" + orderSn)
                .then(function (res) {
                    if (res.body.success) {
                        var qrEle = _self.initQrCodeEle();
                        _self.makeCode(qrEle, res.body.qrCodeUrl);
                    }
                });
        },
        wapAliPay: function () {
            this.wapPay("h5AliPay");
        },
        wapQuickPassPay: function () {
            this.wapPay("h5QuickPass");
        },
        wapPay: function (payType) {
            var _self = this;
            _self.$http.post("/api/v0/pay/wap-pay-url", {
                orderSn: _self.orderSn,
                payType: payType
            })
                .then(function (res) {
                    if (res.body.success) {
                        window.location.href = res.body.payUrl;
                    }
                });
        },
        wapUnionPay: function () {
            window.location.href = "/pay/wap-gate-way/" + this.orderSn;
        }
    },
    updated: function () {
        var _self = this;
        if (this.isShowQrCode) {
            _self.qrCodePay();
            interval != null && window.clearInterval(interval);
            interval = window.setInterval(checkPayment, 5000);
        }
    }
});

function checkPayment() {
    $.get("/v1/api/order/paid/" + orderSn + "?v=" + new Date().getTime())
        .then(function (data) {
            if (data.success) {
                window.location.href = "/pay/success/" + orderSn;
            }
        });
}
