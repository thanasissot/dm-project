// Authenticate as the root user
var dbName = 'mydatabase';
// db.auth('admin','secret')

// Use admin then create user root
var adminDB = db.getSiblingDB('admin');

adminDB.auth('admin', 'secret');

db = db.getSiblingDB('orders_db');  // Select the database 'mydatabase'

db.createCollection('orders');