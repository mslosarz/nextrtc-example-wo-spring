import React from "react";

class NextRTC extends React.Component {
    constructor(props) {
        super(props);
        // this.state = {client: new NRTC.NextRTC(props.config)};
        this.state = {client: "noga"};
    }

    render() {
        let that = this;
        const items = React.Children.map(this.props.children, (Item) => {
            return React.cloneElement(Item, {client: that.state.client});
        });
        return (<div>
            {items}
        </div>);
    }

}


export default NextRTC;