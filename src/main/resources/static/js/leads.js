var shopOrderVm = {};
var submitTimes = 0;
window.onload = function () {
    shopOrderVm = new Vue({
        el: "#leads_main",
        data: {
            dealerBoxShow: true,
            isShowProvinceOptions: false,
            isShowCityOptions: false,

            provinces: [],
            cities: [],
            dealers: [],
            selectedProvince: -1,
            selectedCity: -1,
            errorMessage: ''
        },
        created: function () {
            this.getProvincesForDealer();
        },
        methods: {
            showProvinceOptions: function () {
                this.isShowProvinceOptions = !this.isShowProvinceOptions;
                this.isShowCityOptions = false;//关掉其它select
            },
            closeProvinceOptions: function () {
                this.isShowProvinceOptions = false;
            },
            showCityOptions: function () {
                this.isShowCityOptions = !this.isShowCityOptions;
                this.isShowProvinceOptions = false;//关掉其它select

            },
            closeCityOptions: function () {
                this.isShowCityOptions = false;
            },
            getProvincesForDealer: function () {
                var _self = this;
                this.$http.get("/v1/api/dealer/province").then(function (res) {
                    _self.provinces = res.body;
                });
            },
            getCitiesForDealer: function (provinceId) {
                if (!provinceId) {
                    return;
                }
                var _self = this;
                this.$http.get("/v1/api/dealer/city/" + provinceId).then(function (res) {
                    _self.cities = res.body;
                    console.log(_self.cities);
                });
            },
            getDealers: function (cityId, level) {
                if (!cityId) {
                    return;
                }
                var _self = this;
                this.$http.get("/v1/api/dealer/" + cityId + "?level=" + level).then(function (res) {
                    _self.dealers = res.body;
                    _self.dealers.map(function (dealer) {
                        _self.$set(dealer, 'isSelected', false);
                    });
                });
            },
            selectProvince: function (province) {
                this.selectedProvince = province;
            },

            selectCity: function (city) {
                this.selectedCity = city;
            },
            selectDealer: function (dealer) {
                this.errorMessage = '';
                this.$set(dealer, 'isSelected', !dealer.isSelected);
            },
            submit: function () {
                var _self = this;
                var leadsDealersSaveInput = [];

                if (submitTimes >= 1){
                    return;
                }
                this.dealers.filter(function (dealer) {
                    return dealer.isSelected === true;
                }).forEach(function (dealer) {
                    var leadsDealer = {
                        province: _self.selectedProvince.nameCn,
                        provinceCode: _self.selectedProvince.id,
                        city: _self.selectedCity.nameCn,
                        cityCode: _self.selectedCity.id,
                        dealerId: dealer.id,
                        dealerCode: dealer.dealerCode,
                        dealerName: dealer.nameCn
                    };
                    leadsDealersSaveInput.push(leadsDealer);
                });

                if (leadsDealersSaveInput.length <= 0) {
                    _self.errorMessage = "请选择意向的保时捷中心";
                    alertMsg(_self.errorMessage);
                    return;
                }
                submitTimes ++;
                this.$http.post("/v1/api/leads/save", leadsDealersSaveInput).then(function (res) {
                    if (res.body.success) {
                        location.href = "/leads/success/" + res.body.data.id;
                    } else {
                        _self.errorMessage = res.body.message;
                        alertMsg(_self.errorMessage);
                        submitTimes --;
                    }
                });
            }
        },
        watch: {
            selectedProvince: function (newVal, oldVal) {
                this.selectedCity = -1;
                this.cities = [];
                this.dealers = [];
                this.getCitiesForDealer(newVal.id);
            },
            selectedCity: function (newVal, oldVal) {
                this.dealers=[];
                this.getDealers(newVal.id, newVal.level);
            }
        }
    });
};




