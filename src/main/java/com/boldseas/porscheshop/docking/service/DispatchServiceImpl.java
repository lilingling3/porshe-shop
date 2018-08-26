package com.boldseas.porscheshop.docking.service;

import com.alibaba.fastjson.JSON;
import com.boldseas.porscheshop.EventListener;
import com.boldseas.porscheshop.client.DispatchClient;
import com.boldseas.porscheshop.common.constant.CommonConstants;
import com.boldseas.porscheshop.common.constant.PrefixConstant;
import com.boldseas.porscheshop.docking.DispatchService;
import com.boldseas.porscheshop.docking.beans.LeadsDispatchLog;
import com.boldseas.porscheshop.docking.beans.OrderDispatchLog;
import com.boldseas.porscheshop.docking.repository.LeadsDispatchLogRepository;
import com.boldseas.porscheshop.docking.repository.OrderDispatchLogRepository;
import com.boldseas.porscheshop.dtos.*;
import com.boldseas.porscheshop.user.UserService;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author fei.ye
 * @version 2018/5/9.
 */
@Service
@EventListener
@Slf4j
public class DispatchServiceImpl implements DispatchService {
    private final ModelMapper mapper;
    private final DispatchClient dispatchClient;
    private final LeadsDispatchLogRepository leadsDispatchLogRepository;
    private final OrderDispatchLogRepository orderDispatchLogRepository;
    private final UserService userService;

    @Value("${pmp.apiKey}")
    private String pmpApiKey;
    private final String ORDER_DISPATCH_SUCCESS = "success";

    @Autowired
    public DispatchServiceImpl(ModelMapper mapper,
                               DispatchClient dispatchClient,
                               LeadsDispatchLogRepository leadsDispatchLogRepository,
                               OrderDispatchLogRepository orderDispatchLogRepository,
                               UserService userService) {
        this.mapper = mapper;
        this.dispatchClient = dispatchClient;
        this.leadsDispatchLogRepository = leadsDispatchLogRepository;
        this.orderDispatchLogRepository = orderDispatchLogRepository;
        this.userService = userService;
    }

    @Override
    @Subscribe
    public void leadsDispatch(LeadsDTO leadsDTO) {
        log.info("----Leads Dispatch => before dispatch leads: \n {}", JSON.toJSONString(leadsDTO));
        leadsDTO.getLeadsDealers().forEach(leadsDealerDTO -> {
            LeadsDispatchDTO leadsDispatchDTO = mapper.map(leadsDTO, LeadsDispatchDTO.class);
            leadsDispatchDTO.setMobilePhone(leadsDTO.getPhone());
            leadsDispatchDTO.setProvinceId(leadsDTO.getProvinceCode());
            leadsDispatchDTO.setCityId(leadsDTO.getCityCode());
            leadsDispatchDTO.setStreet(leadsDTO.getAddress());
            leadsDispatchDTO.setDealerId(leadsDealerDTO.getDealerId());
            leadsDispatchDTO.setLeadsSource(String.format("%s_%s", PrefixConstant.LEADS_DEALER, leadsDealerDTO.getId().toString()));

            boolean success = true;
            String errorMessage = CommonConstants.EMPTY_STRING;
            try {
                dispatchClient.dispatchLeads(pmpApiKey, leadsDispatchDTO);
            } catch (Exception e) {
                success = false;
                errorMessage = e.getMessage();
                log.error("----Leads Dispatch => dispatch error! leads: \n {}", JSON.toJSONString(leadsDispatchDTO), e);
            } finally {
                LeadsDispatchLog leadsDispatchLog = new LeadsDispatchLog(leadsDTO.getId(), leadsDealerDTO.getId(), success, errorMessage);
                leadsDispatchLogRepository.save(leadsDispatchLog);
            }
        });
    }

    @Override
    @Subscribe
    public void orderDispatch(OrderDTO orderDTO) {
        log.info("----Order Dispatch => before dispatch order: \n {}", JSON.toJSONString(orderDTO));
        UserDTO userDTO = userService.findById(orderDTO.getUserId());
        OrderDispatchDTO orderDispatchDTO = mapper.map(orderDTO, OrderDispatchDTO.class);
        mapper.map(userDTO, orderDispatchDTO);
        orderDispatchDTO.from(userDTO);

        boolean success;
        String errorMessage;
        try {
            OrderDispatchResult result = dispatchClient.dispatchOrder(pmpApiKey, orderDispatchDTO);
            success = Objects.equals(result.getMessage(), ORDER_DISPATCH_SUCCESS);
            errorMessage = result.getMessage();
        } catch (Exception e) {
            success = false;
            errorMessage = e.getMessage();
            log.error("----Order Dispatch => dispatch error! order: \n {}", JSON.toJSONString(orderDispatchDTO), e);
        }
        log.info(orderDispatchDTO.toString());
        OrderDispatchLog orderDispatchLog = new OrderDispatchLog(success, errorMessage, orderDTO.getOrderSn());
        orderDispatchLogRepository.save(orderDispatchLog);
    }
}
