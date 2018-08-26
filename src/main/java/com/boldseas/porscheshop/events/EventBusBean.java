package com.boldseas.porscheshop.events;

import com.google.common.eventbus.AsyncEventBus;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author fei.ye
 * @version 2018/5/23.
 */
@Component
public class EventBusBean {

    @Bean
    public AsyncEventBus asynceventbus(){
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2,
                new BasicThreadFactory.Builder().namingPattern("porsche-shop-event-bus-" + UUID.randomUUID()).daemon(true).build());
        return new AsyncEventBus(executorService);
    }
}
