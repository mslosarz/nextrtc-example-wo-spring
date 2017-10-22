import React from "react";

class LeaveConversation extends React.Component {
    constructor(props) {
        super(props);
        // props.client.join(convId);
    }

    render() {
        return <p>Leave: {this.props.client}</p>;
    }
}

export default LeaveConversation;