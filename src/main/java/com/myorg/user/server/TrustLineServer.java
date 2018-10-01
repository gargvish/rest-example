package com.myorg.user.server;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

import com.myorg.user.model.User;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.HostAndPort;
import com.myorg.user.config.TrustLineContext;

/**
 * Standalone Embedded Trustline Jetty server
 * <pre>
 *     - takes the userName and endPoint as input in initialize the Trustline Context
 *     - sets up the resource and injection paths
 * </pre>
 * 
 * @author vg
 * @since Oct 2018
 */
public class TrustLineServer {
    private static final Logger logger = LoggerFactory.getLogger(TrustLineServer.class);
    private static final String PATH = "/api/v1/*";

    private static final String PROVIDER_PATH = "jersey.config.server.provider.packages";
    private static final String RESOURCE_PATH = "com.myorg.user.rest";

    private static final String APP_PATH = "javax.ws.rs.Application";
    private static final String APP_INJECT_PATH = "com.myorg.user.app.TrustLineApp";

    public static void main(String[] args) {

        if (args.length < 2) {
            logger.error("invaid parameters");
            return;
        }
        final String name = args[0];
        final String localEndPoint = args[1];

        HostAndPort local = HostAndPort.fromString(localEndPoint);

        final User user = new User();
        user.setUserName(name);
        user.setEndPoint(localEndPoint);
        final TrustLineContext config = TrustLineContext.initialize(user);
        Server server = createServer(local);

        try {
            logger.info("Starting -- server: " + server + " user: " + config.getUser());
            server.start();

            logger.info("[Trustline] Balance: " + 0);

            logger.info("Join -- server: " + server + " user: " + config.getUser());
            server.join();

        }
        catch (Exception ex) {
            logger.error("Error occurred while starting Jetty", ex);
            System.exit(1);
        }

        finally {
            server.destroy();
        }
    }

    public static Server createServer(final HostAndPort hostAndPort) {
        final ServletContextHandler handler = createContextHandler();
        Server server = new Server(hostAndPort.getPort());
        server.setHandler(handler);
        logger.info("Initializing -- server: " + server + " port: " + hostAndPort.getPort());
        return server;
    }

    public static ServletContextHandler createContextHandler() {
        ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
        servletContextHandler.setContextPath("/");
        ServletHolder servletHolder = servletContextHandler.addServlet(ServletContainer.class, PATH);
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter(PROVIDER_PATH, RESOURCE_PATH);
        servletHolder.setInitParameter(APP_PATH, APP_INJECT_PATH);
        return servletContextHandler;
    }
}
