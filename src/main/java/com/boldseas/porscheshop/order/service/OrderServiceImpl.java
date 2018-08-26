package com.boldseas.porscheshop.order.service;

import com.boldseas.porscheshop.EventListener;
import com.boldseas.porscheshop.dtos.OrderDTO;
import com.boldseas.porscheshop.dtos.OrderStatusChangeDTO;
import com.boldseas.porscheshop.dtos.OrderStatusDTO;
import com.boldseas.porscheshop.dtos.OrderStatusEnum;
import com.boldseas.porscheshop.events.OrderStatusChangeEvent;
import com.boldseas.porscheshop.interceptor.UserProvider;
import com.boldseas.porscheshop.order.OrderService;
import com.boldseas.porscheshop.order.beans.Order;
import com.boldseas.porscheshop.order.beans.OrderStatus;
import com.boldseas.porscheshop.order.repository.OrderRepository;
import com.boldseas.porscheshop.order.repository.OrderStatusRepository;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author feng
 */
@Service
@EventListener
public class OrderServiceImpl implements OrderService {
    private OrderStatusRepository orderStatusRepository;
    private OrderRepository orderRepository;
    private ModelMapper mapper;
    private AsyncEventBus eventBus;
    private UserProvider userProvider;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository,
                     OrderStatusRepository orderStatusRepository,
                     ModelMapper mapper,
                     AsyncEventBus eventBus,
                     UserProvider userProvider) {
        this.orderStatusRepository = orderStatusRepository;
        this.orderRepository = orderRepository;
        this.mapper = mapper;
        this.eventBus = eventBus;
        this.userProvider = userProvider;
    }

    @Override
    public Order generateOrder(OrderDTO orderDTO) {
        Order order = mapper.map(orderDTO, Order.class);
        order.setStatus(OrderStatusEnum.waitingPay);
        order.generateOrderSn();
        order.setSource(userProvider.getUserSource());
        generateOrderStatus(order.getOrderSn(), OrderStatusEnum.waitingPay);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderBySn(String orderSn) {
        return orderRepository.findByOrderSn(orderSn);
    }

    /***
     * 生成订单状态
     * @param orderSn
     * @param statusEnum
     * @return
     */
    private OrderStatus generateOrderStatus(String orderSn, OrderStatusEnum statusEnum) {
        OrderStatus status = new OrderStatus(orderSn, statusEnum);
        return orderStatusRepository.save(status);
    }

    @Override
    public boolean isExistValidOrder(Long userId) {
        Long ordersCount = orderRepository.findByUserIdAndValidIsTrue(userId)
                .stream()
                .filter(order -> !Objects.equals(order.getStatus(), OrderStatusEnum.refundSuccess))
                .count();
        return ordersCount > 0;
    }

    @Override
    public List<OrderDTO> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserIdAndValidIsTrue(userId);
        return orders.stream().map(order -> mapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }


    @Override
    public List<OrderDTO> getPaySuccessOrdersBeForeToday() {
        LocalTime time = LocalTime.of(0, 0);
        LocalDateTime before = LocalDateTime.of(LocalDate.now(), time);
        List<OrderStatus> orderStatuses = orderStatusRepository.findByStatusAndCreateDateBefore(OrderStatusEnum.paySuccess, before);
        List<String> sns = orderStatuses.stream().map(OrderStatus::getOrderSn).distinct().collect(Collectors.toList());
        List<Order> orders = orderRepository.findByOrderSnIn(sns);
        return orders.stream().map(order -> mapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }

    /**
     * 更新订单状态到订单表
     *
     * @param orderStatusDTO
     * @return
     */
    private Order updateOrderStatus(OrderStatusChangeDTO orderStatusDTO) {
        Order order = orderRepository.findByOrderSn(orderStatusDTO.getOrderSn());
        order.updateOrderStatus(orderStatusDTO.getPayType(), orderStatusDTO.getTerminalEnum(), orderStatusDTO.getStatusEnum());

        return orderRepository.save(order);
    }

    /**
     * 监听状态改变
     *
     * @param changeEvent
     */
    @Subscribe
    public void orderStatusChange(OrderStatusChangeEvent changeEvent) {
        generateOrderStatus(changeEvent.getOrderSn(), changeEvent.getStatusEnum());
        Order order = updateOrderStatus(mapper.map(changeEvent, OrderStatusChangeDTO.class));
        eventBus.post(order);
    }

    @Override
    public List<OrderDTO> getUserOrdersCompleteInfo(Long userId) {
        return orderRepository.findByUserIdAndValidIsTrue(userId).stream().
                sorted((order1, order2) -> order2.getCreateDate().compareTo(order1.getCreateDate())).
                map(order -> {
                    OrderDTO orderDTO = mapper.map(order, OrderDTO.class);
                    orderStatusRepository.findByOrderSn(orderDTO.getOrderSn()).forEach(orderStatus -> {
                        if(orderStatus.getStatus().equals(OrderStatusEnum.paySuccess)){
                            orderDTO.setPaymentTime(orderStatus.getCreateDate());
                        }
                    });
                    return orderDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public List<OrderStatusDTO> getOrderStatusBySn(String sn) {
        List<OrderStatus> orderStatus = orderStatusRepository.findByOrderSn(sn);
        return orderStatus.stream().map(o -> mapper.map(o, OrderStatusDTO.class)).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderCompleteInfo(String orderSn) {
        OrderDTO orderDTO = mapper.map(orderRepository.findByOrderSn(orderSn), OrderDTO.class);
        orderStatusRepository.findByOrderSn(orderDTO.getOrderSn()).forEach(orderStatus -> {
            if(orderStatus.getStatus().equals(OrderStatusEnum.paySuccess)){
                orderDTO.setPaymentTime(orderStatus.getCreateDate());
            }
        });
        return orderDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(Order order) {
        order.setStatus(OrderStatusEnum.deleted);
        order.setValid(false);
        orderRepository.save(order);

        OrderStatus orderStatus = new OrderStatus(order.getOrderSn(), OrderStatusEnum.deleted);
        orderStatusRepository.save(orderStatus);
    }

    @Override
    public List<OrderDTO> findBefore(LocalDateTime localDateTime) {
        return orderRepository.findByCreateDateBefore(localDateTime)
                .stream()
                .map(order -> mapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }
}
