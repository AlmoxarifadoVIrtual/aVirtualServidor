import {Component, ViewEncapsulation} from '@angular/core';
import{LogginService} from "./services/loggin.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class AppComponent {

  constructor(private logginS: LogginService , private router: Router ) { }

  model: any = {};
  error: string;
  token = '';
  logado = false;

  ngOnInit() {

  }

  onSubmitLogin() {
    console.log(this.model.loginCred + this.model.senha);
    this.logginS.login(this.model.loginCred, this.model.senha).subscribe((result) => {
      console.log(result);
      let v = result.valueOf();
      console.log(v);
      if (this.logginS.getLogado()) {
        this.logado = true;
        console.log(this.logado+ " este é this.logadooo");
        this.router.navigate(['SistemaAtividade']);
      } else {
        this.error = 'Username or password is incorrect';

      }
    });
  }

  logoutUser() {

    this.router.navigate(['home']);
    this.logado = false;
    this.logginS.loggOut();
    if (this.logado = false){
      this.router.navigate(['home']);
    }
    console.log(this.logginS.loggOut());

  }


}
