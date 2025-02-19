package com.gamehouse.weather.application.controller;

import com.gamehouse.weather.application.controller.request.WeatherCreateRequest;
import com.gamehouse.weather.application.controller.response.LastWeatherResponse;
import com.gamehouse.weather.application.controller.response.MeasuredWeatherResponse;
import com.gamehouse.weather.exception.ErrorResponse;
import com.gamehouse.weather.exception.ResourceCreateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Tag(name = "Weather", description = "Weather stations management")
public interface WeatherApi {
    @Operation(
        summary = "Save weather measurement", operationId = "saveWeather", tags = "{Weather}"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "weather measurement saved")
    })
    ResponseEntity<Void> saveWeather(
            @RequestBody(description = "Save weather measurement from a station", required = true) WeatherCreateRequest request
    )throws ResourceCreateException;

    @Operation(summary = "Retrieve last weather data received", operationId = "getLastWeather", tags = "{Weather}")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Weather data"),
            @ApiResponse(responseCode = "404", description = "Entity Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<LastWeatherResponse> getLastWeather(
        @Parameter(name = "stationCode", required = true) String stationCode
    );

    @Operation(
            summary = "Get weather by station and range",
            operationId = "getWeatherByRange",
            tags = "{Weather}"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather data"),
            @ApiResponse(responseCode = "404", description = "Entity Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<MeasuredWeatherResponse>  getWeatherByRange(
      @Parameter(name = "stationCode", required = true) String stationCode,
      @Parameter(name = "startDate", required = true) String startDate,
      @Parameter(name = "endDate", required = true) String endDate
    );

}
