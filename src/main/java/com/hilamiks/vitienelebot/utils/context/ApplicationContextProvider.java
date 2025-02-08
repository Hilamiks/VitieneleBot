package com.hilamiks.vitienelebot.utils.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy(false)
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(final ApplicationContext ctx) throws BeansException {
        context = ctx;
    }

    public static <T> T getBean(final Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static <T> T getBean(final String beanName) {
        return (T) context.getBean(beanName);
    }
}