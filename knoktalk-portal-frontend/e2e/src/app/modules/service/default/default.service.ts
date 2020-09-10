import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Api } from "src/app/api.enum";

@Injectable({
  providedIn: "root",
})

export class DefaultService {

  baseUrl = Api.baseUrl;

  constructor(private httpClient: HttpClient) {

  }

  home() {
    return this.httpClient.get(`${this.baseUrl}/home`);
  }

}

// File Name :-default.service.ts	   	Created Date :-01-09-2020     Created By :- Faiser I (Emp. Id : VTPL/BLR/006)
	
// Description :- this is the service file to communicate wit api's 					
	
// ============================================================================================================================
//   Sl.No			    Description			                                Modified By		              	      Modified Date	
// ============================================================================================================================