import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserInfo } from '../models/userInfo.model';
import { RegisterRequest } from '../models/registerRequest.model';

export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  success: boolean;
  token: string;
  failureReason?: string;
}


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly baseUrl = 'http://localhost:8080/auth';
  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());
  isLoggedIn$ = this.loggedIn.asObservable();
  
  constructor(private http: HttpClient) {}

    register(request: RegisterRequest): Observable<any> {
      return this.http.post(`${this.baseUrl}/register`, request);
    }
  login(request: LoginRequest): Observable<LoginResponse> {
       this.loggedIn.next(true);
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, request);

  }

getCurrentUser(): Observable<UserInfo> {
  const token = localStorage.getItem('token');
  if (!token) {
    throw new Error('No token found');
  }

  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });

  return this.http.get<UserInfo>(`${this.baseUrl}/me`, { headers });
}

  logout(): void {
    localStorage.removeItem('token');
      this.loggedIn.next(false);  

  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }
    private hasToken(): boolean {
    return !!localStorage.getItem('token');
  }
  
  saveToken(token: string) {
    localStorage.setItem('token', token);
    this.loggedIn.next(true);
  }
}
