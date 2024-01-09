import React, {useState} from 'react';
import CreateTaskPopup from "../CreateTaskPopup";

function ListTasks() {
    const tasks = [
        { name: "Task 1", state: "pending", obs: "-------" },
        { name: "Task 2", state: "pending", obs: "-------" },
        { name: "Task 3", state: "pending", obs: "-------" },
    ];
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
                {tasks.map((task, index) => (
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
                            <button name="delete" onClick={(event) => handleTask(event, task)} className="p-2 bg-red-200">
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            {taskPopupShow && <CreateTaskPopup onClose={() => setTaskPopupShow(!taskPopupShow)} onTaskSelected={taskSelected}/> }
        </div>
    );
}

export default ListTasks;
