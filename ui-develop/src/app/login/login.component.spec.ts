import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterTestingModule } from '@angular/router/testing';
import { Observable, of, throwError } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';
import { NavbarComponent } from '../shared/navbar/navbar.component';
import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthenticationService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        ReactiveFormsModule,
        MatToolbarModule,
      ],
      declarations: [LoginComponent, NavbarComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    authService = TestBed.inject(AuthenticationService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('forms populate', () => {
    let username = component.loginForm.controls['username'];
    let password = component.loginForm.controls['password'];
    expect(username.valid).toBeFalsy();
    expect(password.valid).toBeFalsy();
    username.setValue('lmao');
    password.setValue('123');
    expect(username.valid).toBeTruthy();
    expect(password.valid).toBeTruthy();
  });

  it('onSubmit is called when login in', () => {
    const spyOnService = spyOn(component, 'onSubmit').and.callThrough();
    component.onSubmit();
    expect(spyOnService).toHaveBeenCalled();
  });

  it('login AuthService is called when onSubmit', () => {
    const spyOnService = spyOn(authService, 'login').and.callThrough();
    component.onSubmit();
    expect(spyOnService).toHaveBeenCalled();
  });

  it('should handle error on login', () => {
    const xService = fixture.debugElement.injector.get(AuthenticationService);
    const mockCall = spyOn(xService, 'login').and.returnValue(
      throwError(() => new Error('status: 404 '))
    );

    component.onSubmit();

    expect(mockCall).toHaveBeenCalled();
  });

  it('should handle success on login', () => {
    const xService = fixture.debugElement.injector.get(AuthenticationService);
    const mockCall = spyOn(xService, 'login').and.returnValue(of());

    component.onSubmit();

    expect(mockCall).toHaveBeenCalled();
  });
});
