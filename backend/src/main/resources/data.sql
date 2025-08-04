-- This script is executed automatically by Spring Boot on startup.
-- It seeds the database with initial data if the tasks table is empty.

-- Insert sample tasks into the table
-- This check prevents the data from being inserted every time the server restarts.
IF NOT EXISTS (SELECT 1 FROM tasks)
BEGIN
INSERT INTO tasks (task_code, title, description, status, created_at) VALUES
                                                                          ('T202508040001', 'Finalize project requirements', 'Review the official documentation and clarify all points with the project manager.', 'DONE', GETDATE()),
                                                                          ('T202508040002', 'Implement backend API endpoints', 'Create all required REST endpoints in the Spring Boot application following Clean Architecture.', 'IN_PROGRESS', GETDATE()),
                                                                          ('T202508040003', 'Develop React frontend components', 'Build the UI for adding, viewing, and filtering tasks using React and Bootstrap.', 'TODO', GETDATE());
END
GO
