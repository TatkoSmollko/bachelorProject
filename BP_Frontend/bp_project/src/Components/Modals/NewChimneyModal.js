import React, {useState} from "react";
import {Modal, ModalHeader,ModalBody, ModalFooter, Button, Form, FormGroup, Label, Input} from "reactstrap";
import createChimney from "../Actions/ChimneyActions"


 const AddChimney = (props) =>{
    const [chimneyWidth, setWidth] = useState(0);      
    const [chimneyHeight, setHeigth] = useState(0); 

    const dataToCreateChimney={
      width:chimneyWidth,
      heigth:chimneyHeight,
      roof:{
           id: localStorage.getItem('newRoofId')
     }
    }

    const handleUpload = () => {
        createChimney(dataToCreateChimney,props);
    }


    return (
      
        <Modal isOpen={props.isOpen}>
          <ModalHeader toggle={props.toggleNewFileModal()}>
            <strong>
             Zadajte rozmery pre pridávanie komina:
            </strong>
          </ModalHeader>
          <ModalBody>
          <Form>
      <FormGroup>
        <Label for="width">Width</Label>
        <Input type="text" name="width" id="width" onChange = {e => setWidth(e.target.value)} placeholder="Sirka komina" />
        <Label for="lenth">Heigth</Label>
        <Input type="text" name="Heigth" id="length" onChange = {e => setHeigth(e.target.value)} placeholder="Dlzka komina" />
      </FormGroup>
   
    </Form>
          </ModalBody>
          <ModalFooter>
          <Button onClick={()=>handleUpload()} color="success" >
                  Upload
                </Button>
            <Button
              color='danger'
              onClick={ props.toggleNewFileModal()}
            >
              Cancel
            </Button>
           
          </ModalFooter>
        </Modal>
      );
}  

export default AddChimney;