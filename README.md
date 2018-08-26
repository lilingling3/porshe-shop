[TOC]

## Porsche shop
保时捷商城， 提供用户登陆，留资，下单，支付，派发，通知等功能。

### 测试环境：
* 地址: http://192.168.2.212:30115/
* 域名: porsche-shop.boldseas.com
* jenkins job: http://192.168.8.188:8081/view/rocket-porsche/job/porsche-shop/
* k8s: http://dashboard.bs-k8s.cn/#!/workload?namespace=porsche-shop-test
* 日志: https://es.boldseas.com/kibana/app/kibana#/discover?_g=()&_a=(columns:!(_source),index:AWNjRVV5PiwJyZIZkyro,interval:auto,query:(match_all:()),sort:!('@timestamp',desc))
* 接口测试: https://porsche-shop.boldseas.com/swagger-ui.html
  （先登录，再调用）

### 正式环境：
* 域名: https://www.porsche-online-preorder.com.cn
* jenkins job: https://jenkins.porsche-campaign.com.cn/
* portainer: https://portainer.porsche-campaign.com.cn/
* redash(数据库查看): https://redash.porsche-campaign.com.cn
* 日志: 


### ui 模块
负责处理页面展示，跳转，权限拦截，检测等。高级模块

### api 模块
负责系统对外接口。高级模块

### docking 模块
负责对接第三方系统，比如派发，数据导入等。高层模块

### notify 模块
负责通知发送，包括短信，邮件等。该模块包含业务逻辑处理。高级模块

### order 模块
负责订单信息管理，订单状态管理及订单状态改变时的消息触发等。中层模块

### user 模块
负责用户登录，用户信息管理，用户保存时消息触发等。中层模块

### payment 模块
负责对接UMS支付平台，出发支付完成消息，记录支付记录等。底层模块

## 开发规范约定：

### 数据库Entity
所有数据库Entity全部继承自*com.boldseas.porscheshop.common.jpa.entity*

### 入参对象有效性(valid)验证

入参对象使用@Valid注解,不添加BindingResult参数,如下：
```
    /**
     * 生成支付跳转地址(WAP端支付宝，微信支付）
     *
     * @param generateWapPayUrlInput
     * @return
     */
    @ApiOperation("生成wap端订单支付地址(不包括银联支付)")
    @PostMapping(path = "wap-pay-url")
    public GenerateWapPayUrlOutput generateWapPayUrl(@Valid @RequestBody GenerateWapPayUrlInput generateWapPayUrlInput) {
        return payService.generateWapPayUrl(generateWapPayUrlInput);
    }
```
如果参数校验无效，会抛出MethodArgumentNotValidException异常，全局异常中指定这种异常的统一处理方法：
```
    /**
     * 统一处理请求参数无效的情况
     * 针对情况：@Valid 验证入参对象字段不符合条件 获取BindingResult错误信息
     *
     * @param ex 方法参数无效异常
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResult handleArgumentInvalidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return RestResult.failure(String.join("|", errorMessages));
    }

```
其中，可以获取到具体的校验失败信息.

### 基础数据：
从pmp获取经销商接口 对宁波经销商特殊处理会在6.11上线
需要把/api/sps/dealer/getDealersByCity/{cityId}
换成/api/sps/dealer/getDealersByCityForJ1/{cityId}

把/api/sps/dealer/getDealersByCityForReferral/{cityId}
换成/api/sps/dealer/getDealersByDirectCityForJ1/{cityId}
