import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EventService } from '../services/events.service';
import { EventModel } from '../models/Event.model';
import { CategoryService } from '../services/category.service';
import { CategoryRequestDTO } from '../models/category.model';
import { BookingService } from '../services/booking.service'; 

@Component({
  selector: 'app-events',
  standalone: true,
  templateUrl: './events.component.html',
  imports: [CommonModule, HttpClientModule, RouterModule, FormsModule]
})
export class EventsComponent implements OnInit {
  events: EventModel[] = [];
  currentPage = 0;
  totalPages = 0;
  pageSize = 6;
  loading = true;

   categories: CategoryRequestDTO[] = [];
  selectedCategory: string = 'All';
    bookedEventIds: number[] = [];

  constructor(private eventService: EventService,
              private categoryService: CategoryService,
              private bookingService: BookingService ,
                private route: ActivatedRoute


  ) {}

ngOnInit(): void {
  this.loadCategories();
  this.loadBookedEvents(); 
  this.loadEvents();
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
loadCategories() {
  this.categoryService.getAllCategories().subscribe({
    next: (data) => {
      this.categories = [{ id: -1, name: 'All', events: [] }, ...data];

      // Handle initial query param after categories are ready
      this.route.queryParams.subscribe((params) => {
        const categoryParam = params['category'];
        if (categoryParam) {
          const selectedCategoryObj = this.categories.find(cat => cat.id == categoryParam);
          if (selectedCategoryObj) {
            this.selectedCategory = selectedCategoryObj.name;
          }
        }
        this.loadEvents();
      });
    },
    error: (err) => console.error('Failed to load categories', err),
  });
}



loadEvents(page: number = 0) {
  this.loading = true;
  this.eventService.getEvents(page, this.pageSize, this.selectedCategory).subscribe({
    next: (data) => {
      this.events = data.content.map(event => ({
        ...event,
        imageUrl: event.image ? `http://localhost:8080/upload/${event.image}` : 'assets/default-image.png' // fallback if no image
      }));
      this.currentPage = data.number;
      this.totalPages = data.totalPages;
      this.loading = false;
    },
    error: (err) => {
      this.loading = false;
      console.error('Error loading events', err);
    }
  });
}

  onCategoryChange(category: string) {
    this.selectedCategory = category;
    this.loadEvents(0);
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.loadEvents(this.currentPage + 1);
    }
  }

  prevPage() {
    if (this.currentPage > 0) {
      this.loadEvents(this.currentPage - 1);
    }
  }
}
