package ru.tkachev.tinkoff_review.spring.task1;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class User implements InitializingBean, ApplicationContextAware {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Шаг 1 инициализации");
        System.out.println("User проинициализирован с именем: " + name);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Шаг 2 инициализации");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
