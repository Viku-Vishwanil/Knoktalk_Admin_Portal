import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { isNullOrUndefined } from 'util';
import { UserserviceService } from '../service/users/userservice.service';
import { VerificationrequestService } from '../service/users/verificationrequest.service';
import { SoundsService } from '../service/sounds/sounds.service';
import { VideosService } from '../service/videos/videos.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  usersCount: number;
  soundsCount: number;
  verificationCount: number;
  videosCount: number;
  userId: any;

  constructor(private route: Router,
    private userService: UserserviceService,
    private soundService: SoundsService,
    private videosService: VideosService,
    private verificationRequestService: VerificationrequestService,
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit() {

    this.userId = sessionStorage.getItem(this.authenticationService.SESSION_USER_ID_KEY)

    // to get the count of users
    this.userService.getAllUsersDetails().subscribe((data: any) => {
      if (data.success) {
        if (!isNullOrUndefined(data)) {
          this.usersCount = data['listObject'].length;
        }
      }
    });
    // to get count of sounds
    this.soundService.getSoundList().subscribe((data: any) => {
      if (data.success) {
        if (!isNullOrUndefined(data)) {
          this.soundsCount = data['listObject'].length;
        }
      }
    });

    // to get count of Videos
    this.videosService.getVideoList().subscribe((data: any) => {
      if (data.success) {
        if (!isNullOrUndefined(data)) {
          this.videosCount = data['listObject'].length;
        }
      }
    })

    // to get the count of verification Requests
    this.verificationRequestService.getAllVerificationDetails().subscribe((data: any) => {
      if (data.success) {
        if (!isNullOrUndefined(data)) {
          this.verificationCount = data['listObject'].length;
        }
      }
    })
  }

  // method to open listusers component
  routeToUsers() {
    this.route.navigate(['home/listusers'])
  }

  // method to open sounds component
  routeToSounds() {
    this.route.navigate(['home/soundshome/sounds'])
  }

  // method to open profileverification component
  routeToVerification() {
    this.route.navigate(['home/profileverification'])
  }

  // method to open allvideos component
  routeToVideos() {
    this.route.navigate(['home/allvideos'])
  }

  // method to open reports component
  routeToReports() {
    this.route.navigate(['home/reports'])
  }

  // method to open discoverysectionlist component
  routeToDiscoverySection() {
    this.route.navigate(['home/discoverysectionlist'])
  }
}
