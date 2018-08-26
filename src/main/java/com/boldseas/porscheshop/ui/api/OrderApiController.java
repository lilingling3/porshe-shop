package com.boldseas.porscheshop.ui.api;

import com.boldseas.porscheshop.common.config.Goods;
import com.boldseas.porscheshop.common.exception.ApiException;
import com.boldseas.porscheshop.dtos.*;
import com.boldseas.porscheshop.order.beans.Order;
import com.boldseas.porscheshop.common.constant.OrderValidMessage;
import com.boldseas.porscheshop.interceptor.UserProvider;
import com.boldseas.porscheshop.order.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author feng
 */
@RestController
@RequestMapping("/v1/api/order")
public class OrderApiController {

    private UserProvider userProvider;
    private OrderService orderService;
    private ModelMapper mapper;
    private Goods goods;

    @Autowired
    public OrderApiController(UserProvider userProvider, OrderService orderService, ModelMapper mapper, Goods goods) {
        this.userProvider = userProvider;
        this.orderService = orderService;
        this.mapper = mapper;
        this.goods = goods;
    }

    /**
     * 生成订单接口
     *
     * @param orderDTO
     * @return
     */
    @PostMapping()
    public RestResult<OrderDTO> generateOrder(@RequestBody @Valid OrderDTO orderDTO) {
        RestResult result = checkOrderInfo();
        if (!result.getSuccess()) {
            return result;
        }
        orderDTO.setUserInfo(userProvider.getCurrentUser());
        orderDTO.setGoodsInfo(goods);
        result.setData(mapper.map(orderService.generateOrder(orderDTO), OrderDTO.class));
        return result;
    }

    @GetMapping("/user/{userId}")
    public RestResult<List<OrderDTO>> getUserOrders(@PathVariable Long userId) {
        List<OrderDTO> orderDTOS = orderService.getUserOrders(userId);
        RestResult<List<OrderDTO>> result = new RestResult<>(true, OrderValidMessage.SUCCESS, orderDTOS);
        result.setData(orderDTOS);
        return result;
    }

    /**
     * 获取已支付的订单
     *
     * @param sn
     * @return
     * @throws ApiException
     */
    @GetMapping("/paid/{sn}")
    public RestResult<OrderDTO> getPaidOrderBySn(@PathVariable String sn) throws ApiException {
        Order order = orderService.getOrderBySn(sn);
        return order.getStatus().equals(OrderStatusEnum.paySuccess)
                ? RestResult.success(mapper.map(order, OrderDTO.class))
                : RestResult.failure(OrderValidMessage.ORDER_NOT_FOUND);
    }

    /**
     * 校验订单信息
     *
     * @return
     */
    private RestResult checkOrderInfo() {
        UserDTO user = userProvider.getCurrentUser();

        if (!user.hasNecessaryInfo()) {
            return new RestResult<>(false, OrderValidMessage.USER_COMPLETE_EMPTY);
        }

        if (orderService.isExistValidOrder(user.getId())) {
            return new RestResult<>(false, OrderValidMessage.USER_ORDER_EXIST);
        }
        return new RestResult(true, OrderValidMessage.SUCCESS);
    }

    /**
     * 删除订单
     * 修改订单状态为已删除，订单置为无效
     * @param orderSn order sn
     * @return
     */
    @DeleteMapping("/delete/{orderSn}")
    public RestResult deleteOrder(@PathVariable String orderSn) {
        Order order = orderService.getOrderBySn(orderSn);
        if (!order.isCanBeDeleted()) {
            return RestResult.failure(OrderValidMessage.CAN_NOT_DELETE_ORDER);
        }
        orderService.deleteOrder(order);
        return RestResult.success();
    }
}