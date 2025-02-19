package com.gamehouse.weather.application.controller.response;

import com.gamehouse.weather.application.util.Mapper;
import com.gamehouse.weather.domain.entity.WeatherData;

public class LastWeatherResponseMapper implements Mapper<WeatherData,LastWeatherResponse> {
    @Override
    public LastWeatherResponse map(WeatherData input) {
        return LastWeatherResponse.builder()
                .station(input.getStation())
                .measuredTime(input.getLocalTime())
                .savedTime(input.getReceptionTime())
                .temperature(input.getTemperature())
                .humidity(input.getHumidity())
                .windSpeed(input.getWindSpeed())
                .build();
    }
}
