import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddsoundgalleryComponent } from './addsoundgallery.component';

describe('AddsoundgalleryComponent', () => {
  let component: AddsoundgalleryComponent;
  let fixture: ComponentFixture<AddsoundgalleryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddsoundgalleryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddsoundgalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
