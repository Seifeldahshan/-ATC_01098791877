import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'app-my-bookings',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './my-bookings.component.html',
  providers: [DatePipe]
})
export class MyBookingsComponent implements OnInit {
  bookings: any[] = [];
  errorMessage: string = '';

  constructor(private http: HttpClient, private router: Router) {}
ngOnInit(): void {
  const token = localStorage.getItem('token');
  if (!token) {
    this.router.navigate(['/login']);
    return;
  }

  const headers = new HttpHeaders({
    Authorization: `Bearer ${token}`
  });

  this.http.get<any[]>('http://localhost:8080/booking/my-bookings', { headers }).subscribe({
    next: (data) => {
      this.bookings = data.map(booking => {
        const eventDateStr = booking.eventRequest.date; 
        const eventTimeStr = booking.eventRequest.startTime || booking.bookingDate; 
        const eventDateTime = new Date(`${eventDateStr}T${eventTimeStr}`);
        
        const now = new Date();
        booking.isExpired = now > eventDateTime;

        return booking;
      });
    },
    error: (err) => this.errorMessage = 'Failed to load bookings'
  });
}


}
