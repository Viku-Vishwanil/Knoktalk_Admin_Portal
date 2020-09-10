import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SoundhomeComponent } from './soundhome.component';

describe('SoundhomeComponent', () => {
  let component: SoundhomeComponent;
  let fixture: ComponentFixture<SoundhomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SoundhomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SoundhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
