Name    : Andrew Devito Aryo

NPM     : 2306152494

Class    : Adpro-A

# Reflection 3: Maintainability and OO Principles
>  Explain what principles you apply to your project!
## SOLID Principles
### Single Responsibility Principle (SRP)
I apply the SRP by ensuring that each class has a single responsibility and encapsulates a single functionality. For example, the `ProductService` class is responsible for handling product-related business logic, while the `ProductController` class is responsible for handling HTTP requests and responses. Besides that, I separate the Car and Product into different classes to ensure that each class has a clear and distinct responsibility.

### Open/Closed Principle (OCP)
I apply the OCP by designing classes that are open for extension but closed for modification. For example, I use `ModelAbstract` as a base class for `Car` and `Product` to allow for easy extension with new models in the future without modifying existing code. This approach promotes code reusability and maintainability by enabling new features to be added without altering the existing codebase.

### Liskov Substitution Principle (LSP)
I apply the LSP by ensuring that subclasses can be substituted for their base class without affecting the behavior of the program. For example, the `Car` and `Product` classes can be used interchangeably in the `ModelAbstract` class without changing the expected behavior. This principle helps in creating a consistent and predictable behavior across different subclasses.

### Interface Segregation Principle (ISP)
I apply the ISP by defining specific interfaces for different functionalities to avoid bloated interfaces. For example, the `CarService` and `ProductService` interfaces define methods specific to car and product operations, respectively. This approach ensures that classes only implement the methods they need, promoting code clarity and reducing unnecessary dependencies.

### Dependency Inversion Principle (DIP)
I apply the DIP by decoupling high-level modules from low-level modules and depending on abstractions rather than concrete implementations. For example, I use `CarService` and `ProductService` interfaces to make service instance in controller, allowing for easy swapping of implementations. This approach promotes flexibility, testability, and maintainability by reducing tight coupling between components.

> Explain the advantages of applying SOLID principles to your project with examples.
Applying SOLID principles to the project offers several advantages, including: 
1. **Improved Maintainability**: By following the SRP, classes have a single responsibility, making it easier to understand, modify, and extend the codebase. For example, the `ProductService` class is responsible for product-related logic, while the `ProductController` class handles HTTP requests and responses.
2. **Enhanced Flexibility**: By adhering to the OCP, classes are open for extension but closed for modification, allowing new features to be added without altering existing code. For example, new models can be easily added by extending the `ModelAbstract` class without changing the base functionality.
3. **Increased Reusability**: By following the LSP, subclasses can be substituted for their base class without affecting the program's behavior, promoting code reusability and consistency. For example, the `Car` and `Product` classes can be used interchangeably in the `ModelAbstract` class.

> Explain the disadvantages of not applying SOLID principles to your project with examples.
Not applying SOLID principles to the project can lead to several disadvantages, including:
1. **Increased Complexity**: Ignoring the SRP can lead to classes with multiple responsibilities, making the code harder to understand and maintain. For example, a class handling both business logic and data access can become overly complex and difficult to debug.
2. **Tight Coupling**: Disregarding the OCP can result in tightly coupled classes, making it challenging to add new features or modify existing ones without affecting other parts of the code. For instance, changes to a base class might require updates in multiple subclasses.
3. **Inconsistent Behavior**: Neglecting the LSP can cause subclasses to behave inconsistently with their base class, reducing code reliability. For example, if subclasses do not adhere to the base class's contract, they may introduce unexpected behavior or errors.

# Reflection 2: CI/CD and DevOps
> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

During the exercise, I fixed the following code quality issues:
1. **Unnecessary Public Modifier**: I removed the `public` modifier from the `ProductServiceImpl` class declaration as it is not needed for classes within the same package. This change improves encapsulation and reduces unnecessary visibility.
2. **Use Interface for Hashmap**: I refactored the `ProductRepository` implementation to use the `Map` interface instead of the `HashMap` concrete class. This change allows for easier switching between different map implementations in the future.
3. **Add MIT License**: I added the MIT license to the project repository to clarify the terms of use and distribution for the source code. This change ensures that the project is open-source and can be freely used by others.
4. **Add Dependabot Configuration**: I added a Dependabot configuration file to enable automated dependency updates for the project. This change helps in keeping dependencies up-to-date and secure by automatically checking for new versions.

> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

The current CI/CD workflows in GitHub have met the definition of Continuous Integration and Continuous Deployment. The CI workflow for testing is triggered on every push to the master branch, ensuring that changes are continuously integrated and tested. Besides that, the scoreboard, pmd, and dependabot checks are triggered on every push. The CD workflow is triggered after the CI workflow passes, automatically deploying the master branch of the application to Koyeb. This automated process helps in maintaining a consistent and reliable deployment pipeline, reducing manual errors and enabling faster delivery of new features.


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

### Encrypted UUID
I use encrypted UUIDs for product IDs to prevent information disclosure and protect sensitive data from unauthorized access. This approach enhances data security and privacy by ensuring that product identifiers are not easily predictable or tampered with.

# Reflection 2: Unit Test and Functional Test
> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? 

After writing the unit test, I feel more confident about the correctness of my code and its behavior under different scenarios. The number of unit tests in a class depends on the complexity of the class and the number of methods or functionalities it contains. Ideally, each method should have at least one unit test to cover different input cases and edge cases. To ensure that our unit tests are enough to verify our program, we can use code coverage tools to measure the percentage of code that is executed during testing. However, having 100% code coverage does not guarantee that our code has no bugs or errors. It only indicates that all lines of code are executed at least once during testing, but it does not guarantee the correctness of the tests or the absence of logical errors.

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

When creating a new functional test suite similar to the prior functional test suites, it is essential to maintain code cleanliness and avoid code duplication. The new code should follow the same structure, naming conventions, and best practices to ensure consistency and readability. However, if the new code introduces redundant setup procedures, instance variables, or repetitive test logic, it may reduce code quality and maintainability.
