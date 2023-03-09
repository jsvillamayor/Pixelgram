import { Component, Input, OnInit } from '@angular/core';
import { PageOfItems } from 'src/app/models/page-of-items.model';
import { PostFrontEnd } from 'src/app/models/post-front-end.model';
import { CommentService } from 'src/app/services/comment.service';
import { PostComment } from "src/app/models/postcomment.model";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  page = 0;
  size = 5;

  @Input()
  postData!: PostFrontEnd; //changed here cz  
  
  
  //subject to change just need enough to pass minimal tests
  // public user:string="braydoncoyer";
  // public img:string="https://i0.wp.com/boingboing.net/wp-content/uploads/2020/06/IMG_20200602_082003_707.jpg?fit=1&resize=620%2C4000&ssl=1";
  // public description:string="I can't wait to see the new Star Wars movie! I've been following the new thilogy since they announced..."
  // public numberOfComments:number=14;
  // public numberOfLikes:number=13;
  // public comments:string[]=["I know! I can't wait to see it!!!!"];
  // public commentsbyuser:string[]=["razzle_dazzle"]; 

  constructor(private commentService: CommentService) { }

  ngOnInit(): void {
    
  }

  getComments(): void { 
    this.page++;
    this.commentService.getComments(this.postData.id, this.page, this.size)
    .subscribe((response: PageOfItems<PostComment>) => {
      this.postData.comments.items.push(...response.items);
      this.postData.comments.hasNext = response.hasNext;
    });
  }
}
