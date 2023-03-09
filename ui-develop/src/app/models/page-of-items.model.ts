import { PostFrontEnd } from "./post-front-end.model";
import { Post } from "./post.model";


export class PageOfItems<T> {
  items: T[];
  hasNext: boolean;
  totalElements: number;

  constructor(items: T[], hasNext: boolean, totalElements: number){
    this.items = items;
    this.hasNext = hasNext;
    this.totalElements = totalElements;
  }
}
