import React, {Component} from 'react';
import {TextField, Button} from '@material-ui/core';
import './NewPriceOfferForm.scss';
import createEmptyRoof from '../../Actions/RoofActions';
import AddAttic from '../../Modals/NewAtticModal';
import createAttic from '../../Actions/AtticActions';
import AddChimney from '../../Modals/NewChimneyModal';
import finishPriceOffer from '../../Actions/PriceOfferActions'

class NewPriceOfferForm extends Component{
  roofId = "";
    state = {
        heigth : 0,
        width : 0,
        isOpenAddAttic: false,
        isOpenAddChimney: false,
        id: ""
    }
    toggleNewModelModal = () => {
      this.setState(
        {
          isOpenAddAttic:!this.state.isOpenAddAttic
        }
      )
    }

    toggleNewChimneyModal = () => {
      this.setState(
        {
          isOpenAddChimney:!this.state.isOpenAddChimney
        }
      )
    }
  
    dataToCreateEmptyRoof = {
      heigth : this.state.heigth,
      length : this.state.width,
      attics:[],
      items:[],
      chimneys:[]
    }

    componentDidMount() {

      }

    componentDidUpdate(){
      this.dataToCreateEmptyRoof.heigth = this.state.heigth;
      this.dataToCreateEmptyRoof.length = this.state.length;
    }

    updateIdRoof = () => {
      this.setState({id:localStorage.getItem("newRoofId")
      });
      console.log(this.state.id);
    }

    handleFinishPriceOfferProces = (roofId, customer) =>{
      finishPriceOffer(roofId,customer);
    }

    handleCreationOfAttic = () => {
      createAttic()
    }

    startProcessOfPriceOffer = () => {
      createEmptyRoof(this.dataToCreateEmptyRoof,this.props,this.updateIdRoof())
    }
      render(){

        return(
            <div className= 'parentDiv'>
              <div className ='inputFields'>
                <TextField type ="number" id="width" label="Zadajte šírku strechy" variant="outlined" onChange = {e => this.setState({heigth: e.target.value})}/>
                <TextField id="heigth" label="Zadajte výšku strechy" variant="outlined" onChange = {e => this.setState({length: e.target.value})}/>
              </div> 
              <Button variant="contained" color="default"   onClick={()=> this.startProcessOfPriceOffer()}>Začať proces cenovej ponuky</Button>
              <Button className="uploadButton" variant="contained" color="default"  onClick= {()=> this.toggleNewModelModal()}>Pridať atiku</Button>
              {this.state.isOpenAddAttic && <AddAttic isOpen={this.state.isOpenAddAttic} toggleNewFileModal= {()=> this.toggleNewModelModal}/>}
              <Button className="uploadButton" variant="contained" color="default"  onClick= {()=> this.toggleNewChimneyModal()}>Pridať komín</Button>
              {this.state.isOpenAddChimney && <AddChimney isOpen={this.state.isOpenAddChimney} toggleNewFileModal= {()=> this.toggleNewChimneyModal}/>}
              <Button className="uploadButton" variant="contained" color="default"  onClick= {()=> this.handleFinishPriceOfferProces(localStorage.getItem('newRoofId'),"Customer")}>FinishProcess</Button>
            </div>
        );

      }
}

export default NewPriceOfferForm;