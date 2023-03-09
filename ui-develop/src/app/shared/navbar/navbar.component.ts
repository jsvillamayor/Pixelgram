import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  title: string = 'Pixelgram';
  show!: boolean;
  isLoggedIn!: boolean;
  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}

  ngOnInit(): void {
    this.show = this.onHomePage();
    this.isLoggedIn = this.authService.isLoggedIn;
    // this.subscription = this.authService.isLoggedIn.subscribe((result)=>{
    //   this.isLoggedIn = result;
    //   console.log(result + " WHOOOOOOOO")
    // })
    if (this.isLoggedIn == true) {
      this.redirectToHome();
    }
  }

  onHomePage(): boolean {
    if (this.router.url === '/') {
      return true;
    }

    return false;
  }

  logout(): void {
    this.authService.logout();
    this.isLoggedIn = this.authService.isLoggedIn;
    this.router.navigate(['']);
  }

  redirectToHome(): void {
    this.router.navigate(['']);
  }
}
