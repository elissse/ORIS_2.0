package com.lab2_03;

import com.lab2_03.component.Abonent;
import com.lab2_03.component.AbonentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);


        Abonent abonent = (Abonent) context.getBean("abonent");

        System.out.println(abonent);

        AbonentService service = (AbonentService) context.getBean(AbonentService.class);

        service.print();
    }
}