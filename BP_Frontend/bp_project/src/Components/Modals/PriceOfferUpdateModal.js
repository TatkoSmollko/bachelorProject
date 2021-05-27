import React, { useState } from 'react';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button, Label } from 'reactstrap';
import { updatePriceOffer } from '../Actions/PriceOfferActions';
import '../Home.scss';

const PriceOfferUpdateModal = props => {
  const handleClick = (status, priceOfferId) => {
    updatePriceOffer(priceOfferId, { id: priceOfferId, status: status }, props.getOffers());
  };
  return (
    <Modal isOpen={props.isOpen}>
      <ModalHeader toggle={props.toggleModal()}>
        <strong>Cenová ponuka</strong>
      </ModalHeader>
      <ModalBody>
        <div>
          <Label>
            <strong>Meno zákazníka:</strong> {props.priceOffer.customerName}{' '}
          </Label>
        </div>
        <div>
          <label>
            <strong>Cena:</strong> {props.priceOffer.price} €
          </label>
        </div>
        <div>
          <div>
            {' '}
            <label>
              <strong>Status cenovej ponuky:</strong>
            </label>
          </div>

          <Button
            className='navbuttons'
            variant='contained'
            color='info'
            size='lg'
            onClick={() => handleClick('NEW', props.priceOffer.id)}
          >
            Nová
          </Button>
          <Button
            className='navbuttons'
            variant='contained'
            color='success'
            size='lg'
            onClick={() => handleClick('ACCEPTED', props.priceOffer.id)}
          >
            Akceptovaná
          </Button>
          <Button
            className='navbuttons'
            variant='contained'
            color='danger'
            size='lg'
            onClick={() => handleClick('DECLINED', props.priceOffer.id)}
          >
            Zamietnutá
          </Button>
        </div>
      </ModalBody>
      <ModalFooter>
        <Button color='danger' onClick={props.toggleModal()}>
          Cancel
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export default PriceOfferUpdateModal;
