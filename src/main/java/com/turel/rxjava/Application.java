package com.turel.rxjava;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by chaimturkel on 9/20/16.
 */
@SpringBootApplication
@EnableWebMvc
public class Application extends WebMvcConfigurerAdapter {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private ListableBeanFactory beanFactory;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
