import { Component, Input } from '@angular/core';

import { Customer } from '../interfaces/customer';
import { DataService } from '../services/data.service';

@Component({
  selector: 'customer-detail',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css'],
  providers: [DataService]
})

export class CustomerDetailsComponent {

  @Input() customer: Customer;

  constructor(private dataService: DataService) {}
/*
  delete(): void {
    this.dataService.delete(this.customer.nome).then(() => this.goBack());
  }
*/
  goBack(): void {
    window.location.replace('');
  }
}
