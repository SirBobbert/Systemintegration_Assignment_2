# MP2 - Business Process Modelling and Automation

This project is part of a course focused on system integration and the practical implementation of business process modeling and automation. Our goal is to develop a system that can efficiently manage the process of organizing customer activations or handling customer complaints, using various integration patterns and good programming practices.

## Requirements

- Java Development Kit (JDK)
- Maven
- Camunda Modeler
- Spring Boot (https://start.camunda.com/)
- javamail-1.4.5
- jaf-1.1.1

## Project Structure

- `Application.java`: This is the main entry point of the application, a Spring Boot application that initializes and starts the Spring Boot framework when executed.
- `ComplaintBusinessRule.java`: This class implements the JavaDelegate interface and checks whether a submitted complaint is sufficient based on its character length. It sets a variable and prints a message accordingly.
- `SendResolvedMail.java`: Responsible for sending an email to the user to notify them that their complaint has been resolved.
- `SendSubmissionMail.java`: Handles the task of sending a confirmation email to the user who submitted the complaint.
- `SendUnresolvedMail.java`: Sends an email if the complaint couldn't be resolved
- `process.bpmn`: Manages the process of sending an email if the complaint couldn't be resolved.

## Usage

1. Clone this repository.
2. Build the project using maven: `mvn clean install`
3. After the build is successful, you can run the application with the following command: `java -jar target/CamundaProject-1.0.0-SNAPSHOT.jar`
4. Once the application is running, you can access it in a web browser at `http://localhost:8080/`

## Contributors

- Robert Pallesen
- Mathias Brix Drejer
- Tobias Linge Jensen

## TODO

- Business Rule Task
- Explanation of BPMN flow
- "bug" at the second use of `camunda-forms:/forms/Is-Complaint-Solvable-Form.form` at Manager Review
- Explanation of forms
- Implementation of EIP's (?)
