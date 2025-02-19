package com.gamehouse.weather.application.bean;

import com.gamehouse.weather.application.controller.request.WeatherDataMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {

    @Bean
    public WeatherDataMapper weatherDataMapper() {
        return new WeatherDataMapper();
    }
}
