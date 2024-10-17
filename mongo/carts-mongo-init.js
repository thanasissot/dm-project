// Authenticate as the root user
// db.auth('admin','secret')

// Use admin then create user root
var adminDB = db.getSiblingDB('admin');

adminDB.auth('admin', 'secret');

db = db.getSiblingDB('carts_db');  // Select the database 'mydatabase'

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