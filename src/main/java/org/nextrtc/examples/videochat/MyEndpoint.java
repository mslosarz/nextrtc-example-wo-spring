package org.nextrtc.examples.videochat;

import org.apache.log4j.Logger;
import org.nextrtc.signalingserver.api.ConfigurationBuilder;
import org.nextrtc.signalingserver.api.EndpointConfiguration;
import org.nextrtc.signalingserver.api.NextRTCEndpoint;
import org.nextrtc.signalingserver.codec.MessageDecoder;
import org.nextrtc.signalingserver.codec.MessageEncoder;
import org.nextrtc.signalingserver.domain.InternalMessage;
import org.nextrtc.signalingserver.domain.Signal;

import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/signaling",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class)
public class MyEndpoint extends NextRTCEndpoint {

    private static final Logger log = Logger.getLogger(MyEndpoint.class);

    protected EndpointConfiguration manualConfiguration(ConfigurationBuilder builder) {
        log.info("Manual configuration done");
        EndpointConfiguration configuration = builder.createDefaultEndpoint();

        configuration.nextRTCProperties().setPingPeriod(1);
        configuration.nextRTCProperties().setJoinOnlyToExisting(true);

        configuration.signalResolver().addCustomSignal(Signal.fromString("upperCase"), (msg) -> InternalMessage.create()
                .to(msg.getFrom())
                .signal(Signal.fromString("upperCase"))
                .content(msg.getContent() == null ? "" : msg.getContent().toUpperCase())
                .build().send());

        configuration.eventDispatcher().addListener(new CustomHandler());
        return configuration;
    }

}
