import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { SoundsService } from 'src/app/modules/service/sounds/sounds.service';

@Component({
  selector: 'app-soundlist',
  templateUrl: './soundlist.component.html',
  styleUrls: ['./soundlist.component.scss']
})
export class SoundlistComponent implements OnInit {

  soundList;
  constructor(private router: Router,
    public soundService: SoundsService) { }

  ngOnInit() {
    $(document).ready(function () {
      //Pagination numbers
      $('#sectionDetails').DataTable({
        "pagingType": "simple_numbers"
      });
    });
    // Method Calling
    this.getsoundList()
  }

  // This Method is Used to Get the Sound List from DB and to Display in Table
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

  // This Method is Used To Redirect to Upload Sound Page
  routeToUploadSound() {
    this.router.navigate(['/home/uploadSound'])
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
