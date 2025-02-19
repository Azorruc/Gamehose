package com.gamehouse.weather.application.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MeasuredWeatherResponse {

    private MeasuredData temperature;
    private MeasuredData humidity;
    private MeasuredData windSpeed;
}
