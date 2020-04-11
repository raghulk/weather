package com.weather.app.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherModel {
  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getTemperature() {
    return temperature;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }

  public String getSunRiseTime() {
    return sunRiseTime;
  }

  public void setSunRiseTime(String sunRiseTime) {
    this.sunRiseTime = sunRiseTime;
  }

  public String getSunsetTime() {
    return sunsetTime;
  }

  public void setSunsetTime(String sunsetTime) {
    this.sunsetTime = sunsetTime;
  }

  public String getTemperatureHigh() {
    return temperatureHigh;
  }

  public void setTemperatureHigh(String temperatureHigh) {
    this.temperatureHigh = temperatureHigh;
  }

  public String getTemperatureHighTime() {
    return temperatureHighTime;
  }

  public void setTemperatureHighTime(String temperatureHighTime) {
    this.temperatureHighTime = temperatureHighTime;
  }

  public String getTemperatureLow() {
    return temperatureLow;
  }

  public void setTemperatureLow(String temperatureLow) {
    this.temperatureLow = temperatureLow;
  }

  public String getTemperatureLowTime() {
    return temperatureLowTime;
  }

  public void setTemperatureLowTime(String temperatureLowTime) {
    this.temperatureLowTime = temperatureLowTime;
  }

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
