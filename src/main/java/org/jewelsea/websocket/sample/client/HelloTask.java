package org.jewelsea.websocket.sample.client;

import javafx.concurrent.Task;
import org.glassfish.tyrus.client.ClientManager;
import org.jewelsea.websocket.sample.server.HelloServer;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeoutException;

/**
 * Wraps communication with a WebSocket endpoint in a JavaFX Task.
 *
 * This allows the communication to occur asynchronously to the JavaFX application thread,
 * so the JavaFX application thread is not blocked by the communication process.
 *
 * A new server connection is created for each communication task.
 * (i.e. connection resources are not shared or reused between tasks).
 * For high traffic communication it would be recommended to use a different implementation
 * which reuses such resources.
 */
public class HelloTask extends Task<String> {
    private static final String SERVER_ENDPOINT_ADDRESS =
            HelloServer.SERVER_ADDRESS + "/hello";

    private final String name;

    /**
     * Creates a new task for server communication.
     * @param name the request name to send to the server.
     */
    public HelloTask(String name) {
        this.name = name;
    }

    /**
     * Sends the requested name passed in the Task constructor to the server endpoint.
     * A new connection is established for the request.
     *
     * @return the response from the server.
     * @throws IOException if there was an error communication with the server.
     * @throws TimeoutException if communication with the server timed out before a response was received.
     */
    @Override
    protected String call() throws IOException, TimeoutException {
        String response = null;

        HelloClientEndpoint clientEndpoint = new HelloClientEndpoint(
                name
        );

        try {
            ClientManager client = ClientManager.createClient();
            client.connectToServer(
                    clientEndpoint,
                    URI.create(SERVER_ENDPOINT_ADDRESS)
            );

            response = clientEndpoint.getResponse();
        } catch (DeploymentException e) {
            throw new IOException(e);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }

        return response;
    }

}
