import { Routes } from '@angular/router';
import { BookingsPageComponent } from './pages/bookings-page.component';

export const routes: Routes = [
  { path: '', component: BookingsPageComponent },
  { path: '**', redirectTo: '' }
];


