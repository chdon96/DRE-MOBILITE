import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Candidature } from './core/model/Candidature';

@Injectable({
  providedIn: 'root'
})
export class CandidatureService {
  private baseUrl = `http://localhost:8088/Candidature`;
  private apiUrl = 'http://localhost:8088/Candidature/AddCandidature/'
  constructor(private http: HttpClient) { }
  SubmitApplication(idOffre: number, candidature: Candidature): Observable<Candidature> {
    const url = `${this.baseUrl}/AddCandidature/${idOffre}`;
    return this.http.post<Candidature>(url, candidature);
    
  }
  public addCandidature(candidature: Candidature){
    return this.http.post("http://localhost:8088/Candidature/AddCandidature/",candidature,{responseType:'text' as 'json'});

  } 
  GetTopCandidature(idOffre: number) {
    return this.http.get<Candidature>(`http://localhost:8088/Candidature/Show-Top/${idOffre}`);      
  }
  public ShowAllCandidature() {
    return this.http.get("http://localhost:8088/Candidature/Show-All");
  }
  
}
