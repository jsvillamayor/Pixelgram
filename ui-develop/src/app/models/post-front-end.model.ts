import { PostComponent } from "../home-page/post/post.component";
import { PageOfItems } from "./page-of-items.model";
import { PostComment } from "./postcomment.model";
import { User } from "./user.model";

export class PostFrontEnd {
    id: number;
    user: User;   
    img: string;
    description: string;
    createdOn: Date;
    comments: PageOfItems<PostComment>;

    constructor(id: number, user: User, img: string, description: string, createOn: Date, comments:PageOfItems<PostComment>){
        this.id = id;
        this.user = user;
        this.img = img;
        this.description = description;
        this.createdOn = createOn;
        this.comments = comments;
    }
    
}
