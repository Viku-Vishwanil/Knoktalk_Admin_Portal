import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-userreport',
  templateUrl: './userreport.component.html',
  styleUrls: ['./userreport.component.scss']
})
export class UserreportComponent implements OnInit {

  userReportForm: FormGroup
  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.userReportFormBuilder();
    $(document).ready(function () {
      //Pagination numbers
      $('#paginationSimpleNumbers').DataTable({
        "pagingType": "simple_numbers"
      });
    });
  }

  userReportFormBuilder() {
    this.userReportForm = this.fb.group({
      fromDate: [null],
      toDate: [null,],
      username: [null]

    });
  }
}
