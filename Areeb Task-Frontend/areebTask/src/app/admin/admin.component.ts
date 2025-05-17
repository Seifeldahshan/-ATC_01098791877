import { Component, OnInit } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EventModel, AddEventRequest } from '../models/Event.model';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.component.html'
})
export class AdminPageComponent implements OnInit {
  categories: any[] = [];
  newCategoryName = '';

  events: EventModel[] = [];
  newEvent: Partial<AddEventRequest> = {};
  selectedFile?: File;

  editingEvent: EventModel | null = null;
  editingFile?: File;

  showAddEventForm = false;
  showEditEventForm = false;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadCategories();
    this.loadEvents();
  }

  loadCategories(): void {
    this.adminService.getCategories().subscribe({
      next: data => this.categories = data,
      error: err => console.error('Failed to load categories', err)
    });
  }

  addCategory(): void {
    const name = this.newCategoryName.trim();
    if (!name) return;
    this.adminService.addCategory(name).subscribe({
      next: () => {
        this.newCategoryName = '';
        this.loadCategories();
      },
      error: err => console.error('Failed to add category', err)
    });
  }

  deleteCategory(id: number): void {
    this.adminService.deleteCategory(id).subscribe({
      next: () => this.loadCategories(),
      error: err => console.error('Failed to delete category', err)
    });
  }

  loadEvents(): void {
    this.adminService.getEvents().subscribe({
      next: data => this.events = data,
      error: err => console.error('Failed to load events', err)
    });
  }

  onFileSelected(event: any, isEdit = false): void {
    const file = event.target.files?.[0];
    if (!file) return;

    if (isEdit) {
      this.editingFile = file;
    } else {
      this.selectedFile = file;
    }
  }

  addEvent(): void {
    if (!this.newEvent.name || !this.selectedFile || !this.newEvent.categoryId) {
      alert('Please fill all required fields, select an image and category.');
      return;
    }

    this.adminService.addEvent(this.newEvent, this.selectedFile).subscribe({
      next: () => {
        this.resetNewEventForm();
        this.loadEvents();
      },
      error: err => console.error('Failed to add event', err)
    });
  }

startEditEvent(event: EventModel): void {
  this.editingEvent = { ...event }; 
  this.editingFile = undefined;
  
  if (this.editingEvent.date) {
    this.editingEvent.date = this.formatDateToInput(this.editingEvent.date);
  }

  this.showEditEventForm = true;
}


  updateEvent(): void {
    if (!this.editingEvent || !this.editingEvent.name) {
      alert('Event name is required');
      return;
    }

    this.adminService.updateEvent(this.editingEvent.id, this.editingEvent, this.editingFile).subscribe({
      next: () => {
        this.cancelEdit();
        this.loadEvents();
      },
      error: err => console.error('Failed to update event', err)
    });
  }

  cancelEdit(): void {
    this.showEditEventForm = false;
    this.editingEvent = null;
    this.editingFile = undefined;
  }

  deleteEvent(id: number): void {
    this.adminService.deleteEvent(id).subscribe({
      next: () => this.loadEvents(),
      error: err => console.error('Failed to delete event', err)
    });
  }

  private resetNewEventForm(): void {
    this.showAddEventForm = false;
    this.newEvent = {};
    this.selectedFile = undefined;
  }

  private formatDateToInput(date: string | Date): string {
  if (!date) return '';
  const d = new Date(date);
  const year = d.getFullYear();
  const month = (d.getMonth() + 1).toString().padStart(2, '0');
  const day = d.getDate().toString().padStart(2, '0');
  return `${year}-${month}-${day}`;
}
logout(): void {
  localStorage.removeItem('token'); 
  window.location.href = '/login'; 
}

}
