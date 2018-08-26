package com.boldseas.porscheshop.docking;

import com.boldseas.porscheshop.dtos.LeadsDTO;
import com.boldseas.porscheshop.dtos.OrderDTO;

/**
 * @author fei.ye
 * @version 2018/5/9.
 */
public interface DispatchService {

    /**
     * 线索派发
     * @param leadsDTO 线索信息
     */
    void leadsDispatch(LeadsDTO leadsDTO);

    /**
     * 派发订单
     * @param order
     */
    void orderDispatch(OrderDTO order);
}
