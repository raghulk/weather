import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private httpClient: HttpClient) { }

  getData(){
    this.httpClient.get("http://localhost:8080").subscribe((res) =>{
      console.log(res)
    })
  }
}
