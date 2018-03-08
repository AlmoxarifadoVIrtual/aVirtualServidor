import {AppRoutingModule} from './app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {CustomerDetailsComponent} from './customer-details/customer-details.component';
import {CustomersComponent} from './customers/customers.component';
import {DataService} from './services/data.service';
import {CreateCustomerComponent} from './create-customer/create-customer.component';
import{ LoginComponent} from "./login/login.component";
import { HomeComponent} from "./home/home.component";
import {AuthenticationService} from "./services/authentication.service";
//import { AuthGuardGuard} from "./authGuard/auth-guard.guard";


import {SearchCustomersComponent} from './search-customers/search-customers.component';
import { CadastrarProdutoComponent } from './cadastrar-produto/cadastrar-produto.component';
import {LogginService} from "./services/loggin.service";
import {ProdutoService} from "./services/produto.service";
import {ListarProdutoComponent} from "./listar-produto/listar-produto.component";
import {HttpClient, HttpHandler} from "@angular/common/http";

import { HttpClientModule } from '@angular/common/http';
import { BacklogComponent } from './backlog/backlog.component';
import {ChaveTService} from "./services/chave-t.service";
import {RotasGuard} from "./rotas.guard";
import {ConfigService} from "./config-service";


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [ConfigService, DataService, LogginService, AuthenticationService, ProdutoService,HttpClient, ChaveTService, RotasGuard],
  declarations: [
    AppComponent,
    CustomerDetailsComponent,
    CustomersComponent,
    CreateCustomerComponent,
    SearchCustomersComponent,
    LoginComponent,
    HomeComponent,
    CadastrarProdutoComponent,
    ListarProdutoComponent,
    BacklogComponent

  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
