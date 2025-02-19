package com.gamehouse.weather.application.controller;

import com.gamehouse.weather.application.controller.request.WeatherCreateRequest;
import com.gamehouse.weather.application.controller.request.WeatherDataMapper;
import com.gamehouse.weather.application.controller.response.LastWeatherResponse;
import com.gamehouse.weather.application.controller.response.LastWeatherResponseMapper;
import com.gamehouse.weather.domain.entity.WeatherData;
import com.gamehouse.weather.domain.use_case.GetLastUseCase;
import com.gamehouse.weather.domain.use_case.SaveWeatherUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WeatherApiControllerTest {

    @InjectMocks
    private WeatherApiController weatherApiController;

    @Mock
    private SaveWeatherUseCase saveWeatherUseCase;

    @Mock
    private WeatherDataMapper weatherDataMapper;

    @Mock
    private GetLastUseCase getLastUseCase;

    @Mock
    private LastWeatherResponseMapper lastWeatherResponseMapper;

    @BeforeEach
    public void setUp() {
        this.weatherApiController = new WeatherApiController(saveWeatherUseCase, weatherDataMapper, lastWeatherResponseMapper, getLastUseCase);
    }

    @Test
    public void when_save_save_use_case_is_called() {
        WeatherCreateRequest request = WeatherCreateRequest.builder()
                .station("ABC")
                .measuredTime(LocalDateTime.of(2025, 2, 18, 10, 30, 30))
                .temperature(BigDecimal.valueOf(15.5))
                .humidity(BigDecimal.valueOf(35.25))
                .windSpeed(BigDecimal.valueOf(20.5))
                .build();
        WeatherData data = WeatherData.builder()
                .station("ABC")
                .localTime(LocalDateTime.of(2025, 2, 18, 10, 30, 30))
                .receptionTime(LocalDateTime.of(2025, 2, 18, 10, 31, 30))
                .temperature(BigDecimal.valueOf(15.5))
                .humidity(BigDecimal.valueOf(35.25))
                .windSpeed(BigDecimal.valueOf(20.5))
                .build();
        doReturn(data).when(weatherDataMapper).map(any());
        weatherApiController.saveWeather(request);
        verify(this.saveWeatherUseCase).execute(data);

    }

    @Test
    public void when_save_mapper_is_called() {
        WeatherCreateRequest request = WeatherCreateRequest.builder()
                .station("ABC")
                .measuredTime(LocalDateTime.of(2025, 2, 18, 10, 30, 30))
                .temperature(BigDecimal.valueOf(15.5))
                .humidity(BigDecimal.valueOf(35.25))
                .windSpeed(BigDecimal.valueOf(20.5))
                .build();

        weatherApiController.saveWeather(request);
        verify(weatherDataMapper).map(request);
    }

    @Test
    public void when_save_response_is_returned() {

        WeatherCreateRequest request = WeatherCreateRequest.builder()
                .station("ABC")
                .measuredTime(LocalDateTime.of(2025, 2, 18, 10, 30, 30))
                .temperature(BigDecimal.valueOf(15.5))
                .humidity(BigDecimal.valueOf(35.25))
                .windSpeed(BigDecimal.valueOf(20.5))
                .build();
        WeatherData data = WeatherData.builder()
                .station("ABC")
                .localTime(LocalDateTime.of(2025, 2, 18, 10, 30, 30))
                .receptionTime(LocalDateTime.of(2025, 2, 18, 10, 31, 30))
                .temperature(BigDecimal.valueOf(15.5))
                .humidity(BigDecimal.valueOf(35.25))
                .windSpeed(BigDecimal.valueOf(20.5))
                .build();
        doReturn(data).when(weatherDataMapper).map(any());
        doReturn(data).when(saveWeatherUseCase).execute(any());
        ResponseEntity<Void> response = weatherApiController.saveWeather(request);

        assert(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void when_get_last_use_case_is_called() {
        weatherApiController.getLastWeather("ABC");
        verify(getLastUseCase).execute("ABC");
    }

    @Test
    public void when_get_last_mapper_is_called() {

        WeatherData data = WeatherData.builder()
                .station("ABC")
                .localTime(LocalDateTime.of(2025, 2, 18, 10, 30, 30))
                .receptionTime(LocalDateTime.of(2025, 2, 18, 10, 31, 30))
                .temperature(BigDecimal.valueOf(15.5))
                .humidity(BigDecimal.valueOf(35.25))
                .windSpeed(BigDecimal.valueOf(20.5))
                .build();

        doReturn(Optional.of(data)).when(getLastUseCase).execute(any());
        weatherApiController.getLastWeather("ABC");
        verify(lastWeatherResponseMapper).map(data);
    }

    @Test
    public void when_get_last_response_is_returned() {
        WeatherData data = WeatherData.builder()
                .station("ABC")
                .localTime(LocalDateTime.of(2025, 2, 18, 10, 30, 30))
                .receptionTime(LocalDateTime.of(2025, 2, 18, 10, 31, 30))
                .temperature(BigDecimal.valueOf(15.5))
                .humidity(BigDecimal.valueOf(35.25))
                .windSpeed(BigDecimal.valueOf(20.5))
                .build();

        LastWeatherResponse mockResponse = LastWeatherResponse.builder()
                .station("ABC")
                .measuredTime(LocalDateTime.of(2025, 2, 18, 10, 30, 30))
                .savedTime(LocalDateTime.of(2025, 2, 18, 10, 31, 30))
                .temperature(BigDecimal.valueOf(15.5))
                .humidity(BigDecimal.valueOf(35.25))
                .windSpeed(BigDecimal.valueOf(20.5))
                .build();

        doReturn(Optional.of(data)).when(getLastUseCase).execute(any());
        doReturn(mockResponse).when(lastWeatherResponseMapper).map(any());
        ResponseEntity<LastWeatherResponse> response = weatherApiController.getLastWeather("ABC");
        assert(response.getStatusCode().is2xxSuccessful());
        assertEquals(response.getBody(), mockResponse);

    }

    @Test
    public void when_get_last_not_found_404_is_returned() {
        doReturn(Optional.empty()).when(getLastUseCase).execute(any());
        ResponseEntity<LastWeatherResponse> response = weatherApiController.getLastWeather("CBD");
        assert(response.getStatusCode().is4xxClientError());

    }
}
