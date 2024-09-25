package org.adaschool.Weather;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class WeatherReportServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherReport() {
        double latitude = -14.554;
        double longitude = -87.234;

        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(0.0);
        main.setHumidity(70.0);

        WeatherApiResponse response = new WeatherApiResponse();
        response.setMain(main);

        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(response);
        WeatherReport weatherReport = weatherReportService.getWeatherReport(latitude, longitude);

        assertEquals(0.0, weatherReport.getTemperature());
        assertEquals(81.0, weatherReport.getHumidity());
    }



}
