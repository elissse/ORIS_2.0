package com.lab2_03;

import com.lab2_03.component.Abonent;
import com.lab2_03.component.AbonentImpl;
import com.lab2_03.component.Phone;
import com.lab2_03.component.PhoneImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.lab2_03.component")
public class Config {

    @Bean
    public Phone phone() {
        return new PhoneImpl("9090232345", "+7");
    }

    @Bean("abonent")
    public Abonent getAbonent() {
        return new AbonentImpl("Абонент", phone());
    }
}