import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { DefaultComponent } from "./layouts/default/default.component";
import { DashboardComponent } from "./modules/dashboard/dashboard.component";
import { ListusersComponent } from './modules/admin/user/listusers/listusers.component';
import { SoundhomeComponent } from './modules/admin/sound/soundhome/soundhome.component';
import { SoundlistComponent } from './modules/admin/sound/soundlist/soundlist.component';
import { SectionlistComponent } from './modules/admin/sound/sectionlist/sectionlist.component';
import { AddsoundComponent } from './modules/admin/sound/addsound/addsound.component';
import { AddsoundgalleryComponent } from './modules/admin/sound/addsoundgallery/addsoundgallery.component';
import { ProfileverificationComponent } from './modules/admin/user/profileverification/profileverification.component';
import { DiscoverysectionlistComponent } from './modules/admin/discoverysection/discoverysectionlist/discoverysectionlist.component';
import { ReportsComponent } from './modules/admin/reports/reports/reports.component';
import { UserreportComponent } from './modules/admin/reports/userreport/userreport.component';
import { RewardsreportComponent } from './modules/admin/reports/rewardsreport/rewardsreport.component';
import { AllvideosComponent } from './modules/admin/allvideos/allvideos/allvideos.component';
import { LoginComponent } from './modules/login/login/login.component';
import { ChangepasswordComponent } from './modules/login/changepassword/changepassword.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: "login", component: LoginComponent },
  {
    path: "home",
    // path: "",
    component: DefaultComponent,
    children: [
      { path: "", component: DashboardComponent },
      { path: "dashboard", component: DashboardComponent },
      { path: "changepassword", component: ChangepasswordComponent },

      // Faiser routing starts here

      { path: 'listusers', component: ListusersComponent },

      { path: 'profileverification', component: ProfileverificationComponent },

      // Faiser routing ends here

      // Vikash routing starts here 

      {
        path: 'soundshome', component: SoundhomeComponent,
        children: [
          { path: 'sounds', component: SoundlistComponent },
          { path: 'sections', component: SectionlistComponent },
          { path: 'soundGallery', component: AddsoundgalleryComponent }
        ]
      },
      { path: 'uploadSound', component: AddsoundComponent },
      // Vikash routing ends here

      // Rambabu routing starts here
      { path: 'allvideos', component: AllvideosComponent },
      { path: 'discoverysectionlist', component: DiscoverysectionlistComponent },
      { path: 'reports', component: ReportsComponent },
      { path: 'userreport', component: UserreportComponent },
      { path: 'rewardsreport', component: RewardsreportComponent }

      // Rambabu routing ends here

    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
