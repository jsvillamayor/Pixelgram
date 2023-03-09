import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import {MatCardModule} from '@angular/material/card';
import { PageOfItems } from 'src/app/models/page-of-items.model';
import { PostFrontEnd } from 'src/app/models/post-front-end.model';
import { PostComment } from 'src/app/models/postcomment.model';
import { User } from 'src/app/models/user.model';
import { CommentService } from 'src/app/services/comment.service';
import { PostComponent } from './post.component';

describe('PostComponent', () => {
  let postComponent: PostComponent;
  let fixture: ComponentFixture<PostComponent>;
  let commentService: CommentService;

  // beforeEach(async () => {
  //   await TestBed.configureTestingModule({
  //     imports:[
  //       MatCardModule, HttpClientModule
  //     ],
  //     declarations: [ 
  //       PostComponent
  //     ]
  //   })
  //   .compileComponents();
  // });

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        MatCardModule, HttpClientModule
      ],
      declarations: [ 
        PostComponent
      ]
    })
    .compileComponents();
    fixture = TestBed.createComponent(PostComponent);
    postComponent = fixture.componentInstance;
    commentService = TestBed.inject(CommentService);
    let first:PostComment = new PostComment(1, 1, "James", "GREAT POST!!!", new Date("2022-09-02"));
    let second = new PostComment(1, 1, "Demetrius", "GREAT POST!!!", new Date("2022-09-02"));
    let third = new PostComment(1, 1, "Tam", "GREAT POST!!!", new Date("2022-09-02"));
    let pageOfComments:PageOfItems<PostComment> = new PageOfItems<PostComment>([first, second, third], false, 3);
    let user:User = new User(1, "Andrew", "img.com");
    let post:PostFrontEnd = new PostFrontEnd(1, user, "img.com", "this is a description", 
            new Date("2022-09-02"), pageOfComments);
    postComponent.postData = post;
    fixture.detectChanges();
  });

  it('should create', async () => {
    expect(postComponent).toBeTruthy();
  });

  it('should display username', async () => {
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('div').textContent).toContain('more_horiz');
  });

  it('service is created', () => {
    expect(commentService).toBeTruthy();
  });

  it('service is called when view more comments', () => {
    const spyOnService = spyOn(commentService, 'getComments').and.callThrough();
    postComponent.getComments();
    expect(spyOnService).toHaveBeenCalled();
  });

  // it('more comments button adds to comments', () => {
  //   spyOn(postComponent, "ngOnInit").and.callThrough();
  //   postComponent.ngOnInit();
  //   postComponent.getComments();
  //   fixture.detectChanges();
  //   fixture.whenStable().then(() => {
  //     expect(postComponent.getComments.length).toBeGreaterThanOrEqual(1);//.toBeLessThanOrEqual(10);
  //   });

  // it('call comment service from getComments', () => {
  //   const service = TestBed.inject(CommentService);
  //   const spyOnService = spyOn(service, 'getComments').and.callThrough();
  //   postComponent.getComments();
  //   expect(spyOnService).toHaveBeenCalled();
  // });

  afterEach(() => {
    fixture.destroy();
  });

});
