import { Component, OnInit, Inject } from '@angular/core';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
} from "@angular/material/dialog";
import { MatRadioChange, TooltipPosition, MatSnackBar } from '@angular/material';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { UserserviceService } from 'src/app/modules/service/users/userservice.service';
import { WalletService } from 'src/app/modules/service/wallet/wallet.service';
import { AppComponent } from 'src/app/app.component';
import { isNullOrUndefined } from 'util';
import { DeviceService } from 'src/app/modules/service/device/device.service';
import { Router } from '@angular/router';
declare var $: any;
interface Transaction {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-listusers',
  templateUrl: './listusers.component.html',
  styleUrls: ['./listusers.component.scss']
})
export class ListusersComponent implements OnInit {

  usersList: any;

  constructor(public dialog: MatDialog,
    private _snackBar: MatSnackBar,
    private router: Router,
    public userService: UserserviceService) { }

  ngOnInit() {
    //This method is used to get the data from back end and to display in fornt end 
    this.getAllUserDetails();
  }

  // this method is used to change color while Blocked and UnBlocked
  isBlocked(blockFlag) {
    if (blockFlag == 0) {
      return true
    }
    return false
  }

  //This method is used to get the data from back end and to display in fornt end 
  getAllUserDetails() {
    this.userService.getAllUsersDetails().subscribe((data: any) => {
      if (data.success) {
        this.usersList = data['listObject'];
        $(document).ready(function () {
          $('#paginationSimpleNumbers').DataTable();
        });
      }
    })
  }

  // This Method is Used to reload the User List
  reloadUserList() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/home/listusers'])
  }

  // this method is used to block user
  blockUser(user) {
    if (confirm(`Block ${user.knoktalkId} user`)) {
      this.userService.blockUser(user.userId).subscribe((response: any) => {
        if (response.success) {
          this.getAllUserDetails();
        }
        this._snackBar.open(user.knoktalkId, response.message, { duration: 2500, panelClass: ['mat-primary'] });
      })
    }
  }

  // this method is used to Unblock user
  unblockUser(user) {
    if (confirm(`UnBlock ${user.knoktalkId} user`)) {
      this.userService.unBlockUser(user.userId).subscribe((response: any) => {
        if (response.success) {
          this.getAllUserDetails();
        }
        this._snackBar.open(user.knoktalkId, response.message, { duration: 2500, });
      })
    }
  }

  // this method is used to delete user
  deleteUser(user) {
    if (confirm(`Delete ${user.knoktalkId} user`)) {
      let index = this.usersList.findIndex((data: any) => data.userId === user.userId);
      this.userService.deleteUser(user.userId).subscribe((response: any) => {
        if (response.success) {
          this.usersList.splice(index, 1);
          this.reloadUserList();
          // this.customFilter();
        }
        this._snackBar.open(user.knoktalkId, response.message, { duration: 2500, });
      })
    }
  }

  //for popup gift and coin
  openDialog(userDetails: any): void {
    const dialogRef = this.dialog.open(GiftAndCoin, {
      width: "700px",
      height: "600px",
      data: { pageValue: userDetails }
    });
    dialogRef.afterClosed().subscribe((result) => {
    });
  }

  openDevice(userDetails: any): void {
    const dialogRef = this.dialog.open(Device, {
      width: "700px",
      data: { pageValue: userDetails }
    });
    dialogRef.afterClosed().subscribe((result) => {

    });
  }
}

// giftandcoin Component
@Component({
  selector: "giftandcoin",
  templateUrl: "giftandcoin.html",
  styleUrls: ['./listusers.component.scss']
})
export class GiftAndCoin {
  userObject: any;
  noOfGifts: any;
  noOfCoins: any;
  giftOrCoinValue: String;
  selectedValue: string;

  giftForm: FormGroup;
  coinForm: FormGroup
  walletObject: any;
  giftOperation: string;
  coinOperation: string;

  isShown: boolean = false; // hidden by default


  constructor(public dialog: MatDialog,
    private fb: FormBuilder,
    private walletService: WalletService,
    public dialogRef: MatDialogRef<GiftAndCoin>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.userObject = data.pageValue;
  }

  gift: Transaction[] = [
    { value: 'addGift', viewValue: 'Add Gift' },
    { value: 'substractGift', viewValue: 'Substract Gift' },
  ];

