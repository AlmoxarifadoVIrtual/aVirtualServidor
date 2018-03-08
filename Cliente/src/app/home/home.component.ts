import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {DataService} from "../services/data.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {


  constructor(private dataService: DataService) { }

  ngOnInit() {

  }

}
