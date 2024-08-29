# WebProgramCharter
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points). Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.

 +-----------------+
  |   Transaction   |
  |   Records       |
  |   (Input Data)  |
  +-----------------+
           |
           v
  +-----------------+
  |   API Endpoint  |
  |  /processTransactions  |
  +-----------------+
           |
           v
  +-----------------+
  |   Process       |
  |   Transactions  |
  |   (Batch)       |
  +-----------------+
           |
           v
  +-----------------+
  |   Calculate     |
  |   Points        |
  +-----------------+
           |
           v
  +-----------------+
  |   Store Points  |
  |   (Monthly)     |
  +-----------------+
           |
           v
  +-----------------+
  |   API Endpoint  |
  |  /generateMonthlyReport |
  +-----------------+
           |
           v
  +-----------------+
  |   Generate      |
  |   Monthly Report|
  +-----------------+
           |
           v
  +-----------------+
  |   API Endpoint  |
  |  /generateTotalReport |
  +-----------------+
           |
           v
  +-----------------+
  |   Generate      |
  |   Total Report  |
  +-----------------+
           |
           v
  +-----------------+
  |   Output        |
  |   Reports       |
  +-----------------+

Here’s a step-by-step approach, including a stack data structure that we can use to manage transactions if needed (though a stack is not strictly required for this specific problem, it may be useful for certain operations such as backtracking or undoing operations).

Transaction Data Structure: Represent each transaction with relevant details such as amount and customer ID.
Point Calculation Logic: Implement the logic to calculate points based on the given rules.
Data Aggregation: Aggregate points per customer per month and calculate the total.
Java Implementation
Transaction Class: Define a class to hold transaction details.
implemented the method to calculate reward points.
Aggregated points per customer per month and total.

Explanation
Transaction Class: Holds the transaction details such as customer ID, amount, and date.
RewardsCalculator: Contains the logic for calculating points based on the amount spent.
RewardsProgram: Main class that aggregates points:
Transactions: A list of transaction records is created.
Monthly and Total Points: Points are aggregated for each customer per month and in total.
Output: The results are printed to show the points earned by each customer per month and in total.


To develop a system for calculating reward points based on transactions in Java, you can leverage a range of Java technologies and libraries. Below is an overview of the Java technologies that can be used to implement the described rewards program, from basic Java features to more advanced tools:

1. Core Java

Java Collections Framework: For managing and processing collections of transactions.
Java Streams API: For processing collections of transactions in a functional style.
Java Date and Time API: For handling transaction dates and periods.

2. Java Enterprise Edition 

Java Persistence API (JPA): For managing database transactions and storing transaction records.
Spring Framework: For creating a more sophisticated application, especially if you need a web interface or additional features like dependency injection and transaction management.

3. Database Technologies

JDBC (Java Database Connectivity): For interacting with relational databases.
JPA/Hibernate: For ORM (Object-Relational Mapping) to interact with databases using Java objects.

4. Web Technologies 
Spring Boot: To create RESTful APIs or web services for managing transactions and retrieving rewards.

5. Testing Technologies

JUnit: For unit testing the reward calculation logic and other components.
Mockito: For mocking dependencies and testing components in isolation.
Spring Test: For testing Spring Boot applications and RESTful endpoints.

Core Java: Implement the Transaction class and reward calculation logic in plain Java.
JPA/Hibernate: Use to persist and retrieve transaction records from a database.
Spring Boot: Create RESTful endpoints for submitting transactions and retrieving reward reports.
JUnit/Mockito: Write and run unit tests to verify the correctness of the reward calculation logic.
