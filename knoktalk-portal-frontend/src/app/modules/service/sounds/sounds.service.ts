import { Injectable } from '@angular/core';
import { Api } from 'src/app/api.enum';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SoundsService {

  baseUrl = Api.baseUrl;

  constructor(private httpClient: HttpClient) { }

  // Save Section into DB
  saveSectionDetails(SectionDetails: any) {
    return this.httpClient.post(`${this.baseUrl}/sound/saveSectioDetails`, SectionDetails);
  }

  // Get existing Section Details by id from DB
  getSectionDetails(id: number) {
    return this.httpClient.get(`${this.baseUrl}/sound/getSectionDetails/${id}`)
  }

  // Update existing Section into DB
  updateSectionDetails(SectionDetails: any) {
    return this.httpClient.put(`${this.baseUrl}/sound/updateSectionDetails`, SectionDetails);
  }

  // Delete existing Section from DB
  deleteSectionDetails(sectionId: any) {
    return this.httpClient.put(`${this.baseUrl}/sound/deleteSectionDetails`, null, { params: { "sectionId": sectionId } });
  }

  // Get All existing Section Details from DB
  getSectionList() {
    return this.httpClient.get(`${this.baseUrl}/sound/getAllSectionDetails`)
  }

  // Save Sound into DB
  uploadSound(files:any){
    return this.httpClient.post(`${this.baseUrl}/sound/soundUpload`,files)
  }

  // Get All existing Sound Details from DB
  getSoundList(){
    return this.httpClient.get(`${this.baseUrl}/sound/getAllSoundDetails`)
  }

  // Publish the Sound from Sound Gallery
  publishSound(publishDetails:any, soundId:any){
    return this.httpClient.put(`${this.baseUrl}/sound/publishSound/${soundId}`, publishDetails)
  }

  // Save thumb into Sound Table
  uploadThum(thumFile:any){
    return this.httpClient.put(`${this.baseUrl}/sound/savethum`,thumFile)
  }

  // Delete existing Sound from DB from All Sound Table and Sound Gallery Table
  deleteSoundDetails(soundId:any){
    return this.httpClient.put(`${this.baseUrl}/sound/deleteSound`, null, { params: { "soundId": soundId } });
  }
  
}
