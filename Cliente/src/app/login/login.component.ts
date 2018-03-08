import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { LogginService} from "../services/loggin.service";
import { Router } from "@angular/router";
import { LoginCC} from "../interfaces/credencial";


@Component({
  selector: 'app-login',
  moduleId: module.id,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {

ngOnInit(){}


}
