package org.nextrtc.examples.videochat;

import org.nextrtc.signalingserver.api.NextRTCServer;
import org.nextrtc.signalingserver.domain.Connection;
import org.nextrtc.signalingserver.domain.InternalMessage;
import org.nextrtc.signalingserver.domain.Signal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint(value = "/signaling")
public class MyEndpoint {

    private static final Logger log = LoggerFactory.getLogger(MyEndpoint.class);
    private static final NextRTCServer server = buildServer();

    private static NextRTCServer buildServer() {
        return NextRTCApp.serverInstance(NextRTCServer.create(configuration -> {
            configuration.nextRTCProperties().setPingPeriod(1);
            configuration.nextRTCProperties().setJoinOnlyToExisting(true);

            configuration.signalResolver().addCustomSignal(Signal.fromString("upperCase"), (msg) ->
                    configuration.messageSender().send(InternalMessage.create()
                            .to(msg.getFrom())
                            .signal(Signal.fromString("upperCase"))
                            .content(msg.getContent() == null ? "" : msg.getContent().toUpperCase())
                            .build()));

            configuration.eventDispatcher().addListener(new CustomHandler());
            return configuration;
        }));
    }

    private static class SessionWrapper implements Connection {

        private final Session session;

        public SessionWrapper(Session session) {
            this.session = session;
        }

        @Override
        public String getId() {
            return session.getId();
        }

        @Override
        public boolean isOpen() {
            return session.isOpen();
        }

        @Override
        public void sendObject(Object object) {
            try {
                session.getBasicRemote().sendText(NextRTCServer.MessageEncoder.encode(object));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void close() throws IOException {
            session.close();
        }
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        log.info("Opening: " + session.getId());
        server.register(new SessionWrapper(session));
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.debug("Handling message from: " + session.getId());
        server.handle(NextRTCServer.MessageDecoder.decode(message), new SessionWrapper(session));
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        log.info("Closing: " + session.getId() + " with reason: " + reason.getReasonPhrase());
        server.unregister(new SessionWrapper(session), reason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable exception) {
        log.error("Occured exception for session: " + session.getId() + ", reason: " + exception.getMessage());
        log.debug("Endpoint exception: ", exception);
        server.handleError(new SessionWrapper(session), exception);
    }

}
