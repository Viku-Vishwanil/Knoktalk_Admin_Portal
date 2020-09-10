import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportsComponent } from './reports/reports.component';
import { MatIconModule, MatListModule, MatTableModule, MatInputModule, MatFormFieldModule, MatPaginatorModule, MatButtonModule, MatSnackBarModule } from '@angular/material';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserreportComponent } from './userreport/userreport.component';
import { RewardsreportComponent } from './rewardsreport/rewardsreport.component';



@NgModule({
  declarations: [ReportsComponent, UserreportComponent, RewardsreportComponent],
  imports: [
    CommonModule,
    MatIconModule,
    MatListModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    MatPaginatorModule,
    MatButtonModule,
    MatSnackBarModule,
    RouterModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
  ]
})
export class ReportsModule { }
