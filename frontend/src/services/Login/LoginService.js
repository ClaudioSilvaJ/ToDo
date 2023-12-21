import axios from 'axios';

import { withRouter } from 'react-router';
async function LoginService(email, password) {
    const loginData = {
        email: email,
        password: password,
    };

    console.log(loginData)

    try {
        const response = await axios.post('http://localhost:8231/api/auth/login', loginData, {
            headers: {
                'Content-Type': 'application/json',
            },
        });
        localStorage.setItem("token", response.data)

        if (response.status === 200) {
            window.location.href = "/user/dashboard"; //Todo - Resolver isso
            console.log('Login bem-sucedido');
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

export default LoginService;