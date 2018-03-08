import { TestBed, async, inject } from '@angular/core/testing';

import { RotasGuard } from './rotas.guard';

describe('RotasGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RotasGuard]
    });
  });

  it('should ...', inject([RotasGuard], (guard: RotasGuard) => {
    expect(guard).toBeTruthy();
  }));
});
