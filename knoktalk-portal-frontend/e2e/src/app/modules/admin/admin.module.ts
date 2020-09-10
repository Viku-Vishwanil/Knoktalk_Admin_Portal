import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDividerModule, MatToolbarModule, MatIconModule, MatButtonModule, MatMenuModule, MatListModule, MatSlideToggleModule } from '@angular/material';
import {MatFormFieldModule, MatLabel} from '@angular/material/form-field';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatDividerModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatMenuModule,
    MatListModule,
    FlexLayoutModule,
    MatSlideToggleModule,
    MatDividerModule,
    MatFormFieldModule,
  ]
})
export class AdminModule { }
