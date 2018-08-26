window.onload = function () {
    new Vue({
        el: "#navigation",
        data: {},
        methods: {
            toSubscription: function () {
                location.href = '/register/subscription'
            },
            toReservation: function () {
                location.href = "/register/reservation"
            },
            toAccount: function () {
                location.href = "/account"
            }
        }
    });

};




