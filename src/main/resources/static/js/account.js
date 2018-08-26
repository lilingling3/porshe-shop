var accountVue = {};
window.onload = function () {
    accountVue = new Vue({
        el: ".account",
        mixins: [register_data, my_select],
        data: {
            showEdit: false,
            orders: orders,
            isShowProvinceOptions: false,
            isShowCityOptions: false,
            isShowShortName: false,
            isReadPolicy: true,
            isOpenDialog: false,
            curOrderSn: ''
        },
        methods: {
            edit: function () {
                this.isAllowSubmit = true;
                this.showEdit = true;
            },
            save: function () {
                var _self = this;
                if (_self.isAllowSubmit) {
                    _self.isAllowSubmit = false;
                    setTimeout(_self.allowSubmit, 2000);
                    _self.register(function () {
                        _self.showEdit = false;
                        _self.synchroUserInfo();
                    });
                }
            },
            inputClick: function () {
                this.errMsg = '';
            },
            cancle: function () {
                this.showEdit = false;
                this.errMsg = '';
                this.isCancle = true;
                this.initUserInfo();
            },
            toReservation: function () {
                location.href = '/register/reservation';
            },
            goPay: function (orderSn) {
                location.href = '/pay/index?orderSn=' + orderSn+"&source="+source;
            },
            goRefund: function (orderSn) {
                location.href = '/pay/initiate-refund/' + orderSn;
            },
            goInstentionList: function (orderSn) {
                location.href = "/account/intentionList?orderSn=" + orderSn;
            },
            showProvinceOptions: function () {
                this.isShowProvinceOptions = !this.isShowProvinceOptions;
                this.isShowCityOptions = false;//关掉其它select
                this.isShowShortName = false;
            },
            closeProvinceOptions: function () {
                this.isShowProvinceOptions = false;
            },
            showCityOptions: function () {
                this.isShowCityOptions = !this.isShowCityOptions;
                this.isShowProvinceOptions = false;//关掉其它select
                this.isShowShortName = false;

            },
            closeCityOptions: function () {
                this.isShowCityOptions = false;
            },
            showShortNameOptions: function () {
                this.isShowShortName = true;
                this.isShowCityOptions = false;
                this.isShowProvinceOptions = false;
            },
            closeShortNameOptions: function () {
                this.isShowShortName = false;
            },
            initUserInfo: function () {
                var _self = this;
                _self.gender = user.gender;
                _self.genderValue = user.genderValue;
                _self.lastName = user.lastName ? user.lastName : "";
                _self.firstName = user.firstName ? user.firstName : "";
                _self.postalCode = user.postalCode;
                _self.email = user.email;
                _self.province = user.provinceCode ? user.provinceCode : -1;
                _self.provinceName = user.province;
                _self.city = user.cityCode ? user.cityCode : -1;
                _self.cityName = user.city;
                _self.address = user.address;
                _self.initData();
            },
            synchroUserInfo: function () {
                var _self = this;
                user.gender = _self.gender;
                user.genderValue = _self.genderValue;
                user.lastName = _self.lastName;
                user.firstName = _self.firstName;
                user.postalCode = _self.postalCode;
                user.email = _self.email;
                user.provinceCode = _self.province;
                user.province = _self.provinceName;
                user.cityCode = _self.city;
                user.city = _self.cityName;
                user.address = _self.address;
            },
            deleteOrder: function () {
                $.ajax({
                    url: '/v1/api/order/delete/' +this.curOrderSn,
                    type: "DELETE",
                    dataType: 'json',
                    contentType: "application/json; charset=utf-8",
                    success: function (res) {
                        if (res.success) {
                            location.reload();
                            this.curOrderSn = '';
                        } else {
                            this.isOpenDialog =false;
                            alert('删除失败');
                        }
                    }
                });
            },
            openOrCloseDialog: function (curOrderSn) {
                if(!this.isOpenDialog) {
                    this.curOrderSn = curOrderSn;
                    document.getElementsByTagName('html')[0].style.overflow='hidden'
                }
                else document.getElementsByTagName('html')[0].style.overflow='inherit'

                this.isOpenDialog = !this.isOpenDialog;
            },
            handleTouchMove:function (e) {
                e.preventDefault();
            }
        }
    });
};





