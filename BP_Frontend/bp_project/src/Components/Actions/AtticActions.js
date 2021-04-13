import axios from 'axios';

export function createAttic(data, finish) {
  const config = {
    method: 'POST',
    url: 'http://localhost:8080/api/attics/create',
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
    .then(() => {
      console.log('Success');
      //props.history.push("/")
    })
    .finally(() => {
      finish();
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

export function getAllAtticsByRoofId(roofId, onSuccess) {
  const config = {
    method: 'GET',
    url: 'http://localhost:8080/api/attics/getAllAtticsByRoofId/' + roofId,
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
      onSuccess(response);
      //props.history.push("/")
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
