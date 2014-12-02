Test of using a Websocket client within a JavaFX client application.

This is a JavaFX maven plugin built project.
Information on using the JavaFX maven plugin is at:

 * [https://github.com/zonski/javafx-maven-plugin](https://github.com/zonski/javafx-maven-plugin)

This project requires Oracle Java 8u20+ and Maven 3.2.3+

How to checkout this project:

    git clone https://github.com/jewelsea/javafx-websocket-test.gi

How to build this project:

    mvn com.zenjava:javafx-maven-plugin:8.1.2:web

To run the resultant application as a standalone jar:

    java -jar target/jfx/app/javafx-websocket-test-jfx.jar

To run the resultant application as webstart app:

    javaws target/jfx/web/javafx-websocket-test.jnlp
