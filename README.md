# TDL

This project is a Full stack Web application which acts as a system for creating and manipulating a To Do List or short for TDL, which will allow us to add, edit, read and delete to dos. The application was created using Spring Boot and It has been tested using Unit, Integration and Selenium tests.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to first use the application for running it and testing it, We need to have **Java**(v1.8 onward), **MySql**, **Eclipse**(recommended), **Visual Studio Code**(reccommended), **Spring Boot** and **Maven** installed on our computer. Links for downloading them are provided on the **Built With** section down below.

### Installing

In order to get our envirnoment setup ready, we need to follow the below instructions.

(1) Clone this repository to your local machine.

(2) Open the backend folder with Eclipse IDE.

(3) After the project is loaded, you should be able to see it on the **Package Explorer** window placed on the left hand side.

(4) Expand the project folder and you will be able to see a series of folders. The main ones that we need to focus are:

* src/main/java --> It is where our application code resides.
 
* src/main/resources --> We keep the resources needed for our main java code, for example our database properties connection.

* src/test/java --> It is where all the testing resides

(5) Right-click the project folder and click **Run As** > **Spring Boot App**

(6) The Spring Boot App will start the server on port 8080.

(7) After that, It is time to open the front-end part of our application and run it on a browser. To do so, open the project folder from visual studio code. Go to **index.html**, right click from the document and select **Open With Live server**(This takes into account, your visual studio code has the extension of live server installed).


(8) The application will open from the browser and we are ready to play with it. You will be presented with a Homepage and a To Do list page.

(9) The Home page is just indicating to click onto the To Do List page as it is the heart of this application.

![HomePNG](https://user-images.githubusercontent.com/56220535/128322064-5849ed47-b1ff-4131-8c21-d521a406f9af.PNG)

(10) The To Do List page will present a dropdown menu, which will allows us to:
* Add a to do
* Read one to do
* Read all to dos
* Update a to do
* Delete a to do

![Todolistpage](https://user-images.githubusercontent.com/56220535/128323163-a5e1343a-8b87-4668-b9de-8f2c2f2b6f9a.PNG)


When we choose an option from the menu we will be displayed a form to fill in order to complete our CRUD operation as it is displayed below.

![createToDo](https://user-images.githubusercontent.com/56220535/128323685-db0396af-b101-4a7b-ba92-5fe0b122e427.PNG)

Or if we select to display all to dos, we will be presented with something similiar to this:

![retrieveAllToDo](https://user-images.githubusercontent.com/56220535/128323876-1897d57a-9185-48ef-b670-b4d34b6584f9.PNG)


## Running the tests

The application present Unit tests, Integration tests and Selenium tests. It can be tested using JUnit, which is a dependency of Maven. Unit tests have been used to test the services. Meanwhile Integration tests have been used to test the controllers. Selenium will test the front-end of our application. To run all tests just right-click onto the project folder from **Package Explorer** and click on **Run As** > **JUnit Test**. There are 29 tests executed in total.

![JUnit and Integration tests](https://user-images.githubusercontent.com/56220535/128327092-304b25e3-fffb-40ad-affd-6d8d92d9f084.PNG)

The tests have a coverage of more than 80% which is the standard for tests to be accepted.

![AllTestCoverage](https://user-images.githubusercontent.com/56220535/128327503-a7fb6714-edc2-4de4-a544-242f1fe0e3e1.PNG)

### Unit Tests 

To run only Unit tests we can right click onto any services located in our test folder, and click on **Run As** > **JUnit Test**. You will find that the tests files are named after the class they will be testing followed by **Test** keyword, as JUnit will recognize it in this way. 

### Integration Tests 

To run Integration tests we can right click onto the controller package located in our test folder, and click on **Run As** > **JUnit Test**. 

### Selenium Tests 

To run Selenium tests using chrome web driver we can right click onto the selenium package located in our test folder, and click on **Run As** > **JUnit Test**. Selenium tests cover from checking a logo and clicking a button to click on dropdown menu and verify that functionalities display us what it is expected.

![Selenium Tests](https://user-images.githubusercontent.com/56220535/128326573-7de8e24a-14a4-4389-9589-f458dff3d57b.PNG)

### SonarQube tests

SonarQube is used for reports on bugs, code quality, vulnerabilities, code duplications and code smells. In order to run a test with SonarQube we need to clear our backend first. So, let's open Git bash from our project path(Be sure to be in the backend side) and type the following:

```
java clean package
```
This will also run tests we have set up and produce a JAR file. Wait for it to finish. You need to install SonarQube Community Edition from this [link](https://www.sonarqube.org/downloads/) if you do not have it yet.
After the clean is done, we need to type the following:

```
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=login -Dsonar.password=password
 ```
Substitute the login and password with your personal login details and press enter. Now we are able to access the test from *localhost:9000*. 

## Built With

* [Maven](https://maven.apache.org/) - Build Tool and Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - API Development Platform
* [Java](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html) - Back-End Programming Language
* [MySql](https://www.mysql.com/downloads/) - Database
* [Eclipse](https://www.eclipse.org/downloads/) - IDE
* [Visual Studio Code](https://code.visualstudio.com/) - Code Editor
* [Git](https://git-scm.com/downloads) - Version Control
* [Jira](https://www.atlassian.com/) - Software helping managing the work
* [HTML5, CSS, JS] - Front-End Web Technologies

## Versioning

We use [Git](https://git-scm.com/downloads) for versioning.

## Authors

* **Marco Castellana** 

## Acknowledgments

* Thank you to my teachers who have been helpful at helping me solve some issues.
* Thank you to Stack overflow which is always helpful when dealing with programming issues.