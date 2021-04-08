import { Button } from "@material-ui/core";
import React, { Component } from "react";
import Menu from "../Menu/Menu"
import "./ScreenLeftSide.scss";
import "../Menu/Menu.scss";
import Logout from "../../Actions/Logout";
import logo from "../../../img/logo.png"

const handleLogout = () => {
  localStorage.clear();
    Logout(
      () => {
        console.log("Success");
      },
      () => {
        console.log("401")
      }
    );
}



 
class ScreenLeftSide extends Component {
  render() {
    return (
      <div className='ScreenLeftSide'> 
          <div className='brand-logo'>
                <img className='logo' src={logo} alt='App Logo' />
              </div>
          <Menu className = "menuComponentStyle"></Menu>
          <Button variant="contained" color="secondary" className ='logoutButton' onClick= {handleLogout}>
          Logout
          </Button>
    </div>
      
    );
  }
}
 
export default ScreenLeftSide;