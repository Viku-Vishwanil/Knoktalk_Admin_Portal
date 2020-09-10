import { TestBed } from '@angular/core/testing';

import { DiscoversectionService } from './discoversection.service';

describe('DiscoversectionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DiscoversectionService = TestBed.get(DiscoversectionService);
    expect(service).toBeTruthy();
  });
});
