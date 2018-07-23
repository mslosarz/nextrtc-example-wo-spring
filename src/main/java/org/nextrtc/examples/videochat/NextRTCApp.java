package org.nextrtc.examples.videochat;

import org.nextrtc.signalingserver.api.NextRTCServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class NextRTCApp implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(MyEndpoint.class);
    private static NextRTCServer server;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Context initialized!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (NextRTCApp.server != null) {
                log.info("Stopping NextRTC Server!");
                NextRTCApp.server.close();
            }
        } catch (IOException e) {
            log.error("Context couldn't be destroyed!: " + e.getMessage());
            throw new RuntimeException(e);
        }
        log.info("Context destroyed without error!");
    }

    public static NextRTCServer serverInstance(NextRTCServer server) {
        log.info("Instance registered!");
        NextRTCApp.server = server;
        return server;
    }
}
