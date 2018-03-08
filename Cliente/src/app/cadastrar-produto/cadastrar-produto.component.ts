import { Component, OnInit } from '@angular/core';
import {ProdutoService} from "../services/produto.service";



@Component({
  selector: 'app-cadastrar-produto',
  templateUrl: './cadastrar-produto.component.html',
  styleUrls: ['./cadastrar-produto.component.css']
})
export class CadastrarProdutoComponent implements OnInit {

  constructor( private produtoService: ProdutoService){

  }

  model: any = {};
  error = ''
  isAddProduto = true;

  subimisaoProduto = false;

  cadastrarProduto(){

    this.produtoService.addProduto(this.model.nomeProduto, this.model.marcaProduto,this.model.precoProduto, this.model.unidadeDeMedida,
      this.model.corProduto,this.model.referenciaProduto, this.model.quantProduto, this.model.descricaoProduto, this.model.observacaoProduto);

  }

  newProduto(){
    this.subimisaoProduto = false;
  }



  ngOnInit() {
  }


}
