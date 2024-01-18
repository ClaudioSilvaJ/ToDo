import React, {useEffect, useState} from 'react';
import CreateTaskPopup from "../CreateTaskPopup";
import DashboardUser, {fetchTasks} from "../../views/commonUser/dashboardUser/DashboardUser";
import axios from "axios";

let i = 0;
let arrayTasks= null;
let tasks = [];

function ListTasks(task) {

    while (i < task.tasks.length) {
        arrayTasks = task.tasks[i]
        tasks[i] = [
            {name: arrayTasks.name, state: arrayTasks.state, obs: arrayTasks.obs},
        ];
        i++;
    }

    const [taskPopupShow, setTaskPopupShow] = useState(false);
    const [taskSelected, setTaskSelected] = useState();

    const handleTask = (event, task) => {
        setTaskSelected()
        const functionName = event.target.name
        functionName === "create" && setTaskPopupShow(!taskPopupShow)
        if(functionName === "edit"){
            setTaskSelected(task)
            setTaskPopupShow(!taskPopupShow)

        }
    }

    function deleteTask(event, task){
        console.log(task)
        return axios.delete("http://localhost:8231/api/user/task/", {
            name: task.name,
            state: task.state,
            obs: task.obs
        }).then((response) => (
            console.log(response)
        ))
    }

    const listMapping =  task.tasks.map((task, index) => (
        <tr className="text-center" key={index}>
            <td>{task.name}</td>
            <td>{task.state}</td>
            <td>{task.obs}</td>
            <td>
                <button name="create" onClick={(event) => handleTask(event, task)} className="p-2 bg-green-200">
                    Create
                </button>
                <button name="edit" onClick={(event) => handleTask(event, task)} className="p-2 bg-blue-200">
                    Edit
                </button>
                <button name="delete" onClick={(event) => deleteTask(event, task)} className="p-2 bg-red-200">
                    Delete
                </button>
            </td>
        </tr>
    ))

    return (
        <div className="flex flex-column justify-center items-center font-oswald">
            <h2 className="text-5xl mb-5">Tasks</h2>
            <table className="justify-content-center">
                <thead>
                <tr>
                    <th className="px-4">Nome</th>
                    <th className="px-4">Estado</th>
                    <th className="px-4">Observacoes</th>
                </tr>
                </thead>
                <tbody className="border">
                {listMapping}
                </tbody>
            </table>
            {taskPopupShow &&
                <CreateTaskPopup onClose={() => setTaskPopupShow(!taskPopupShow)} onTaskSelected={taskSelected}/>}
        </div>
    );
}

export default ListTasks;
