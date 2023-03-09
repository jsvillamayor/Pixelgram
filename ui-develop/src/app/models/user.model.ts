export class User {
    id: number;
    username: string;
    profileimg!: string;

    constructor(id: number, username: string, profileimg: string){
        this.id = id;
        this.username = username;
        this.profileimg = profileimg;
    }
}
