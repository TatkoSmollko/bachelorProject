import React, { Component } from 'react';
import ScreenLeftSide from '../Components/Common/ScreenLeftSide/ScreenLeftSide';
import StoreScreen from '../Components/Common/Store/StoreScreen';

import './Home.scss';
import { getAllItems } from './Actions/ItemActions';

class StoreView extends Component {
  state = {
    rows: []
  };

  componentDidMount() {
    getAllItems(response => {
      const models = response;
      console.log(models);
      this.setState({
        rows: models
      });
    });
  }
  render() {
    return (
      <div className='home'>
        <ScreenLeftSide></ScreenLeftSide>
        <StoreScreen rows={this.state.rows} className='storeView'></StoreScreen>
        <div>
          {/* {' '}
          <Button className='btnUpload' color='success'>
            Nahrať položku
          </Button> */}
        </div>
      </div>
    );
  }
}

export default StoreView;
