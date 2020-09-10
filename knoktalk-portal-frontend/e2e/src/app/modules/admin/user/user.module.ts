import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListusersComponent, GiftAndCoin, Device } from './listusers/listusers.component';
import { ProfileverificationComponent } from './profileverification/profileverification.component';
import { MatIconModule, MatDividerModule, MatFormFieldModule, MatInputModule, MatDatepickerModule, MatButtonModule, MatListModule, MatTableModule, MatPaginatorModule, MatSnackBarModule, MatCheckboxModule, MatAutocompleteModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';


@NgModule({
  declarations: [ListusersComponent, ProfileverificationComponent, GiftAndCoin, Device],
  imports: [
    CommonModule,
    MatIconModule,
    MatRadioModule,
    MatDividerModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatIconModule,
    MatRadioModule,
    MatButtonModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    FormsModule,
    MatSelectModule,
    MatCheckboxModule,
    MatAutocompleteModule,
    MatToolbarModule,
    MatTooltipModule
  ],
  entryComponents: [GiftAndCoin, Device]

})
export class UserModule { }
