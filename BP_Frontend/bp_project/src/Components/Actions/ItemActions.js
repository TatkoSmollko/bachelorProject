import axios from 'axios';

export function getAllItems(onSuccess) {
  const config = {
    method: 'GET',
    url: 'http://localhost:8080/api/item/getAllItems',
    responseType: 'json',
    headers: {
      Authorization: 'Basic ' + btoa('mia-dms-gui:secret'),
      'Content-Type': 'application/json'
    },
    params: {
      access_token: localStorage.getItem('access_token')
    }
  };
  axios
    .request(config)
    .then(response => {
      onSuccess(response.data);
    })
    .catch(error => {
      if (error.response !== undefined) {
        if (error.response.status === 401) {
          console.log(error.response);
        }
        if (error.response.status === 403) {
        }
      }
    });
}

export function getAllItemsByStoreId(onSuccess, storeId) {
  const config = {
    method: 'GET',
    url: 'http://localhost:8080/api/item/getByStore/' + storeId,
    responseType: 'json',
    headers: {
      Authorization: 'Basic ' + btoa('mia-dms-gui:secret'),
      'Content-Type': 'application/json'
    },
    params: {
      access_token: localStorage.getItem('access_token')
    }
  };
  axios
    .request(config)
    .then(response => {
      onSuccess(response.data);
    })
    .catch(error => {
      if (error.response !== undefined) {
        if (error.response.status === 401) {
          console.log(error.response);
        }
        if (error.response.status === 403) {
        }
      }
    });
}

export function updateItem(itemId, data, onSuccess) {
  const config = {
    method: 'PUT',
    url: 'http://localhost:8080/api/item/updateItem/' + itemId,
    responseType: 'json',
    headers: {
      Authorization: 'Basic ' + btoa('mia-dms-gui:secret'),
      'Content-Type': 'application/json'
    },
    params: {
      access_token: localStorage.getItem('access_token')
    },
    data: data
  };
  axios
    .request(config)
    .then(() => onSuccess())
    .catch(error => {
      if (error.response !== undefined) {
        if (error.response.status === 401) {
          console.log(error.response);
        }
        if (error.response.status === 403) {
        }
      }
    });
}
