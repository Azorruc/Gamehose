package com.gamehouse.weather.application.controller;

import com.gamehouse.weather.application.controller.request.WeatherCreateRequest;
import com.gamehouse.weather.application.controller.request.WeatherDataMapper;
import com.gamehouse.weather.application.controller.response.LastWeatherResponse;
import com.gamehouse.weather.application.controller.response.MeasuredWeatherResponse;
import com.gamehouse.weather.domain.entity.WeatherData;
import com.gamehouse.weather.domain.use_case.SaveWeatherUseCase;
import com.gamehouse.weather.exception.ResourceCreateException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/weather")
public class WeatherApiController implements WeatherApi {

    private final SaveWeatherUseCase saveWeatherUseCase;

    private final WeatherDataMapper weatherDataMapper;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveWeather(
            @RequestBody @Valid WeatherCreateRequest request) throws ResourceCreateException {
        WeatherData weatherData = this.saveWeatherUseCase.execute(weatherDataMapper.map(request));

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<LastWeatherResponse> getLastWeather(String stationCode) {
        return null;
    }

    @Override
    public ResponseEntity<MeasuredWeatherResponse> getWeatherByRange(String stationCode, String startDate, String endDate) {
        return null;
    }
}
