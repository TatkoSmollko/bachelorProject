import React, {useState} from "react";
import {Modal, ModalHeader,ModalBody, ModalFooter, Button, Form, FormGroup, Label, Input} from "reactstrap";
import {AddNewFile} from "../Actions/DocumentActions";




 const NewModel = (props) =>{
    const [file, setFile] = useState(null);
    const [title,setTitle] = useState(String);
    const [isSelected, setIsSelected] = useState(false);      
// AddNewFile(data, props, f, addNewFileSuccess, addNewFileError)
    const handleUpload = () => {
        AddNewFile({title}, props, file,
             () => {props.getAllModels()},
             (err,status) => {console.log(err,status)}
            );
    }
    return (
        <Modal isOpen={props.isOpen}>
          <ModalHeader toggle={props.toggleNewFileModal()}>
            <strong>
             Upload new xml model 
            </strong>
          </ModalHeader>
          <ModalBody>
          <Form>
      <FormGroup>
        <Label for="title">Title</Label>
        <Input type="text" name="title" id="title" onChange = {e => setTitle(e.target.value)} placeholder="Set the title of xmlModel" />
      </FormGroup>
   
    </Form>
          <Input type="file" onChange={(event) => {setFile(event.target.files[0]); setIsSelected(true)}} />
          </ModalBody>
          <ModalFooter>
          <Button onClick={()=>handleUpload()} color="success" disabled={!isSelected} >
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

export default NewModel;