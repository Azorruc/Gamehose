package com.gamehouse.weather.application.controller.request;

import com.gamehouse.weather.domain.entity.WeatherData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WeatherDataMapperTest {

    @InjectMocks
    private WeatherDataMapper weatherDataMapper;

    @Test
    public void when_mapper_is_used_then_data_is_mapped(){
        WeatherCreateRequest request = WeatherCreateRequest.builder()
                .station("ABC")
                .measuredTime(LocalDateTime.of(2025, 2, 18, 10, 20, 30))
                .temperature(BigDecimal.valueOf(15.5))
                .humidity(BigDecimal.valueOf(32.5))
                .windSpeed(BigDecimal.valueOf(45.25))
                .build();

        WeatherData data = weatherDataMapper.map(request);

        assertEquals("ABC", data.getStation());
        assertEquals(LocalDateTime.of(2025, 2, 18, 10, 20, 30), data.getLocalTime());
        assertEquals(BigDecimal.valueOf(15.5), data.getTemperature());
        assertEquals(BigDecimal.valueOf(32.5), data.getHumidity());
        assertEquals(BigDecimal.valueOf(45.25), data.getWindSpeed());
    }
}
