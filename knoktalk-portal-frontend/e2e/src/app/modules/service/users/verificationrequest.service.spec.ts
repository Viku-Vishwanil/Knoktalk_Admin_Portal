import { TestBed } from '@angular/core/testing';

import { VerificationrequestService } from './verificationrequest.service';

describe('VerificationrequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VerificationrequestService = TestBed.get(VerificationrequestService);
    expect(service).toBeTruthy();
  });
});
