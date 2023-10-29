import React, { useState } from 'react';
import LoginService from "../services/LoginService";

function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loggedIn, setLoggedIn] = useState(false);

    const handleLogin = async () => {
        const responseLogin = await LoginService(username, password);
        //Temp Auth
        if(responseLogin.status === 200) {
            setLoggedIn(true);
        }
    };

    return (
        <div>
            {loggedIn ? (
                <div>
                    <h2>Bem-vindo, {username}!</h2>
                    <button onClick={() => setLoggedIn(false)}>Sair</button>
                </div>
            ) : (
                <div>
                    <h2>Entre na sua conta</h2>
                    <input
                        type="text"
                        placeholder="Nome de usuário"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    <input
                        type="password"
                        placeholder="Senha"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <button onClick={handleLogin}>Login</button>
                </div>
            )}
        </div>
    );
}

export default LoginPage;
