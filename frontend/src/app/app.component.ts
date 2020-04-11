import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private static SERVER_URL = "/api/weather-data";
  constructor(private httpClient: HttpClient) { }

  getData(){
    this.httpClient.get(AppComponent.SERVER_URL).subscribe((res) =>{
      console.log(res)
    })
  }
}
