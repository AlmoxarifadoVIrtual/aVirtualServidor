import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions, } from '@angular/http';
import 'rxjs/add/operator/map';
import {ChaveTService} from "./chave-t.service";
import {Observable} from "rxjs/Observable";
import {LoginCC} from "../interfaces/credencial";
import {HttpClient} from "@angular/common/http";
import {stringify} from "@angular/core/src/util";
import {jsonpCallbackContext} from "@angular/common/http/src/module";
import {ConfigService} from "../config-service";



@Injectable()
export class LogginService {

  constructor(private http: Http, private chaveH: ChaveTService, private htt: HttpClient, private urlConfig: ConfigService) {
  }

  chave: any;
  headers = new Headers({'Content-Type': 'application/json'});
  options = new RequestOptions({headers: this.headers});
  loginStatus = false;
  url = this.urlConfig.getUrlService();

  login(login, senha) {

    console.log(JSON.stringify({login: login, senha: senha}));

    return this.http.post(this.url+'/acesso', JSON.stringify({login: login, senha: senha}), this.options
    ).map((response) => {

      let status = response.status.valueOf();
      // aqui ta a key
      console.log(response.text());

      if (status === 200) {
        this.chave = response.text();
        localStorage.setItem('chave', this.chave );
        //console.log(" aqui é o local storage  "+localStorage.getItem('token'));
        this.headers.set('chave',  this.chave );
        //console.log("aqui é o header " + this.headers.get('x-access-token'));
        this.loginStatus = true;

        return this.chave;
        // return true to indicate successful login

      } else {
        console.log("nada ainda");
        // return false to indicate failed login

      }

    });
  }

  funcaoUser(){
    return this.http.get('/acesso', this.headers.get('chave'));
  }

  loggOut(){
    this.http.delete(this.url+'/acesso', this.headers.get('chave'));
    this.headers.set('chave', '' );
    this.loginStatus= false;
    localStorage.removeItem('chave');
    console.log( "aqui é o header apagado " + this.headers.get('chave'));
    console.log(" aqui é o local storage   apagado "+localStorage.getItem('chave'));

  }

  getLogado(){
    return this.loginStatus;
  }

}
