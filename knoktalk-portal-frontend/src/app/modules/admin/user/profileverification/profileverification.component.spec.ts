import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileverificationComponent } from './profileverification.component';

describe('ProfileverificationComponent', () => {
  let component: ProfileverificationComponent;
  let fixture: ComponentFixture<ProfileverificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfileverificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileverificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
