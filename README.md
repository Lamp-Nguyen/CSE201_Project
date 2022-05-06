# CSE201 Project Documentation

This is the documentation for our product, Business+ Index. The application is a catalog of businesses that allows users to search for a specific and sort through the businesses in the records, as well as adding their own businesses.  

## Installation
Make sure you have the latest version of JDK downloaded (preferably[ JDK 17.0.3](https://www.oracle.com/java/technologies/downloads/#java17)) before installing and running the program.

The runnable JAR file for the application can be downloaded from this Google Drive [link](https://bit.ly/3w3OY1V). 

## User Instruction

### 1/ Running the application

To run the application, run the JAR executable file.

***If the file is being opened with WinRAR or similar application, right click the JAR file, select "Open with", then select JAVA(TM) Platform SE Binary.***

***Or, create a .bat file with a text editor and type in the command prompt bellow:***
```bash
java -jar <File_Name>.jar
```

### 2/ User manual
The application is split into two main parts, the function panels on the left and the businesses display on the right. Each business will contain the business's **name, type, rating, date established, expense category,** and the **contact information**.

To login to an existing account, click on the login button seen on the user panel (upper left corner). Enter the correct username and the corresponding password to login. The user panel will be updated accordingly if login was successful. Once signed in, the user can click the log out button to log out of their current account, which will return the user panel back to its orginal state.

To sign up for a new account, click on the sign up button seen on the user panel. Enter your desired username, which has to be unique from the existing accounts, and a password for the account. If the username is unique and the password confirmation matches your typed-in password, the account will be created and you can now log in to that account.

To search for a specific business by name, type the text into the search bar then click search. The display will be updated accordingly.

To sort the businesses by a particular category, choose the corresponding radio button for that category. The businesses can be sort by the following fields  **name, date established, rating,** and **number**. Select from the drop down box right below if you wish to sort the list by ascending or descending order.
***(Search attempts will reset the sort selection.)***

To add a business to the catalog, the user must first login to an existing account. The business *must* contain the following fields to be valid: **name, date established,** and **owner name**. If the entry is valid, the business will be added to the display.

## External libraries and code reference:
[JDatePicker](https://jdatepicker.org/): the components used to select a date for adding the business. Included in a project as a Maven dependency and is used in the AddBusinessFrame class.

[Hashing and Hash validation functions](https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/): This code can be seen in the class Hash, which is modified to be a Singleton. The purpose of the class is to hash a password string and to validate whether a hashed password and a string password are the same.

[Flat Look & Feel (FlatLaF)](https://github.com/JFormDesigner/FlatLaf#applications-using-flatlaf): The Look & Feel used for the application. Included in a project as a Maven dependency and is used in the MetaApp class to set the Look & Feel configurations before creating the GUI.

[Amazon Web Services (AWS) JDBC Driver for MySQL](https://github.com/awslabs/aws-mysql-jdbc#amazon-web-services-aws-jdbc-driver-for-mysql): The Driver used to get the connection from the java application to the database hosted on Amazon AWS. Included in a project as a Maven dependency and is used in the ConnectionManager class to make the database URL valid.

## Contributors
Tim Haller - Project Manager

Christopher Tung - Technical Manager

Brooke Blackwell - Programmer 

Elton Zeng - Creator of Project's Diagrams 

Lam Nguyen - Programmer
