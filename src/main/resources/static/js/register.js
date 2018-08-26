var appRegister = {};
window.onload = function () {
    appRegister = new Vue({
        el: "#register-form",
        mixins: [register_data,my_select],
        data: {
            isReadPolicy: false,
            emailTip: false,
            adressTip: false ,
            isShowPolicy: false,
            readPolicyAndSubmit: true,
            isHasOrder: false,
            isSeeDetailPage:false,
            isShowProvinceOptions:false,
            isShowShortName:false,
            isShowCity:false,
            classWrap:intention == 'subscription'?'subscription':'reservation'
        },
        methods: {
            handleTouchMove:function(e){
                e.preventDefault();
            },
            emailFocus:function () {
               this.errMsg = ''
            },
            showCityOptions:function () {
                this.isShowCity = true;
                this.isShowShortName =false;
                this.isShowProvinceOptions = false
            },
            closeCityOptions:function () {
                this.isShowCity = false
            },
            showShortNameOptions:function () {
                this.isShowShortName = true;
                this.isShowCity = false;
                this.isShowProvinceOptions =false;
            },
            closeShortNameOptions:function () {
                this.isShowShortName = false;
            },
            showProvinceOptions:function () {
                this.isShowProvinceOptions = true;
                this.isShowShortName = false;
                this.isShowCity = false;
            },
            closeProvinceOptions:function () {
                this.isShowProvinceOptions = false
            },
            toBackPage:function () {
                this.isSeeDetailPage = false
                document.getElementsByTagName('html')[0].style.overflow='inherit';
                document.getElementsByTagName('html')[0].style.position='inherit';
            },
            seeDetailPage:function () {
                this.isSeeDetailPage = true;
                document.getElementsByTagName('html')[0].style.overflow='hidden';
                document.getElementsByTagName('html')[0].style.position='fixed';
            },
            openMask: function () {
                this.isShowPolicy = true
            },
            closeMask: function (e) {
                e.preventDefault();
                this.isShowPolicy = false;
                this.isHasOrder = false
            },
            mouseenterEventAdress: function () {
                this.adressTip = true
            },
            mouseleaveEventAdress: function () {
                this.adressTip = false
            },
            mouseenterEventEmail: function () {
                this.emailTip = true
            },
            mouseleaveEventEmail: function () {
                this.emailTip = false
            },
            readedPolicy: function () {
                this.isReadPolicy = !this.isReadPolicy;
                this.readPolicyAndSubmit = true;
            },
            lastNameFocus: function () {
                this.errMsg = ''
            },
            firstNameFocus: function () {
                this.errMsg = ''
            },
            emailFocus: function () {
                this.errMsg = ''
            },
            toShopOrderPage: function () {
                window.location.href = '/leads'
            },
            toAccoutPage: function () {
                window.location.href = '/account'
            },
            callBack: function (e, link) {
                var data = e.body;
                if (data.success) {
                    if (data.isExistValidOrder && link == '/order/agreement') {
                        this.isHasOrder = true;
                        return;
                    } else {
                        window.location.href = link;
                    }
                }
            },
            skip: function (link) {
                var _self = this;
                if (!this.isAllowSubmit) {
                    return ;
                }
                _self.isAllowSubmit = false;
                setTimeout(_self.allowSubmit,2000);
                this.readPolicyAndSubmit = true;
                _self.register(_self.callBack, link);

            }
        }

    });
};
