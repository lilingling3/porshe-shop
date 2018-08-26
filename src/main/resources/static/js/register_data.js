var register_data = {
    data: {
        gender: user.gender,
        genderValue: user.genderValue,
        lastName: user.lastName ? user.lastName : "",
        firstName: user.firstName ? user.firstName : "",
        postalCode: user.postalCode,
        email: user.email,
        province: user.provinceCode ? user.provinceCode : -1,
        provinceName: user.province,
        city: user.cityCode ? user.cityCode : -1,
        cityName: user.city,
        address: user.address,
        provinceList: [],
        cityList: [],
        isAllowSubmit: true,
        genderList: [{"id": "MALE", "nameCn": "先生"}, {"id": "FEMALE", "nameCn": "女士"}],
        errMsg: '',
        isCancle:false
    },
    methods: {
        getProvince: function (newVal) {
            var _self = this;
            var selectProvince ;
            _self.provinceList.forEach(function (item) {
                if (item.id == _self.province) {
                    selectProvince = item;
                    return ;
                }
            });

            if (typeof(selectProvince) != "undefined") {
                _self.provinceName = selectProvince['nameCn'];
            }
            this.$http.get("/v1/api/city/" + newVal).then(function (e) {
                _self.cityList = e.body;
            })
        },
        register: function (callBack, link) {
            var _self = this;
            if(!_self.checkData()){
                return;
            }
            _self.$http.post("/v1/api/user/register", {
                "firstName": _self.firstName,
                "lastName": _self.lastName,
                "gender": _self.gender,
                "email": _self.email,
                "province": _self.provinceName,
                "provinceCode": _self.province == -1? null: _self.province,
                "city": _self.cityName,
                "cityCode": _self.city == -1? null : _self.city,
                "address": _self.address,
                "intention": intention,
                "postalCode": _self.postalCode
            }).then(function (e) {
                callBack(e, link);
            }, function (error) {
                console.log(error)
            })
        },
        allowSubmit: function () {
            this.isAllowSubmit = true;
        },
        checkData:function () {
            var _self = this;
            if (isNull(_self.genderValue)) {
                _self.errMsg = '请完善称谓信息';
                alertMsg(_self.errMsg);
                return false;
            }
            // 提交前校验
            if (isNull(_self.firstName) || isNull(_self.lastName)) {
                _self.errMsg = "请完善姓名信息";
                alertMsg(_self.errMsg);
                return false;
            }

            if (!isNull(_self.email )) {
                if (!isEmail(_self.email)) {
                    _self.errMsg = "邮箱格式错误";
                    alertMsg(_self.errMsg);
                    return false;
                }
            }else{
                _self.email = null;
            }

            if (!isNull(_self.postalCode)) {
                if (!isPostcode(_self.postalCode)) {
                    _self.errMsg = '邮政编码错误';
                    alertMsg(_self.errMsg);
                    return false;
                }
            }else{
                _self.postalCode = null;
            }

            if(isNull(_self.address)){
                _self.address = null;
            }

            if(_self.province != "-1" || _self.city != "-1" || _self.address != null || _self.postalCode != null){
                if(!(_self.province != "-1" && _self.city != "-1" && _self.address != null && _self.postalCode != null)){
                    _self.errMsg = '请完善邮寄信息';
                    alertMsg(_self.errMsg);
                    return false;
                }
            }

            if (!_self.isReadPolicy) {
                this.readPolicyAndSubmit = false;
                return false;
            }

            return true;
        }

    },
    created: function () {
        var _self = this;
        _self.$http.get("/v1/api/province").then(function (e) {
            _self.provinceList = e.body;
        });
        if (_self.province != -1) {
            _self.getProvince(_self.province);
        }
    },
    watch: {
        province: function (newVal) {
            if(this.isCancle){
                this.isCancle = false;
                return;
            }
            this.city = -1;
            this.initCityData = [0, this.firstCity];
            if (newVal == -1) {
                this.provinceName = null;
                this.cityList = [];
                return;
            }
            this.getProvince(newVal);
        },
        city: function (newVal) {
            var _self = this;
            if (newVal == -1) {
                this.cityName = null;
            }
            var selectCity;
            _self.cityList.forEach(function (item) {
                if (item.id == _self.city) {
                    selectCity =  item;
                    return;
                }
            });
            if (typeof(selectCity) != "undefined") {
                _self.cityName = selectCity['nameCn'];
            }
        }
    }
};
