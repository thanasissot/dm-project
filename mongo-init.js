// Authenticate as the root user
var dbName = 'mydatabase';
// db.auth('admin','secret')

// Use admin then create user root
var adminDB = db.getSiblingDB('admin');

adminDB.auth('admin', 'secret');

db = db.getSiblingDB('mydatabase');  // Select the database 'mydatabase'

db.createCollection('orders');

db.createCollection('carts');
db.carts.insertMany([
    {_id: ObjectId("111111111111111111111111"), customerId: ObjectId("111111111111111111111111")},
    {_id: ObjectId("222222222222222222222222"), customerId: ObjectId("222222222222222222222222")}
])

db.createCollection('cartitems');
db.cartitems.insertMany([
    {_id: ObjectId("111111111111111111111111"), cartId: ObjectId("111111111111111111111111"),
    quantity: 1, productId: ObjectId("6d62d909f957430e8689b512")},
    {_id: ObjectId("222222222222222222222222"), cartId: ObjectId("111111111111111111111111"),
    quantity: 2, productId: ObjectId("a0a4f044b040410d8ead4de0")},

    {_id: ObjectId("333333333333333333333333"), cartId: ObjectId("222222222222222222222222"),
        quantity: 2, productId: ObjectId("808a2de11aaa4c25a9b96612")},
])

db.createCollection('tags');
db.tags.insertMany([
    {_id: ObjectId("111111111111111111111111"), name: "brown"},
    {_id: ObjectId("222222222222222222222222"), name: "geek"},
    {_id: ObjectId("333333333333333333333333"), name: "formal"},
    {_id: ObjectId("444444444444444444444444"), name: "blue"},
    {_id: ObjectId("555555555555555555555555"), name: "skin"},
    {_id: ObjectId("666666666666666666666666"), name: "red"},
    {_id: ObjectId("777777777777777777777777"), name: "action"},
    {_id: ObjectId("888888888888888888888888"), name: "sport"},
    {_id: ObjectId("999999999999999999999999"), name: "black"},
    {_id: ObjectId("111111111111111111111112"), name: "magic"},
    {_id: ObjectId("111111111111111111111113"), name: "green"}
]);

