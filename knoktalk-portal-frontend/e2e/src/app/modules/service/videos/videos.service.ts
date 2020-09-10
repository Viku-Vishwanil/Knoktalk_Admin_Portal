import { Injectable } from '@angular/core';
import { Api } from 'src/app/api.enum';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class VideosService {

  baseUrl = Api.baseUrl;

  constructor(private httpClient: HttpClient) { }


  // saveVideoWithSectionDetails(discoverDetails: any) {
  //   return this.httpClient.post(`${this.baseUrl}/videos/saveDiscoverSectioDetails`, discoverDetails);
  // }

  // get CompanyDetails by id
  getDiscoverSectionDetails(id: number) {
    return this.httpClient.get(`${this.baseUrl}/videos/getVideoDetails/${id}` )
  }

  // update Company Details
  updateVideoWithDiscoverSectionDetails(videoId:number,videoDetils: any) {
    return this.httpClient.put(`${this.baseUrl}/videos/updateVideoDetails/${videoId}`,videoDetils.discoverSectionBean);
  }

  // delete Company
  deleteVideoDetails(videoId: any):any{
    return this.httpClient.put(`${this.baseUrl}/videos/deleteVideoDetails`, null, { params: { "videoId": videoId } });
  }
  getVideoList() {
    return this.httpClient.get(this.baseUrl + '/videos/getAllVideosDetails')
  }
}
