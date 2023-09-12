import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { user } from '../model/user';
import { Router } from '@angular/router';

import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  private url = 'http://localhost:8088/api/v1/Auth';



  httpOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Bearer' + localStorage.getItem('token')
    })
  };


  constructor(private http: HttpClient, private router: Router) { }

  getAll(): Observable<user[]> {
    return this.http.get<user[]>("http://localhost:8088/api/v1/Auth/AllUsers");
  }
  public AddUser(user: user){
    return this.http.post("http://localhost:8088/api/v1/Auth/register",user,{responseType:'text' as 'json'});

  }
  

  SignUp(data: any): Observable<any> {
    return this.http.post(this.url + "/register", data)
  }
  login(data: any): Observable<any> {
    return this.http.post<{ token: string }>(this.url + "/Authen", data).pipe(
      map(result => {
        if (result && result.token) {
          localStorage.setItem('token', result.token);
          return true;
        }
        return false;
      })
    );

  }

  delete(idProjet: any): Observable<Object> {
    return this.http.delete(`http://localhost:8088/api/v1/Auth/DeleteUser/?id=${idProjet}`, this.httpOptions);
  }
  enable(idProjet: any): Observable<Object> {
    return this.http.put(`http://localhost:808/api/v1/Auth/Enable/${idProjet}`, this.httpOptions);
  }
  disable(idProjet: any): Observable<Object> {
    return this.http.put(`http://localhost:8088/api/v1/Auth/Disable/${idProjet}`, this.httpOptions);
  }
  confirm(token: any): Observable<Object> {
    return this.http.put(`http://localhost:808/api/v1/Auth/confirm?token=${token}`, this.httpOptions);
  }

  SendReset(email: any): Observable<Object> {
    return this.http.put(`http://localhost:8088/api/v1/Auth/ResetPass/InsetYourEmailHere/?email=${email}`, this.httpOptions);
  }
  ResetPass(idUser: any, token: any, password: any): Observable<Object> {
    return this.http.put(`http://localhost:8088/api/v1/Auth/ResetPass?idUser=${idUser}&token=${token}&neoPass=${password}`, this.httpOptions);
  }
  public getToken() {
    return localStorage.getItem('jwtToken');
  }
  public setToken(token: string) {

    localStorage.setItem('token', token);
    console.log(localStorage.getItem('jwtToken'));

  }










  // logout(): void {
  //   localStorage.removeItem('token');
  // }

  // isLoggedIn(): boolean {
  //   const token = localStorage.getItem('token');
  //   if (token) {
  //     const decodedToken = jwt_decode(token);
  //     const expirationDate = new Date(decodedToken.exp * 1000);
  //     return expirationDate > new Date();
  //   }
  //   return false;
  // }


  // getRole(): string {
  //   // Get the JWT token from local storage
  //   const token = localStorage.getItem('token');

  //   // Extract the payload from the token (i.e. everything between the first and second dots)
  //   const payload = token.split('.')[1];

  //   // Decode the payload from base64 encoding
  //   const decodedPayload = JSON.parse(atob(payload));

  //   // Get the user's role
  //   const userRole = decodedPayload.role;
  //   return userRole;
  // }
}
