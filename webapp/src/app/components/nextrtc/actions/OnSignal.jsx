import React from "react";

class OnSignal extends React.Component {
    constructor(props) {
        super(props);
        // props.client.join(convId);
    }

    render() {
        return <div>
            <p>ONSIGNAL: {this.props.name}</p>
            <button onClick={this.props.execute}>Press me</button>
        </div>;
    }
}

export default OnSignal;