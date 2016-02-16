package com.shadowbring.controller;

import com.shadowbring.TasklistApp;
import com.shadowbring.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TasklistController {
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> nameColumn;
    @FXML
    private TableColumn<Task, String> pidColumn;
    @FXML
    private TableColumn<Task, String> usedMemoryColumn;

//    @FXML
//    private Label firstNameLabel;
//    @FXML
//    private Label lastNameLabel;
//    @FXML
//    private Label streetLabel;
//    @FXML
//    private Label postalCodeLabel;
//    @FXML
//    private Label cityLabel;
//    @FXML
//    private Label birthdayLabel;

    private TasklistApp application;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        pidColumn.setCellValueFactory(cellData -> cellData.getValue().pidProperty().asString());
        usedMemoryColumn.setCellValueFactory(cellData -> cellData.getValue().memoryProperty().asString());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param application - instance of the EntryPoint
     */
    public void setApplication(TasklistApp application) {
        this.application = application;
        taskTable.setItems(application.getTaskList());
    }

//    public void showPersonDetails(Person person) {
//        if (person != null) {
//            // Fill the labels with info from the person object.
//            firstNameLabel.setText(person.getFirstName());
//            lastNameLabel.setText(person.getLastName());
//            streetLabel.setText(person.getStreet());
//            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
//            cityLabel.setText(person.getCity());
//            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
//        } else {
//            // Person is null, remove all the text.
//            firstNameLabel.setText("");
//            lastNameLabel.setText("");
//            streetLabel.setText("");
//            postalCodeLabel.setText("");
//            cityLabel.setText("");
//            birthdayLabel.setText("");
//        }
//    }

    @FXML
    private void handleClearDuplicates() {
        application.clearDuplicates();
    }
}
