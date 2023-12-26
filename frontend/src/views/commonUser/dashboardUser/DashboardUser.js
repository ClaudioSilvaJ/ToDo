import React from 'react';

import {Logout} from "../../../services/Login/LoginService"

function DashboardUser() {

    const handleLogout = () => {
        Logout();
    }
    return (
        <div className="flex-column justify-center items-center h-screen font-oswald">
            <div className="flex flex-col justify-center items-center">
                <label className="text-5xl pb-10">Você está logado.</label>
                <button onClick={handleLogout} className="box-border bg-emerald-600 hover:bg-emerald-700 h-20 w-64 p-4 border-4 rounded-lg border-none font-oswald text-xl font-bold text-white">Sair</button>
            </div>
        </div>
    );
}

export default DashboardUser;
