import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RewardsreportComponent } from './rewardsreport.component';

describe('RewardsreportComponent', () => {
  let component: RewardsreportComponent;
  let fixture: ComponentFixture<RewardsreportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RewardsreportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RewardsreportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