  coin: Transaction[] = [
    { value: 'addCoin', viewValue: 'Add Coin' },
    { value: 'substractCoin', viewValue: 'Substract Coin' },
  ];

  ngOnInit() {
    this.giftFormBuilder();
    this.coinFormBuilder();
    this.getWalletByUserId();
  }


  // this methos is used to get the wallet data by using using Id
  getWalletByUserId() {
    this.walletService.getWalletDetailsByUserId(this.userObject.userId).subscribe((response: any) => {
      if (response.success) {
        this.walletObject = response.object;
      }
    })
  }

  // gift code starts here
  // to get value from radio button of gift or coin
  giftOrCoin(gcradio: MatRadioChange) {
    this.giftOrCoinValue = gcradio.value;
  }

  giftFormBuilder() {
    this.giftForm = this.fb.group({
      gifts: "",
      rewardsUpdate: [
        null,
        Validators.compose([
          Validators.required,
          Validators.pattern("^[0-9]+$"),
        ]),
      ],
    })
  }

  giftDropdown(operation) {
    this.giftOperation = operation.value;
  }


  // this method is used to do the add and substraction operation of gift
  updateGift(value) {
    let updatedGift: number;
    if (this.giftOperation == "addGift") {
      updatedGift = +this.walletObject.gifts + +value
      this.giftForm.patchValue({ gifts: updatedGift })
    }
    else if (this.giftOperation == "substractGift") {
      updatedGift = +this.walletObject.gifts - +value
      this.giftForm.patchValue({ gifts: updatedGift })
    }
    else {
      alert("please select an operation");
    }
  }

  // gift form submit
  giftFormSubmit() {
    if (this.giftForm.valid) {
      this.walletService.updateWalletGifts(this.giftForm.value, this.userObject.userId).subscribe((data: any) => {
        if (data.success) {
          this.getWalletByUserId();
          alert(data.message)
          this.toggleShow();
        } else {
          alert(data.message)
        }
      });
    } else {
      alert("Please, fill the proper details.");
    }
  }
  // gifts code ends here

  // toggle for displaying table
  toggleShow() {
    this.isShown = !this.isShown;
  }

  // coin code starts here
  coinFormBuilder() {
    this.coinForm = this.fb.group({
      coins: "",
      rewardsUpdate: [
        null,
        Validators.compose([
          Validators.required,
          Validators.pattern("^[0-9]+$"),
        ]),
      ],
    })
  }

  // to get the value of dropdown from coin
  coinDropdown(operation) {
    this.coinOperation = operation.value;
  }

  // this method is used to do the add and substraction operation of Coins
  updateCoin(value) {
    let updatedCoin: number;
    if (this.coinOperation == "addCoin") {
      updatedCoin = +this.walletObject.coins + +value
      this.coinForm.patchValue({ coins: updatedCoin })
    }
    else if (this.coinOperation == "substractCoin") {
      updatedCoin = +this.walletObject.coins - +value
      this.coinForm.patchValue({ coins: updatedCoin })
    }
    else {
      alert("please select an operation");
    }
  }


  // coin form submit
  coinFormSubmit() {
    if (this.coinForm.valid) {
      this.walletService.updateWalletCoin(this.coinForm.value, this.userObject.userId).subscribe((data: any) => {
        if (data.success) {
          this.walletService.getWalletDetailsByUserId(this.userObject.userId).subscribe((response: any) => {
            if (response.success) {
              this.walletObject = response.object;
            }
          });
          alert(data.message)
          this.toggleShow();
        } else {
          alert(data.message)
        }
      });
    } else {
      alert("Please, fill the proper details.");
    }
  }

  // coin code ends here

  close(): void {
    this.dialogRef.close();
  }
}

// Device Component
@Component({
  selector: "device",
  templateUrl: "device.html",
  styleUrls: ['./listusers.component.scss']
})
export class Device {
  userObject: any;
  deviceObject: any;

  constructor(public dialog: MatDialog,
    private deviceService: DeviceService,
    public dialogRef: MatDialogRef<GiftAndCoin>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.userObject = data.pageValue;
  }



  ngOnInit() {
    // this methos is used to get the Device data by using using Id
    this.deviceService.getDeviceDetailsByUserId(this.userObject.userId).subscribe((response: any) => {
      if (response.success) {
        this.deviceObject = response['listObject'];
      }
    })
  }

  close(): void {
    this.dialogRef.close();
  }
}

