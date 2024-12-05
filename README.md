# FitnessApp

A Java-based desktop fitness application built using JavaFX, designed to help users track their calorie intake, exercise, and manage their personal fitness goals. The app provides features for logging food, exercises, and monitoring progress with real-time updates on calorie consumption and burn.

## Features

- **User Authentication**: Simple login and registration system.
- **Calorie Goal Management**: Set and modify a calorie goal.
- **Food Tracker**: Add food items and track calories consumed, with the ability to search and manually input food items.
- **Exercise Tracker**: Log exercises and track calories burned based on duration.
- **Real-Time Progress**: View progress through dynamic progress bars for calories consumed and calories burned.
- **Personalization**: Display personalized greetings and allow users to edit their name.
- **Database Integration**: The app uses a database to manage food and exercise data, allowing for persistent tracking across sessions.

## Technologies Used

- **Java 11+**: The core programming language.
- **JavaFX**: For building the user interface.
- **Maven**: For project dependency management.
- **SQL Server**: For storing and retrieving food and exercise data.
- **JDBC**: For database connectivity.
- **Scene Builder**: Used for designing the application’s FXML layout files.

## Required Software & Tools

Before you begin, ensure you have the following installed:

1. **Java 11 or later**  
   You can download it from [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
   
2. **Apache Maven**  
   Download Maven from [Maven's official website](https://maven.apache.org/).

3. **JavaFX SDK**  
   Download the JavaFX SDK from [Gluon](https://gluonhq.com/products/javafx/), and add it to your project as an external library in your build tool.

4. **Scene Builder**  
   Download Scene Builder from [Gluon](https://gluonhq.com/products/scene-builder/).

5. **SQL Server**  
   - Install [SQL Server](https://www.microsoft.com/en-us/sql-server/sql-server-downloads).
   - Set up a database named `fitness_app` with tables for `food` and `exercise`.

6. **JDBC Driver for SQL Server**  
   - Download the JDBC driver from [Microsoft's official site](https://docs.microsoft.com/en-us/sql/connect/jdbc/build-microsoft-jdbc-driver-for-sql-server).
   - Include the JDBC driver in your project as a dependency in `pom.xml`:

     ```xml
     <dependency>
         <groupId>com.microsoft.sqlserver</groupId>
         <artifactId>mssql-jdbc</artifactId>
         <version>10.2.0.jre11</version>  <!-- Or the latest version -->
     </dependency>
     ```

## Installation

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/yourusername/FitnessApp.git

2. Open the project in Visual Studio Code.

3. Ensure that you have Java 11 or later, Maven, JavaFX, Scene Builder, and the JDBC driver installed.

4. Configure your SQL Server:
- Create the fitness_app database.
- Create tables food and exercise with the appropriate fields (food_name, calories_per_serving, exercise_name, calories_burned).

5. JDBC Connection: Set up a connection to the database using JDBC.

6. Add JavaFX as a dependency in the pom.xml file:
   ```xml
     <dependency>
          <groupId>org.openjfx</groupId>
          <artifactId>javafx-controls</artifactId>
          <version>17</version>  <!-- Use the version you downloaded -->
      </dependency>
  ```
7. To run the project, simply execute the following Maven command in the terminal:
  ```bash
        mvn javafx:run
  ```

Contributions
Feel free to fork the repository, open issues, and submit pull requests for any improvements or fixes.

License
This project is licensed under the MIT License - see the LICENSE file for details.
