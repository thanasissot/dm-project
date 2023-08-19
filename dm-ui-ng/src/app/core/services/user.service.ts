import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {catchError, Observable, of} from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private user_api_url = 'http://localhost:8081/api';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(
    private http: HttpClient
  ) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.user_api_url)
      .pipe(
        catchError(this.handleError<User[]>('getUsers', []))
      )
  }

  createUser(newUser: any): Observable<any> {
    return this.http.post(this.user_api_url, newUser, this.httpOptions)
      .pipe(
        catchError(this.handleError<User>('createUser', null as any))
      )
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      return of(result as T);
    };
  }
}
