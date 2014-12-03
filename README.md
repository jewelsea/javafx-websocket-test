*Overview*

Sample of using a WebSocket client within a JavaFX client application.

The project makes use of the [Tyrus WebSocket framework](https://tyrus.java.net).

*Description*

Creates a scene where the user can input their name, then submit
a request to a WebSocket server, which will respond with `Hello <name>`,
the output of which is recorded in a label on the scene.

As this is built to be a self-contained demonstration application,
a local WebSocket server is started when the application starts up and
the local server is shutdown when the application is stopped.

*Implementation Notes*

 1. Websocket communication occurs within an asynchronously executed JavaFX [Task](http://docs.oracle.com/javase/8/javafx/api/javafx/concurrent/Task.html).
 2. WebSocket endpoints are defined as annotated endpoints as documented in the specification for [JSR-356: Java API for WebSockets](https://jcp.org/en/jsr/detail?id=356).
 3. A new server connection is created for each communication task (i.e. connection resources are not shared or reused between tasks).
 4. This implementation is not proxy aware.  For information on making the system proxy aware see the [Tyrus proxy documentation](https://tyrus.java.net/documentation/1.9/user-guide.html#d0e1323).
 5. For high traffic communication it would be recommended to use a different implementation
which reuses such resources, for example:

    * Using a [Tyrus shared container](https://tyrus.java.net/documentation/1.9/user-guide.html#d0e1215) OR
    * Creating a persistent connection which is used for server calls (probably in conjunction with a the [Tyrus client reconnect facility](https://tyrus.java.net/documentation/1.9/user-guide.html#d0e1311)).      

*Sample Screenshot*

![image](https://raw.githubusercontent.com/jewelsea/javafx-websocket-test/master/screenshot.png)

*Build Requirements*

This project requires Oracle Java 8u20+ and Maven 3.2.3+.

*Checkout*

    git clone https://github.com/jewelsea/javafx-websocket-test.git

*Build*

    mvn com.zenjava:javafx-maven-plugin:8.1.2:web

*Execution*

To run the resultant application as a standalone jar:

    java -jar target/jfx/app/javafx-websocket-test-jfx.jar

To run the resultant application as webstart app:

    javaws target/jfx/web/javafx-websocket-test.jnlp
    
*Attribution*

This is a JavaFX Maven Plugin built project.
Information on using the JavaFX Maven Plugin is at:

 * [https://github.com/zonski/javafx-maven-plugin](https://github.com/zonski/javafx-maven-plugin)

A skeleton for this project was generated using the [JavaFX Maven Plugin Quickstart Archetype](http://zenjava.com/javafx/maven/basic-archetype.html), then significantly modified after that.

