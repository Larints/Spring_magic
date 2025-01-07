package com.example.quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class); // Получаем аннотацию на поле
            if (annotation != null) { // Если аннотация есть, то присваиваем случайное число в поле
                int min = annotation.min(); // Вытаскаиваем минимальное и максимальное значения
                int max = annotation.max();
                Random random = new Random();
                int i = random.nextInt(max - min) + min;
                field.setAccessible(true);
                ReflectionUtils.setField(field,bean, i);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
