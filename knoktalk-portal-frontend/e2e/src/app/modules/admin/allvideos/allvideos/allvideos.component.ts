import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { VideosService } from 'src/app/modules/service/videos/videos.service';
import { DiscoversectionService } from 'src/app/modules/service/discoversection/discoversection.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AddDiscovery } from '../../discoverysection/discoverysectionlist/discoverysectionlist.component';
declare var $: any;

@Component({
  selector: 'app-allvideos',
  templateUrl: './allvideos.component.html',
  styleUrls: ['./allvideos.component.scss']
})
export class AllvideosComponent implements OnInit {

  videoList: any;
  constructor(public dialog: MatDialog, private videoService: VideosService, private router: Router) { }

  ngOnInit() {
    $(document).ready(function () {
      //Pagination numbers
      $('#paginationSimpleNumbers').DataTable({
        "pagingType": "simple_numbers"
      });
    });
    this.getDataFormBackEnd();
  }
  openDialog(data) {
    // this.dialog.open(AddToDiscovery, {
    //   width: "600px",
    //   data: {
    //     animal: 'panda'
    //   }
    // });

    const dialogRef = this.dialog.open(AddToDiscovery, {
      width: "600px",
      data: { pageValue: data }
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.getDataFormBackEnd();
    });
  }




  getDataFormBackEnd() {
    this.videoService.getVideoList().subscribe((data: any) => {
      if (data.success) {
        this.videoList = data['listObject'];
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
  //This method is used to get the data from back end and to display in UI
  // getDataFormBackEnd() {
  //   this.videoService.getVideoList().subscribe((data: any) => {
  //     if (data.success) {
  //       this.videoList = data['listObject'];
  //       $(document).ready(function () {
  //         $('#paginationSimpleNumbers').DataTable();
  //       });
  //     } else {
  //       $(document).ready(function () {
  //         $('#paginationSimpleNumbers').DataTable();
  //       });
  //     }
  //   })
  // }
  reloadComponent() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/home/allvideos'])
  }

  //This method delete dicovery sectioon
  routeToDeleteVideo(videoDetails: any) {

    if (confirm(`Are you sure to delete this video ?`)) {
      this.videoService.deleteVideoDetails(videoDetails.videoId).subscribe((resp: any) => {
        if (resp.success) {
          alert(resp.message);
          this.reloadComponent();
          // this.router.navigate(["/home/discoverysectionlist"]);
          // this.getDataFormBackEnd();
        }
      });

    }
  }

}
@Component({
  selector: 'add-todiscovery',
  templateUrl: 'addtodiscovery.html',
  styleUrls: ['./allvideos.component.scss']
})
export class AddToDiscovery {
  discoverList: any;
  addDiscoverSectionForm: FormGroup;
  addDiscovery
  constructor(
    public dialogRef: MatDialogRef<AddToDiscovery>,
    // @Inject(MAT_DIALOG_DATA) public data: DialogData,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private router: Router,
    private discovetSectionService: DiscoversectionService,
    private videoService: VideosService,
    private fb: FormBuilder,


  ) {

    this.addDiscovery = data.pageValue


  }

  ngOnInit() {

    this.getDataFormBackEnd();
    this.addDiscoverSectionForm = this.fb.group({
      discoverSectionBean: [null, [Validators.required]]
    })

  }


  getDataFormBackEnd() {
    this.discovetSectionService.getDiscoverSectionList().subscribe((data: any) => {
      if (data.success) {
        this.discoverList = data['listObject'];
      }
    })
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  addDiscoverSectionToVideoDetails() {
    if (this.addDiscoverSectionForm.valid) {


      // this.appComponent.startSpinner("Updating data..\xa0\xa0Please wait ...");
      this.videoService.updateVideoWithDiscoverSectionDetails(this.addDiscovery.videoId, this.addDiscoverSectionForm.value).subscribe((resp: any) => {
        if (resp.success) {
          setTimeout(() => {
            alert(resp.message);
            // this.appComponent.stopSpinner();
            this.close();
            // this.router.navigate(["/home/discoverysectionlist"]);
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


  //email validation for forgot password
  // emailError: string;
  // emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z]+.[a-zA-Z]{2,4}$/;


  close() {
    this.dialogRef.close();
  }

}



