package com.lab2_03.context;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.lab2_03")
@RequiredArgsConstructor

public class ApplicationContext {
}
