package com.example.quoters;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

public class PropertyFileApplicationContext extends GenericApplicationContext {

    public PropertyFileApplicationContext(String fileName) {
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(this);
        int i = propertiesBeanDefinitionReader.loadBeanDefinitions(fileName);
        System.out.println("found " + i + " beans");
        String[] beanNames = getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("Bean name: " + beanName);
        }
        refresh();
    }

    public static void main(String[] args) {
        PropertyFileApplicationContext context = new PropertyFileApplicationContext("context.properties");
       context.getBean("quoter", Quoter.class).sayQuote();
    }
}
