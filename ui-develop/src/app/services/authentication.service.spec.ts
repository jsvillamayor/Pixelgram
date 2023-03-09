import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { fakeAsync, TestBed, waitForAsync } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';

describe('AuthenticationService', () => {
  let authService: AuthenticationService;
  let httpTestingController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthenticationService],
      imports: [HttpClientTestingModule]
    });
    authService = TestBed.inject(AuthenticationService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(authService).toBeTruthy();
  });

  it('check' , ()=>{
    let api = "https://enablement-keycloak.work.cognizant.studio/auth/realms/Uunderlings/protocol/openid-connect/token";
    let mockResponse = {
      "access_tokens": 'test'
    }

    authService.login("test","test").subscribe(result=>{
        // expect(result).toEqual(mockResponse, 'no tokens')
    })
    const req = httpTestingController.expectOne(api);
    expect(req.request.method).toEqual("POST");

    req.flush(mockResponse);
    
    
  });

  it('saveAccessToken to be called', ()=>{
    spyOn(authService, 'saveAccessToken');
    authService.saveAccessToken("test");
    expect(authService.saveAccessToken).toHaveBeenCalled();
  })

  it('isUserLoggedIn to be called', ()=>{
    spyOn(authService, 'isUserLoggedIn');
    authService.isUserLoggedIn();
    expect(authService.isUserLoggedIn).toHaveBeenCalled();
  })

  it('logout to be called', ()=>{
    spyOn(authService, 'logout');
    authService.logout();
    expect(authService.logout).toHaveBeenCalled();
  })

  afterEach(()=>{
    httpTestingController.verify();
})
});

// {
//   access_tokens: "testAccessToken",
//   expires_in: 300,
//   refresh_expires_in: 1800,
//   refresh_token: "testrefreshtoken",
//   token_type: "Bearer",
//   id_token: "testIdToken",
//   "not-before-policy": 0,
//   session_state: "testSessionState",
//   scope: "openid email profile"
// }