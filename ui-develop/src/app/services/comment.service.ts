import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PageOfItems } from '../models/page-of-items.model';
import { PostComment } from '../models/postcomment.model';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private apiServerURL: string = environment.apiBaseUrl;
  private localURL: string = "http://34.171.94.182"; //http://localhost:8084

  constructor(private http: HttpClient) { }

  public getComments(postId: number, pageNumber: number, pageSize: number): Observable<PageOfItems<PostComment>>{
    return this.http.get(`${this.apiServerURL}/comments?postid=${postId}&pageNumber=${pageNumber}&pageSize=${pageSize}`, {responseType: 'json'}) as Observable<PageOfItems<PostComment>>;
  }
}
