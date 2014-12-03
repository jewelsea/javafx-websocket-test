package org.jewelsea.websocket.sample.server;

import org.glassfish.tyrus.server.Server;
import org.jewelsea.websocket.sample.JavaFXWebsocketDemoApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.DeploymentException;

/** A WebSocket server for handling hello message requests. */
public class HelloServer {
    private static final Logger log = LoggerFactory.getLogger(JavaFXWebsocketDemoApp.class);

    private static final String SERVER_HOSTNAME = "localhost";
    private static final int SERVER_PORT = 8025;
    private static final String SERVER_CONTEXT_PATH = "/websocket";

    private Server server;

    public static final String SERVER_ADDRESS =
            "ws://" + SERVER_HOSTNAME + ":" + SERVER_PORT + SERVER_CONTEXT_PATH;

    /**
     * Starts the server executing.
     *
     * @throws DeploymentException if there was an error starting the server and
     *                             deploying the server websocket endpoint to it.
     */
    public void start() throws DeploymentException {
        try {
            log.info("Starting server for " + SERVER_ADDRESS);

            server = new Server(
                    SERVER_HOSTNAME,
                    SERVER_PORT,
                    SERVER_CONTEXT_PATH,
                    null,
                    HelloServerEndpoint.class
            );

            server.start();
        } catch (DeploymentException e) {
            server = null;
            throw e;
        }
    }

    /**
     * Shuts down the server.
     */
    public void stop() {
        if (server != null) {
            log.info("Stopping server for " + SERVER_ADDRESS);

            server.stop();
        }
    }
}
