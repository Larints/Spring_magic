package com.example.demo;

import com.example.quoters.Quoter;
import com.example.quoters.TerminatorQuoter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws InterruptedException {
        //SpringApplication.run(DemoApplication.class, args);
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        classPathXmlApplicationContext.getBean(Quoter.class).sayQuote();
//        while (true) {
//            Thread.sleep(100);
//            classPathXmlApplicationContext.getBean(Quoter.class).sayQuote();
//        }

//        classPathXmlApplicationContext.getBean(Quoter.class).sayQuote();
    }

}
