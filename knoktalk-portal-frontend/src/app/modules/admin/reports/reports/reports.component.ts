import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.scss']
})
export class ReportsComponent implements OnInit {

  constructor(private route: Router) { }

  ngOnInit() {
  }

  userreport

  routeToUserReport() {
    this.route.navigate(['/home/userreport'])
  }

  routeToRewardsReport() {
    this.route.navigate(['/home/rewardsreport'])
  }

}
