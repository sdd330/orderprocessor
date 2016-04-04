# Order Processing System
A demo project for order processing system

## Usage

To start the order processing system:
1. Install docker tool box 1.10.x
2. Clone the project and run:

	gradle build
	docker-compose up

To submit a new order

	http://[DockerIP]:9000/orders/new

To query order status

	http://[DockerIP]:9000/orders/query?orderId=[orderId]


