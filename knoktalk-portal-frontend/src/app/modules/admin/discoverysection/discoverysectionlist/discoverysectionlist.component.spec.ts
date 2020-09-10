import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscoverysectionlistComponent } from './discoverysectionlist.component';

describe('DiscoverysectionlistComponent', () => {
  let component: DiscoverysectionlistComponent;
  let fixture: ComponentFixture<DiscoverysectionlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiscoverysectionlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiscoverysectionlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
