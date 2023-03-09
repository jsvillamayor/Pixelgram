import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { Post } from '../models/post.model';

import { PostService } from './post.service';

describe('PostService', () => {
  let service: PostService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule]
    });
    service = TestBed.inject(PostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return post', () => {
    expect(service.getPost(1, 1)).toBeTruthy();
  });
  
});
