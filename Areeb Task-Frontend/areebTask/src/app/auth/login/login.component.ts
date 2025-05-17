import { Component } from '@angular/core';
import { AuthService, LoginRequest } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  standalone: true,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [CommonModule, FormsModule]
})
export class LoginComponent {
  model: LoginRequest = {
    email: '',
    password: ''
  };
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    if (!this.model.email || !this.model.password) {
      this.errorMessage = 'Email and Password are required!';
      return;
    }

    this.authService.login(this.model).subscribe({
      next: (res) => {
        if (res && res.token) {
          localStorage.setItem('token', res.token);

          this.authService.getCurrentUser().subscribe({
            next: (user) => {
              if (user.role === 'ADMIN') {
                this.router.navigate(['/admin']);
              } else {
                this.router.navigate(['/']);
              }
            },
            error: () => {
              this.errorMessage = 'Failed to fetch user info.';
            }
          });

        } else {
          this.errorMessage = res.failureReason || 'Login failed';
        }
      },
      error: () => {
        this.errorMessage = 'Invalid email or password.';
      }
    });
  }
}
