package com.example.quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class DeprecationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            String className = beanDefinition.getBeanClassName();
            try {
                Class<?> beanClass = Class.forName(className);
                DeprecatedClass annotation = beanClass.getAnnotation(DeprecatedClass.class);
                if (annotation != null) {
                   beanDefinition.setBeanClassName(annotation.newImplementation().getName());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
