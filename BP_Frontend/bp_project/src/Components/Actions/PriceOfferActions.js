import axios from 'axios';

export function finishPriceOffer(roofId, customer, finish) {
  const config = {
    method: 'POST',
    url: 'http://localhost:8080/api/roof/calculateNeededItems/' + roofId,
    responseType: 'json',
    headers: {
      'content-type': 'application/json'
      // origin: requestOrigin
    },
    params: {
      access_token: localStorage.getItem('access_token')
    }
  };

  axios
    .request(config)
    .then(response => {
      if (response.data !== undefined && response.data != null) {
        let priceOfferId = response.data.priceOfferId;

        const configNext = {
          method: 'GET',
          url:
            'http://localhost:8080' +
            '/api/priceOffer/finishPriceOffer/' +
            customer +
            '/' +
            priceOfferId,
          responseType: 'json',
          headers: {
            'content-type': 'multipart/form-data'
            // origin: requestOrigin
          },
          params: {
            access_token: localStorage.getItem('access_token')
          }
        };

        axios
          .request(configNext)
          .then(responseNext => {
            if (responseNext.status === 200) {
              console.log('ahoj');
            }
          })
          .finally(() => {
            finish();
          })
          .catch(err => {
            console.log(err);
            if (err.response !== undefined) {
              if (err.response.status === 403) {
                console.log('403');
              }

              if (err.response.status === 401) {
                console.log('401');
              }
            }
          });
      }
    })
    .catch(err => {
      if (err.response !== undefined) {
        if (err.response.status === 403) {
          console.log('undefinded');
        }
      }
    });
}

export function getAllPriceOffers(onSuccess) {
  const config = {
    method: 'GET',
    url: 'http://localhost:8080/api/priceOffer/getAllPriceOffers',
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

export function updatePriceOffer(priceOfferId, data, onSuccess) {
  const config = {
    method: 'PUT',
    url: 'http://localhost:8080/api/priceOffer/updatePriceOffer/' + priceOfferId,
    responseType: 'json',
    headers: {
      Authorization: 'Basic ' + btoa('mia-dms-gui:secret'),
      'Content-Type': 'application/json'
    },
    data: data,
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
