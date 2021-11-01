## Cart Kata

### Requirements
* An IDE or installation of maven
* An installation of java (17 is recommended)

### Running The application:

The easiest way to run the application is by using an IDE. Alternatively you can use maven to build and run the
application.

To run the main application run:
```mvn compile exec:testCompile```
in the base directory.

To run the tests run ```mvn test``` in the base directory

### Configuration

The configuration for this application can be found in main/resources/Config.json It contains the following fields:

* "ItemPath": The path to where items are stored
* "OfferPath": The path to where offers are stored
* "ItemSKU": The identifying field for item SKUs when retrieving items and offers
* "ItemPrice": The price of an item
* "OfferQuantity": The number of items required for a special offer
* "OfferPrice": The special price for a special offer

### Assumptions:

* The CartApplication merely exists to serve as a proof of concept to show that the functions work.
* Each item currently requires up to one special offer, however in the future this may change.
* If an invalid item is input by a user we do not want to terminate the transaction
* If an error occurs due to bad items/offers being loaded in then we do want to terminate the transaction
* Items and offers are stored/retrieved in a simple json format

### Future actions:

* Remove the "CartConsole class". Replace it with a webserver which would present the user with a basic web form that
would allow them to do the same functions as the "CartConsole"