import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Router, NavigationExtras } from '@angular/router';
import { isNullOrUndefined } from 'util';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { disablePrefixSpace } from '../../custom.validation';
import { SoundsService } from 'src/app/modules/service/sounds/sounds.service';
import { DialogData } from 'src/app/modules/login/login/login.component';
declare let $: any;
@Component({
  selector: 'app-sectionlist',
  templateUrl: './sectionlist.component.html',
  styleUrls: ['./sectionlist.component.scss']
})
export class SectionlistComponent implements OnInit {

  sectionList;
  sectionName;

  constructor(public dialog: MatDialog,
    private sectionService: SoundsService,
    private router: Router
  ) { }
  ngOnInit() {

    // Method Calling
    this.getsectionList();
  }

  // This Method is Used to Get the Section List from DB and to Display in Table
  getsectionList() {
    this.sectionService.getSectionList().subscribe((data: any) => {
      if (data.success) {
        this.sectionList = data['listObject'];
        $(document).ready(function () {
          $('#sectionDetails').DataTable();
        });
      } else {
        $(document).ready(function () {
          $('#sectionDetails').DataTable();
        });
      }
    })
  }

  // This Method is Used to Open the Add Section Pop Up Form
  openDialog() {
    const dialogRef = this.dialog.open(AddSection, {
      width: "600px",
      data: { sectionName: this.sectionName },
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.getsectionList();
    });
  }

  // This Method is Used to Open the Edit Section Pop Up Form
  openEditDialog(data) {
    const dialogRef = this.dialog.open(EditSection, {
      width: "600px",
      data: { pageValue: data }
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.getsectionList();
    });
  }

  // This Method is Used to reload the Section List
  reloadSectionList() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/home/soundshome/sections'])
  }

  // This Method is Used to Delete the Existing Section From the Table
  routeToDeleteSection(sectionDetails: any) {
    if (confirm("Are You Sure to Delete this Section ?")) {
      this.sectionService.deleteSectionDetails(sectionDetails.sectionId).subscribe((resp: any) => {
        if (resp.success) {
          alert(resp.message)
          this.reloadSectionList()
        }
      });
    }
  }

}
/**
 * 
 *  Add Section Component ( It's Act Like a Component.ts)
 * 
 */
@Component({
  selector: 'add-section',
  templateUrl: 'addsection.html',
  styleUrls: ['./sectionlist.component.scss']
})
export class AddSection {

  addSection: FormGroup;
  sectionList;

  constructor(
    public dialogRef: MatDialogRef<AddSection>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private router: Router,
    private fb: FormBuilder,
    private sectionService: SoundsService
  ) { }

  ngOnInit() {
    this.addSection = this.fb.group({
      sectionName: [null, [Validators.required, disablePrefixSpace]]
    })
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  // This Method is Used to Close the Add Section Pop Up Form
  close() {
    this.dialogRef.close(SectionlistComponent);

  }

  // This Methhod is Used to Save the Section Details into DB
  saveSectionDetails() {
    if (this.addSection.valid) {

      this.sectionService.saveSectionDetails(this.addSection.value).subscribe(
        (resp: any) => {
          if (resp.success) {
            alert(resp.message);
            setTimeout(() => {
              if (confirm("Do you want to add more section ?")) {
                this.addSection.reset();
              } else {
                this.reloadSectionList();
                this.close();
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
      alert("Please, Fill the Proper Details.");
    }
  }

  // This Method is Used to reload the Section List
  reloadSectionList() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/home/soundshome/sections'])
  }

}

/**
 * 
 *  Edit Section Component ( It's Act Like a Component.ts)
 * 
 */
@Component({
  selector: 'edit-section',
  templateUrl: 'editsection.html',
  styleUrls: ['./sectionlist.component.scss']
})
export class EditSection {

  editSection: any;
  addSection: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<EditSection>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private router: Router,
    private sectionService: SoundsService,
    private fb: FormBuilder,
  ) {
    this.editSection = data.pageValue;
  }
  ngOnInit() {
    this.sectionService.getSectionDetails(this.editSection.sectionId).subscribe((data: any) => {
      if (data.success) {
        this.addSection.patchValue(data.object)
      }
    })
    this.addSection = this.fb.group({
      sectionId: [0],
      sectionName: [null, [Validators.required, disablePrefixSpace]]
    })
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

  // This Method is Used to Close the Edit Section Pop Up Form
  close() {
    this.dialogRef.close();
  }

  // This Method is Used to reload the Section List
  reloadSectionList() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/home/soundshome/sections'])
  }

  // This Methhod is Used to Update the Existing Section Details 
  updateSectionDetails() {
    if (this.addSection.valid) {
      this.sectionService.updateSectionDetails(this.addSection.value).subscribe((resp: any) => {
        if (resp.success) {
          setTimeout(() => {
            alert(resp.message)
            this.reloadSectionList();
            this.close();
          }, 1000);
        }
        else {
          setTimeout(() => {
            alert(resp.message)
          }, 1000);
        }
      }, (error) => {
        setTimeout(() => {
          alert("Error ! - Something went wrong ! try again")
        }, 1000);
      })
    } else {
      alert("Please , fill the proper details")
    }
  }
}
