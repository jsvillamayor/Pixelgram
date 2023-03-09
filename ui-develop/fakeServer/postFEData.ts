import { User } from "src/app/models/user.model";



export const PostFrontEndData= [
    {
        PostFrontEnd:
        {
            id: 1,
            User: {
                id: 1,
                username: "Demetrius1",
                profileImg: "https://i0.wp.com/boingboing.net/wp-content/uploads/2020/06/IMG_20200602_082003_707.jpg?fit=1&resize=620%2C4000&ssl=1"
            },
            img: "https://i0.wp.com/boingboing.net/wp-content/uploads/2020/06/IMG_20200602_082003_707.jpg?fit=1&resize=620%2C4000&ssl=1",
            description: "star wars!!!!!!!!",
            createdOn: new Date("2022-08-31"),
            comments: {
                items: [
                    {
                        id: 1,
                        postId: 2,
                        username: "a",
                        body: "astring",
                        createdOn: new Date("2022-09-01"),
                    },
                    {
                        id: 2,
                        postId: 2,
                        username: "aa",
                        body: "aastring",
                        createdOn: new Date("2022-09-02"),

                    },
                    {
                        id: 3,
                        postId: 3,
                        username: "aaa",
                        body: "aaastring",
                        createdOn: new Date("2022-09-02"),

                    },
                    {
                        id: 4,
                        postId: 4,
                        username: "aaaa",
                        body: "aaaastring",
                        createdOn: new Date("2022-09-02"),

                    },
                    {
                        id: 5,
                        postId: 5,
                        username: "bb",
                        body: "aaaaastring",
                        createdOn: new Date("2022-09-02"),

                    },
                ],
                "hasNext": false,
                "totalElements": 5,
            },
    },
    }
]
