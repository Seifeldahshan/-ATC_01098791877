import { Component, OnInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']  
})
export class NavbarComponent implements OnInit {
  isLoggedIn: boolean = false;
  isDarkMode = false;

  constructor(public authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.authService.isLoggedIn$.subscribe(status => {
      this.isLoggedIn = status;
    });
  }

toggleDarkMode() {
  this.isDarkMode = !this.isDarkMode;
  console.log('Dark mode toggled:', this.isDarkMode);  
  this.updateBodyClass();
}

updateBodyClass() {
  if (this.isDarkMode) {
    document.body.classList.add('dark-theme');
    console.log('dark-theme class added to body');
  } else {
    document.body.classList.remove('dark-theme');
    console.log('dark-theme class removed from body');
  }
}


  logout() {
    this.authService.logout();
    this.router.navigate(['/auth']);
  }
}
