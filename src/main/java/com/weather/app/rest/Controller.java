package com.weather.app.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
    long currentTime = System.currentTimeMillis()/1000;
    System.out.println(currentTime);
    String oneYearMilliS = "31556952000";
    long oneYear = Long.parseLong(oneYearMilliS)/1000;
    long oneYearBackTime = currentTime - oneYear;
    String latitude = "42.3";
    String longitude = "-32.8";
    ResponseEntity<String> response = restTemplate.getForEntity("https://api.darksky.net/forecast/0b67f8f549800f7bdeccc85500ba9324/"+ latitude + "," + longitude + "," + currentTime, String.class);
    ResponseEntity<String> responseOneYearBack = restTemplate.getForEntity("https://api.darksky.net/forecast/0b67f8f549800f7bdeccc85500ba9324/"+ latitude + "," + longitude + "," + oneYearBackTime, String.class);
    JsonNode responseNode = new ObjectMapper().readTree(Objects.requireNonNull(response.getBody()));
    WeatherModel currentWeatherData = new WeatherModel();
    currentWeatherData.setLongitude(longitude);
    currentWeatherData.setLatitude(latitude);
    currentWeatherData.setTemperature(responseNode.get("currently").get("temperature").toString());
    ArrayNode dailyData = (ArrayNode) responseNode.get("daily").get("data");
    Iterator<JsonNode> dailyIterator = dailyData.elements();
    while (dailyIterator.hasNext()) {
      JsonNode dailyNode = dailyIterator.next();
      currentWeatherData.setSunRiseTime(dailyNode.get("sunriseTime").toString());
      currentWeatherData.setSunsetTime(dailyNode.get("sunsetTime").toString());
      currentWeatherData.setTemperatureHigh(dailyNode.get("temperatureHigh").toString());
      currentWeatherData.setTemperatureHighTime(dailyNode.get("temperatureHighTime").toString());
      currentWeatherData.setTemperatureLow(dailyNode.get("temperatureLow").toString());
      currentWeatherData.setTemperatureLowTime(dailyNode.get("temperatureLowTime").toString());
    }

//    currentWeatherData.setSunRiseTime(responseNode.get("dailyData").get("data").toString());
    ArrayList<WeatherModel> hey = new ArrayList<>();
    hey.add(currentWeatherData);
//    hey.add(oneYearBackWeatherData);
    return hey;
  }
}
