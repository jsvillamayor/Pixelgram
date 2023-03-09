import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Route, Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { throwError } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';
import { NavbarComponent } from '../shared/navbar/navbar.component';

import { RegisterComponent } from './register.component';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let authService: AuthenticationService;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        MatToolbarModule,
        ReactiveFormsModule,
      ],
      declarations: [RegisterComponent, NavbarComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    authService = TestBed.inject(AuthenticationService);
    router = TestBed.inject(Router);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('onSubmit is called when login in', () => {
    const spyOnService = spyOn(component, 'onSubmit').and.callThrough();
    component.onSubmit();
    expect(spyOnService).toHaveBeenCalled();
  });

  it('AuthService called when onSubmit called', () => {
    const spyOnService = spyOn(authService, 'register').and.callThrough();
    component.onSubmit();
    expect(spyOnService).toHaveBeenCalled();
  });

  it('register should throw error with invalid data', () => {
    const errorResponse = new HttpErrorResponse({ status: 409, error: {} });

    const xService = fixture.debugElement.injector.get(AuthenticationService);
    spyOn(xService, 'register').and.returnValue(throwError(errorResponse));
    component.onSubmit();
    fixture.detectChanges();
    expect(component.usernameTaken).toBe(true);
  });
});
