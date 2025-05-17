import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class BookingService {

  private baseUrl = 'http://localhost:8080/booking';

  constructor(private http: HttpClient) {}

  getBookedEventIds(): Observable<number[]> {
    const token = localStorage.getItem('token') || '';
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.get<number[]>(`${this.baseUrl}/user/booked-event-ids`, { headers });
  }
}
