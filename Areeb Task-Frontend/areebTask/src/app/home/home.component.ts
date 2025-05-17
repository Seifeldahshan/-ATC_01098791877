// home.component.ts
import { Component, OnInit } from '@angular/core';
import { CategoryRequestDTO } from '../models/category.model';
import { CategoryService } from '../services/category.service';
import { BookingService } from '../services/booking.service';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  imports: [CommonModule, RouterModule],
})
export class HomeComponent implements OnInit {
  categories: CategoryRequestDTO[] = [];
  bookedEventIds: number[] = [];
  maxEventsShown = 5;

  
  constructor(
    private categoryService: CategoryService,
    private bookingService: BookingService,
    private router: Router
  ) {}

ngOnInit(): void {
  this.loadBookedEvents(); 
  this.categoryService.getAllCategories().subscribe({
    next: (data) => {
      this.categories = data.map(cat => ({
        ...cat,
        events: cat.events
          ?.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime())
          .slice(0, this.maxEventsShown) || []
      }));
      // Log event ids for debugging:
      this.categories.forEach(cat => {
        cat.events.forEach(event => {

        });
      });
    },
    error: (err) => console.error('Failed to load categories:', err)
  });
}

  loadBookedEvents(): void {
    this.bookingService.getBookedEventIds().subscribe({
      next: (ids) => (this.bookedEventIds = ids),
      error: (err) => console.error('Failed to load booked events', err),
    });
  }
  isEventBooked(eventId: number): boolean {
    return this.bookedEventIds.includes(eventId);
  }

  exploreCategory(categoryId: number) {
    this.router.navigate(['/events'], {
      queryParams: { category: categoryId }
    });
  }

  scrollLeft(categoryId: number): void {
    const container = document.getElementById('scroll-container-' + categoryId);
    if (container) container.scrollLeft -= 320;
  }

  scrollRight(categoryId: number): void {
    const container = document.getElementById('scroll-container-' + categoryId);
    if (container) container.scrollLeft += 320;
  }
}
