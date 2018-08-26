Vue.component('vm-select', {
    props: ['options', 'value', 'first'],
    template: "<div class='my-select'> " +
    "<p  @click='changeValue' :class=\"isDefaultValue?'select-value  default-value' :'select-value'\" >{{name}}<img src='//cdn.boldseas.com/image/porsche-shop/test/mobile/common/down.png'/></p>" +
    "<div class = 'select-option' v-show= 'showOption'>" +
    "<ul  @click= 'showOption = !showOption' >" +
    "<li @click = 'select(-1,first)'  :class='{selected:id == -1}'  :value = '-1' v-if = 'first'> {{first}} </li>" +
    "<li v-for='option in options' @click = 'select(option.id,option.nameCn)'  :class='{selected:id==option.id}'  :value = 'option.id'> {{option.nameCn}} </li>" +
    "</ul>" +
    "</div>" +
    "</div>",
    data: function () {
        return {
            showOption: false,
            id: this.value[0],
            name: this.value[1],
            ind: 0,
            selected: 'selected',
            isDefaultValue:false
        }
    },
    created: function () {
        var _self = this;
        _self.isDefault();
        document.addEventListener('click', function (e) {
            if (!_self.$el.contains(e.target)) {
                _self.showOption = false;
            }
        });
    },
    methods: {
        isDefault:function () {
            if(this.id == -1|| this.id == null){
                this.isDefaultValue = true;
            }else{
                this.isDefaultValue = false;
            }
        },
        changeValue:function () {
            this.showOption = !this.showOption;
        },
        select: function (id, name) {
            this.id = id;
            this.name = name;
            this.isDefaultValue = false;
            this.$emit('select', this.id, this.name);
        }
    },
    watch: {
        value: function (newVal) {
            this.id = newVal[0];
            this.name = newVal[1];
        }
    }
});

var my_select = {
    data: {
        initCityData: [],
        initProvinceData: [],
        initGenderData: [],
        initDealerData: [],
        firstCity: '市/区',
        firstProvince: '省/市',
        firstDealer: '选择保时捷中心'
    },
    methods: {
        selectCity: function (id, name) {
            this.city = id;
            this.cityName = id == -1 ? null : name;
            this.initCityData = [id, name];
            this.errMsg = ''
        },
        selectProvince: function (id, name) {
            this.province = id;
            this.provinceName = id == -1 ? null : name;
            this.initProvinceData = [id, name];
            this.errMsg = ''
        },
        selectGender: function (gender, genderValue) {
            this.gender = gender;
            this.genderValue = genderValue;
            this.initGenderData = [gender, genderValue];
            this.errMsg = ''
        },
        selectDealer: function (id, name) {
            this.dealerCode = id;
            this.dealerName = id == -1 ? null : name;
            this.initGenderData = [id, name];
            this.errMsg = ''
        },
        initData:function () {
            this.initCityData = [this.city==''?-1:this.city, this.cityName ? this.cityName : this.firstCity];
            this.initProvinceData = [this.province==''?-1:this.province, this.provinceName ? this.provinceName : this.firstProvince];
            this.initGenderData = [this.gender, this.genderValue ? this.genderValue : "请选择称谓"];
            this.initDealerData = [-1, this.firstDealer];
        }
    },
    created: function () {
        this.initData();
    }
};
