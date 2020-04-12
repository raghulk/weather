package com.weather.app.rest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class Controller {

  @Autowired
  private WeatherService weatherService;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @RequestMapping(value = "/weather-data", method = POST)
  public ArrayList<WeatherModel> getWeatherData(@RequestBody JsonNode latLong) throws IOException, ParseException {
    String latitude = latLong.get("lat").toString();
    String longitude = latLong.get("lng").toString();
    return weatherService.getTodayData(latitude, longitude);
  }
}
