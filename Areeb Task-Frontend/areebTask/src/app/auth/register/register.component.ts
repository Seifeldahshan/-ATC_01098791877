import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RegisterRequest } from '../../models/registerRequest.model';

@Component({
  standalone: true,
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  imports: [CommonModule, FormsModule]
})
export class RegisterComponent {
  model: RegisterRequest = {
    username: '',
    password: '',
    email: ''
  };
  confirmPassword: string = '';  
  errorMessage: string = '';  
  successMessage: string = '';  

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    if (this.model.password !== this.confirmPassword) {
      this.errorMessage = 'Passwords do not match!';
      return;
    }
    if (this.model.password.length < 6) {
      this.errorMessage = 'Password must be at least 6 characters long.';
      return;
    }

    this.authService.register(this.model).subscribe({
      next: () => {
        this.successMessage = 'Registration successful!';
        setTimeout(() => {
          this.router.navigate(['/auth']);
        }, 500);
      },
      error: (err) => {
        if (err.status === 409) {
          this.errorMessage = 'User already exists';
        } else {
          this.errorMessage = 'Registration failed';
        }
      }
    });
  }
}
