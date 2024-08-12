import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private final List<String> CATEGORIES = List.of("Food", "Transport", "Entertainment", "Utilities");
    private ExpenseTracker expenseTracker = new ExpenseTracker();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Expense Tracker");

        VBox root = new VBox();
        root.setSpacing(10);

        // UI Elements
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");

        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll(CATEGORIES);
        categoryComboBox.setPromptText("Select category");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter description");

        TextField additionalInfoField = new TextField();
        additionalInfoField.setPromptText("Enter additional info");

        Button addButton = new Button("Add Expense");
        Button viewButton = new Button("View Expenses");

        // Add Expense Button Action
        addButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String category = categoryComboBox.getValue();
                String description = descriptionField.getText();
                String additionalInfo = additionalInfoField.getText();

                Expense expense = new Expense(amount, category, description, additionalInfo);
                expenseTracker.addExpense(expense);

                clearFields(amountField, categoryComboBox, descriptionField, additionalInfoField);
                showAlert("Success", "Expense added successfully!");
            } catch (NumberFormatException ex) {
                showAlert("Error", "Invalid amount!");
            }
        });

        // View Expenses Button Action
        viewButton.setOnAction(e -> {
            TableView<Expense> tableView = new TableView<>();

            TableColumn<Expense, String> categoryColumn = new TableColumn<>("Category");
            categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));

            TableColumn<Expense, Double> amountColumn = new TableColumn<>("Amount");
            amountColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());

            TableColumn<Expense, String> descriptionColumn = new TableColumn<>("Description");
            descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

            TableColumn<Expense, String> additionalInfoColumn = new TableColumn<>("Additional Info");
            additionalInfoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdditionalInfo()));

            TableColumn<Expense, String> dateTimeColumn = new TableColumn<>("Date & Time");
            dateTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateTime().toString()));

            TableColumn<Expense, Void> actionColumn = new TableColumn<>("Actions");
            actionColumn.setCellFactory(column -> new TableCell<Expense, Void>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(e -> {
                        Expense expense = getTableView().getItems().get(getIndex());
                        expenseTracker.deleteExpense(expense);
                        getTableView().getItems().remove(expense);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(empty ? null : deleteButton);
                }
            });

            tableView.getColumns().add(categoryColumn);
            tableView.getColumns().add(amountColumn);
            tableView.getColumns().add(descriptionColumn);
            tableView.getColumns().add(additionalInfoColumn);
            tableView.getColumns().add(dateTimeColumn);
            tableView.getColumns().add(actionColumn);

            tableView.getItems().setAll(expenseTracker.viewExpenses());

            VBox vbox = new VBox(tableView);
            Scene scene = new Scene(vbox, 800, 600);
            Stage viewStage = new Stage();
            viewStage.setTitle("View Expenses");
            viewStage.setScene(scene);
            viewStage.show();
        });

        root.getChildren().addAll(amountField, categoryComboBox, descriptionField, additionalInfoField, addButton, viewButton);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearFields(TextField amountField, ComboBox<String> categoryComboBox, TextField descriptionField, TextField additionalInfoField) {
        amountField.clear();
        categoryComboBox.setValue(null);
        descriptionField.clear();
        additionalInfoField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}