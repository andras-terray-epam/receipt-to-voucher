use receipt-to-voucher
db.dropDatabase()
use receipt-to-voucher
db.user.createIndex( { "email": 1 }, { unique: true } )