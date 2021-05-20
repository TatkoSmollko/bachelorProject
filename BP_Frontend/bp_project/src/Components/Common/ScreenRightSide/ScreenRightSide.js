import React, { Component } from 'react';
import HomeScreenGrid from '../HomeScreenGrid/HomeScreenGrid';
import TableNavbar from '../TableNavbarButtons/TableNavbarButton';
import './ScreenRightSide.scss';

class ScreenRightSide extends Component {
  render() {
    return (
      <div className='ScreenRightSide'>
        <div className='navbarTable'>
          <TableNavbar></TableNavbar>
        </div>
        <HomeScreenGrid></HomeScreenGrid>
      </div>
    );
  }
}

export default ScreenRightSide;
