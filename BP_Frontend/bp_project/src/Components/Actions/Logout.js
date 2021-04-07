import axios from 'axios';
import { apiTarget } from '../Global/globalVariables';

export function logout(onLogouSuccess, onLogouError) {
  const config = {
    method: 'DELETE',
    url: 'http://localhost:8080/api/logout',
    responseType: 'json',
    headers: {
      Authorization: 'Basic ' + btoa('mia-dms-gui:secret'),
      "Content-Type": "application/json",
  },
    params: {
      access_token: localStorage.getItem('access_token')
    }
  };

  axios
    .request(config)
    .then(() => {
      onLogouSuccess();
    })
    .catch(error => {
      if (error.response !== undefined) {
        if (error.response.status === 401) {
          console.log(error.response);
          onLogouError();
        }
        if (error.response.status === 403) {
          onLogouError();
        }
      }
    });
}

export default logout;