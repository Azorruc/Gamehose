package com.gamehouse.weather.domain.use_case;

import com.gamehouse.weather.domain.entity.WeatherData;
import com.gamehouse.weather.domain.repository.WeatherDataRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Builder
@Data
@AllArgsConstructor
public class GetLastUseCase {

    @Autowired
    private final WeatherDataRepository weatherDataRepository;

    public Optional<WeatherData> execute(String stationCode){
        return weatherDataRepository.findTopByStationOrderByReceptionTimeDesc(stationCode);
    }
}
