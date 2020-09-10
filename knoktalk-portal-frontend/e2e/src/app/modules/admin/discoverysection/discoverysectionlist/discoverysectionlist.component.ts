import { Component, OnInit, Inject } from '@angular/core';
import { Router, NavigationExtras, ActivatedRoute } from '@angular/router';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormGroup, FormBuilder, Validators, ValidatorFn, ValidationErrors } from '@angular/forms';
import { disablePrefixSpace } from '../../custom.validation';
import { DiscoversectionService } from 'src/app/modules/service/discoversection/discoversection.service';
import { DialogData } from 'src/app/modules/login/login/login.component';
import { isNullOrUndefined } from 'util';
declare var $: any;

@Component({
  selector: 'app-discoverysectionlist',
  templateUrl: './discoverysectionlist.component.html',
  styleUrls: ['./discoverysectionlist.component.scss']
})
export class DiscoverysectionlistComponent implements OnInit {

  discoverList;
  dataForEdit;
  constructor(public dialog: MatDialog, private discovetSectionService: DiscoversectionService, private router: Router) { }

  ngOnInit() {

    //This method is used to get the data from back end and to display in fornt end 
    this.getDataFormBackEnd();
  }

  //This method is use to open the Pop up form
  openDialog() {
    // this.dataForEdit=list;
    this.dialog.open(AddDiscovery, {
      width: "600px",
      data: {
        animal: 'panda'
      }
    });

  }
  // This method is use to open the edit pop up form
  openEditDialog(data) {
    this.routeToEditDiscovery(data)
    const dialogRef = this.dialog.open(editDiscovery, {
      width: "600px",
      data: { pageValue: data }
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.getDataFormBackEnd();
    });
  }

  //This method is used to get the data from back end and to display in UI
  getDataFormBackEnd() {
    this.discovetSectionService.getDiscoverSectionList().subscribe((data: any) => {
      if (data.success) {
        this.discoverList = data['listObject'];
        $(document).ready(function () {
          $('#paginationSimpleNumbers').DataTable();
        });
      } else {
        $(document).ready(function () {
          $('#paginationSimpleNumbers').DataTable();
        });
      }
    })
  }
  routeToEditDiscovery(discovery: any) {
    let navigationExtras: NavigationExtras = {
      queryParams: { discoverSectionId: discovery.discoverSectionId }
    };
    // this.router.navigate(["/home/companieshome/editCompanies"], navigationExtras);
  }
  //This method is used the for reload the same page
  reloadComponent() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/home/discoverysectionlist'])
  }

  //This method delete dicovery sectioon
  routeToDeleteDiscoverSection(discorySectionDetails: any) {

    if (confirm(`Are you sure to delete this discovery section ?`)) {
      this.discovetSectionService.deleteDiscoverSectiojDetails(discorySectionDetails.discoverSectionId).subscribe((resp: any) => {
        if (resp.success) {
          alert(resp.message);
          this.reloadComponent();
        }
      });

    }
  }
}

@Component({
  selector: 'add-discovery',
  templateUrl: 'adddiscovery.html',
  styleUrls: ['./discoverysectionlist.component.scss']
})
export class AddDiscovery {

  discoveryObject: any;
  discoverList: any;
  addDiscoverSectionForm: FormGroup;
  constructor(
    public dialogRef: MatDialogRef<AddDiscovery>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private router: Router, private fb: FormBuilder, private discovetSectionService: DiscoversectionService
  ) {
    this.discoveryObject = data.pageValue;
  }

  ngOnInit() {
    this.addDiscoverSectionForm = this.fb.group({
      sectionName: [null, [Validators.required, Validators.pattern("^[a-zA-Z \s]+$"), disablePrefixSpace]]
    })
    this.addDiscoverSectionForm.setValidators(this.customValidation());
    this.getDataFormBackEnd();
  }

  //This method is used to get the data from back end
  getDataFormBackEnd() {
    this.discovetSectionService.getDiscoverSectionList().subscribe((data: any) => {
      if (data.success) {
        this.discoverList = data['listObject'];
      }
    })
  }

