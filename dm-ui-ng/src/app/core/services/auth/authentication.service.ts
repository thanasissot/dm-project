import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {User} from "../../model/user.model";
import * as moment from 'moment';
import { pluck, share, shareReplay, tap } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private user_api_url = 'http://localhost:8081/api';
  loggedInUser: any;
  constructor(private http: HttpClient) {

  }

  login(email:string, password:string ) {
    return this.http.post<User>('http://localhost:8081/api/user/authenticate', {email, password})
      .subscribe({
        next: res => {
          this.setSession(res)
          console.log('user logged in');
        },
      })
  }

  private setSession(authResult: any) {
    const expiresAt = moment().add(authResult.expiresIn,'second');

    localStorage.setItem('id_token', authResult.idToken);
    localStorage.setItem("expires_at", JSON.stringify(expiresAt.valueOf()) );
  }

  logout() {
    localStorage.removeItem("id_token");
    localStorage.removeItem("expires_at");
  }

  public isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

  getExpiration() {
    const expiration = localStorage.getItem("expires_at");
    if (expiration) {
      const expiresAt = JSON.parse(expiration);
      return moment(expiresAt);
    }

    return undefined;
  }
}
