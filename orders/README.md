How Orders Work
Receive new order ->
sent request to get Address for shipping
sent request to get Customer 
sent request to get Card (customer credit card data?)
sent request to get Items data (quantity + price for each in sotiroglou.athanasios.microservices.cartsq.Cart)

calculate total cost
request to Payments to make sure they've paid
Payment response = null | declined with message

Payment is OK? then 
send request to Shipment and create a shipment for the order (id, name)

Persist new CustomerOrder to db
id, customerId, Customer. Address. Card. Items, Shipment, Date(now), total

Now we need to achieve this using MQ instead of async requests