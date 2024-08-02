## <p align=center>**Setting up TestNG framework with Extent Report Adapter**</p>

### Table of Contents

1.  [Introduction](#1-introduction)

2. [Technical Knowledge](#2-technical-knowledge)

3. [Functional knowledge](#3-functional-knowledge)

4. [Project structure](#4-repository-structure)

5.  [Installation](#5-prerequisites-&-installation)

6.  [Configuration](#6-configuration)

7.  [Execution](#7-execution)
      
---
### 1. Introduction
This is a TestNG-based test automation framework designed for testing an e-commerce website. 
It leverages Selenium WebDriver for browser automation and Extent Reports for generating detailed test execution reports.

---
### 2. Technical Knowledge
- **Java**: A popular programming language used for building cross-platform applications. This framework is written in Java, so familiarity with basic Java concepts will be beneficial.

- **Selenium WebDriver**: A powerful tool for automating web browsers. It allows you to simulate user interactions with a web application, such as clicking buttons and entering text into fields.

- **TestNG**: A testing framework inspired by JUnit and NUnit, designed to simplify a broad range of testing needs, from unit testing to end-to-end testing. It supports test configurations, parallel execution, and data-driven testing.

- **Extent Reports**: A reporting library that provides beautiful and comprehensive reports of your test execution results. These reports include details of test steps, status, screenshots, and more.

### 3. Functional Knowledge
1. **User Authentication**
   - **Login**: Users can log in with valid credentials to access the shopping platform. Swag Labs includes various user roles, such as standard users, locked-out users, and problem users, to simulate different scenarios.
   - **Logout**: Users can log out from their accounts.

2. **Product Catalog**
   - **Product Listing**: Displays a list of products available for purchase. Each product includes an image, name, description, and price.
   - **Product Details**: Clicking on a product reveals detailed information about the item, including additional descriptions and specifications.

3. **Shopping Cart**
   - **Add to Cart**: Users can add products to their shopping cart from both the product listing and product detail pages.
   - **Remove from Cart**: Users can remove products from their cart.
   - **View Cart**: Users can view all products currently in their cart, with options to update quantities or remove items.

4. **Checkout Process**
   - **Checkout Steps**: The checkout process consists of multiple steps, including providing shipping information, selecting payment methods, and reviewing the order summary.
   - **Order Confirmation**: After completing the checkout process, users receive an order confirmation with a unique order number.

---   
### 4. Repository Structure
TestNG-Framework-Athi

├── src

│ ├── main

│ ├── test

│ │   └──java

│ │       ├── RunnerClass #Main class which has the test steps

│ │       ├── stepDefinition #Reusable methods are represented

│ │       └── extentReport #ExtentReportManager

│ ├── config #Framework configuration property file

│ └── TestReport #Test Execution Report

├── pom.xml #Maven configuration file

├── Runner.xml #TestNG configuration file

└── README.md #Project documentation


---
### 5. Prerequisites & Installation

#### Prerequisites
- **Java Development Kit (JDK)**: Ensure JDK 8 or later is installed.
- **Apache Maven**: Used for managing project dependencies and build automation.

#### Installation

1. **Clone the Repository**:
   ```git clone https://github.com/athithiyawarman-r/TestNG-Framework-Athi.git```
   
2. **Navigate to Project Directory**:
  ```cd TestNG-Framework-Athi```

3. **Install Dependencies**:
  ```mvn clean install```
  
---
### 6. Configuration
☛ Browser and Environment Configuration: Modify the config.properties file in the config directory to specify browser settings and URLs.

☛ TestNG Configuration: The Runner.xml file in the root directory specifies which tests to run and how to execute them.

### 7. Execution
☛ Extent Reports are automatically generated at the end of the test execution. The report can be found at TestReport/extent.html in the project directory.
