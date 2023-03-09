export class Post {
  id: number;
  //change username -> userId as per contract
  username: number;
  img: string;
  description: string;
  createdOn: Date;

  constructor(id: number, username: number, img: string, description: string, createdOn: Date){
    this.id = id;
    this.username = username;
    this.img = img;
    this.description = description;
    this.createdOn = createdOn;
  }
}
