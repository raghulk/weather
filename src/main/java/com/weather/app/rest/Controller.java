package com.weather.app.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
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
  public ArrayList<WeatherModel> getWeatherData() throws IOException {
    long currentTime = System.currentTimeMillis();
    String oneYearMilliS = "200";
    long oneYear = Long.parseLong(oneYearMilliS);
    long oneYearBackTime = currentTime - oneYear;
    String latitude = "42.3";
    String longitude = "-32.8";
    ResponseEntity<String> response = restTemplate.getForEntity("https://api.darksky.net/forecast/0b67f8f549800f7bdeccc85500ba9324/"+latitude+","+longitude, String.class);
//    WeatherModel currentWeatherData = restTemplate.getForObject("https://api.darksky.net/forecast/0b67f8f549800f7bdeccc85500ba9324/"+latitude+","+longitude, WeatherModel.class);
//    WeatherModel oneYearBackWeatherData = restTemplate.getForObject("https://api.darksky.net/forecast/0b67f8f549800f7bdeccc85500ba9324/"+latitude+","+longitude, WeatherModel.class);
//    System.out.println(response.getBody());
    JsonNode responseNode = new ObjectMapper().readTree(Objects.requireNonNull(response.getBody()));
    WeatherModel currentWeatherData = new WeatherModel();
    currentWeatherData.setLongitude(longitude);
    currentWeatherData.setLatitude(latitude);
    System.out.println(responseNode.get("currently").get("temperature").toString());
    currentWeatherData.setTemperature(responseNode.get("currently").get("temperature").toString());
    currentWeatherData.setSunRiseTime(responseNode.get("daily").get("data").toString());
    ArrayList<WeatherModel> hey = new ArrayList<>();
    hey.add(currentWeatherData);
//    hey.add(oneYearBackWeatherData);
    return hey;
  }
}
