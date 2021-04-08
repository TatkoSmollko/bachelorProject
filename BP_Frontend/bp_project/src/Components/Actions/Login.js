import React from 'react';
import https from "https";
import axios from "axios";
import PropTypes from 'prop-types';


const apiTarget = "http://localhost:8080"


const login = (userName, Password,props) => {
const config = {
    method: "POST",
    url: apiTarget + "/oauth/token",
    responseType: "json",

    httpsAgent: new https.Agent({
        rejectUnauthorized: false,
    }),

    headers: {
        Authorization: 'Basic ' + btoa('mia-dms-gui:secret'),
        "Content-Type": "application/json",
    },
    data: {
        grant_type: "password",
        username: userName,
        password: Password,
    },
};

axios.defaults.httpsAgent = new https.Agent({
    rejectUnauthorized: false,
});

axios
    .request(config)
    .then((response) => {
        Date.prototype.addSeconds = function (seconds) {
            this.setSeconds(this.getSeconds() + seconds);
            return this;
        };

        const expires_in = new Date().addSeconds(response.data.expires_in);
        localStorage.setItem("expires_in", expires_in.getTime());
        localStorage.setItem("access_token", response.data.access_token);
        localStorage.setItem("refresh_token", response.data.refresh_token);
        localStorage.setItem("token_type", response.data.token_type);
        localStorage.setItem("user_name", userName);
        localStorage.setItem("is_admin", "");
        props.history.push('/')
    })
    .catch((err) => {
        if (err.response != null) {
            if (err.response.data != null) {
                if (err.response.data.error === "invalid_grant") {
                }
            }
        } else {
            console.log(err);
        }
    });

    
login.propTypes = {
    router: PropTypes.object,
    history: PropTypes.object
  };
}


export default login;