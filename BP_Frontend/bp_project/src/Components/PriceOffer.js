import React, { Component } from "react";
import ScreenLeftSide from '../Components/Common/ScreenLeftSide/ScreenLeftSide';
import './Home.scss';
import NewPriceOfferForm from './Common/NewPriceOfferForm/NewPriceOfferForm';

 
class PriceOffer extends Component {
  render() {
    return (
      <div className='home'>
        <ScreenLeftSide ></ScreenLeftSide>
        <NewPriceOfferForm></NewPriceOfferForm>
      </div>
    );
  }
}
 
export default PriceOffer;