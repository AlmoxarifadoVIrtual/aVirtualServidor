import { TestBed, inject } from '@angular/core/testing';

import { LogginService } from './loggin.service';

describe('LogginService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LogginService]
    });
  });

  it('should be created', inject([LogginService], (service: LogginService) => {
    expect(service).toBeTruthy();
  }));
});
