package com.gamehouse.weather.domain.use_case;

import com.gamehouse.weather.domain.entity.WeatherData;
import com.gamehouse.weather.domain.repository.WeatherDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetLastUseCaseTest {

    @InjectMocks
    private GetLastUseCase getLastUseCase;

    @Mock
    private WeatherDataRepository weatherDataRepository;

    @BeforeEach
    void setUp() {
        getLastUseCase = new GetLastUseCase(weatherDataRepository);
    }

    @Test
    public void when_executed_repository_is_called(){
        getLastUseCase.execute("ABC");
        verify(weatherDataRepository).findTopByStationOrderByReceptionTimeDesc("ABC");
    }

    @Test
    public void when_executed_data_is_returned(){
        WeatherData data = WeatherData.builder()
                .station("ABC")
                .localTime(LocalDateTime.of(2025, 2, 18, 10, 30, 30))
                .receptionTime(LocalDateTime.of(2025, 2, 18, 10, 31, 30))
                .temperature(BigDecimal.valueOf(15.5))
                .humidity(BigDecimal.valueOf(35.25))
                .windSpeed(BigDecimal.valueOf(20.5))
                .build();
        doReturn(Optional.of(data)).when(weatherDataRepository).findTopByStationOrderByReceptionTimeDesc(any());
        assertEquals(Optional.of(data), getLastUseCase.execute("ABC"));
    }
}
