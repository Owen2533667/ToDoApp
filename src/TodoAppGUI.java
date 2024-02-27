import Model.Priority;
import Model.Task;
import Model.TaskList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.util.LinkedList;

public class TodoAppGUI extends JFrame {

    private TaskList taskList;
    private DefaultTableModel tableModel;
    private JTable taskTable;
    private JTextField taskDescriptionField;
    private JButton addTaskButton, markCompletedButton, editButton, deleteButton;

    public TodoAppGUI() {
        super("To-Do List");

        // Initialize task list
        taskList = new TaskList("My Tasks");

        // Create table model and table
        tableModel = new DefaultTableModel(new String[]{"Description", "Completed", "Due Date", "Priority"}, 0);
        taskTable = new JTable(tableModel);
        taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow single selection only

        // Add task description field, buttons, and menu bar
        taskDescriptionField = new JTextField(20);
        addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(e -> addTask());
        markCompletedButton = new JButton("Mark Completed");
        markCompletedButton.addActionListener(e -> markCompleted());
        editButton = new JButton("Edit");
        editButton.addActionListener(e -> editTask());
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteTask());
        JMenuBar menuBar = createMenuBar();

        // Layout components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(taskTable), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addTaskButton);
        buttonPanel.add(markCompletedButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        JPanel topPanel = new JPanel();
        topPanel.add(taskDescriptionField);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Add content, display window, and set default close operation
        setContentPane(mainPanel);
        setJMenuBar(menuBar); // Add menu bar
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Update table on launch
        updateTable();
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(e -> saveTasks());
        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.addActionListener(e -> loadTasks());
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        return menuBar;
    }

    private void addTask() {
        String description = taskDescriptionField.getText();
        taskList.addTask(new Task(description, false, LocalDate.now(), Priority.LOW));
        updateTable();
        taskDescriptionField.setText("");
    }

    private void markCompleted() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow >= 0) {
            Task selectedTask = taskList.getTasks().get(selectedRow);
            selectedTask.setCompleted(!selectedTask.isCompleted());
            updateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to mark completed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editTask() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow >= 0) {
            Task selectedTask = taskList.getTasks().get(selectedRow);
            String newDescription = JOptionPane.showInputDialog(this, "Enter new description:", "Edit Task", JOptionPane.PLAIN_MESSAGE);
            if (newDescription != null && !newDescription.isEmpty()) {
                selectedTask.setDescription(newDescription);
                updateTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTask() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?", "Delete Task", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                taskList.removeTask(taskList.getTasks().get(selectedRow));
                updateTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable() {
        LinkedList<Task> tasks = taskList.getTasks();
        tableModel.setRowCount(0); // Clear existing data

        // Iterate through tasks and add them to the table model
        for (Task task : tasks) {
            tableModel.addRow(new Object[]{
                    task.getDescription(),
                    task.isCompleted() ? "Yes" : "No", // Display "Yes" or "No" for completed
                    task.getDueDate(),
                    task.getPriority()
            });
        }
    }

    private void saveTasks() {
        // Choose a file using a file chooser
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Implement code to save tasks to the selected file
            // You can use file I/O techniques like serialization or writing to a text file
        }
    }

    private void loadTasks() {
        // Choose a file using a file chooser
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Implement code to load tasks from the selected file
            // You can use file I/O techniques like deserialization or reading from a text file
            // Clear existing tasks and update the model
            taskList.removeAllTasks();
            updateTable();
        }
    }

    public static void main(String[] args) {
        new TodoAppGUI();
    }
}