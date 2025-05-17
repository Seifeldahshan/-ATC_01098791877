export interface EventModel  {
  id: number;
  name: string;
  description: string;
  venue: string;
  startTime: string; 
  date: string | Date; 
  price: number;
   image?: string;      // add this
  imageUrl?: string; 
  
}
export interface AddEventRequest {
  name: string;
  description: string;
  venue: string;
  startTime: string; 
  date: string | Date; 
  price: number;
  imageUrl?: string; 
  categoryId: number;
}


export interface Page<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;  
  first: boolean;
  last: boolean;
}
