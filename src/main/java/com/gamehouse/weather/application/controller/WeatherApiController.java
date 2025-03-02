package com.gamehouse.weather.application.controller;

import com.gamehouse.weather.application.controller.request.WeatherCreateRequest;
import com.gamehouse.weather.application.controller.request.WeatherDataMapper;
import com.gamehouse.weather.application.controller.response.LastWeatherResponse;
import com.gamehouse.weather.application.controller.response.LastWeatherResponseMapper;
import com.gamehouse.weather.application.controller.response.MeasuredWeatherResponse;
import com.gamehouse.weather.domain.entity.WeatherData;
import com.gamehouse.weather.domain.use_case.GetLastUseCase;
import com.gamehouse.weather.domain.use_case.GetMeasuredWeatherUseCase;
import com.gamehouse.weather.domain.use_case.SaveWeatherUseCase;
import com.gamehouse.weather.exception.ResourceCreateException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/weather")
public class WeatherApiController implements WeatherApi {

    private final SaveWeatherUseCase saveWeatherUseCase;

    private final WeatherDataMapper weatherDataMapper;

    private final LastWeatherResponseMapper lastWeatherResponseMapper;

    private final GetLastUseCase getLastUseCase;

    private final GetMeasuredWeatherUseCase getMeasuredWeatherUseCase;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveWeather(
            @RequestBody @Valid WeatherCreateRequest request) throws ResourceCreateException {
        this.saveWeatherUseCase.execute(weatherDataMapper.map(request));
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(value = "/{stationCode}/last", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LastWeatherResponse> getLastWeather(@PathVariable("stationCode")  String stationCode) {
        Optional<WeatherData> result = this.getLastUseCase.execute(stationCode);
        return result.map(weatherData -> ResponseEntity.ok(lastWeatherResponseMapper.map(weatherData))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping(value = "/{stationCode}/range")
    public ResponseEntity<MeasuredWeatherResponse> getWeatherByRange(@PathVariable("stationCode") String stationCode, @RequestParam String startDate, @RequestParam String endDate) {
        Optional<MeasuredWeatherResponse> result = getMeasuredWeatherUseCase.execute(stationCode, LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
