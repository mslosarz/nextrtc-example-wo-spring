package org.nextrtc.examples.videochat;

import org.apache.log4j.Logger;
import org.nextrtc.signalingserver.api.NextRTCEvents;
import org.nextrtc.signalingserver.api.NextRTCHandler;
import org.nextrtc.signalingserver.api.annotation.NextRTCEventListener;
import org.nextrtc.signalingserver.api.dto.NextRTCEvent;

@NextRTCEventListener({NextRTCEvents.SESSION_OPENED, NextRTCEvents.MEMBER_JOINED, NextRTCEvents.MEMBER_LEFT})
class A implements NextRTCHandler {
    private static final Logger log = Logger.getLogger(A.class);

    @Override
    public void handleEvent(NextRTCEvent event) {
        log.info("REACTION ON EVENT:" + event);
    }
}