  //This method is used to validate the User Input fileds
  sectionName: string;
  customValidation(): ValidatorFn {
    return (formGroup: FormGroup): ValidationErrors => {
      const sectionName = formGroup.controls['sectionName'];
      if (sectionName.value !== '' && sectionName.value !== null) {
        if (sectionName.valid) {
          if (!isNullOrUndefined(this.discoverList)) {
            this.discoverList.forEach((data: any) => {
              if (data.sectionName == sectionName.value) {
                this.sectionName = data.sectionName;
                sectionName.setErrors({ DuplicateUserName: true });
              }
            });
          }
        } else {
          if (this.sectionName == sectionName.value) {
            sectionName.setErrors({ DuplicateUserName: true });
          }
        }
      } else {
        sectionName.setErrors({ NoUserName: true });
      }
      return;
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  //This methos close the pop up
  close() {
    this.dialogRef.close();
  }
  //This Method is used to save the section details 
  saveDiscoversectionDetails() {
    if (this.addDiscoverSectionForm.valid) {
      this.discovetSectionService.saveDiscoverSectionDetails(this.addDiscoverSectionForm.value).subscribe(
        (resp: any) => {
          if (resp.success) {
            alert(resp.message);

            setTimeout(() => {
              if (confirm("Do you want to add more discover section ?")) {
                this.addDiscoverSectionForm.reset();
                this.reloadComponent();
              } else {
                this.reloadComponent();
                this.backDicoverList();
              }
            }, 500);
          } else {
            setTimeout(() => {
              alert(resp.message);

            }, 1000);
          }
        },
        (error) => {
          setTimeout(() => {
            alert("Error! - Something Went Wrong! Try again.");
          }, 1000);
        }
      );
    } else {
      alert("Please, fill the proper details.");
    }
  }
  backDicoverList() {
    // alert("Tets")
    this.router.navigate(["/home/allvideos"]);
    setTimeout(() => {
    }, 1000);
    this.router.navigate(["/home/discoverysectionlist"]);
    this.close();

  }
  reloadComponent() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/home/discoverysectionlist'])
  }
}




@Component({
  selector: 'edit-discovery',
  templateUrl: 'editdiscovery.html',
  styleUrls: ['./discoverysectionlist.component.scss']
})
export class editDiscovery {

  id;
  editDiscoverSectionForm: FormGroup;
  editdiscovery: any;
  discoverList: any;
  constructor(
    public dialogRef: MatDialogRef<editDiscovery>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private router: Router, private fb: FormBuilder, private discovetSectionService: DiscoversectionService, private route: ActivatedRoute,

  ) {
    this.editdiscovery = data.pageValue

  }


  ngOnInit() {
    this.discovetSectionService.getDiscoverSectionDetails(this.editdiscovery.discoverSectionId).subscribe((data: any) => {
      if (data.success) {
        this.editDiscoverSectionForm.patchValue(data.object)
      }
    })
    this.editDiscoverSectionForm = this.fb.group({
      discoverSectionId: [0],
      sectionName: [null, [Validators.required, disablePrefixSpace]]
    })
    this.editDiscoverSectionForm.setValidators(this.customValidation());
    this.getDataFormBackEnd();
  }



  getDataFormBackEnd() {
    this.discovetSectionService.getDiscoverSectionList().subscribe((data: any) => {
      if (data.success) {
        this.discoverList = data['listObject'];
      }
    })
  }


  sectionName: string;
  customValidation(): ValidatorFn {
    return (formGroup: FormGroup): ValidationErrors => {
      const sectionName = formGroup.controls['sectionName'];
      if (sectionName.value !== '' && sectionName.value !== null) {
        if (sectionName.valid) {
          if (!isNullOrUndefined(this.discoverList)) {
            this.discoverList.forEach((data: any) => {
              if (data.sectionName == sectionName.value) {
                this.sectionName = data.sectionName;
                sectionName.setErrors({ DuplicateUserName: true });
              }
            });
          }
        } else {
          if (this.sectionName == sectionName.value) {
            sectionName.setErrors({ DuplicateUserName: true });
          }
        }
      } else {
        sectionName.setErrors({ NoUserName: true });
      }
      return;
    }
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

  close() {
    this.dialogRef.close();
  }
  updateDiscoverSectionDetails() {
    if (this.editDiscoverSectionForm.valid) {
      // this.appComponent.startSpinner("Updating data..\xa0\xa0Please wait ...");
      this.discovetSectionService.updateDiscoverSectionDetails(this.editDiscoverSectionForm.value).subscribe((resp: any) => {
        if (resp.success) {
          setTimeout(() => {
            alert(resp.message);
            // this.appComponent.stopSpinner();
            this.close();
            this.router.navigate(["/home/discoverysectionlist"]);
          }, 1000);
        } else {
          setTimeout(() => {
            alert(resp.message);
            // this.appComponent.stopSpinner();
          }, 1000);
        }
      }, (error) => {
        setTimeout(() => {
          alert("Error! - Something Went Wrong! Try again.");
          // this.appComponent.stopSpinner();
        }, 1000);
      });
    }

    else {
      alert("Please, fill the proper details.");
    }
  }

  backDicoverList() {
    this.router.navigate(["/home/allvideos"]);
    setTimeout(() => {
    }, 10000);
    // this.router.navigate(["/home/discoverysectionlist"]);
  }
}