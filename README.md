Name    : Andrew Devito Aryo

NPM     : 2306152494

Class    : Adpro-A

# Reflection 1: Coding Standards and Secure Coding
> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code
## Coding Standards
### Separation of Concern
I have structured the application into distinct layers—model, repository, service, service implementation, and controller—to ensure modularity, maintainability, and a clear responsibility for each component. This approach helps in better organization, making the codebase scalable and easier to debug or extend.

### Meaningful Names
I follow best practices for naming conventions by using descriptive and self-explanatory names for variables, methods, classes, and packages. This ensures that the purpose of each entity is clear without requiring excessive comments, improving code readability and maintainability.

### Consistent Formatting and Indentation
I adhere to Java coding standards and maintain a uniform code style with proper indentation, spacing, and line breaks. This consistency enhances code clarity, avoids unnecessary complexity, and makes collaboration easier when working in a team.

## Secure Coding
### Input Validation
I validate user input by checking for data type, length, and format to prevent common security vulnerabilities such as SQL injection, cross-site scripting, and command injection. This helps in ensuring that the application is robust against malicious attacks and user errors.

### Error Handling
I implement proper error handling mechanisms to provide informative error messages, log exceptions, and gracefully handle unexpected situations. This helps in preventing sensitive information leakage, improving user experience, and maintaining the integrity of the application.

# Reflection 2: Unit Test and Functional Test
> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? 

After writing the unit test, I feel more confident about the correctness of my code and its behavior under different scenarios. The number of unit tests in a class depends on the complexity of the class and the number of methods or functionalities it contains. Ideally, each method should have at least one unit test to cover different input cases and edge cases. To ensure that our unit tests are enough to verify our program, we can use code coverage tools to measure the percentage of code that is executed during testing. However, having 100% code coverage does not guarantee that our code has no bugs or errors. It only indicates that all lines of code are executed at least once during testing, but it does not guarantee the correctness of the tests or the absence of logical errors.

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

When creating a new functional test suite similar to the prior functional test suites, it is essential to maintain code cleanliness and avoid code duplication. The new code should follow the same structure, naming conventions, and best practices to ensure consistency and readability. However, if the new code introduces redundant setup procedures, instance variables, or repetitive test logic, it may reduce code quality and maintainability.
