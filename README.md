Overview
This project is a 3-tier rule engine application that determines user eligibility based on various attributes like age, department, salary, etc. The system uses an Abstract Syntax Tree (AST) to represent and evaluate conditional rules dynamically. It supports the creation, combination, and evaluation of rules using a RESTful API.
Features
Dynamically create, modify, and combine rules.
Evaluate user data against predefined rules.
Supports complex logical conditions (AND/OR) in rules.
Provides a flexible and scalable system with an intuitive API.
System Architecture
Frontend (UI): A simple UI for creating and testing rules (optional, not mandatory for the backend).
API Layer: A RESTful API to handle rule creation, combination, and evaluation.
Backend: The backend logic to parse and represent rules as AST and store them in a database.
Database: Stores rules, ASTs, and application metadata.
Data Structure
The rules are represented as an Abstract Syntax Tree (AST). Each node in the tree can be either:
An Operator Node (AND, OR)
An Operand Node (Conditions like age > 30, salary > 50000)
Example data structure for a node:
java
Copy code
public class Node {
    String type; // "operator" or "operand"
    Node left;   // left child node
    Node right;  // right child node
    String operator; // AND/OR for operator nodes
    String condition; // for operand nodes (e.g., age > 30)
    Object value;  // value for operand nodes (e.g., number for comparisons)
}
API Endpoints
1. Create Rule (POST /rules)
Creates a new rule and stores its AST representation.
Request:
json
Copy code
{
    "rule_string": "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)"
}
Response:
json
Copy code
{
    "rule_id": "rule1",
    "ast": {
        "type": "operator",
        "operator": "AND",
        "left": {
            "type": "operator",
            "operator": "OR",
            // ... (AST structure)
        },
        "right": {
            "type": "operator",
            "operator": "OR",
            // ... (AST structure)
        }
    }
}
2. Combine Rules (POST /rules/combine)
Combines multiple rules using an operator (e.g., AND, OR).
Request:
json
Copy code
{
    "rule_ids": ["rule1", "rule2"],
    "operator": "AND"
}
Response:
json
Copy code
{
    "combined_ast": {
        "type": "operator",
        "operator": "AND",
        "left": { "type": "operator", "operator": "OR", ... },
        "right": { "type": "operator", "operator": "AND", ... }
    }
}
3. Evaluate Rule (POST /rules/evaluate)
Evaluates user data against the combined AST and returns whether the user meets the criteria.
Request:
json
Copy code
{
    "rule_id": "rule1",
    "data": {
        "age": 35,
        "department": "Sales",
        "salary": 60000,
        "experience": 3
    }
}
Response:
json
Copy code
{
    "eligible": true
}
Installation
Prerequisites
Java (JDK 11+)
Maven
MongoDB (or any preferred database)
Steps to Install and Run
Clone the repository: bash Copy code   git clone https://github.com/yourusername/rule-engine-ast.git
cd rule-engine-ast
  
Install Dependencies: Run Maven to install all dependencies. bash Copy code   mvn clean install
  
Set up the Database:
For MongoDB:
Ensure MongoDB is installed and running locally or in a cloud service.
Modify the application.properties file to include the MongoDB connection details.
Run the Application: bash Copy code   mvn spring-boot:run
  
Access API:
The API will be accessible at http://localhost:8080.
Use tools like Postman to interact with the API.
Database
This application uses MongoDB to store the rules and their AST representation.
Sample MongoDB Schema
json
Copy code
{
    "rule_id": "rule1",
    "root": {
        "type": "operator",
        "operator": "AND",
        "left": {
            "type": "operator",
            "operator": "OR",
            "left": {
                "type": "operator",
                "operator": "AND",
                "left": { "type": "operand", "condition": "age > 30", "value": null },
                "right": { "type": "operand", "condition": "department = 'Sales'", "value": null }
            },
            "right": {
                "type": "operator",
                "operator": "AND",
                "left": { "type": "operand", "condition": "age < 25", "value": null },
                "right": { "type": "operand", "condition": "department = 'Marketing'", "value": null }
            }
        },
        "right": {
            "type": "operator",
            "operator": "OR",
            "left": { "type": "operand", "condition": "salary > 50000", "value": null },
            "right": { "type": "operand", "condition": "experience > 5", "value": null }
        }
    }
}
Testing
Sample Test Cases
Create Rule and Verify AST Representation
Test creating a rule using /rules endpoint and check if the AST is structured correctly.
Combine Rules and Evaluate
Combine multiple rules and test evaluating user data against the combined AST.
Evaluate Rule with Sample Data
Use sample user data to evaluate rule conditions and verify if the user is eligible.
Running Unit Tests
bash
Copy code
mvn test
Bonus Features
Error Handling: Handles invalid rule strings and missing data gracefully with error messages.
Attribute Validation: Ensures that attributes like age, salary, etc., are valid based on a predefined catalog.
Rule Modification: Supports the modification of existing rules by adding/removing conditions.
Security: Implemented basic authentication and token-based authorization (JWT).
Technologies Used
Java (Spring Boot): Backend application logic.
MongoDB: NoSQL database for storing ASTs and rule data.
Maven: Build and dependency management.
Postman: API testing tool.
