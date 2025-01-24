import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskManagerController {
    @FXML private TextField titleField;
    @FXML private TextArea descriptionField;
    @FXML private ComboBox<String> priorityComboBox;
    @FXML private DatePicker dueDatePicker;
    @FXML private TableView<Task> taskTable;

    private ObservableList<Task> taskList;
    private DatabaseHelper db;

    // Initialize method to set up the task manager
    @FXML
    public void initialize() {
        db = new DatabaseHelper("tasks.db");

        // Initialize task list from database
        taskList = FXCollections.observableArrayList(db.getAllTasks());
        taskTable.setItems(taskList);

        // Bind columns to Task properties
        taskTable.getColumns().get(0).setCellValueFactory(data -> data.getValue().titleProperty());
        taskTable.getColumns().get(1).setCellValueFactory(data -> data.getValue().priorityProperty());
        taskTable.getColumns().get(2).setCellValueFactory(data -> data.getValue().dueDateProperty());
    }

    // Handle adding a new task
    @FXML
    public void handleAddTask() {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String priority = priorityComboBox.getValue();
        String dueDate = (dueDatePicker.getValue() != null) ? dueDatePicker.getValue().toString() : null;

        if (title.isEmpty() || priority == null || dueDate == null) {
            showAlert("Error", "Please fill in all fields");
            return;
        }

        // Create and save the task
        Task newTask = new Task(title, description, priority, dueDate);
        db.addTask(newTask);
        taskList.add(newTask);

        // Clear input fields
        titleField.clear();
        descriptionField.clear();
        priorityComboBox.setValue(null);
        dueDatePicker.setValue(null);
    }

    // Show error alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
