var timeOut;
var verificationCodeTime = 60;

var loginVue = new Vue({
    el: ".login",
    data: {
        countdown: verificationCodeTime,
        phone: '',
        verificationCode: '',
        show: true,
        phoneErrorMsg: '',
        errorMsg: '',
        openMenuShow:false,
        isReadPolicy:false,
        isShowPolicy:false,
        isReadPolicyError:false
    },
    methods: {
        handleTouchMove:function(e){
            e.preventDefault();
        },
        readedPolicy: function () {
            this.isReadPolicy = !this.isReadPolicy;
            if(this.isReadPolicy)this.isReadPolicyError = false;
            this.errorMsg = '';
        },
        openMask: function () {
            this.isShowPolicy = true
            // $('html').setAttribute("style","overflow:hidden;height:100%;");
        },
        closeMask:function () {
            this.isShowPolicy = false;
        },
        menuOpenClick:function(){
            var _self = this;
            _self.openMenuShow = true;
        },
        menuCloseClick:function(){
            var _self = this;
            _self.openMenuShow = false;
        },
        phoneInputClick: function () {
            this.phoneErrorMsg = '';
            this.errorMsg = '';

        },
        verificationCodeInputClick: function () {
            this.errorMsg = '';
            this.phoneErrorMsg = '';
        },
        getVerificationCode: function () {
            var _self = this;
            var phoneReg = /^(0|86|17951)?(13[0-9]|15[0-9]|16[6]|17[0-9]|18[0-9]|14[56789]|19[89])[0-9]{8}$/;
            if (!phoneReg.test(_self.phone)) {
                _self.phoneErrorMsg = "请输入正确的手机号";
                alertMsg("请输入正确的手机号");
                return;
            }
            this.sendVerificationCode();
            timeOut = setTimeout(timer, 0);
        },
        login: function () {
            var _self = this;
            var phoneReg = /^1[0-9]{10}$/;
            if (!phoneReg.test(_self.phone)) {
                _self.phoneErrorMsg = "请输入正确的手机号";
                alertMsg("请输入正确的手机号");
                return;
            }
            var verificationCodeReg = /^\d{4}$/;
            if (!verificationCodeReg.test(_self.verificationCode)) {
                _self.errorMsg = "请输入正确的验证码";
                alertMsg("请输入正确的验证码");
                return;
            }
            if (!_self.isReadPolicy) {
                _self.isReadPolicyError = true;
                return;
            }
            this.$http.post("/v1/api/user/login", {
                phone: _self.phone,
                verificationCode: _self.verificationCode,
                source: source
            }).then(function (res) {
                if (res.body.success) {
                    location.href = '/home?source=' + source;
                } else {
                    _self.errorMsg = res.body.message;
                    alertMsg(res.body.message);
                }
            });
        },
        sendVerificationCode: function () {
            var _self = this;
            this.$http.post("/v1/api/user/send_verification_code", {phone: _self.phone})
                .then(function (res) {
                    if(!res.body.success)  {
                        _self.errorMsg = res.body.message;
                        alertMsg(res.body.message);
                    }
                })
        },
        clearErrorMsg: function () {
            this.errorMsg = '';
            this.phoneErrorMsg = '';
        }

    },
    mounted: function () {
        var swiper = new Swiper('.swiper-container', {
            slidesPerView: 1,
            spaceBetween: 30,
            loop: true,
            pagination: {
                el: '.swiper-pagination',
                clickable: true
            },
            autoplay: {
                delay: 3000,
                disableOnInteraction: true
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev'
            }
        });
    }
});


function timer() {
    if (loginVue.countdown === 0) {
        loginVue.countdown = verificationCodeTime;
        loginVue.show = true;
        clearTimeout(timeOut)
    } else {
        loginVue.show = false;
        loginVue.countdown--;
        setTimeout(timer, 1000);
    }
}
