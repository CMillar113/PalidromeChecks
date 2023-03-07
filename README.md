
# Palindrome API

This RESTFUL API service allows a user to submit a string to determine whether a string is a palindrome or not.

## Installation

You have a package manager e.g. Brew

Install Maven
```bash
brew install maven
```

Build Project and Install
```bash
mvn clean install
```
Run the Application
```bash
mvn spring-boot:run
```
The applcation runs on localhost:8080 on your local machine.
The REST API has the following endpoints you can access.

Provide a Username and Input String to test whether this is a palindrome.
```bash
POST /api/palindrome/?username=Chris&input=racecar
```

The output is as follows
```bash
Hello Chris
racecar is a palindrome
```


Provide a Username and Input String to test whether this is a palindrome.


Get the cache of already submitted guesses and whether they are a palindrome.
```bash
GET /api/palindrome/cache
```

The output is as follows
```bash
{heat=false, rocks=false, racecar=true}
```    
## Running Tests

To run Unit tests, run the following command
```bash
  mvn test
```

