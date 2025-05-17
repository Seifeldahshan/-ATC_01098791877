// src/app/services/admin.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EventModel } from '../models/Event.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrl = 'http://localhost:8080/admin';

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }

  getCategories(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/getCategories`, {
      headers: this.getAuthHeaders()
    });
  }

 addCategory(name: string): Observable<string>  {
  return this.http.post(`${this.apiUrl}/addCategory`, name, {
    headers: this.getAuthHeaders(),
    responseType: 'text'
  });
}


  deleteCategory(categoryId: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/deleteCategory/${categoryId}`, {
      headers: this.getAuthHeaders(),
      responseType: 'text'
    });
  }

getEvents(): Observable<EventModel[]> {
  return this.http.get<EventModel[]>(`${this.apiUrl}/getEvents`, {
    headers: this.getAuthHeaders()
  });
}

  deleteEvent(eventId: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/deleteEvent/${eventId}`, {
      headers: this.getAuthHeaders(),
      responseType: 'text'
    });
  }

  addEvent(eventRequest: any, file: File): Observable<string> {
    const formData = new FormData();
    formData.append('eventRequest', new Blob([JSON.stringify(eventRequest)], { type: 'application/json' }));
    formData.append('file', file);

    return this.http.post(`${this.apiUrl}/addEvents`, formData, {
      headers: this.getAuthHeaders(),
      responseType: 'text'
    });
  }

  updateEvent(eventId: number, eventRequest: any, file?: File): Observable<string> {
    const formData = new FormData();
    formData.append('eventRequest', new Blob([JSON.stringify(eventRequest)], { type: 'application/json' }));
    if (file) {
      formData.append('file', file);
    }

    return this.http.put(`${this.apiUrl}/updateEvent/${eventId}`, formData, {
      headers: this.getAuthHeaders(),
      responseType: 'text'
    });
  }
}
