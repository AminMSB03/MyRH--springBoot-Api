import { TestBed } from '@angular/core/testing';

import { Authentication.ServiceService } from './authentication.service.service';

describe('Authentication.ServiceService', () => {
  let service: Authentication.ServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Authentication.ServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
