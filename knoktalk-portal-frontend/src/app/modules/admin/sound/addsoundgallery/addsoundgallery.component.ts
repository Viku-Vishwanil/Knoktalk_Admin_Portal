import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { SoundsService } from 'src/app/modules/service/sounds/sounds.service';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { isNullOrUndefined } from 'util';
import { Location } from '@angular/common';
import { DialogData } from 'src/app/modules/login/login/login.component';

@Component({
  selector: 'app-addsoundgallery',
  templateUrl: './addsoundgallery.component.html',
  styleUrls: ['./addsoundgallery.component.scss']
})
export class AddsoundgalleryComponent implements OnInit {

  soundList;

  constructor(public dialog: MatDialog, public soundService: SoundsService) { }

  ngOnInit() {
    // Method Calling
    this.getsoundList();
  }

  // This Method is Used to Get the Section List from DB and to Display in Sound Gallery Table
  getsoundList() {
    this.soundService.getSoundList().subscribe((data: any) => {
      if (data.success) {
        this.soundList = data['listObject'];
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

  // This Method is Used to Open the Publish Sound Pop Up Form
  openDialog(data) {
    this.dialog.open(PublishSound, {
      width: "600px",
      data: {
        pageValue: data
      }
    });
  }

  // This Method is Used to Delete the Existing Sound From the Table
  routeToDeleteSound(soundDetails: any) {
    if (confirm("Are You Sure to Delete this Sound ?")) {
      this.soundService.deleteSoundDetails(soundDetails.soundId).subscribe((resp: any) => {
        if (resp.success) {
          alert(resp.message)
          this.soundService.getSoundList().subscribe((data: any) => {
            if (data.success) {
              this.soundList = data['listObject'];
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
      });
    }
  }

}

/**
 * 
 *  Publish Section Component ( It's Act Like a Component.ts)
 * 
 */
@Component({
  selector: 'publish-sound',
  templateUrl: 'publish.html',
  styleUrls: ['./addsoundgallery.component.scss']
})
export class PublishSound {

  publishSound: FormGroup;
  sectionList;
  publish;

  constructor(
    public dialogRef: MatDialogRef<PublishSound>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private router: Router,
    public sectionService: SoundsService,
    private fb: FormBuilder,
    private location: Location,
    private soundGalleryService: SoundsService
  ) {
    this.publish = data.pageValue
  }

  ngOnInit() {
    this.publishSound = this.fb.group({
      soundId: [0],
      sectionBean: [null, [Validators.required]],
      soundName: [null, [Validators.required]],
      description: [null, [Validators.required]],
      thumbline: [null, [Validators.required]]
    })
    this.getsectionList();
  }

  // This Method is Used to Get the File From System
  base64File: string = null;
  filename: string = null;
  file: any;
  onFileSelect(e: any): void {
    this.file = null;
    try {
      this.file = e.target.files[0];
      const fReader = new FileReader()
      fReader.readAsDataURL(this.file)
      fReader.onloadend = (_event: any) => {
        this.filename = this.file.name;
        this.base64File = _event.target.result;
      }
    } catch (error) {
      this.filename = null;
      this.base64File = null;
    }
  }

  // This Method is Used to Get the Section List from DB and to Display in Option 
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
  onNoClick(): void {
    this.dialogRef.close();
  }

  // This Method is Used to Close the Publish Sound Pop Up Form
  close() {
    this.dialogRef.close();
  }

  // This Methhod is Used to Save the Publish Sound Details into DB
  savePublishSound() {
    this.soundGalleryService.publishSound(this.publishSound.value, this.publish.soundId).subscribe((data: any) => {
      if (data.success) {
        if (!isNullOrUndefined(this.filename)) {
          const profileFormData = new FormData();
          profileFormData.append('thumFile', this.file);
          profileFormData.append('soundId', this.publish.soundId);
          this.soundGalleryService.uploadThum(profileFormData).subscribe((resp: any) => {
            if (resp.success) {
              alert("Data Saved Successfully and Files Uploaded Successfully");
              this.publishSound.reset();
            } else {
              alert("Data Saved Successfully. But, failure to upload Profile Photo");
            }

          });
        } else {
          alert("Data Saved Successfully and File Uploaded Successfully");
          this.publishSound.reset();

        }

      } else {
        setTimeout(() => {
          alert(data.message);
        }, 1000);
      }
    });

  }

  // This Method is Used to Back the Previous Location
  gotoBack = () => {
    this.location.back();
  };

}