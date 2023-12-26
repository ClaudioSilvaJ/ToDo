import axios from "axios";


function TokenVerify() {
  const token = localStorage.getItem("token");
  if (token !== undefined && token !== null) {
    const tokenDTO = { token: localStorage.getItem("token") };
    return axios.post('http://localhost:8231/api/auth/token-verify', tokenDTO, {
      headers: {
        'Content-Type': 'application/json',
      },
    }).then((response) => {
      if(response.status === 200){
        return Promise.resolve(true);
      } else {
        localStorage.removeItem("token");
        return Promise.resolve(false);
      }
    }).catch((error) => {
      if(error.response && error.response.status === 401){
        localStorage.removeItem("token");
        return Promise.resolve(false);
      } else {
        throw error;
      }
    })
  } else {
    return Promise.resolve(false);
  }
}
export default TokenVerify;