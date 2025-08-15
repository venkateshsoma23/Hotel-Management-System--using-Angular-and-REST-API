import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AvailabilityInfo, Booking } from '../models/booking.model';

@Injectable({ providedIn: 'root' })
export class HotelService {
  private readonly baseUrl = '/api/hotel_db';

  constructor(private http: HttpClient) {}

  getBookings(): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.baseUrl}/bookings`);
  }

  createBooking(request: {
    customerName: string;
    contact: string;
    roomType: 'AC' | 'Non-AC' | string;
    count: number;
  }): Observable<Booking> {
    return this.http.post<Booking>(`${this.baseUrl}/bookings`, request);
  }

  deleteBooking(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/bookings`, { params: { id } as any });
  }

  getAvailability(type: 'AC' | 'Non-AC' | string): Observable<AvailabilityInfo> {
    return this.http.get<AvailabilityInfo>(`${this.baseUrl}/rooms/availability`, { params: { type } as any });
  }
}


