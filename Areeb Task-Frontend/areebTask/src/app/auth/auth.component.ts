import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-auth',
  standalone: true,
 imports: [CommonModule, LoginComponent, RegisterComponent],
  templateUrl: './auth.component.html'
})
export class AuthComponent {
  mode: 'login' | 'register' = 'login';

  constructor(private route: ActivatedRoute, private router: Router) {
    this.route.queryParams.subscribe(params => {
      this.mode = params['mode'] === 'register' ? 'register' : 'login';
    });
  }

  toggleMode() {
    this.router.navigate(['/auth'], {
      queryParams: { mode: this.mode === 'login' ? 'register' : 'login' }
    });
  }
}
