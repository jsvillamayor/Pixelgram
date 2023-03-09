import { Component, OnInit } from '@angular/core';
import { PageOfItems } from '../models/page-of-items.model';
import { PostFrontEnd } from '../models/post-front-end.model';
import { Post } from '../models/post.model';
import { PostService } from '../services/post.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  
  page = 0;
  size = 5;
  pageOfItems!: PageOfItems<PostFrontEnd>;
  posts: PostFrontEnd[] = [];

  //mockdata !:any;
  
  constructor(private postService: PostService) { }

  ngOnInit(): void {
    //comment below out for local
    this.postService.getPost(this.page, this.size)
    .subscribe((response: PageOfItems<PostFrontEnd>) => {
      this.posts = response.items;
    });
    /*this.postService.getMockdata().subscribe((response:any) => {
      this.mockdata  = response ;
    })*/
   
  }

  onScroll(): void { 
    this.page++;
    this.postService.getPost(this.page, this.size)
    .subscribe((response: PageOfItems<PostFrontEnd>) => {
      this.posts.push(...response.items);
    });
    
  }
}
