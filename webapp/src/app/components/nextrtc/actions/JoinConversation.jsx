import React from "react";

class JoinConversation extends React.Component {
    constructor(props) {
        super(props);
        // props.client.join(convId);
    }

    render() {
        return <p>Join: {this.props.client}</p>;
    }
}

export default JoinConversation;