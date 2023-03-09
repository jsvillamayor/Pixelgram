export class PostComment {
    id: number;
    postId: number;
    username: string;
    body: string;
    createdOn: Date;

    constructor(id: number, postId: number, username: string, body: string, createdOn: Date){
        this.id = id; 
        this.postId = postId;
        this.username = username;
        this.body = body;
        this.createdOn = createdOn;
    }
}