db.createCollection('socks');
db.socks.insertMany([
    {
        "_id": ObjectId("6d62d909f957430e8689b512"),
        "name": "Weave special",
        "description": "Limited issue Weave socks.",
        "price": 17.15,
        "count": 33,
        "imageUrls": [
            "/catalogue/images/weave1.jpg",
            "/catalogue/images/weave2.jpg"
        ]
    },
    {
        "_id": ObjectId("a0a4f044b040410d8ead4de0"),
        "name": "Nerd leg",
        "description": "For all those leg lovers out there. A perfect example of a swivel chair trained calf. Meticulously trained on a diet of sitting and Pina Coladas.",
        "price": 7.99,
        "count": 115,
        "imageUrls": [
            "/catalogue/images/bit_of_leg_1.jpeg",
            "/catalogue/images/bit_of_leg_2.jpeg"
        ]
    },
    {
        "_id": ObjectId("808a2de11aaa4c25a9b96612"),
        "name": "Crossed",
        "description": "A mature sock, crossed, with an air of nonchalance.",
        "price": 17.32,
        "count": 738,
        "imageUrls": [
            "/catalogue/images/cross_1.jpeg",
            "/catalogue/images/cross_2.jpeg"
        ]
    },
    {
        "_id": ObjectId("510a0d7e8e834193b483e27e"),
        "name": "SuperSport XL",
        "description": "Ready for action. Engineers: be ready to smash that next bug! Be ready, with these superactionsportmasterpieces.",
        "price": 15.00,
        "count": 820,
        "imageUrls": [
            "/catalogue/images/puma_1.jpeg",
            "/catalogue/images/puma_2.jpeg"
        ]
    },
    {
        "_id": ObjectId("03fef6ac18964ce8bd69b798"),
        "name": "Holy",
        "description": "Socks fit for a Messiah. You too can experience walking in water with these special edition beauties.",
        "price": 99.99,
        "count": 1,
        "imageUrls": [
            "/catalogue/images/holy_1.jpeg",
            "/catalogue/images/holy_2.jpeg"
        ]
    },
    {
        "_id": ObjectId("d3588630ad8e49dfbbd73167"),
        "name": "YouTube.sock",
        "description": "We were not paid to sell this sock. It's just a bit geeky.",
        "price": 10.99,
        "count": 801,
        "imageUrls": [
            "/catalogue/images/youtube_1.jpeg",
            "/catalogue/images/youtube_2.jpeg"
        ]
    },
    {
        "_id": ObjectId("819e1fbf8b7e4f6d811f6935"),
        "name": "Figueroa",
        "description": "enim officia aliqua excepteur esse deserunt quis aliquip nostrud anim",
        "price": 14,
        "count": 808,
        "imageUrls": [
            "/catalogue/images/WAT.jpg",
            "/catalogue/images/WAT2.jpg"
        ]
    },
    {
        "_id": ObjectId("aaa4f044b040410d8ead4de0"),
        "name": "Classic",
        "description": "Keep it simple.",
        "price": 12,
        "count": 127,
        "imageUrls": [
            "/catalogue/images/classic.jpg",
            "/catalogue/images/classic2.jpg"
        ]
    },
    {
        "_id": ObjectId("3395a43e2d8840deb95fe00e"),
        "name": "Colourful",
        "description": "proident occaecat irure et excepteur labore minim nisi amet irure",
        "price": 18,
        "count": 438,
        "imageUrls": [
            "/catalogue/images/colourful_socks.jpg",
            "/catalogue/images/colourful_socks.jpg"
        ]
    },
    {
        "_id": ObjectId("837ab141399e4c1f9abcbace"),
        "name": "Cat socks",
        "description": "consequat amet cupidatat minim laborum tempor elit ex consequat in",
        "price": 15,
        "count": 175,
        "imageUrls": [
            "/catalogue/images/catsocks.jpg",
            "/catalogue/images/catsocks2.jpg"
        ]
    }
]);

