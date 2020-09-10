import { Injectable } from '@angular/core';
import { Api } from 'src/app/api.enum';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VerificationrequestService {
  private baseUrl = Api.baseUrl;
  constructor(private http: HttpClient) { }

  // get All Verification details
  getAllVerificationDetails() {
    return this.http.get(this.baseUrl + '/admin/verificationrequest/getAllVerificationDetails')
  }

  // approveUser 
  approveUser(userId: any) {
    return this.http.put(`${this.baseUrl}/admin/users/approveUser`, null, { params: { "userId": userId } });
  }

  // decline User
  declineUser(userId: any) {
    return this.http.put(`${this.baseUrl}/admin/users/declineUser`, null, { params: { "userId": userId } });
  }
}

// File Name :-verificationrequest.service.ts	   	Created Date :-04-09-2020     Created By :- Faiser I (Emp. Id : VTPL/BLR/006)
	
// Description :- this is the service file to communicate wit api's 					
	
// ============================================================================================================================
//   Sl.No			    Description			                                Modified By		              	      Modified Date	
// ============================================================================================================================
