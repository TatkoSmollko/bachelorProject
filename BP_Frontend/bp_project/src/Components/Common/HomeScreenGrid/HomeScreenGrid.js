import React, { useState, useEffect } from 'react';
import './HomeScreenGrid.scss';
import { DataGrid } from '@material-ui/data-grid';
import { getAllPriceOffers } from '../../Actions/PriceOfferActions';
import PriceOfferUpdateModal from '../../Modals/PriceOfferUpdateModal';

const columns = [
  { field: 'customerName', headerName: 'Meno zákazníka', flex: 0.5 },
  { field: 'status', headerName: 'Status cenovej ponuky', flex: 0.5 }
];

export default function HomeScreenGrid() {
  const [rows, setRows] = useState([]);
  const [data, setData] = useState({});
  const [isOpenPriceOfferModal, setIsOpenPriceOfferModal] = useState(false);

  const togglePriceOfferModal = () => {
    setIsOpenPriceOfferModal(!isOpenPriceOfferModal);
  };

  useEffect(() => {
    getAllOffers();
  }, []);

  const getAllOffers = () => {
    if (isOpenPriceOfferModal) {
      setIsOpenPriceOfferModal(!isOpenPriceOfferModal);
    }
    getAllPriceOffers(response => {
      setRows(response);
    });
  };

  const handleRowClick = e => {
    setData(e.row);
    togglePriceOfferModal();
    console.log(data);
  };

  return (
    <div className='gridDiv' style={{ height: '80%', width: '95%' }}>
      <DataGrid rows={rows} columns={columns} pageSize={15} onRowClick={e => handleRowClick(e)} />
      <PriceOfferUpdateModal
        getOffers={() => getAllOffers}
        toggleModal={() => togglePriceOfferModal}
        isOpen={isOpenPriceOfferModal}
        priceOffer={data}
      />
    </div>
  );
}
