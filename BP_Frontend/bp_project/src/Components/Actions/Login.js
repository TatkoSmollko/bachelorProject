
import https from "https";
import axios from "axios";

const apiTarget = "http://localhost:8080"

const login = (userName, Password) => {
const config = {
    method: "POST",
    url: apiTarget + "/oauth/token",
    responseType: "json",

    httpsAgent: new https.Agent({
        rejectUnauthorized: false,
    }),

    headers: {
        Authorization: "Basic bWlhLWRtcy1ndWk6c2VjcmV0", // + btoa("mia-dms-gui:secret"),
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
        
        //this.props.history.push("/home");
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
}


export default login;