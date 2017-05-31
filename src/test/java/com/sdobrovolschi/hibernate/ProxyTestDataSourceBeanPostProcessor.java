package com.sdobrovolschi.hibernate;

import net.ttddyy.dsproxy.asserts.ProxyTestDataSource;
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSource;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * @author Stanislav Dobrovolschi
 */
@Component
public class ProxyTestDataSourceBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            ProxyDataSource proxyDataSource = ProxyDataSourceBuilder.create((DataSource) bean)
                    .logQueryBySlf4j(SLF4JLogLevel.INFO)
                    .countQuery()
                    .logSlowQueryBySlf4j(10, TimeUnit.MINUTES)
                    .build();

            return new ProxyTestDataSource(proxyDataSource);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanNane) throws BeansException {
        return bean;
    }
}
