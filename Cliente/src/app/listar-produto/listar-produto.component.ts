import { Component, OnInit, Input, Output , EventEmitter } from '@angular/core';
import { Router} from "@angular/router";

import {ProdutoService} from "../services/produto.service";
import {Produto} from "../interfaces/produto";
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-listar-produto',
  templateUrl: './listar-produto.component.html',
  styleUrls: ['./listar-produto.component.css']
})
export class ListarProdutoComponent implements OnInit {

  produtoL: Observable<Produto[]>;
  produtoo: Produto[];
  errorMessage: String;

  constructor(private produtoService: ProdutoService ) {

    }


  //minhaListaProduto = this.produtoService.getAllProdutos();
  //minhaListaProduto = this.produtoService.getAllProdutos();


  getProduto(){

  }

  /*
  deletarItem(nome){

    for ( var i =0; i < this.produtosLista; i++ ){

      if( this.minhaListaProduto[i]["nome"] === nome ){
        this.minhaListaProduto.splice(i,1);
      }

    }

    this.minhaListaProduto.pop();

  }

  deletePPP(produto: Produto): void {
    //this.produto = this.produto.filter(h => h !== produto);
    //this.heroService.deleteHero(hero).subscribe();
  }

 */

  ngOnInit(): void {
    this.produtoL = this.produtoService.getDosProduto();
    this.produtoL.subscribe(
      produto => this.produtoo = produto,
      error =>  this.errorMessage = <any>error);
  }


}
