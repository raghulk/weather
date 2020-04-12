package com.weather.app.rest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

  @Autowired
  private RestTemplate restTemplate;

  public ArrayList<WeatherModel> getTodayData(String latitude, String longitude)
      throws JsonProcessingException {
    ResponseEntity<String> response = restTemplate.getForEntity("https://api.darksky.net/forecast/0b67f8f549800f7bdeccc85500ba9324/"+ latitude + "," + longitude + "," + new Date().getTime()/1000, String.class);
    WeatherModel currentWeatherData = new WeatherModel();
    currentWeatherData.setLongitude(longitude);
    currentWeatherData.setLatitude(latitude);
    parseJSON(response,currentWeatherData);
    ArrayList<WeatherModel> hey = new ArrayList<>();
    hey.add(currentWeatherData);
    return hey;
  }

  private void parseJSON(ResponseEntity<String> response, WeatherModel currentWeatherData)
      throws JsonProcessingException {

    JsonNode responseNode = new ObjectMapper().readTree(Objects.requireNonNull(response.getBody()));

    currentWeatherData.setTemperature(responseNode.get("currently").get("temperature").toString());

    ArrayNode dailyData = (ArrayNode) responseNode.get("daily").get("data");
    Iterator<JsonNode> dailyIterator = dailyData.elements();

    JsonNode dailyNode = dailyIterator.next();
    currentWeatherData.setSunRiseTime(epochToDate(dailyNode.get("sunriseTime").toString()));
    currentWeatherData.setSunsetTime(epochToDate(dailyNode.get("sunsetTime").toString()));
    currentWeatherData.setTemperatureHigh(dailyNode.get("temperatureHigh").toString());
    currentWeatherData.setTemperatureHighTime(epochToDate(dailyNode.get("temperatureHighTime").toString()));
    currentWeatherData.setTemperatureLow(dailyNode.get("temperatureLow").toString());
    currentWeatherData.setTemperatureLowTime(epochToDate(dailyNode.get("temperatureLowTime").toString()));

  }

  private String epochToDate(String epoch) {
    long epoch1 = Integer.parseInt(epoch) * 1000;
    String date = new java.text.SimpleDateFormat("HH:mm").format(new Date(epoch1));
    return date;
  }
}
