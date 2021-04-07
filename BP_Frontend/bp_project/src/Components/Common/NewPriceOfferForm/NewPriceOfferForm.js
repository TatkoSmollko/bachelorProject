import React, {Component} from 'react';
import {TextField, Button} from '@material-ui/core';
import './NewPriceOfferForm.scss';
import createEmptyRoof from '../../Actions/RoofActions'

class NewPriceOfferForm extends Component{
    state = {
        heigth : 0,
        width : 0
    }

    dataToCreateEmptyRoof = {
      heigth : this.state.heigth,
      length : this.state.width,
      attics:[],
      items:[],
      chimneys:[]
    }

    componentDidMount() {
       console.log("Renderovane");  
      }

    componentDidUpdate(){
      this.dataToCreateEmptyRoof.heigth = this.state.heigth;
      this.dataToCreateEmptyRoof.length = this.state.length;
    }


    startProcessOfPriceOffer = () => {
      createEmptyRoof(this.dataToCreateEmptyRoof,this.props)
    }
      render(){

        return(
            <div className= 'parentDiv'>
              <div className ='inputFields'>
                <TextField type ="number" id="width" label="Zadajte šírku strechy" variant="outlined" onChange = {e => this.setState({heigth: e.target.value})}/>
                <TextField id="heigth" label="Zadajte výšku strechy" variant="outlined" onChange = {e => this.setState({length: e.target.value})}/>
              </div> 
              <Button variant="contained" color="default"   onClick={()=> this.startProcessOfPriceOffer()}>Začať proces cenovej ponuky</Button>
            </div>
        );

      }
}

export default NewPriceOfferForm;