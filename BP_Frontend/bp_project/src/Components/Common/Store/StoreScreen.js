import React, { Component } from 'react';
import { DataGrid } from '@material-ui/data-grid';
import './StoreScreen.scss';
import { getAllItems } from '../../Actions/ItemActions';

import ItemUpdateModal from '../../Modals/ItemUpdateModal';

const columns = [
  { field: 'name', headerName: 'Názov položky', width: 300 },
  { field: 'size', headerName: 'Veľkosť', width: 250 },
  { field: 'price', headerName: 'Cena', width: 250 },
  { field: 'count', headerName: 'Počet', width: 250 }
];

class StoreScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isUpdateModalOpen: false,
      rows: [],
      row: null
    };
  }

  row = {};

  toggleUpdateModalOpen = () => {
    this.setState({
      isUpdateModalOpen: !this.state.isUpdateModalOpen
    });
  };

  getAllItemsFunction = () => {
    getAllItems(response => {
      const models = [];
      response.forEach(element => {
        models.push(element);
      });
      this.setState({
        rows: models
      });
    });

    if (this.state.isUpdateModalOpen) {
      this.toggleUpdateModalOpen();
    }
  };

  handleRowClick = e => {
    this.setState({
      row: e.row
    });
    this.row = e.row;

    this.toggleUpdateModalOpen();
  };

  componentDidMount() {
    getAllItems(response => {
      const models = [];
      response.forEach(element => {
        models.push(element);
      });
      this.setState({
        rows: models
      });
    });
  }

  render() {
    return (
      <div className='store-view'>
        <div className='gridDiv' style={{ height: '80%', width: '95%' }}>
          {this.state.rows.length && (
            <DataGrid
              rows={this.state.rows}
              columns={columns}
              pageSize={15}
              onRowClick={e => this.handleRowClick(e)}
            />
          )}
        </div>
        {this.state.isUpdateModalOpen && (
          <ItemUpdateModal
            dataId={this.row.id}
            isOpen={this.state.isUpdateModalOpen}
            toggleUpdateModal={() => this.toggleUpdateModalOpen()}
            row={this.row}
            getAllItems={() => this.getAllItemsFunction()}
          />
        )}
      </div>
    );
  }
}

export default StoreScreen;
