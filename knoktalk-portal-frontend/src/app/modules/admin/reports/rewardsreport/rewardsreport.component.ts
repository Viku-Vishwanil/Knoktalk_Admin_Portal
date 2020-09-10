import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-rewardsreport',
  templateUrl: './rewardsreport.component.html',
  styleUrls: ['./rewardsreport.component.scss']
})
export class RewardsreportComponent implements OnInit {

  rewardsReportForm: FormGroup;
  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.rewardsReportFormBuilder();
    $(document).ready(function () {
      //Pagination numbers
      $('#paginationSimpleNumbers').DataTable({
        "pagingType": "simple_numbers"
      });
    });
  }

  rewardsReportFormBuilder() {
    this.rewardsReportForm = this.fb.group({
      fromDate: [null],
      toDate: [null,],
    });
  }


}
