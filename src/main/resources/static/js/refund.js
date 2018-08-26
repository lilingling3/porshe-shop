
var submitTimes = 0;
new Vue({
    el: ".initiate-refund",
    data: {
        buyOtherCar: false,
        waittingLongTime: false,
        postponePlan: false,
        userInfoChange: false,
        pickCarCityChange: false,
        other: false,
        detail:"",
        order: order,
        refundSuccess: false,
        errorMessage:''
    },
    methods: {
        choose: function (cause, bool) {
            this.errorMessage = '';
            this.$set(this, cause, !bool);
        },
        submit: function () {
            if (submitTimes >= 1){
                return;
            }
            if(!(this.buyOtherCar||this.waittingLongTime||this.postponePlan||this.userInfoChange||this.pickCarCityChange||this.other)){
                this.errorMessage = '请选择退款理由';
                alertMsg("请选择退款理由");
                return;
            }
            if (this.other && this.detail.trim().length <= 0) {
                this.errorMessage = '请填写其他原因';
                alertMsg("请填写其他原因");
                return;
            }
            submitTimes ++;
            this.$http.post("/api/v0/pay/refund", {
                "buyOtherCar": this.buyOtherCar,
                "waittingLongTime": this.waittingLongTime,
                "postponePlan": this.postponePlan,
                "userInfoChange": this.userInfoChange,
                "pickCarCityChange": this.pickCarCityChange,
                "other": this.other,
                "detail": this.detail,
                "orderSn": this.order.orderSn
            }).then(function (e) {
                var data = e.body;
                if (data.success) {
                    this.refundSuccess = true;
                }else{
                    submitTimes --;
                    this.account();
                }
            })
        },
        account: function () {
            location.href = "/account";
        },
        selectOther:function () {
            this.other = true;
            this.errorMessage = '';
        }
    }
});

