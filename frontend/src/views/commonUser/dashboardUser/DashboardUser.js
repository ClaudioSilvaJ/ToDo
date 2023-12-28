import React from 'react';

import {Logout} from "../../../services/Login/LoginService"
import ListTasks from "../../../components/ListTasks/ListTasks";

function DashboardUser() {

    const handleLogout = () => {
        Logout();
    }
    const handleTask = (event) =>{
        const buttonName = event.target.name

        console.log(buttonName)
    }
    return (
        <div className="flex flex-row h-screen">
            <div className="flex-column justify-center items-center w-3/12  font-oswald">

                <div className="flex flex-row">
                    <div className="flex flex-col justify-center items-center">
                        <label className="text-5xl pb-10">Você está logado.</label>
                        <button onClick={handleLogout} className="box-border py-4 px-8 bg-emerald-600 hover:bg-emerald-700 h-20 w-64 p-4 border-4 rounded-lg border-none font-oswald text-xl font-bold text-white">Sair</button>
                    </div>
                </div>
            </div>
            <div className="flex flex-col justify-content-between w-9/12 rounded-md bg-blue-100 m-8">
                <ListTasks/>
            </div>
        </div>

    );
}

export default DashboardUser;
