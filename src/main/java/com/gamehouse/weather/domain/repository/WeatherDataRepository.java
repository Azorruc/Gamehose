package com.gamehouse.weather.domain.repository;

import com.gamehouse.weather.domain.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    Optional<WeatherData> findTopByStationOrderByReceptionTimeDesc(String stationCode);
}
