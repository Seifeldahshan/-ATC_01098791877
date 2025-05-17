import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-event-details',
  imports: [CommonModule],
  templateUrl: './event-details.component.html',
  styleUrl:'./event-details.component.css',
  providers: [DatePipe]
})
export class EventDetailsComponent implements OnInit {
  event: any;
  successMessage: string = '';
  errorMessage: string = '';
  isBooked: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    const eventId = this.route.snapshot.paramMap.get('id');
    if (eventId) {
      this.loadEvent(eventId);
      this.checkIfBooked(eventId);
    }else{
          console.error('No event ID found in route');
    return;
    }
  }

loadEvent(eventId: string): void {
  this.http.get<any>(`http://localhost:8080/events/${eventId}`).subscribe({
    next: data => {
      this.event = {
        ...data,
        imageUrl: data.image ? `http://localhost:8080/upload/${data.image}` : 'assets/default-image.png'
      };
    },
    error: err => console.error('Failed to load event:', err)
  });
}


  checkIfBooked(eventId: string | null): void {
    const token = localStorage.getItem('token');
    if (!token || !eventId) {
      this.isBooked = false;
      return;
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    this.http.get<boolean>(`http://localhost:8080/booking/isBooked?eventId=${eventId}`, { headers }).subscribe({
      next: booked => this.isBooked = booked,
      error: err => console.error('Failed to check booking status:', err)
    });
  }

  bookEvent(): void {
    const token = localStorage.getItem('token');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    const url = `http://localhost:8080/booking/ticket?eventId=${this.event.id}`;

    this.http.post(url, null, { headers, responseType: 'text' }).subscribe({
      next: response => {
        this.successMessage = response;
        this.errorMessage = '';
        this.isBooked = true; 
        this.router.navigate(['/gratz']);
      },
      error: err => {
        this.successMessage = '';
        this.errorMessage = err.error || 'Booking failed. You may have already booked this event.';
      }
    });
  }

  cancelBooking(): void {
    const token = localStorage.getItem('token');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    const url = `http://localhost:8080/booking/ticket?eventId=${this.event.id}`;

    this.http.delete(url, { headers, responseType: 'text' }).subscribe({
      next: response => {
        this.successMessage = response;
        this.errorMessage = '';
        this.isBooked = false; 
      },
      error: err => {
        this.successMessage = '';
        this.errorMessage = err.error || 'Failed to cancel booking.';
      }
    });
  }
}
