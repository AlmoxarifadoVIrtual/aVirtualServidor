import { Injectable } from '@angular/core';
import {Response, Headers, RequestOptions } from "@angular/http";
import {Observable} from "rxjs/Observable";


@Injectable()
export class ChaveTService {

  constructor() { }

  headers(){
    let headersParametro = {'Content-Type': 'aplication/json'};
    if( localStorage['token']){
        headersParametro['Authorization'] = localStorage['token'];
    }
    let headers = new Headers(headersParametro);
    let options =  new RequestOptions({headers: headers});

    return options;
  }

  extrairDados(response: Response){
    let data = response.json();
    return data || {};
  }

  processarErros(erro: any){
    return Observable.throw("erro ao acessar servidor remoto 500");
  }

}
