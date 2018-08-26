

var headerVue = new Vue({
    el: ".header",
    data: {
        openMenuShow:false,
        openQestion:false
    },
    methods: {
        handleTouchMove:function(e){
            e.preventDefault();
        },
        menuOpenClick:function(){
            var _self = this;
            _self.openMenuShow = true;
        },
        menuCloseClick:function(){
            var _self = this;
            _self.openMenuShow = false;
        },
        menuOpenQestion:function () {
            this.openQestion = true;
            document.getElementsByTagName('html')[0].style.overflow='inherit';
            document.getElementsByTagName('html')[0].style.position='inherit';
            if(document.getElementById('seeDetailImg')){
                document.getElementById('seeDetailImg').style.display = 'none';
            }
            if(document.getElementById('backOrder')){
                document.getElementById('backOrder').style.display = 'none';
            }

            if(document.getElementById('account')){
                document.getElementById('account').style.display = 'none';
            }
        },
        closeQestion:function (e) {
            e.preventDefault();
            this.openQestion = false;
            if(document.getElementById('seeDetailImg')){
                document.getElementById('seeDetailImg').style.display = 'block';
            }
            if(document.getElementById('backOrder')){
                document.getElementById('backOrder').style.display = 'block';
            }

            if(document.getElementById('account')){
                document.getElementById('account').style.display = 'block';
            }
        }
    }

});

function toCommonQestion(source) {
    _hmt.push(['_trackEvent', '常见问题_pc', 'click', 'source='+source])
    window.open('/question');
}


function toHome(source) {
    location.href="/home?source=" + source;
}

function logout(source) {
    $.ajax({
        url: '/v1/api/user/logout',
        type: "POST",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (res) {
            if (res.success) {
                location.href="/login?source=" + source;
            } else {
                alert('退出失败');
            }
        }
    });
}

function alertMsg(txt) {
  if (isWap()) {
      var alertFram = document.createElement("DIV");
      alertFram.style.position = "fixed";
      alertFram.style.width = "220px";
      alertFram.style.textAlign = "center";
      alertFram.style.top = "45%";
      alertFram.style.zIndex = "10001";
      alertFram.style.background = "#fff";
      alertFram.style.left = '50%';
      alertFram.style.fontSize = '13px';
      alertFram.style.borderRadius = '4px';
      alertFram.style.boxShadow = 'rgb(218, 217, 222) 1px 1px 1px 1px';
      alertFram.style.transform = ' translateX(-50%)';
      var strHtml = " <span class='toast'>" + txt + "</span>";
      alertFram.innerHTML = strHtml;
      document.body.appendChild(alertFram);
      setTimeout((function(){
          alertFram.style.display="none";
      }),2000);
  }
}

function isWap() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"];
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            return true;
        }
    }
    return false;
}


// 检查字符串是否为合法邮政编码
function isPostcode (str){
    // 起始数字不能为0，然后是5个数字  [1-9]\d{5}
    var reg = /^[1-9][0-9]{5}$/;
    if (reg.test(str)) {
        return true;
    } else {
        return false;
    }
}
// 检查字符串是否为合法身份证号码
function isIDcard (str){
    var reg = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
    if (reg.test(str)) {
        return true;
    } else {
        return false;
    }
}

//检查邮箱格式
function isEmail(str) {
    var reg =/^[A-Za-z0-9-_.]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    if (str == null||reg.test(str)) {
        return true
    } else {
        return false;
    }
}

//是否为空或者空格
function isNull( str ){
    if (str == null ||str.toString().trim()=='') return true;
    else return false;
}


