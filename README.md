# ShopSimulatorJava
## Simulating running a shop in Java
### Comments
This is part of a coursework I did for my Programming module. It was aimed to develop skills in UML class diagrams, file I/O,
inheritance, exceptions and basic enumerative types in Java, as wel as classes, objects, instance variables and instance methods.
### Descritption
Create a system that models customer interactions and transactions for a shop. products. Product will be an abstract base class that stores basic information
about generic products, such as the product ID, name of the product, and the base price of the product. As a result, I have to implement Food and Drink classes.

All customers in the system will have a unique customer ID, a name, and a balance. Functionality will also be required to both top-up and charge accounts 
when appropriate transactions are carried out. Anyone from the public can sign up for a standard customer account, 
but since shop operates on campus, they have agreed to special treatment of students and members of staff. 

Snack shop class should maintain its collection of products and customers. It has functionality to process transactions when passed
a customer ID and a product ID, charging a customer for a product if the customer has a sufficient balance or rejecting the sale if they do not.

This all ties together by populating a shop with customers and products by reading in a number of text files that have been provided and simulating 
in Simulate class.

### UML diagram
![UML](https://github.com/IgorSteps/ShopSimulatorJava/blob/master/UML%20diagram.png)
