import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  invalidCredentials: boolean = false;
  loginBtnIsPressed = false;

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  //add authService/keycloak and router as parameters
  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}

  ngOnInit(): void {}

  onSubmit(): void {
    this.invalidCredentials = false;
    this.loginBtnIsPressed = true;
    this.authService
      .login(this.loginForm.value.username, this.loginForm.value.password)
      .subscribe(
        (res: any) => {
          this.router.navigate(['']);
          this.loginBtnIsPressed = false;
        },
        (error: any) => {
          this.invalidCredentials = true;
        }
      );
  }

}
