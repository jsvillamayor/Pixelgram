import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AnimationFrameScheduler } from 'rxjs/internal/scheduler/AnimationFrameScheduler';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  usernameTaken = false;
  registerBtnIsPressed = false;

  registerForm = new FormGroup({
    username: new FormControl('', [
      Validators.required,
      Validators.minLength(4),
    ]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(4),
    ]),
  });
  constructor(private authService: AuthenticationService, private router: Router) {}

  ngOnInit(): void {}

  onSubmit() {
    this.usernameTaken = false;
    this.authService.register(this.registerForm.value.username, this.registerForm.value.password).subscribe(
      (res: any) => {
        this.router.navigate(['']);
      },
      (error) => {
        if(error.status == 409){
          this.usernameTaken = true;
        }
      });
    this.registerBtnIsPressed = true;
  }
}
