import {Component, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Address} from "ngx-google-places-autocomplete/objects/address";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  private static SERVER_URL = "/api/weather-data";
  loaded = false;
  dataSource;
  date = new Date();

  displayedColumns: string[] = ['date', 'time', 'temperature', 'sunriseTime', 'sunsetTime', 'temperatureHigh', 'temperatureHighTime', 'temperatureLow', 'temperatureLowTime'];

  private ELEMENT_DATA: WeatherData[] = [];

  constructor(private httpClient: HttpClient) { }

  handleAddressChange($event: Address) {
    this.makeCall( $event.geometry.location.toJSON())
  }

  findMe() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.makeCall({lat: position.coords.latitude, lng: position.coords.longitude})
      });
    } else {
      alert("Geolocation is not supported by this browser.");
    }
  }

  makeCall(json: Object) {
    this.httpClient.post(AppComponent.SERVER_URL, json).subscribe((res) => {
      this.loaded = true;
      this.ELEMENT_DATA = res as Array<WeatherData>;
      this.dataSource = this.ELEMENT_DATA;
    })
  }
}

export interface WeatherData {
  latitude: string;
  longitude: string;
  date: string;
  time: string;
  temperature: string;
  sunRiseTime: string;
  sunsetTime: string;
  temperatureHigh: string;
  temperatureHighTime: string;
  temperatureLow: string;
  temperatureLowTime: string;
}
