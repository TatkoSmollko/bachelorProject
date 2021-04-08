import axios from 'axios';

export function createAttic(data, props) {
  const config = {
    method: 'POST',
    url:  'http://localhost:8080/api/attics/create',
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
    .then(() => {
      console.log('Success');
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

export default createAttic;