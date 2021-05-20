import React, { useState } from 'react';
import {
  Modal,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Button,
  Form,
  FormGroup,
  Label,
  Input
} from 'reactstrap';
import { updateItem } from '../Actions/ItemActions';

const ItemUpdateModal = props => {
  const [count, setCount] = useState(0);
  const [price, setPrice] = useState(0);
  const [size, setSize] = useState(0);

  const dataToUpdateIdem = {
    id: props.row.id,
    count: count,
    price: price,
    store: {
      id: '237a6132-6cbb-11eb-9439-0242ac130002'
    },
    size: size
  };

  const handleUpload = () => {
    updateItem(props.row.id, dataToUpdateIdem, () => props.getAllItems());
  };

  return (
    <Modal isOpen={props.isOpen}>
      <ModalHeader toggle={() => props.toggleUpdateModal()}>
        <strong>Zadajte potrebné údaje o položke:</strong>
      </ModalHeader>
      <ModalBody>
        <Form>
          <FormGroup>
            <Label for='count'>Počet na sklade</Label>
            <Input
              type='text'
              name='count'
              id='count'
              onChange={e => setCount(e.target.value)}
              placeholder={props.row.count}
            />
            <Label for='price'>Jednotková cena</Label>
            <Input
              type='text'
              name='price'
              id='price'
              onChange={e => setPrice(e.target.value)}
              placeholder={props.row.price}
            />
            <Label for='size'>Veľkosť</Label>
            <Input
              type='text'
              name='size'
              id='size'
              onChange={e => setSize(e.target.value)}
              placeholder={props.row.size}
            />
          </FormGroup>
        </Form>
      </ModalBody>
      <ModalFooter>
        <Button onClick={() => handleUpload()} color='success'>
          Update
        </Button>
        <Button color='danger' onClick={() => props.toggleUpdateModal()}>
          Cancel
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export default ItemUpdateModal;
