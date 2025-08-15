import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HotelService } from '../services/hotel.service';
import { AvailabilityInfo, Booking } from '../models/booking.model';

@Component({
  selector: 'app-bookings-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="row">
      <div class="col">
        <div class="card">
          <h2 style="margin-top:0">New Booking</h2>
          <div class="row">
            <div class="col">
              <div class="label">Customer Name</div>
              <input class="input" [(ngModel)]="form.customerName" />
            </div>
            <div class="col">
              <div class="label">Contact</div>
              <input class="input" [(ngModel)]="form.contact" />
            </div>
          </div>
          <div class="row" style="margin-top:12px">
            <div class="col">
              <div class="label">Room Type</div>
              <select [(ngModel)]="form.roomType">
                <option value="AC">AC</option>
                <option value="Non-AC">Non-AC</option>
              </select>
            </div>
            <div class="col">
              <div class="label">Count</div>
              <input type="number" class="input" min="1" [(ngModel)]="form.count" />
            </div>
            <div class="col" style="display:flex; align-items:flex-end">
              <button class="btn" (click)="submit()">Book</button>
            </div>
          </div>
          <div style="margin-top:12px; color: var(--muted)">
            <span>Availability: </span>
            <span class="pill" *ngIf="availability()?.type as t">{{t}} {{availability()?.available}} / {{availability()?.total}}</span>
            <button class="btn secondary" style="margin-left:8px" (click)="refreshAvailability()">Refresh</button>
          </div>
        </div>
      </div>
    </div>

    <div class="row" style="margin-top:16px">
      <div class="col">
        <div class="card">
          <h2 style="margin-top:0">Bookings</h2>
          <table class="list">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Contact</th>
                <th>Type</th>
                <th>Count</th>
                <th>Rooms</th>
                <th>When</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <tr *ngFor="let b of bookings()">
                <td>{{b.id}}</td>
                <td>{{b.customerName}}</td>
                <td>{{b.contact}}</td>
                <td>{{b.roomType}}</td>
                <td>{{b.count}}</td>
                <td>{{b.bookedRooms?.join(', ')}}</td>
                <td>{{b.timestamp | date:'short'}}</td>
                <td><button class="btn secondary" (click)="remove(b)">Delete</button></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  `
})
export class BookingsPageComponent implements OnInit {
  bookings = signal<Booking[]>([]);
  availability = signal<AvailabilityInfo | null>(null);

  form: { customerName: string; contact: string; roomType: 'AC' | 'Non-AC'; count: number } = {
    customerName: '',
    contact: '',
    roomType: 'AC',
    count: 1
  };

  constructor(private readonly api: HotelService) {}

  ngOnInit(): void {
    console.log('BookingsPageComponent initialized');
    this.loadBookings();
    this.refreshAvailability();
  }

  loadBookings(): void {
    console.log('Loading bookings...');
    this.api.getBookings().subscribe({
      next: (data) => {
        console.log('Bookings loaded:', data);
        this.bookings.set(data ?? []);
      },
      error: (err) => {
        console.error('Error loading bookings:', err);
        this.bookings.set([]);
      }
    });
  }

  refreshAvailability(): void {
    console.log('Refreshing availability for:', this.form.roomType);
    this.api.getAvailability(this.form.roomType).subscribe({
      next: (data) => {
        console.log('Availability loaded:', data);
        this.availability.set(data);
      },
      error: (err) => {
        console.error('Error loading availability:', err);
        this.availability.set(null);
      }
    });
  }

  submit(): void {
    const { customerName, contact, roomType, count } = this.form;
    if (!customerName || !contact || count < 1) return;
    this.api.createBooking({ customerName, contact, roomType, count }).subscribe({
      next: () => {
        this.form.count = 1;
        this.loadBookings();
        this.refreshAvailability();
      },
      error: (err) => alert(err?.error?.message || 'Booking failed')
    });
  }

  remove(b: Booking): void {
    this.api.deleteBooking(b.id).subscribe({
      next: () => {
        this.loadBookings();
        this.refreshAvailability();
      },
      error: (err) => console.error(err)
    });
  }
}


