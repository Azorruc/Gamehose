package com.gamehouse.weather.application.bean;

import com.gamehouse.weather.application.controller.request.WeatherDataMapper;
import com.gamehouse.weather.application.controller.response.LastWeatherResponseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {

    @Bean
    public WeatherDataMapper weatherDataMapper() {
        return new WeatherDataMapper();
    }

    @Bean
    public LastWeatherResponseMapper lastWeatherResponseMapper() {
        return new LastWeatherResponseMapper();
    }
}
