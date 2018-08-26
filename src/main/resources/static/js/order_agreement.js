var timeOut;
var verificationCodeTime = 21;

var agreementVue= new Vue({
    el: '#order_agreement',
    data: {
        isShowAgreement:true,
        isAgreePolicy:false,
        countdown:verificationCodeTime
    },
    methods: {
        showAgreement: function () {
            this.isShowAgreement = true;

        },
        toAgreement:function () {
            console.log('===>>>',this.isAgreePolicy);
            if(!this.isAgreePolicy) return;
            // this.isShowAgreement = false;
            window.location.href='/order/generate?source='+source;
        }
    },
    created:function () {
        timeOut = setTimeout(timer, 0)
    },

    updated: function () {
        // console.log(this.isAgreePolicy)
    }
});

function timer() {
    if (agreementVue.countdown === 1) {
        agreementVue.countdown = verificationCodeTime;
        agreementVue.isAgreePolicy = true;
        clearTimeout(timeOut)
    } else {
        agreementVue.isAgreePolicy = false;
        agreementVue.countdown--;
        setTimeout(timer, 1000);
    }
}