package com.gamehouse.weather.application.controller.request;

import com.gamehouse.weather.application.util.Mapper;
import com.gamehouse.weather.domain.entity.WeatherData;

import java.time.LocalDateTime;

public class WeatherDataMapper implements Mapper<WeatherCreateRequest, WeatherData > {
    @Override
    public WeatherData map(WeatherCreateRequest input) {
        return WeatherData.builder()
                .station(input.getStation())
                .localTime(input.getMeasuredTime())
                .receptionTime(LocalDateTime.now())
                .temperature(input.getTemperature())
                .humidity(input.getHumidity())
                .windSpeed(input.getWindSpeed())
                .build();
    }
}
