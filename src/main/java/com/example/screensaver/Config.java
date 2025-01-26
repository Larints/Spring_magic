package com.example.screensaver;

import org.springframework.context.annotation.*;

import java.awt.*;
import java.util.Random;

@Configuration
@ComponentScan(basePackages = "com.example.screensaver")
public class Config {

    @Bean
    @Scope(value = "periodical")
    public Color color() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    @Bean
    public ColorFrame colorFrame() {
        return new ColorFrame() {
            @Override
            public Color getColor() {
                return color();
            }
        };
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        while (true) {
            try {
                context.getBean(ColorFrame.class).showOnRandomPlace();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
