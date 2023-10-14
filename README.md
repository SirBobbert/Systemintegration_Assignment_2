# MP2 - Business Process Modelling and Automation

This project is part of a course focused on system integration and the practical implementation of business process modeling and automation. Our goal is to develop a system that can efficiently manage the process of organizing customer activations or handling customer complaints, using various integration patterns and good programming practices.

## Requirements

- Java Development Kit (JDK)
- Maven
- Camunda Modeler
- Spring Boot (https://start.camunda.com/)
- javamail-1.4.5
- jaf-1.1.1
- javax.mail-1.6.2

## Project Structure

- `Application.java`: This is the main entry point of the application, a Spring Boot application that initializes and starts the Spring Boot framework when executed.
- `ComplaintBusinessRule.java`: This class implements the JavaDelegate interface and checks whether a submitted complaint is sufficient based on its character length. It sets a variable and prints a message accordingly.
- `SendResolvedMail.java`: Responsible for sending an email to the user to notify them that their complaint has been resolved.
- `SendSubmissionMail.java`: Handles the task of sending a confirmation email to the user who submitted the complaint.
- `SendUnresolvedMail.java`: Sends an email if the complaint couldn't be resolved
- `process.bpmn`: Manages the process of sending an email if the complaint couldn't be resolved.
## EIP (Enterprise Integration Patterns)

In this project, we have implemented several aspects of Enterprise Integration Patterns (EIP) to facilitate the exchange of information and automation of business processes.
eIP is a set of design patterns used for solving common integration challenges in enterprise applications.
we have applied these principles in specific areas of the project:

### 1. Message Channels

**Implementation**: The use of email channels for communication.

**Description**: We employ message channels for sending emails to customers, notifying them about the status of their submitted complaints. This basic form of message channel integration allows for efficient communication with users and serves as a fundamental component in our business process automation.

### 2. Messaging Endpoints

**Implementation**: Usage of email addresses as messaging endpoints.

**Description**: In our project, email addresses play the role of messaging endpoints. We utilize these endpoints to send emails to customers, whether to confirm the receipt of their complaint, inform them about the resolution, or notify them if their complaint couldn't be resolved. Each email address acts as a unique destination for messages.

### 3. Message Router

**Implementation**: The branching logic for different email types (submission, resolved, unresolved).

**Description**: We employ message routing to determine which type of email to send based on the outcome of the complaint resolution process. The routing logic decides whether to send a submission confirmation, a resolution notification, or an unresolved complaint notification to the customer.

### 4. Message Translator

**Implementation**: Transformation of text content into email messages.

**Description**: Our code features message translation in the form of constructing email messages using StringBuilder. It converts the complaint details and resolution information into email body content to be sent to the customer.

While our implementation of EIP is limited in scope, it demonstrates how these integration patterns can be applied to streamline business processes and improve communication with customers.

## Forms

This section outlines the purpose of different forms used in the application.

1. **Form: "Your complaint wasn't sufficient enough"**
    - **Purpose:** To gather additional information about a complaint that was deemed insufficient.
    - **Key Field:** `additionalInfoToComplaint`
    - **Description:** Allows users to provide more details or context about their complaint if it was found insufficient.

2. **Form: "Is the complaint solvable?"**
    - **Purpose:** To collect user feedback on whether they believe their complaint is solvable.
    - **Key Field:** `isSolvable`
    - **Description:** Users are asked to express their opinion on the solvability of their complaint, with options for "yes" or "no."

3. **Form: "Was the complaint sufficient?"**
    - **Purpose:** To gather user feedback on whether they found their complaint to be sufficient.
    - **Key Field:** `sufficientComplaint`
    - **Description:** Users are prompted to provide feedback on the sufficiency of their complaint, with options for "yes" or "no."

4. **Form: "Resolve the complaint"**
    - **Purpose:** To collect information for resolving a complaint that was deemed sufficient.
    - **Key Field:** `resolveComplaint`
    - **Description:** If the user's complaint is found sufficient, this form is used to gather details and steps for resolving the complaint.

5. **Form: "Full name, Email, and Complaint"**
    - **Purpose:** To gather essential information when a user submits a new complaint.
    - **Key Fields:** `fullName`, `email`, and `complaint`
    - **Description:** This form collects the full name and email of the complainer, along with a description of the complaint itself.

These forms serve specific functions within our complaint handling process, helping to assess complaint sufficiency, gather information for resolution, and collect contact details for communication. Users interact with these forms based on the status and nature of their complaints.

## BPMN flow
![process](https://github.com/SirBobbert/Systemintegration_Assignment_2/assets/76921857/3f6e2513-6aba-49cd-90d3-1c2d53bf6ddc)

## Usage

1. Clone this repository.
2. Build the project using maven: `mvn clean install`
3. In GlobalVariables, enter your own email and password
4. After the build is successful, you can run the application with the following command: `java -jar target/CamundaProject-1.0.0-SNAPSHOT.jar`
5. Once the application is running, you can access it in a web browser at `http://localhost:8080/`

## Contributors

- Robert Pallesen
- Mathias Brix Drejer
- Tobias Linge Jensen
