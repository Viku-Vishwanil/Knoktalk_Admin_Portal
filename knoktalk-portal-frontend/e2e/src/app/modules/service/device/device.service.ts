import { Injectable } from '@angular/core';
import { Api } from 'src/app/api.enum';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  private baseUrl = Api.baseUrl;
  constructor(private http: HttpClient) { }

  // get Device by userid
  getDeviceDetailsByUserId(userId: number) {
    return this.http.get(`${this.baseUrl}/admin/device/getDeviceDetailsByUserId/${userId}`)
  }
}


// File Name :-device.service.ts	   	Created Date :-09-09-2020     Created By :- Faiser I (Emp. Id : VTPL/BLR/006)

// Description :- this is the service file to communicate wit api's 					

// ============================================================================================================================
//   Sl.No			    Description			                                Modified By		              	      Modified Date	
// ============================================================================================================================



