var submitTimes = 0;
var vm = new Vue({
    el: '#orderInfo',
    mixins: [my_select],
    data: {
        orderInfo: {
            consignee: '',
            idCard: '',
            province: '',
            provinceCode: '',
            city: '',
            cityCode: '',
            dealerCode: '',
            dealerName: '',
            dealerId: 0,
            companyCn: ''
        },
        province: "",
        provinceName: "",
        city: "",
        cityName: "",
        dealerCode: "",
        dealerName: "",
        selectedDealer: {code: -1},
        selectedProvince: {code: -1},
        selectedCity: {code: -1},
        provinceList: [],
        cityList: [],
        dealerList: [],
        isAffirm: false,
        orderSn: '',
        errMsg: '',
        isShowProvinceOptions: false,
        isShowCity: false,
        isShowCenter: false,
        usernameTip: false,
        userinfoTip: false
    },
    methods: {
        focusInput: function () {
            this.errMsg = ''
        },
        mouseenterinfoTip: function () {
            this.userinfoTip = true
        },
        mouseleaveinfoTip: function () {
            this.userinfoTip = false
        },
        mouseenterUsernameTip: function () {
            this.usernameTip = true
        },
        mouseleaveUsernameTip: function () {
            this.usernameTip = false
        },
        httpError: function (e) {
            console.log(e)
        },
        showCenterOptions: function () {
            this.isShowCity = false;
            this.isShowProvinceOptions = false;
            this.isShowCenter = true
        },
        closeCenterOptions: function () {
            this.isShowCenter = false
        },
        showProvinceOptions: function () {
            this.isShowCity = false;
            this.isShowProvinceOptions = true;
            this.isShowCenter = false
        },
        closeProvinceOptions: function () {
            this.isShowProvinceOptions = false
        },
        showCityOptions: function () {
            this.isShowCity = true;
            this.isShowProvinceOptions = false
        },
        closeCityOptions: function () {
            this.isShowCity = false;
        },
        toAffirm: function () {
            this.orderInfo.consignee = this.orderInfo.consignee.trim();// 购车人
            this.orderInfo.idCard = this.orderInfo.idCard.trim();
            this.orderInfo.provinceCode = this.selectedProvince.code; // 空 -1
            this.orderInfo.cityCode = this.selectedCity.code;// 空 -1
            this.orderInfo.dealerName = this.selectedDealer.dealerId;// 空 -1
            if (this.orderInfo.consignee == '') {
                this.errMsg = '购车人姓名不能为空';
                alertMsg(this.errMsg);
            } else if (this.orderInfo.idCard == '') {
                this.errMsg = '身份证号码不能为空';
                alertMsg(this.errMsg);
            } else if (!isIDcard(this.orderInfo.idCard)) {
                this.errMsg = '请填写正确的身份证号码';
                alertMsg(this.errMsg)
            } else if (this.orderInfo.provinceCode =="" || this.orderInfo.provinceCode == -1) {
                this.errMsg = '请选择省/市';
                alertMsg(this.errMsg)
            } else if (!this.orderInfo.cityCode || this.orderInfo.cityCode == -1) {
                this.errMsg = '请选择市/区';
                alertMsg(this.errMsg);
            } else if (this.orderInfo.dealerName == -1||typeof(this.orderInfo.dealerName) == 'undefined' ) {
                this.errMsg = '请选择意向的保时捷中心';
                alertMsg(this.errMsg)
            } else {
                //弹窗
                this.isAffirm = true;
            }
        },
        closeMask: function () {
            this.isAffirm = false;
        },
        submitOrder: function (e) {
            if (submitTimes >= 1){
                return;
            }
            //提交 页面跳转
            var _self = this;
            _self.orderInfo.consignee = _self.orderInfo.consignee.trim();
            _self.orderInfo.idCard = _self.orderInfo.idCard.trim();
            _self.orderInfo.provinceCode = _self.selectedProvince.code;
            _self.orderInfo.province = _self.selectedProvince.name;
            _self.orderInfo.cityCode = _self.selectedCity.code;
            _self.orderInfo.city = _self.selectedCity.name;
            _self.orderInfo.dealerCode = _self.selectedDealer.code;
            _self.orderInfo.dealerName = _self.selectedDealer.name;
            _self.orderInfo.companyCn = _self.selectedDealer.companyCn;
            _self.orderInfo.dealerId = _self.selectedDealer.dealerId;
            submitTimes ++;
            this.$http.post("/v1/api/order", _self.orderInfo).then(function (res) {
                this.isAffirm = false;
                if (res.body.success) {
                    _self.orderSn = res.body.data.orderSn;
                    location.href = '/pay/index?orderSn=' + this.orderSn + "&source=" + source;
                }else {
                    _self.errMsg = res.body.message;
                    alertMsg(_self.errMsg);
                    submitTimes --;
                }
            }, this.httpError);
        }
    },
    watch: {
        selectedProvince: function (newVal, oldVal) {
            var _self = this;
            if (newVal && typeof newVal != "undefined" && newVal.code != -1) {
                $.get("/v1/api/dealer/city/" + newVal.code, function (data) {
                    _self.cityList = data;
                    _self.selectedCity = {code: -1};
                });
            }
        },
        selectedCity: function (newVal, oldVal) {
            var _self = this;
            if (newVal && typeof newVal != "undefined" && newVal.code != -1) {
                $.get("/v1/api/dealer/" + newVal.code + "?level=" + newVal.level, function (data) {
                    _self.dealerList = data;
                })
            }
            _self.selectedDealer = {code: -1};
        },
        province: function (newVal) {
            var _self = this;
            _self.initCityData = [-1,this.firstCity];
            _self.initDealerData = [-1,this.firstDealer];
            _self.dealerList = [];
            if(newVal == "-1"){
                _self.cityList = [];
                _self.selectedProvince = {"code": -1, "name": ""};
                return ;
            }
            _self.provinceList.forEach(function (e) {
                if (e.id == newVal) {
                    _self.selectedProvince = {"code": e.id, "name": e.nameCn};
                    return;
                }
            });
        },
        city: function (newVal) {
            var _self = this;
            _self.initDealerData = [-1,this.firstDealer];
            if(newVal == "-1"){
                _self.dealerList = [];
                _self.selectedCity = {"code": -1, "name": "", "level": ""};
                return ;
            }
            _self.cityList.forEach(function (e) {
                if (e.id == newVal) {
                    _self.selectedCity = {"code": e.id, "name": e.nameCn, "level": e.level};
                    return;
                }
            });
        },
        dealerCode: function (newVal) {
            var _self = this;
            if(newVal == -1){
                _self.selectedDealer={
                    dealerId: -1,
                    addressCn:null
                }
            }else{
                this.dealerList.forEach(function (e) {
                    if (e.id == newVal) {
                        _self.selectedDealer = {
                            "dealerId": e.id,
                            "code": e.dealerCode,
                            "name": e.nameCn,
                            "addressCn": e.addressCn,
                            "companyCn": e.companyCn
                        };
                        return;
                    }
                });
            }
        }

    },

    created: function () {
        var _self = this;
        $.ajax({
            url: '/v1/api/dealer/province',
            type: "GET",
            dataType: 'json',
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                _self.provinceList = res;
            }
        });
    }
});


document.addEventListener('click', function (e) {
    if (e.target.className.indexOf('select-value') < 0) {
        vm.isShowProvinceOptions = false;
        vm.isShowCity = false;
    }
});

