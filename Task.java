import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private StringProperty title;
    private StringProperty description;
    private StringProperty priority;  // Priority: High, Medium, Low
    private StringProperty dueDate;   // Task due date

    // Constructor
    public Task(String title, String description, String priority, String dueDate) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.priority = new SimpleStringProperty(priority);
        this.dueDate = new SimpleStringProperty(dueDate);
    }

    // Getters and setters for each property
    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty priorityProperty() {
        return priority;
    }

    public StringProperty dueDateProperty() {
        return dueDate;
    }
}
