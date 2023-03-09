import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from '../models/post.model';

import { FakePosts } from 'fakeServer/db-data';

import { PostFrontEnd } from '../models/post-front-end.model';
import { Observable } from 'rxjs';
import { PageOfItems } from '../models/page-of-items.model';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  date: Date = new Date('2022-09-01');

  // post: Post = {
  //   id: 1,
  //   userId: 1,
  //   img: "https://i0.wp.com/boingboing.net/wp-content/uploads/2020/06/IMG_20200602_082003_707.jpg?fit=1&resize=620%2C4000&ssl=1",
  //   description: "Watching star wars",
  //   createdOn: this.date
  // }
  // pls
  private apiServerURL: string = environment.apiBaseUrl;
  private localURL: string = 'http://34.171.94.182';

  constructor(private http: HttpClient) {}

  public getPost(
    page: number,
    size: number
  ): Observable<PageOfItems<PostFrontEnd>> {
    //return PostFrontEndData[page];
    //use when with FEMs layer
    return this.http.get(
      `${this.apiServerURL}/posts?pageNumber=${page}&pageSize=${size}`,
      { responseType: 'json' }
    ) as Observable<PageOfItems<PostFrontEnd>>;
  }
  /*public getMockdata() {
      const $mockdata = this.http.get ('http://localhost:3000/api/mockdata',{responseType: 'json'}) ; 
      return $mockdata;
  }*/
}
