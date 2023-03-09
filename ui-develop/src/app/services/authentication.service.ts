import { EventEmitter, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, Observable, switchMap } from 'rxjs';
import { LoginComponent } from '../login/login.component';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  enablementUrl: string = "https://enablement-keycloak.work.cognizant.studio/auth/realms/Uunderlings/protocol/openid-connect/token";
  PIXELGRAM_ACCESS_TOKEN : string = 'PixelgramAccessToken'
  registerUrl: string = "https://enablement-keycloak.work.cognizant.studio/auth/admin/realms/Uunderlings/users";
  public isLoggedIn!:boolean;  

  constructor(private http: HttpClient) {
    this.isUserLoggedIn();
  }

  public login(username:string, password:string):Observable<any>{
    
    let body = new URLSearchParams();
    body.set('grant_type', 'password');
    body.set('client_id', 'springboot-keycloak');
    body.set('username', username);
    body.set('password', password);
    body.set('scope','openid');

    let options = {
      headers: new HttpHeaders().set( 'Content-Type', 'application/x-www-form-urlencoded')
    }

    return this.http.post<any>(this.enablementUrl, body.toString(), options)
      .pipe(map(res=>{
          this.saveAccessToken(res.access_token); //other tokens available... check console.log(res)
          this.isUserLoggedIn();
      }));
  }



  public saveAccessToken(token: string){
    localStorage.setItem(this.PIXELGRAM_ACCESS_TOKEN, token);
  }

  public isUserLoggedIn(){
    let checkTokenPresence = localStorage.getItem(this.PIXELGRAM_ACCESS_TOKEN);
    if(checkTokenPresence == null){
      this.isLoggedIn = false;
      return false;
    }else{
      this.isLoggedIn = true;
      return true;
    }
  }

  public logout(){
    localStorage.removeItem(this.PIXELGRAM_ACCESS_TOKEN);
    this.isUserLoggedIn();
  }

  public register(username:string, password:string): Observable<any> {
    let body = new URLSearchParams();
    body.set('grant_type', 'password');
    body.set('client_id', 'springboot-keycloak');
    body.set('username', 'admin');
    body.set('password', '1234');
    body.set('scope','openid');
    let options = {
      headers: new HttpHeaders().set( 'Content-Type', 'application/x-www-form-urlencoded')
    }
    //getting the admin token by loggin then pass it into the header
    return this.http.post<any>(this.enablementUrl, body.toString(), options)
      .pipe(switchMap(res=>{
          let headers = { headers: new HttpHeaders().set( 'Content-Type', 'application/json').set( 'Authorization', 'Bearer ' + res.access_token)};
          //Send request to create new user
          return this.http.post(this.registerUrl, {
                "enabled": true,
                "username" : username,
                "credentials" : [
                { "type": "password",
                  "value": password,
                  "temporary": false
                }
      ]}, headers).pipe(switchMap((res)=> this.login(username,password)));
      }));

  }
  
}
