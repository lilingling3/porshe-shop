window.onload = function () {
    new Vue({
        el: "#register-success",
        data: {
        },
        methods: {
            order:function () {
                window.location.href = '/register/reservation'
            }
        }

    })
};
