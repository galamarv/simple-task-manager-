# Makefile for the Simple Task Manager Project

# --- Variables ---
# Define the directories for the backend and frontend
BACKEND_DIR := ./backend
FRONTEND_DIR := ./frontend

# --- Installation Targets ---

# Install all dependencies for both frontend and backend
install: install-backend install-frontend

# Install backend dependencies using Maven
install-backend:
	@echo ">>> Installing backend dependencies..."
	@cd $(BACKEND_DIR) && mvn clean install -DskipTests

# Install frontend dependencies using npm
install-frontend:
	@echo ">>> Installing frontend dependencies..."
	@cd $(FRONTEND_DIR) && npm install

# --- Run Targets ---

# Run both the backend and frontend servers concurrently
run:
	@echo ">>> Starting both backend and frontend servers..."
	@make run-backend & make run-frontend

# Run the backend Spring Boot application
run-backend:
	@echo ">>> Starting backend server on http://localhost:8080..."
	@cd $(BACKEND_DIR) && java -jar target/task-manager-api-0.0.1-SNAPSHOT.jar

# Run the frontend Vite development server
run-frontend:
	@echo ">>> Starting frontend server on http://localhost:5173..."
	@cd $(FRONTEND_DIR) && npm run dev

# --- Utility Targets ---

# Clean build artifacts from the backend
clean:
	@echo ">>> Cleaning backend target directory..."
	@cd $(BACKEND_DIR) && mvn clean

# Display help information for the available commands
help:
	@echo "Usage: make [target]"
	@echo ""
	@echo "Targets:"
	@echo "  install          Install all project dependencies (backend and frontend)"
	@echo "  install-backend  Install only backend dependencies"
	@echo "  install-frontend Install only frontend dependencies"
	@echo "  run              Run both backend and frontend servers"
	@echo "  run-backend      Run only the backend server"
	@echo "  run-frontend     Run only the frontend server"
	@echo "  clean            Clean the backend build directory"
	@echo "  help             Show this help message"

# Define phony targets to prevent conflicts with file names
.PHONY: install install-backend install-frontend run run-backend run-frontend clean help
