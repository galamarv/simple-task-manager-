import React, { useState, useEffect } from 'react';
import { type Task, getTasks, createTask, updateTask, deleteTask } from './services/api';

function App() {
    const [tasks, setTasks] = useState<Task[]>([]);
    const [filteredTasks, setFilteredTasks] = useState<Task[]>([]);
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [filter, setFilter] = useState<'ALL' | Task['status']>('ALL');
    const [error, setError] = useState<string | null>(null);

    // Fetch all tasks from the API when the component mounts
    const fetchAllTasks = async () => {
        try {
            const fetchedTasks = await getTasks();
            setTasks(fetchedTasks.sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()));
            setError(null);
        } catch (err) {
            setError("Failed to connect to the backend. Please ensure the server is running.");
            console.error(err);
        }
    };

    useEffect(() => {
        fetchAllTasks();
    }, []);

    // Apply filter whenever the filter state or the tasks list changes
    useEffect(() => {
        if (filter === 'ALL') {
            setFilteredTasks(tasks);
        } else {
            setFilteredTasks(tasks.filter(task => task.status === filter));
        }
    }, [filter, tasks]);

    const handleCreateTask = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!title.trim()) {
            alert("Title is required.");
            return;
        }
        try {
            await createTask({ title, description });
            setTitle('');
            setDescription('');
            fetchAllTasks(); // Refresh the list
        } catch (err) {
            console.error("Failed to create task:", err);
            setError("Failed to create task.");
        }
    };

    const handleUpdateStatus = async (taskToUpdate: Task, newStatus: Task['status']) => {
        const updatedTaskData = { ...taskToUpdate, status: newStatus };
        try {
            await updateTask(taskToUpdate.taskCode, updatedTaskData);
            fetchAllTasks(); // Refresh the list
        } catch (err) {
            console.error("Failed to update status:", err);
            setError("Failed to update task status.");
        }
    };

    const handleDeleteTask = async (id: string) => {
        if (window.confirm("Are you sure you want to delete this task?")) {
            try {
                await deleteTask(id);
                fetchAllTasks(); // Refresh the list
            } catch (err) {
                console.error("Failed to delete task:", err);
                setError("Failed to delete task.");
            }
        }
    };

    return (
         <main style={{ width: '100%', maxWidth: '800px' }}>
            <header className="text-center mb-4">
                <h1>📋 Simple Task Manager</h1>
            </header>

            {/* Add Task Form */}
            <div className="card mb-4 shadow-sm">
                <div className="card-body">
                    <h5 className="card-title">Add New Task</h5>
                    <form onSubmit={handleCreateTask}>
                        <div className="mb-3">
                            <label htmlFor="title" className="form-label">Title</label>
                            <input
                                id="title"
                                type="text"
                                className="form-control"
                                placeholder="What needs to be done?"
                                value={title}
                                onChange={(e) => setTitle(e.target.value)}
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="description" className="form-label">Description</label>
                            <textarea
                                id="description"
                                className="form-control"
                                placeholder="Add a description..."
                                value={description}
                                onChange={(e) => setDescription(e.target.value)}
                            ></textarea>
                        </div>
                        <button type="submit" className="btn btn-primary">Add Task</button>
                    </form>
                </div>
            </div>

            {/* Error Display */}
            {error && <div className="alert alert-danger">{error}</div>}

            {/* Filter and Task List */}
            <div className="d-flex justify-content-end mb-3">
                <select className="form-select w-auto" value={filter} onChange={(e) => setFilter(e.target.value as any)}>
                    <option value="ALL">All Statuses</option>
                    <option value="TODO">To Do</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="DONE">Done</option>
                </select>
            </div>
            
            <div className="list-group">
                {filteredTasks.length > 0 ? (
                    filteredTasks.map((task) => (
                        <div key={task.taskCode} className="list-group-item list-group-item-action">
                            <div className="d-flex w-100 justify-content-between">
                                <h5 className="mb-1">{task.title}</h5>
                                <small>Created: {new Date(task.createdAt).toLocaleDateString()}</small>
                            </div>
                            <p className="mb-1">{task.description || "No description."}</p>
                            <small className="text-muted">Code: {task.taskCode}</small>
                            <div className="d-flex justify-content-end align-items-center mt-2">
                                <select
                                    className="form-select form-select-sm w-auto me-2"
                                    value={task.status}
                                    onChange={(e) => handleUpdateStatus(task, e.target.value as Task['status'])}
                                >
                                    <option value="TODO">To Do</option>
                                    <option value="IN_PROGRESS">In Progress</option>
                                    <option value="DONE">Done</option>
                                </select>
                                <button className="btn btn-danger btn-sm" onClick={() => handleDeleteTask(task.taskCode)}>
                                    Delete
                                </button>
                            </div>
                        </div>
                    ))
                ) : (
                    <div className="text-center p-3 text-muted">No tasks found.</div>
                )}
            </div>
        </main>
    );
}

export default App;