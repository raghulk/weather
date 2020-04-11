package com.weather.app.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

  @RequestMapping(value = "/weather-data")
  public ArrayList<WeatherModel> getWeatherData() throws IOException, ParseException {
    Date currentDate = new Date();
    Date dateOneYearBack = currentDate;
    dateOneYearBack.setYear(currentDate.getYear() - 1);
    String latitude = "42.3";
    String longitude = "-32.8";
    ResponseEntity<String> response = restTemplate.getForEntity("https://api.darksky.net/forecast/0b67f8f549800f7bdeccc85500ba9324/"+ latitude + "," + longitude + "," + new Date().getTime()/1000, String.class);
    ResponseEntity<String> responseOneYearBack = restTemplate.getForEntity("https://api.darksky.net/forecast/0b67f8f549800f7bdeccc85500ba9324/"+ latitude + "," + longitude + "," + dateOneYearBack.getTime()/1000, String.class);
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

    ArrayList<WeatherModel> hey = new ArrayList<>();
    hey.add(currentWeatherData);
//    hey.add(oneYearBackWeatherData);
    return hey;
  }
}
