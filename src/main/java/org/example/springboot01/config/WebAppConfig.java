package org.example.springboot01.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAppConfig {


    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
