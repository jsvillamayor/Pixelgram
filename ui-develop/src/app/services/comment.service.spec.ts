import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { CommentService } from './comment.service';

describe('CommentService', () => {
  let service: CommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule]
    });
    service = TestBed.inject(CommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return comment', () => {
    expect(service.getComments(0,0,1)).toBeTruthy();
  })

});
