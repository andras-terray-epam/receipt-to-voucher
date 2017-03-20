use receipt-to-voucher

//drop db with all used data
db.dropDatabase()

//create db
use receipt-to-voucher

//make email unique
db.user.createIndex(
    { "email": 1 },
    { unique: true }
)

//create admin user
db.user.insertOne(
{
    "_id" : "4891597c-72b7-4b45-9f99-6471756admin",
    "firstName" : "Admin",
    "lastName" : "Admin",
    "email" : "admin@admin.com",
    "password" : "pass",
    "role": "admin"
});
