import { Injectable } from '@angular/core';
import { Api } from 'src/app/api.enum';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DiscoversectionService {

  baseUrl = Api.baseUrl;

  constructor(private httpClient: HttpClient) { }


  saveDiscoverSectionDetails(discoverDetails: any) {
    return this.httpClient.post(`${this.baseUrl}/discover/saveDiscoverSectioDetails`, discoverDetails);
  }

  // get CompanyDetails by id
  getDiscoverSectionDetails(id: number) {
    return this.httpClient.get(`${this.baseUrl}/discover/getDiscoverSectionDetails/${id}`)
  }

  // update Company Details
  updateDiscoverSectionDetails(discoverDetails: any) {
    return this.httpClient.put(`${this.baseUrl}/discover/updateDiscoverSectionDetails`, discoverDetails);
  }

  // delete Company
  deleteDiscoverSectiojDetails(id: any) {
    return this.httpClient.put(`${this.baseUrl}/discover/deleteDiscoverSectionDetails`, null, { params: { "id": id } });
  }
  getDiscoverSectionList() {
    return this.httpClient.get(this.baseUrl + '/discover/getAllDiscoverSectionDetails')
  }
}
