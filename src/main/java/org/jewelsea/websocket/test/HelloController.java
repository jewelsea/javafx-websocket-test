package org.jewelsea.websocket.test;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.lang.StringUtils;
import org.glassfish.tyrus.client.ClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HelloController
{
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private Label messageLabel;

    public void sayHello() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        StringBuilder builder = new StringBuilder();

        if (!StringUtils.isEmpty(firstName)) {
            builder.append(firstName);
        }

        if (!StringUtils.isEmpty(lastName)) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(lastName);
        }

        if (builder.length() > 0) {
            String name = builder.toString();
            log.debug("Saying hello to " + name);
            messageLabel.setText(sendHello(name));
        } else {
            log.debug("Neither first name nor last name was set, saying hello to anonymous person");
            messageLabel.setText(sendHello("mysterious person"));
        }
    }

    private String sendHello(final String name) {
        // For production code, it is best to do this as a concurrent task,
        // but doing it inline on the JavaFX thread is OK for test purposes.

        final SimpleStringProperty response = new SimpleStringProperty("Undefined");

        try {
            final CountDownLatch messageLatch = new CountDownLatch(1);

            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

            ClientManager client = ClientManager.createClient();
            client.connectToServer(new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    try {
                        session.addMessageHandler(new MessageHandler.Whole<String>() {
                            @Override
                            public void onMessage(String message) {
                                log.debug("Received response: "+ message);
                                response.set(message);
                                messageLatch.countDown();
                            }
                        });
                        session.getBasicRemote().sendText(name);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, cec, new URI("ws://localhost:8025/websocket/hello"));

            messageLatch.await(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.get();
    }

}
