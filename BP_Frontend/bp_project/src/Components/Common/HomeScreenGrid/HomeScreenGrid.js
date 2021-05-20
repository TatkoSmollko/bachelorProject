import React, { useState, useEffect } from 'react';
import './HomeScreenGrid.scss';
import { DataGrid } from '@material-ui/data-grid';
import { getAllPriceOffers } from '../../Actions/PriceOfferActions';

const columns = [
  { field: 'id', headerName: 'Meno zákazníka', width: 300 },
  { field: 'status', headerName: 'Status cenovej ponuky', width: 250 },
  { field: 'customerName', headerName: 'Adresa zákazníka', width: 250 }
  // { field: 'age', headerName: 'Suma s prácou', width: 250 }
];

export default function HomeScreenGrid() {
  const [rows, setRows] = useState([]);

  useEffect(() => {
    getAllOffers();
  }, []);

  const getAllOffers = () => {
    getAllPriceOffers(response => {
      setRows(response);
    });
  };

  return (
    <div className='gridDiv' style={{ height: '80%', width: '95%' }}>
      <DataGrid rows={rows} columns={columns} pageSize={15} checkboxSelection />
    </div>
  );
}
