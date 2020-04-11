package com.weather.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class Controller {

  @Autowired
  private RestTemplate restTemplate;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @RequestMapping
  public String getWeatherData() {
    long currentTime = System.currentTimeMillis();
    String oneYearMilliS = "200";
    long oneYear = Long.parseLong(oneYearMilliS);
    long oneYearBackTime = currentTime - oneYear;
    String latitude = "42.3";
    String longitude = "-32.8";
    WeatherModel currentWeatherData = restTemplate.getForObject("https://api.darksky.net/forecast/0b67f8f549800f7bdeccc85500ba9324/"+latitude+","+longitude+","+currentTime, WeatherModel.class);
    WeatherModel oneYearBackWeatherData = restTemplate.getForObject("https://api.darksky.net/forecast/0b67f8f549800f7bdeccc85500ba9324/"+latitude+","+longitude+","+currentTime, WeatherModel.class);
    StringBuilder response = new StringBuilder();
    response.append(currentWeatherData);
    response.append(oneYearBackWeatherData);
    return response.toString();
  }
}
