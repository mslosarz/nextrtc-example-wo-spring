import React from "react";

class OnSignal extends React.Component {
    constructor(props) {
        super(props);
        // props.client.join(convId);
    }

    render() {
        return <p>ON: {this.props.client}</p>;
    }
}

export default OnSignal;