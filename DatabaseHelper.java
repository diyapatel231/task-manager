import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private Connection connection;

    // Constructor to initialize the database connection
    public DatabaseHelper(String dbName) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Creates the tasks table if it doesn't exist
    private void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "description TEXT, " +
                "priority TEXT, " +
                "due_date TEXT)";
        connection.createStatement().execute(createTableSQL);
    }

    // Adds a task to the database
    public void addTask(Task task) {
        try {
            String insertSQL = "INSERT INTO tasks (title, description, priority, due_date) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(insertSQL);
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getPriority());
            pstmt.setString(4, task.getDueDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieves all tasks from the database
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            String querySQL = "SELECT * FROM tasks";
            ResultSet rs = connection.createStatement().executeQuery(querySQL);

            while (rs.next()) {
                tasks.add(new Task(
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("priority"),
                        rs.getString("due_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
