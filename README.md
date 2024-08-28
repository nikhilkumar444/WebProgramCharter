# WebProgramCharter
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.


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
