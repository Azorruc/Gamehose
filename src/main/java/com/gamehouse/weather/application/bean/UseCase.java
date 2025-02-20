package com.gamehouse.weather.application.bean;

import com.gamehouse.weather.domain.repository.WeatherDataRepository;
import com.gamehouse.weather.domain.use_case.GetLastUseCase;
import com.gamehouse.weather.domain.use_case.GetMeasuredWeatherUseCase;
import com.gamehouse.weather.domain.use_case.SaveWeatherUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCase {

    @Bean
    public SaveWeatherUseCase saveWeatherUseCase(WeatherDataRepository weatherDataRepository) {
        return new SaveWeatherUseCase(weatherDataRepository);
    }

    @Bean
    public GetLastUseCase getLastUseCase(WeatherDataRepository weatherDataRepository) {
        return new GetLastUseCase(weatherDataRepository);
    }

    @Bean
    public GetMeasuredWeatherUseCase getMeasuredWeatherUseCase(WeatherDataRepository weatherDataRepository) {
        return new GetMeasuredWeatherUseCase(weatherDataRepository);
    }
}
