import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path:'' ,
        loadComponent: ()=> 
        import('./home/home.component').then(m=>m.HomeComponent),
            
        
    },
    {
        path:'events' ,
        loadComponent: ()=> 
        import('./events/events.component').then(m=>m.EventsComponent),
            
        
    },
       {
        path: 'auth',
        loadComponent: () =>
        import('./auth/auth.component').then(m => m.AuthComponent),
    },
           {
        path: 'login',
        loadComponent: () =>
        import('./auth/login/login.component').then(m => m.LoginComponent),
    },

    {
        path: 'events/:id',
        loadComponent: () => import('./event-details/event-details.component').then(m => m.EventDetailsComponent)
    },
    {
        path: 'my-bookings',
        loadComponent: () =>import('./my-bookings/my-bookings.component').then(m => m.MyBookingsComponent)
    },
    {
        path: 'admin',
        loadComponent: () =>import('./admin/admin.component').then(m => m.AdminPageComponent)
    },
    {
        path: 'gratz',
        loadComponent: () =>import('./congratulation/congratulation.component').then(m => m.CongratulationComponent)
    }

];
