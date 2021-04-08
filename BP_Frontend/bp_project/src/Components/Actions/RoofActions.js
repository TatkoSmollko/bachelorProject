import axios from 'axios';
import { apiTarget } from '../Global/globalVariables';

export function createEmptyRoof(data, props, onSuccess) {
  const config = {
    method: 'POST',
    url:  'http://localhost:8080/api/roof/createEmptyRoof',
    responseType: 'json',
    headers: {
      Authorization: 'Basic ' + btoa('mia-dms-gui:secret'),
      "Content-Type": "application/json",
  },
    params: {
      access_token: localStorage.getItem('access_token')
      },
      data:data
  };
  axios
    .request(config)
    .then(response => {
      localStorage.setItem("newRoofId", response.data.id);
      onSuccess();
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

export default createEmptyRoof;