import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryRequestDTO } from '../models/category.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiUrl = 'http://localhost:8080/api/categories'; 

  constructor(private http: HttpClient) {}

  getAllCategories(): Observable<CategoryRequestDTO[]> {
    return this.http.get<CategoryRequestDTO[]>(this.apiUrl);
  }
}
