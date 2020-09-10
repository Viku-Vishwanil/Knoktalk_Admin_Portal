import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddsoundComponent } from './addsound.component';

describe('AddsoundComponent', () => {
  let component: AddsoundComponent;
  let fixture: ComponentFixture<AddsoundComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddsoundComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddsoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
