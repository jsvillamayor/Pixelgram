import {
  ComponentFixture,
  fakeAsync,
  TestBed,
  waitForAsync,
} from '@angular/core/testing';

import { HomePageComponent } from './home-page.component';
import { NavbarComponent } from '../shared/navbar/navbar.component';
import { PostComponent } from './post/post.component';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { PostService } from '../services/post.service';
import { RouterTestingModule } from '@angular/router/testing';

describe('HomePageComponent', () => {
  let component: HomePageComponent;
  let fixture: ComponentFixture<HomePageComponent>;
  let service: PostService;
  // beforeEach(async () => {
  //   await TestBed.configureTestingModule({

  //     declarations: [
  //       HomePageComponent,
  //       NavbarComponent,
  //       PostComponent
  //     ],
  //     imports: [
  //       HttpClientModule,
  //       MatCardModule,
  //       MatToolbarModule,
  //       InfiniteScrollModule
  //     ]
  //   })
  //   .compileComponents();
  // });

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HomePageComponent, NavbarComponent, PostComponent],
      imports: [
        HttpClientModule,
        MatCardModule,
        MatToolbarModule,
        InfiniteScrollModule,
        RouterTestingModule,
      ],
    }).compileComponents();
    fixture = TestBed.createComponent(HomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    service = TestBed.inject(PostService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have title on nav bar PixelGram', async () => {
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('div').textContent).toContain('Pixelgram');
  });

  it('should create service', () => {
    expect(service).toBeTruthy();
  });

  it('ngoninit calls getpost', () => {
    const spyOnService = spyOn(service, 'getPost').and.callThrough();
    component.ngOnInit();
    expect(spyOnService).toHaveBeenCalled();
  });

  it('call service from onscroll', () => {
    const spyOnService = spyOn(service, 'getPost').and.callThrough();
    component.onScroll();
    expect(spyOnService).toHaveBeenCalled();
  });

  afterEach(() => {
    fixture.destroy();
  });
});
