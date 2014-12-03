package org.jewelsea.websocket.sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.lang.StringUtils;
import org.jewelsea.websocket.sample.client.HelloTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML controller for the application.
 */
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    private static final String DEFAULT_NAME = "mysterious person";

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label messageLabel;

    /**
     * Event handler invoked when the user submits their name.
     *
     * Invokes an asynchronous task which will communicate
     * the user's name to the server and update the message
     * label with the server's response.
     */
    @FXML
    private void sayHello() {
        messageLabel.setText("");

        String name = createFullName();

        HelloTask helloTask = new HelloTask(name);

        helloTask.setOnSucceeded(event -> {
            log.debug(
                    "Said hello to " + name + ", response " + helloTask.getValue()
            );

            messageLabel.setText(
                    helloTask.getValue()
            );
        });

        helloTask.setOnFailed(event ->
            log.error(
                    "Unable to say hello to " + name,
                    helloTask.getException()
            )
        );

        helloTask.start();
    }

    /**
     * Helper function which constructs the user's full name
     * from their input first and last names.
     *
     * @return the user's full name (or a default name if the user has not entered any name).
     */
    private String createFullName() {
        StringBuilder builder = new StringBuilder();

        String firstName = firstNameField.getText();
        String lastName  = lastNameField.getText();

        if (!StringUtils.isEmpty(firstName)) {
            builder.append(firstName);
        }

        if (!StringUtils.isEmpty(lastName)) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(lastName);
        }

        if (builder.length() == 0) {
            builder.append(DEFAULT_NAME);
        }

        return builder.toString();
    }

}
