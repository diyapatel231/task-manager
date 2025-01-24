# task-manager

A task management application built using JavaFX and SQLite. It allows users to organize tasks, set deadlines, and receive reminders.

## Features
- **Task Management**: Add, edit, delete tasks with descriptions and deadlines.
- **Prioritization**: Assign priority levels (High, Medium, Low).
- **Notifications**: Reminders for tasks using Java's `ScheduledExecutorService`.
- **Persistent Storage**: Store tasks in a local SQLite database.

## How to Build and Run
1. Clone this repository:
   ```bash
   git clone <repo_url>
   cd task-manager
2. Build the application:
   ```bash
   gradle build
3. Run the application:
   ```bash
   gradle run
