# books-management
PeerIsland Order Management Service
It is a web based RESTful API for a fictional order service that can perform the
following functions:
1. Create an order
2. Retrieve order details: The system should allow fetching order details by order ID.
3. Update order status
4. List all orders: Retrieve all orders, optionally filtered by status
5. Cancel an order: Customers should be able to cancel an order, but only if it’s still in PENDING status

***Step to run the Application : ***
First maven build the app and run the PeerIsland-Order-Service app

I am using H2 embedded database
url=jdbc:h2:mem:peerIslandDB
username=peer
password=peer12345

## Link

[Swagger-url](http://localhost:8081/swagger-ui/index.html)

