import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { DefaultComponent } from "./default.component";
import {
  MatSidenavModule,
  MatDividerModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatGridListModule,
  MatListModule,
  MatTableModule,
  MatInputModule,
  MatFormFieldModule,
  MatPaginatorModule,
  MatSort,
  MatIconModule,
  MatCardModule,
  MatProgressBarModule,
} from "@angular/material";
import { SharedModule } from "src/app/shared/shared.module";
import { RouterModule } from "@angular/router";
import { FlexLayoutModule } from "@angular/flex-layout";
import { MatSlideToggleModule } from "@angular/material/slide-toggle";
import { DashboardComponent } from "src/app/modules/dashboard/dashboard.component";
import { AdminModule } from "src/app/modules/admin/admin.module";
import { UserModule } from 'src/app/modules/admin/user/user.module';
import { SoundModule } from 'src/app/modules/admin/sound/sound.module';
import { DiscoverysectionModule } from 'src/app/modules/admin/discoverysection/discoverysection.module';
import { ReportsModule } from 'src/app/modules/admin/reports/reports.module';
import { AllvideosModule } from 'src/app/modules/admin/allvideos/allvideos.module';
import { Ng2OdometerModule } from "ng2-odometer";
@NgModule({
  declarations: [DefaultComponent, DashboardComponent],
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
    AdminModule,
    MatSidenavModule,
    MatDividerModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatGridListModule,
    MatListModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    MatPaginatorModule,
    MatIconModule,
    FlexLayoutModule,
    MatCardModule,
    MatProgressBarModule,
    MatSlideToggleModule,
    MatDividerModule,
    UserModule ,
    SoundModule,
    AllvideosModule,
    DiscoverysectionModule,
    ReportsModule,
    Ng2OdometerModule
  ],
})
export class DefaultModule {}
