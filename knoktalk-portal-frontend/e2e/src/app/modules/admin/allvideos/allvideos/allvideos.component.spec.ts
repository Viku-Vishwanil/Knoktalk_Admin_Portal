import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllvideosComponent } from './allvideos.component';

describe('AllvideosComponent', () => {
  let component: AllvideosComponent;
  let fixture: ComponentFixture<AllvideosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllvideosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllvideosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
