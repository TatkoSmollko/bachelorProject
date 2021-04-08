import React from "react";
import "./HomeScreenGrid.scss";
import { DataGrid } from '@material-ui/data-grid';

const columns = [
  { field: 'id', headerName: 'Meno zákazníka', width: 300  },
  { field: 'firstName', headerName: 'Status cenovej ponuky',width: 250 },
  { field: 'lastName', headerName: 'Adresa zákazníka', width: 250  },
  {field: 'age', headerName: 'Suma s prácou',width: 250 },
 // {field: 'fullName',headerName: 'Full name',description: 'This column has a value getter and is not sortable.',sortable: false,width: 160,valueGetter: (params) =>
    //  `${params.getValue('firstName') || ''} ${params.getValue('lastName') || ''}`,
 // },
];

const rows = [
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
  { id: 'Štefan', lastName: 'Bratislava 33', firstName: 'Nová', age: '1000€' },
];

export default function HomeScreenGrid() {
  return (
    <div className= "gridDiv" style={{ height:'80%', width: '95%'}}>
      <DataGrid rows={rows} columns={columns} pageSize={15} checkboxSelection />
    </div>
  );
}