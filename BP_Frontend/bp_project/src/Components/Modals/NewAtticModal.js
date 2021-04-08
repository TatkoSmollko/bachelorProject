import React, {useState} from "react";
import {Modal, ModalHeader,ModalBody, ModalFooter, Button, Form, FormGroup, Label, Input} from "reactstrap";


 const AddAttic = (props) =>{
  
    const [frontHeight,setfrontHeight] = useState(0);
    const [rareHeight,setrareHeight] = useState(0);
    const [width, setWidth] = useState(0);      
    const [length, setLength] = useState(0); 
// AddNewFile(data, props, f, addNewFileSuccess, addNewFileError)
    const handleUpload = () => {
        console.log("clikam")
    }
    return (
      
        <Modal isOpen={props.isOpen}>
          <ModalHeader toggle={props.toggleNewFileModal()}>
            <strong>
             Zadajte rozmery pre pridávanie atiky:
            </strong>
          </ModalHeader>
          <ModalBody>
          <Form>
      <FormGroup>
        <Label for="frontHeigth">Front Heigth</Label>
        <Input type="text" name="frontHeigth" id="frontHeigth" onChange = {e => setfrontHeight(e.target.value)} placeholder="Predna vyska atiky" />
        <Label for="rareHeigth">Rare Heigth</Label>
        <Input type="text" name="rareHeigth" id="rareHeigth" onChange = {e => setrareHeight(e.target.value)} placeholder="Zadna vyska atiky" />
        <Label for="width">Width</Label>
        <Input type="text" name="width" id="width" onChange = {e => setWidth(e.target.value)} placeholder="Sirka strechy" />
        <Label for="lenth">Length</Label>
        <Input type="text" name="length" id="length" onChange = {e => setLength(e.target.value)} placeholder="Sirka strechy" />
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

export default AddAttic;