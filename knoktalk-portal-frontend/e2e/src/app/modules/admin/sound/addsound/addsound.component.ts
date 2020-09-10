import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SoundsService } from 'src/app/modules/service/sounds/sounds.service';

@Component({
  selector: 'app-addsound',
  templateUrl: './addsound.component.html',
  styleUrls: ['./addsound.component.scss']
})
export class AddsoundComponent implements OnInit {

  addSound: FormGroup;
  files: any[] = [];
  constructor(private fb: FormBuilder,
    private soundService: SoundsService) { }

  ngOnInit() {
    this.addSound = this.fb.group({
      fileUpload: [null, [Validators.required]]
    })
  }
  @ViewChild("fileDropRef", { static: false }) fileDropEl: ElementRef;


  /**
   * on file drop handler 
   */
  onFileDropped($event) {
    this.prepareFilesList($event);
  }

  /**
   * handle file from browsing
   */
  fileBrowseHandler(files) {
    for (let i = 0; i < files.length; i++) {
      this.uploadSound(files[i]);
    }
    this.prepareFilesList(files);

  }

  /**
   * Delete file from files list
   * @param index (File index)
   */
  deleteFile(index: number) {
    if (this.files[index].progress < 100) {
      return;
    }
    this.files.splice(index, 1);
  }

  /**
   * Convert Files list to normal array list
   * @param files (Files List)
   */
  prepareFilesList(files: Array<any>) {
    for (const item of files) {
      item.progress = 0;
      this.files.push(item);
    }
    this.fileDropEl.nativeElement.value = "";
    this.uploadFilesSimulator(0);
  }


  /**
   * Simulate the upload process
   */
  uploadFilesSimulator(index: number) {
    setTimeout(() => {
      if (index === this.files.length) {
        return;
      } else {
        const progressInterval = setInterval(() => {
          if (this.files[index].progress === 100) {
            clearInterval(progressInterval);
            this.uploadFilesSimulator(index + 1);
            alert(this.files[index].name + " " + "Uploaded Successfully.")
          } else {
            this.files[index].progress += 5;
          }
        }, 200);
      }
    }, 1000);
  }

  /**
   * format bytes
   * @param bytes (File size in bytes)
   * @param decimals (Decimals point)
   */
  formatBytes(bytes, decimals = 2) {
    if (bytes === 0) {
      return "0 Bytes";
    }
    const k = 1024;
    const dm = decimals <= 0 ? 0 : decimals;
    const sizes = ["Bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + " " + sizes[i];
  }

  // This Methhod is Used to Upload the Sound File into DB
  uploadSound(files: any) {
    const profileFormData = new FormData();
    profileFormData.append('soundFile', files);
    this.soundService.uploadSound(profileFormData).subscribe((resp: any) => {
      if (resp.success) {
      }
    });
  }

}
