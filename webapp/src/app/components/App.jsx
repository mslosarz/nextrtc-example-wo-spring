import React from "react";
import NextRTC from "./nextrtc/NextRTC";
import JoinConversation from "./nextrtc/actions/JoinConversation";
import CreateConversation from "./nextrtc/actions/CreateConversation";
import Broadcast from "./nextrtc/actions/create/Broadcast";
import Mesh from "./nextrtc/actions/create/Mesh";
import LeaveConversation from "./nextrtc/actions/LeaveConversation";
import OnSignal from "./nextrtc/actions/OnSignal";

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            config: {
                wsURL: 'wss://' + location.hostname + (location.port ? ':' + location.port : '') + '/videochat-wo-spring/signaling',
                mediaConfig: {
                    video: true,
                    audio: true,
                },
                peerConfig: {
                    iceServers: [
                        {urls: "stun:23.21.150.121"},
                        {urls: "stun:stun.l.google.com:19302"},
                        {urls: "turn:numb.viagenie.ca", credential: "webrtcdemo", username: "louis@mozilla.com"}
                    ],
                    iceTransportPolicy: 'all',
                    rtcpMuxPolicy: 'negotiate'
                }
            }
        };
    }

    render() {
        return (<div>
            <p> Hello React project</p>
            <NextRTC config={this.state.config}>
                <CreateConversation>
                    <Mesh/>
                    <Broadcast/>
                </CreateConversation>
                <JoinConversation/>
                <LeaveConversation/>
                <OnSignal/>
            </NextRTC>
        </div>);
    }
}

export default App;