import { TestBed, inject } from '@angular/core/testing';

import { ChaveTService } from './chave-t.service';

describe('ChaveTService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ChaveTService]
    });
  });

  it('should be created', inject([ChaveTService], (service: ChaveTService) => {
    expect(service).toBeTruthy();
  }));
});
