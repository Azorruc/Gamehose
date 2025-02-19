package com.gamehouse.weather.application.bean;

import com.gamehouse.weather.domain.repository.WeatherDataRepository;
import com.gamehouse.weather.domain.use_case.SaveWeatherUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCase {

    @Bean
    public SaveWeatherUseCase saveWeatherUseCase(WeatherDataRepository weatherDataRepository) {
        return new SaveWeatherUseCase(weatherDataRepository);
    }
}
