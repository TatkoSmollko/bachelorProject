import React, { Component } from 'react';
import { TextField, Button } from '@material-ui/core';
import './NewPriceOfferForm.scss';
import createEmptyRoof from '../../Actions/RoofActions';
import AddAttic from '../../Modals/NewAtticModal';
import { createAttic, getAllAtticsByRoofId } from '../../Actions/AtticActions';
import AddChimney from '../../Modals/NewChimneyModal';
import finishPriceOffer from '../../Actions/PriceOfferActions';
import { getAllChimneysByRoofId } from '../../Actions/ChimneyActions';

class NewPriceOfferForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      heigth: 0,
      width: 0,
      isOpenAddAttic: false,
      isOpenAddChimney: false,
      customerName: '',
      roofId: '',
      atiky: [],
      chimneys: []
    };
  }

  toggleNewAtticModal = () => {
    this.setState({
      isOpenAddAttic: !this.state.isOpenAddAttic
    });
  };

  toggleNewChimneyModal = () => {
    this.setState({
      isOpenAddChimney: !this.state.isOpenAddChimney
    });
  };

  getChimneysByRoofId = () => {
    this.toggleNewChimneyModal();
    getAllChimneysByRoofId(this.state.roofId, response => {
      this.setState({ chimneys: response.data });
    });
  };

  getAtikyByRoofId = () => {
    this.toggleNewAtticModal();
    getAllAtticsByRoofId(this.state.roofId, response => {
      this.setState({ atiky: response.data });
    });
  };

  // componentDidMount() {}

  // componentDidUpdate() {
  //   this.dataToCreateEmptyRoof.heigth = this.state.heigth;
  //   this.dataToCreateEmptyRoof.length = this.state.length;
  // }

  // updateIdRoof = () => {
  //   this.setState({ id: localStorage.getItem('newRoofId') });
  //   console.log(this.state.id);
  // };

  handleFinishPriceOfferProces = (roofId, customer) => {
    finishPriceOffer(roofId, customer, () => {
      this.setState({ roofId: '', atiky: [], width: '', height: '', customerName: '' });
    });
  };

  handleCreationOfAttic = () => {
    createAttic();
  };

  startProcessOfPriceOffer = () => {
    createEmptyRoof(
      { heigth: this.state.heigth, length: this.state.width, attics: [], items: [], chimneys: [] },
      response => {
        this.setState({ roofId: response });
      }
    );
  };
  render() {
    return (
      <div className='parentDiv'>
        {this.state.roofId === '' ? (
          <div>
            <div className='inputFields'>
              <TextField
                type='number'
                id='width'
                label='Zadajte šírku strechy'
                variant='outlined'
                onChange={e => this.setState({ width: e.target.value })}
              />

              <TextField
                type='number'
                id='heigth'
                label='Zadajte výšku strechy'
                variant='outlined'
                onChange={e => this.setState({ heigth: e.target.value })}
              />
            </div>
            <div style={{ display: 'inline-grid' }}>
              <TextField
                type='text'
                id='customerName'
                label='Zadajte meno zákazníka'
                variant='outlined'
                onChange={e => this.setState({ customerName: e.target.value })}
              />
              <Button
                variant='contained'
                color='default'
                onClick={() => this.startProcessOfPriceOffer()}
              >
                Začať proces cenovej ponuky
              </Button>
            </div>
          </div>
        ) : (
          <div style={{ display: 'flex', height: '400px', textAlign: 'center' }}>
            <div style={{ width: '30%', marginTop: '20px' }}>
              <div className='info-field'>
                <b>Meno zakaznika: </b>
                {this.state.customerName}
              </div>
              <div className='info-field'>
                <b>Šírka strechy:</b>
                {this.state.width}
              </div>
              <div className='info-field'>
                <b>Dĺžka strechy:</b>
                {this.state.heigth}
              </div>

              <div className='updateButt'>
                <Button
                  className='uploadButton'
                  variant='contained'
                  color='default'
                  onClick={() =>
                    this.handleFinishPriceOfferProces(this.state.roofId, this.state.customerName)
                  }
                >
                  Dokončiť proces
                </Button>
              </div>
            </div>

            <div style={{ width: '35%' }}>
              <div className='atiky'>
                <h2>ATIKY</h2>
                <Button
                  className='uploadButton'
                  variant='contained'
                  color='default'
                  onClick={() => this.toggleNewAtticModal()}
                >
                  Pridať atiku
                </Button>
                <div style={{ overflow: 'auto', heigth: '250px' }}>
                  {this.state.atiky.map(atika => (
                    <div key={atika.id}>{atika.id}</div>
                  ))}
                </div>
              </div>
            </div>
            <div style={{ width: '35%' }}>
              <div className='atiky'>
                <h2>KOMÍNY</h2>
                <Button
                  className='uploadButton'
                  variant='contained'
                  color='default'
                  onClick={() => this.toggleNewChimneyModal()}
                >
                  Pridať komín
                </Button>
                <div style={{ overflow: 'auto', heigth: '250px' }}>
                  {this.state.chimneys.map(chimney => (
                    <div key={chimney.id}>{chimney.id}</div>
                  ))}
                </div>
              </div>
            </div>
          </div>
        )}
        {this.state.isOpenAddAttic && (
          <AddAttic
            getAtikyByRoofId={() => this.getAtikyByRoofId()}
            roofId={this.state.roofId}
            isOpen={this.state.isOpenAddAttic}
            toggleNewAtticModal={() => this.toggleNewAtticModal}
          />
        )}

        {this.state.isOpenAddChimney && (
          <AddChimney
            getAllChimneysByRoofId={() => this.getChimneysByRoofId()}
            roofId={this.state.roofId}
            isOpen={this.state.isOpenAddChimney}
            toggleNewChimneyModal={() => this.toggleNewChimneyModal}
          />
        )}
      </div>
    );
  }
}

export default NewPriceOfferForm;
