package com.boldseas.porscheshop.events.service;

import com.boldseas.porscheshop.EventListener;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author fei.ye
 * @version 2018/5/23.
 */
@Component
public class EventBusService implements InitializingBean {

    @Autowired
    private EventBus eventBus;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationContext.getBeansWithAnnotation(EventListener.class).forEach((name, bean) -> eventBus.register(bean));
    }
}
