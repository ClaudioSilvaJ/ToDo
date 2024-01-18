import axios from 'axios';

async function Login(email, password) {
    const loginData = {
        email: email,
        password: password,
    };
    try {
        const response = await axios.post('http://localhost:8231/api/auth/login', loginData, {
            headers: {
                'Content-Type': 'application/json',
            },
        });
        localStorage.setItem("token", response.data.token)
        localStorage.setItem("user", email)

        if (response.status === 200) {
            console.log('Login bem-sucedido');
            window.location.href = "user/dashboard";
        } else {
            console.log('Falha no login');
        }

        return response;
    } catch (error) {
        if (error.response && error.response.status === 401) {
            console.log('Erro 401: Não autorizado');
        } else {
            console.error('Erro na solicitação:', error);
        }
        return error.response;
    }
}

function Logout(){
    localStorage.clear();
    window.location.href = "/login";
}

async function Register(email, password){
    const newLogin = {
        email: email,
        password: password,
    };
    try{
        const response = await axios.post('http://localhost:8231/api/auth/register', newLogin, {
            headers: {
                'Content-Type': 'application/json',
            },
        });
        response.status === 200 ? window.location.href = "/login" : window.location.href = "/dawdawd";
    } catch (error){
        return error.response;
    }
}

export {Login, Register, Logout};
