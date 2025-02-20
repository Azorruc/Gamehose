package com.gamehouse.weather.domain.use_case;

import com.gamehouse.weather.application.controller.response.MeasuredWeatherResponse;
import com.gamehouse.weather.domain.entity.WeatherData;
import com.gamehouse.weather.domain.repository.WeatherDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class GetMeasuredWeatherUseCaseTest {

    @InjectMocks
    private GetMeasuredWeatherUseCase getMeasuredWeatherUseCase;

    @Mock
    private WeatherDataRepository weatherDataRepository;

    @BeforeEach
    void setUp() {
        getMeasuredWeatherUseCase = new GetMeasuredWeatherUseCase(weatherDataRepository);
    }

    @Test
    public void when_no_data_is_found_then_return_empty() {
        doReturn(new ArrayList<WeatherData>()).when(weatherDataRepository).findByStationAndReceptionTimeBetween(any(),any(),any());
        assert(getMeasuredWeatherUseCase.execute("ABC", LocalDateTime.now(), LocalDateTime.now()).isEmpty());
    }

    @Test
    public void when_data_is_found_data_is_returned(){
        WeatherData wd1 = WeatherData.builder()
                .station("ABC")
                .localTime(LocalDateTime.of(2025, 1, 1, 12, 0))
                .receptionTime(LocalDateTime.of(2025, 1, 1, 12, 1))
                .temperature(BigDecimal.valueOf(10))
                .humidity(BigDecimal.valueOf(50))
                .windSpeed(BigDecimal.valueOf(20))
                .build();
        WeatherData wd2 = WeatherData.builder()
                .station("ABC")
                .localTime(LocalDateTime.of(2025, 1, 2, 12, 0))
                .receptionTime(LocalDateTime.of(2025, 1, 2, 12, 1))
                .temperature(BigDecimal.valueOf(20))
                .humidity(BigDecimal.valueOf(40))
                .windSpeed(BigDecimal.valueOf(30))
                .build();
        WeatherData wd3 = WeatherData.builder()
                .station("ABC")
                .localTime(LocalDateTime.of(2025, 2, 1, 12, 0))
                .receptionTime(LocalDateTime.of(2025, 2, 1, 12, 1))
                .temperature(BigDecimal.valueOf(30))
                .humidity(BigDecimal.valueOf(60))
                .windSpeed(BigDecimal.valueOf(70))
                .build();


        List<WeatherData> data = Arrays.asList(wd1, wd2, wd3);
        doReturn(data).when(weatherDataRepository).findByStationAndReceptionTimeBetween(any(),any(),any());
        Optional<MeasuredWeatherResponse> result = getMeasuredWeatherUseCase.execute("ABC", LocalDateTime.now(), LocalDateTime.now());

        assert(result.isPresent());
        assertEquals(BigDecimal.valueOf(20.00).setScale(2, RoundingMode.HALF_UP), result.get().getTemperature().getAvg());
        assertEquals(BigDecimal.valueOf(10.00).setScale(2, RoundingMode.HALF_UP), result.get().getTemperature().getMin());
        assertEquals(BigDecimal.valueOf(30.00).setScale(2, RoundingMode.HALF_UP), result.get().getTemperature().getMax());
        assertEquals(BigDecimal.valueOf(50.00).setScale(2, RoundingMode.HALF_UP), result.get().getHumidity().getAvg());
        assertEquals(BigDecimal.valueOf(40.00).setScale(2, RoundingMode.HALF_UP), result.get().getHumidity().getMin());
        assertEquals(BigDecimal.valueOf(60.00).setScale(2, RoundingMode.HALF_UP), result.get().getHumidity().getMax());
        assertEquals(BigDecimal.valueOf(40.00).setScale(2, RoundingMode.HALF_UP), result.get().getWindSpeed().getAvg());
        assertEquals(BigDecimal.valueOf(20.00).setScale(2, RoundingMode.HALF_UP), result.get().getWindSpeed().getMin());
        assertEquals(BigDecimal.valueOf(70.00).setScale(2, RoundingMode.HALF_UP), result.get().getWindSpeed().getMax());
    }
}
