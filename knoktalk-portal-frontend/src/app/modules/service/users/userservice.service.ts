import { Injectable } from '@angular/core';
import { Api } from 'src/app/api.enum';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class UserserviceService {
  private baseUrl = Api.baseUrl;
  constructor(private http: HttpClient) { }

  // get All User details
  getAllUsersDetails() {
    return this.http.get(this.baseUrl + '/admin/users/getAllUsersDetails')
  }

  // blockUser 
  blockUser(userId: any) {
    return this.http.put(`${this.baseUrl}/admin/users/blockUser`, null, { params: { "userId": userId } });
  }

  // blockUser 
  unBlockUser(userId: any) {
    return this.http.put(`${this.baseUrl}/admin/users/unBlockUser`, null, { params: { "userId": userId } });
  }

  // blockUser 
  deleteUser(userId: any) {
    return this.http.put(`${this.baseUrl}/admin/users/deleteUser`, null, { params: { "userId": userId } });
  }
}


// File Name :-userservice.service.ts	   	Created Date :-03-09-2020     Created By :- Faiser I (Emp. Id : VTPL/BLR/006)
	
// Description :- this is the service file to communicate wit api's 					
	
// ============================================================================================================================
//   Sl.No			    Description			                                Modified By		              	      Modified Date	
// ============================================================================================================================