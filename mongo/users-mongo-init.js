// Authenticate as the root user
var dbName = 'mydatabase';
// db.auth('admin','secret')

// Use admin then create user root
var adminDB = db.getSiblingDB('admin');

adminDB.auth('admin', 'secret');

db = db.getSiblingDB('users_db');  // Select the database 'mydatabase'

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

// Create 3 more users
db.users.insertMany([
    {
        _id: ObjectId("333333333333333333333334"),
        customerId: ObjectId("333333333333333333333334"),
        firstName: "user3",
        lastName: "user3",
        email: "user3@mail.com",
        username: "user3user",
        password: "user3pass",
        salt: "user3salt"
    },
    {
        _id: ObjectId("444444444444444444444444"),
        customerId: ObjectId("444444444444444444444444"),
        firstName: "user4",
        lastName: "user4",
        email: "user4@mail.com",
        username: "user4user",
        password: "user4pass",
        salt: "user4salt"
    },
    {
        _id: ObjectId("555555555555555555555555"),
        customerId: ObjectId("555555555555555555555555"),
        firstName: "user5",
        lastName: "user5",
        email: "user5@mail.com",
        username: "user5user",
        password: "user5pass",
        salt: "user5salt"
    }
]);

// Add 2-3 addresses for each user
db.addresses.insertMany([
    {
        _id: ObjectId("444444444444444444444445"), userId: ObjectId("333333333333333333333334"),
        street: "street4", numberS: 4, city: "city2", country: "country2", postCode: "postCode4"
    },
    {
        _id: ObjectId("444444444444444444444446"), userId: ObjectId("333333333333333333333334"),
        street: "street5", numberS: 5, city: "city2", country: "country2", postCode: "postCode5"
    },
    {
        _id: ObjectId("444444444444444444444447"), userId: ObjectId("444444444444444444444444"),
        street: "street6", numberS: 6, city: "city3", country: "country3", postCode: "postCode6"
    },
    {
        _id: ObjectId("444444444444444444444448"), userId: ObjectId("444444444444444444444444"),
        street: "street7", numberS: 7, city: "city3", country: "country3", postCode: "postCode7"
    },
    {
        _id: ObjectId("555555555555555555555556"), userId: ObjectId("555555555555555555555555"),
        street: "street8", numberS: 8, city: "city4", country: "country4", postCode: "postCode8"
    }
]);

// Add 1-2 cards for each user
db.cards.insertMany([
    {
        _id: ObjectId("444444444444444444444449"), userId: ObjectId("333333333333333333333334"),
        cardNumber: "cardNumber4", expires: "30 days later", ccv: "224"
    },
    {
        _id: ObjectId("444444444444444444444450"), userId: ObjectId("444444444444444444444444"),
        cardNumber: "cardNumber5", expires: "never", ccv: "225"
    },
    {
        _id: ObjectId("555555555555555555555557"), userId: ObjectId("555555555555555555555555"),
        cardNumber: "cardNumber6", expires: "in 2 years", ccv: "226"
    }
]);



