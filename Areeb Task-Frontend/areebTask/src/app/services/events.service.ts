import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EventModel, Page } from '../models/Event.model';

@Injectable({
  providedIn: 'root',
})
export class EventService {
  private apiUrl = 'http://localhost:8080/events/getEvents';

  constructor(private http: HttpClient) {}

  getEvents(page: number = 0, size: number = 6, category?: string): Observable<Page<EventModel>> {
    let url = `${this.apiUrl}?page=${page}&size=${size}`;
    if (category && category !== 'All') {
      url += `&category=${encodeURIComponent(category)}`;
    }
    return this.http.get<Page<EventModel>>(url);
  }

  getEventById(id: number): Observable<EventModel> {
    return this.http.get<EventModel>(`http://localhost:8080/events/${id}`);
  }
}
