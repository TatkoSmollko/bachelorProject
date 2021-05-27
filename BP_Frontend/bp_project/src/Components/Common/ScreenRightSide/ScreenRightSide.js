import React, { Component } from 'react';
import HomeScreenGrid from '../HomeScreenGrid/HomeScreenGrid';
import './ScreenRightSide.scss';

class ScreenRightSide extends Component {
  render() {
    return (
      <div className='ScreenRightSide'>
        <HomeScreenGrid></HomeScreenGrid>
      </div>
    );
  }
}

export default ScreenRightSide;
