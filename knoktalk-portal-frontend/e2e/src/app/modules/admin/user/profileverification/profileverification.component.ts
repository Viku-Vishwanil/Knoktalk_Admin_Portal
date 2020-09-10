import { Component, OnInit } from '@angular/core';
import { VerificationrequestService } from 'src/app/modules/service/users/verificationrequest.service';
import { MatSnackBar } from '@angular/material';
declare var $: any;

@Component({
  selector: 'app-profileverification',
  templateUrl: './profileverification.component.html',
  styleUrls: ['./profileverification.component.scss']
})
export class ProfileverificationComponent implements OnInit {

  verificationList: any;

  constructor(private verificationRequestService: VerificationrequestService,
    private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getAllVerificationDetails();
  }

  // this method isused to get all verification Request List
  getAllVerificationDetails() {
    this.verificationRequestService.getAllVerificationDetails().subscribe((data: any) => {
      if (data.success) {
        this.verificationList = data['listObject'];
        $(document).ready(function () {
          $('#paginationSimpleNumbers').DataTable();
        });
      }
    })
  }

  // this method is used to approve user
  approveUser(verification) {
    if (confirm(`Approve ${verification.user.knoktalkId} user`)) {
      this.verificationRequestService.approveUser(verification.user.userId).subscribe((response: any) => {
        if (response.success) {
          this.getAllVerificationDetails();
        }
        this._snackBar.open(verification.user.knoktalkId, response.message, { duration: 2500, });
      })
    }
  }

  // this method is used to decline user
  declineUser(verification) {
    if (confirm(`Decline ${verification.user.knoktalkId} user`)) {
      this.verificationRequestService.declineUser(verification.user.userId).subscribe((response: any) => {
        if (response.success) {
          this.getAllVerificationDetails();
        }
        this._snackBar.open(verification.user.knoktalkId, response.message, { duration: 2500, });
      })
    }
  }

  // this method is used to change color while approved and decline
  isApproved(verificationFlag) {
    if (verificationFlag == 1) {
      return true
    }
    return false
  }
}
