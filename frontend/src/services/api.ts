import axios from 'axios';

// The base URL of your Spring Boot backend
const API_BASE_URL = 'http://localhost:8080';

/**
 * Defines the structure of a Task object, matching the backend model.
 */
export interface Task {
    taskCode: string;
    title: string;
    description: string;
    status: 'TODO' | 'IN_PROGRESS' | 'DONE';
    createdAt: string; // ISO date string
}

/**
 * Fetches all tasks from the backend.
 */
export const getTasks = async (): Promise<Task[]> => {
    const response = await axios.get(`${API_BASE_URL}/tasks`);
    return response.data;
};

/**
 * Creates a new task.
 * The backend will generate the taskCode, status, and createdAt.
 */
export const createTask = async (taskData: { title: string; description: string }): Promise<Task> => {
    const response = await axios.post(`${API_BASE_URL}/tasks`, taskData);
    return response.data;
};

/**
 * Updates an existing task, primarily its status.
 */
export const updateTask = async (id: string, task: Task): Promise<Task> => {
    const response = await axios.put(`${API_BASE_URL}/tasks/${id}`, task);
    return response.data;
};

/**
 * Deletes a task by its ID.
 */
export const deleteTask = async (id: string): Promise<void> => {
    await axios.delete(`${API_BASE_URL}/tasks/${id}`);
};