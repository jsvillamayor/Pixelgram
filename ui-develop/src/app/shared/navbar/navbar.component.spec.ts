import { DebugElement } from '@angular/core';
import { ComponentFixture, inject, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { AppModule } from 'src/app/app.module';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterTestingModule } from '@angular/router/testing';
import { NavbarComponent } from './navbar.component';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';

describe('NavbarComponent', () => {
  let component: NavbarComponent;
  let fixture: ComponentFixture<NavbarComponent>;
  let de: DebugElement;
  let router: Router;

  // beforeEach(async () => {
  //   await TestBed.configureTestingModule({
  //     imports:[MatToolbarModule],
  //     declarations: [ NavbarComponent ]
  //   })
  //   .compileComponents();
  // });

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthenticationService],
      imports: [MatToolbarModule, RouterTestingModule, HttpClientModule],
      declarations: [NavbarComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(NavbarComponent);
    router = TestBed.inject(Router);
    component = fixture.componentInstance;
    de = fixture.debugElement;

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('logout called when logout', () => {
    const spyOnService = spyOn(component, 'logout').and.callThrough();
    component.logout();
    expect(spyOnService).toHaveBeenCalled();
  });

  it('onHomepage is called when check', () => {
    const spyOnService = spyOn(component, 'onHomePage').and.callThrough();
    component.onHomePage();
    expect(spyOnService).toHaveBeenCalled();
  });

  it('redirectToHome should be called', () => {
    const spyOnRedirect = spyOn(component, 'redirectToHome');
    component.isLoggedIn == true;
    component.redirectToHome();
    expect(spyOnRedirect).toHaveBeenCalled();
  });

  it('redirectToHome should redirect to homepage', () => {
    const navigateSpy = spyOn(router, 'navigate').and.callThrough();
    component.redirectToHome();
    expect(navigateSpy).toHaveBeenCalledWith(['']);
  });

  afterEach(() => {
    fixture.destroy();
  });
});
function waitforAsync(
  arg0: () => void
): ((done: DoneFn) => Promise<void>) | undefined {
  throw new Error('Function not implemented.');
}
