import {CreateCustomerComponent} from './create-customer/create-customer.component';
import {CustomersComponent} from './customers/customers.component';
import {SearchCustomersComponent} from './search-customers/search-customers.component';

import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {CadastrarProdutoComponent} from "./cadastrar-produto/cadastrar-produto.component";
import {ListarProdutoComponent} from "./listar-produto/listar-produto.component";
import {BacklogComponent} from "./backlog/backlog.component";
import {RotasGuard} from "./rotas.guard";



const routes: Routes = [
  {path: '', redirectTo: 'app', pathMatch: 'full'},
  {path: 'home', component: HomeComponent },
  {path: 'customer', component: CustomersComponent, canActivate: [RotasGuard]},
  {path: 'add', component: CreateCustomerComponent, canActivate: [RotasGuard]},
  //{path: 'login', component: LoginComponent},
  {path: 'cadastrarP', component: CadastrarProdutoComponent, canActivate: [RotasGuard]},


  {path: 'findbylastname', component: SearchCustomersComponent, canActivate: [RotasGuard]},
  {path: 'SistemaAtividade', component: BacklogComponent, canActivate: [RotasGuard]},
  {path: 'listarProduto', component: ListarProdutoComponent, canActivate: [RotasGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
