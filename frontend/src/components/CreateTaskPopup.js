import React, {useState} from 'react';

function CreateTaskPopup({ onClose, onTaskCreate, onTaskSelected={name: "", state: "", obs: ""} }) {
    const [taskName, setTaskName] = useState(onTaskSelected.name);
    const [taskState, setTaskState] = useState(onTaskSelected.state);
    const [taskObs, setTaskObs] = useState(onTaskSelected.obs);
    const handleCreateTask = () => {
        const newTask = {
            name: taskName,
            state: taskState,
            obs: taskObs
        };
        onTaskCreate(newTask);
        onClose();
    }



    return (
        <div className="fixed top-0 left-0 w-full h-full flex items-center justify-center bg-black bg-opacity-40">
            <div className="bg-white p-8 shadow-md rounded-md w-4/12">
                <h2 className="text-lg font-bold mb-4">Nova Tarefa</h2>
                <label className="block mb-2">Nome:</label>
                <input
                    type="text"
                    value={taskName}
                    onChange={(e) => setTaskName(e.target.value)}
                    className="w-full p-2 mb-4 border rounded-md"
                />
                <label className="block mb-2">Estado:</label>
                <input
                    type="text"
                    value={taskState}
                    onChange={(e) => setTaskState(e.target.value)}
                    className="w-full p-2 mb-4 border rounded-md"
                />
                <label className="block mb-2">Observações:</label>
                <textarea
                    value={taskObs}
                    onChange={(e) => setTaskObs(e.target.value)}
                    className="w-full p-2 mb-4 border rounded-md"
                />
                <div className="flex justify-end">
                    <button onClick={handleCreateTask} className="bg-blue-500 text-white p-2 rounded-md">Criar Tarefa</button>
                    <button onClick={onClose} className="ml-2 p-2">Fechar</button>
                </div>
            </div>
        </div>
    );
}

export default CreateTaskPopup;
