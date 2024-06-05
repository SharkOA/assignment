# Video Streaming API by Yehor Sharko

This project implements an API for a video streaming service that allows you to publish videos, manage metadata, 
track user interaction statistics with videos, and retrieve these data.


This application currently only has one Controller, Service and Repository each, 
since we store the engagement parameters in the Video class. 
In the future, if we store engagement parameters as separate entities (for example, likes or user reactions), 
then we will need to separate these classes into the existing VideoController and EngagementController, etc.

At this stage, I did not use multithreading, asynchronous methods, or queues, 
since the application is not designed for heavy loads. 
In the future, if we expect high loads and data volumes, 
it would be good to redesign the application architecture.

I chose the H2 database for this project because it's lightweight, 
easy to configure, and easy to test your application with. 
In the future, you can switch to any other relational database depending on your needs.

Since each video can have multiple genres, actors and directors, 
I decided to make these tables have a many-to-many relationship.

All code is covered with Unit tests written using JUnit and Mockito.

## Setup instructions

### Requirements

- JDK 17 or higher
- Installed Maven

### Setup steps

1. Clone the repository
    ```sh
    ssh git clone https://github.com/SharkOA/assignment.git
    cd assignment
   
2. Build the project with Maven
    ```sh
   mvn clean install
   
3. Run the project
    ```sh
   mvn spring-boot:run
   
### API usage
The API will be available at http://localhost:8080. 
Any HTTP client, such as Postman or cURL, can be used to interact with it.

### Testing
To run the tests, use the command:
```sh
mvn test
