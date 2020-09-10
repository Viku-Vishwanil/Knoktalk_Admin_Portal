import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AllvideosComponent, AddToDiscovery } from './allvideos/allvideos.component';
import { MatIconModule, MatListModule, MatTableModule, MatInputModule, MatFormFieldModule, MatPaginatorModule, MatButtonModule, MatSnackBarModule } from '@angular/material';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';



@NgModule({
  declarations: [AllvideosComponent, AddToDiscovery],
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
  ],
  entryComponents:[AddToDiscovery]
})
export class AllvideosModule { }
