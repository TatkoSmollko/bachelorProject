import React, { Component } from "react";
import "./TableNavbarButton.scss";
import Button from '@material-ui/core/Button';
import ButtonGroup from '@material-ui/core/ButtonGroup';
 
class TableNavbarButton extends Component {
  render() {
    return (
      <div className='TableNavbar' >
        <ButtonGroup fullWidth='true' className='navbarButton' color="primary" aria-label="outlined primary button group">
            <Button>Čakajúce</Button>
            <Button>Potvrdené</Button>
            <Button>Archívované</Button>
        </ButtonGroup>
    </div>
      
    );
  }
}
 
export default TableNavbarButton;