# Bank App
Banking Application where the users can register, conduct transactions, uses geolocater api to locate city and state/province through postal code.

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 18.1.4.

## Table of Contents

1. [Introduction](#introduction)
2. [Detailed Explanation of Each Page](#detailed-explanation-of-each-page)
3. [Installation and Setup](#installation-and-setup)

---

## Introduction

**BankApplicationFE** is a front-end application (bank teller system) built with Angular for managing bank customers and their account information. The application features include creating, viewing, updating, and deleting customers, as well as creating and viewing their accounts.

---

## Detailed Explanation of Each Page

### 1. **Home Page**
   - The landing page of the application that displays a welcome message and navigation bar.

   ![Home Page](images/home-page.png)

### 2. **Navigation Bar**
   - Provides navigation between different pages of the application. It directs the user to the Register and View Customers pages.

   ![Navigation Bar](images/nav-bar.png)

### 3. **Create Customer and Account Page**
   - Allows the user to create a new customer and simultaneously create one or more savings and checking accounts for that customer. The page includes fields for customer details like name, type, and address, as well as account details like account type and balance. Additionally, based on the account type, it offers fields like "Next Check Number" or "Interest Rate".

   ![Create Customer and Account](images/create-customer-and-accounts-page.png)

### 4. **Account Creation Success Alert**
   - A success alert that appears once an account is successfully created.

   ![Account Creation Success Alert](images/accounts-alert-once-created.png)

### 5. **Checking Account Creation**
   - A form inside the Create Customer page specifically for creating checking accounts, including fields for balance and next check number.

   ![Checking Account Creation](images/checking-account-create.png)

### 6. **Savings Account Creation**
   - A form inside the Create Customer page specifically for creating savings accounts, including fields for balance and interest rate.

   ![Savings Account Creation](images/savings-account-create.png)

### 7. **Search by Customer ID**
   - Allows users to search for customers by their ID, displaying all customers whose ID equals or includes the input query. If no specific ID is provided, all customers will be displayed to simplify user navigation.

   ![Search by ID](images/search-by-id.png)

### 8. **View Customers Page**
   - Displays a list of all customers with their details, allowing actions such as viewing and editing customer information. You can access a customer's accounts by clicking on the "View Accounts" button. If it is disabled, it means that the specific customer has no accounts registered in the system.

   ![View Customers Page](images/view-customers-page.png)

### 9. **View Accounts Page**
   - Displays a list of accounts associated with a selected customer, including account type and other details. You can access this page by clicking the "View Accounts" button on the "View Customers" page.

   ![View Accounts Page](images/view-accounts-page.png)

### 10. **Update Customer Page**
   - Allows the user to update the details of an existing customer, including their name, type, and address. When clicked, the form is auto-filled with the selected customer's details retrieved from the database. There is also a red "trash" icon, which deletes the existing customer.

   ![Update Customer Page](images/update-customer-page.png)

### 11. **Delete Customer Alert**
   - A confirmation alert that appears when a customer is deleted.

   ![Delete Customer Alert](images/once-delete-clicked.png)

### 12. **Update Customer Alert**
   - A confirmation alert that appears when a customer's information is successfully updated.

   ![Update Customer Alert](images/once-update-clicked.png)

### 13. **Page Not Found**
   - A 404 error page that appears when the user navigates to a non-existent route. Used a sample page from codepen.

   ![Page Not Found](images/page-not-found.png)

---

## Installation and Setup

To get started with this project, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://git.fdmgroup.com/Sai.Vennala/BankApplicationFE.git
   cd BankApplicationFE

2. **Open the project location on terminal (make sure Angular with Node.JS is set up)**
   ```bash
   ng serve