db.createCollection('sockTags');
db.sockTags.insertMany([
    { "sockId": ObjectId("6d62d909f957430e8689b512"), "tagId": ObjectId("222222222222222222222222") },  // Tag 2: geek
    { "sockId": ObjectId("6d62d909f957430e8689b512"), "tagId": ObjectId("999999999999999999999999") },  // Tag 9: black
    { "sockId": ObjectId("a0a4f044b040410d8ead4de0"), "tagId": ObjectId("444444444444444444444444") },  // Tag 4: blue
    { "sockId": ObjectId("a0a4f044b040410d8ead4de0"), "tagId": ObjectId("555555555555555555555555") },  // Tag 5: skin
    { "sockId": ObjectId("808a2de11aaa4c25a9b96612"), "tagId": ObjectId("444444444444444444444444") },  // Tag 4: blue
    { "sockId": ObjectId("808a2de11aaa4c25a9b96612"), "tagId": ObjectId("666666666666666666666666") },  // Tag 6: red
    { "sockId": ObjectId("808a2de11aaa4c25a9b96612"), "tagId": ObjectId("777777777777777777777777") },  // Tag 7: action
    { "sockId": ObjectId("808a2de11aaa4c25a9b96612"), "tagId": ObjectId("333333333333333333333333") },  // Tag 3: formal
    { "sockId": ObjectId("510a0d7e8e834193b483e27e"), "tagId": ObjectId("888888888888888888888888") },  // Tag 8: sport
    { "sockId": ObjectId("510a0d7e8e834193b483e27e"), "tagId": ObjectId("999999999999999999999999") },  // Tag 9: black
    { "sockId": ObjectId("510a0d7e8e834193b483e27e"), "tagId": ObjectId("333333333333333333333333") },  // Tag 3: formal
    { "sockId": ObjectId("03fef6ac18964ce8bd69b798"), "tagId": ObjectId("111111111111111111111112") },  // Tag 10: magic
    { "sockId": ObjectId("03fef6ac18964ce8bd69b798"), "tagId": ObjectId("777777777777777777777777") },  // Tag 7: action
    { "sockId": ObjectId("d3588630ad8e49dfbbd73167"), "tagId": ObjectId("222222222222222222222222") },  // Tag 2: geek
    { "sockId": ObjectId("d3588630ad8e49dfbbd73167"), "tagId": ObjectId("333333333333333333333333") },  // Tag 3: formal
    { "sockId": ObjectId("819e1fbf8b7e4f6d811f6935"), "tagId": ObjectId("333333333333333333333333") },  // Tag 3: formal
    { "sockId": ObjectId("819e1fbf8b7e4f6d811f6935"), "tagId": ObjectId("111111111111111111111113") },  // Tag 11: green
    { "sockId": ObjectId("819e1fbf8b7e4f6d811f6935"), "tagId": ObjectId("444444444444444444444444") },  // Tag 4: blue
    { "sockId": ObjectId("aaa4f044b040410d8ead4de0"), "tagId": ObjectId("111111111111111111111111") },  // Tag 1: brown
    { "sockId": ObjectId("aaa4f044b040410d8ead4de0"), "tagId": ObjectId("111111111111111111111113") },  // Tag 11: green
    { "sockId": ObjectId("3395a43e2d8840deb95fe00e"), "tagId": ObjectId("111111111111111111111111") },  // Tag 1: brown
    { "sockId": ObjectId("3395a43e2d8840deb95fe00e"), "tagId": ObjectId("444444444444444444444444") },  // Tag 4: blue
    { "sockId": ObjectId("837ab141399e4c1f9abcbace"), "tagId": ObjectId("111111111111111111111111") },  // Tag 1: brown
    { "sockId": ObjectId("837ab141399e4c1f9abcbace"), "tagId": ObjectId("111111111111111111111113") },  // Tag 11: green
    { "sockId": ObjectId("837ab141399e4c1f9abcbace"), "tagId": ObjectId("333333333333333333333333") }   // Tag 3: formal
]);

db.createCollection('users');
db.users.insertMany([
    {_id: ObjectId("111111111111111111111111"),
        customerId: ObjectId("111111111111111111111111"),
        firstName: "user1",
        lastName: "user1",
        email: "user1@mail.com",
        username: "user1user",
        password: "user1pass",
        salt: "user1salt" },

    {_id: ObjectId("222222222222222222222222"),
        customerId: ObjectId("222222222222222222222222"),
        firstName: "user2",
        lastName: "user2",
        email: "user2@mail.com",
        username: "user2user",
        password: "user2pass",
        salt: "user2salt" },
]);

db.createCollection('addresses');
db.addresses.insertMany([
    {
        _id: ObjectId("111111111111111111111111"), userId: ObjectId("111111111111111111111111"),
        street: "street1", numberS: 1, city: "city1", country: "country1", postCode: "postCode1"
    },
    {
        _id: ObjectId("222222222222222222222222"), userId: ObjectId("111111111111111111111111"),
        street: "street2", numberS: 2, city: "city1", country: "country1", postCode: "postCode2"
    },
    {
        _id: ObjectId("333333333333333333333333"), userId: ObjectId("222222222222222222222222"),
        street: "street3", numberS: 3, city: "city1", country: "country1", postCode: "postCode3"
    },

]);

db.createCollection('cards');
db.cards.insertMany([
    {
        _id: ObjectId("111111111111111111111111"), userId: ObjectId("111111111111111111111111"),
        cardNumber: "cardNumber1", expires: "never", ccv: "221"
    },
    {
        _id: ObjectId("222222222222222222222222"), userId: ObjectId("111111111111111111111111"),
        cardNumber: "cardNumber2", expires: "never", ccv: "222"
    },
    {
        _id: ObjectId("333333333333333333333333"), userId: ObjectId("222222222222222222222222"),
        cardNumber: "cardNumber3", expires: "10 days ago", ccv: "223"
    },

]);


