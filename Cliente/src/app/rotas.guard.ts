import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import {LogginService} from "./services/loggin.service";

@Injectable()
export class RotasGuard implements CanActivate {

  constructor(private loginService: LogginService) {}
  canActivate() {
    console.log("OnlyLoggedInUsers");
    if (this.loginService.getLogado()) {
      return true;
    } else {
      window.alert("You don't have permission to view this page");
      return false;
    }
  }
}
