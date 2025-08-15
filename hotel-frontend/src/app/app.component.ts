import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `
    <div class="container">
      <h1 style="margin:0 0 12px 0">Hotel Management</h1>
      <router-outlet></router-outlet>
    </div>
  `
})
export class AppComponent {}


