package org.jewelsea.websocket.sample.client;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.glassfish.tyrus.client.ClientManager;
import org.jewelsea.websocket.sample.server.HelloServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Wraps communication with a WebSocket endpoint in a JavaFX Service.
 */
public class HelloService extends Service<String> {
    private static final Logger log = LoggerFactory.getLogger(HelloService.class);

    private final StringProperty name = new SimpleStringProperty(this, "name");

    /**
     * The name property is set as an input parameter for a service execution.
     * @return the name property.
     */
    public final StringProperty nameProperty() { return name; }
    public final void setName(String value) { name.set(value); }
    public final String getName() { return name.get(); }

    @Override
    protected Task<String> createTask() {
        return new HelloTask(getName());
    }
}
