package org.nextrtc.examples.videochat;

import org.apache.log4j.Logger;
import org.nextrtc.signalingserver.api.ConfigurationBuilder;
import org.nextrtc.signalingserver.api.NextRTCEndpoint;
import org.nextrtc.signalingserver.codec.MessageDecoder;
import org.nextrtc.signalingserver.codec.MessageEncoder;

import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/signaling",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class)
public class MyEndpoint extends NextRTCEndpoint {

    private static final Logger log = Logger.getLogger(MyEndpoint.class);

    protected NextRTCEndpoint manualConfiguration(ConfigurationBuilder builder) {
        log.info("Manual configuration done");
        return builder.createDefaultEndpoint().build();
    }

}
