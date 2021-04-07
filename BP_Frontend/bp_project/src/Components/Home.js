import React, { Component } from "react";
import ScreenLeftSide from "./Common/ScreenLeftSide/ScreenLeftSide";
import ScreenRightSide from "./Common/ScreenRightSide/ScreenRightSide";
import './Home.scss';

 
class Home extends Component {
  render() {
    return (
      <div className= 'home'>
        <ScreenLeftSide></ScreenLeftSide>
        <ScreenRightSide></ScreenRightSide>
      </div>
    );
  }
}
 
export default Home;