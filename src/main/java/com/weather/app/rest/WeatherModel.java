package com.weather.app.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeatherModel {

  private String latitude;
  private String longitude;
  private String date;
  private String time;
  private String temperature;
  private String sunRiseTime;
  private String sunsetTime;
  private String temperatureHigh;
  private String temperatureHighTime;
  private String temperatureLow;
  private String temperatureLowTime;
}
