package com.shadowbring.controller;

import com.shadowbring.EntryPoint;
import com.shadowbring.model.ProcessEntity;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TasklistController {
    @FXML
    private TableView<ProcessEntity> processTable;
    @FXML
    private TableColumn<ProcessEntity, String> nameColumn;
    @FXML
    private TableColumn<ProcessEntity, String> pidColumn;
    @FXML
    private TableColumn<ProcessEntity, String> usedMemoryColumn;

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

    // Reference to the main application.
    private EntryPoint application;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TasklistController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
//         Initialize the person table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        pidColumn.setCellValueFactory(cellData -> cellData.getValue().pidProperty().asString());
        usedMemoryColumn.setCellValueFactory(cellData -> cellData.getValue().usedMemoryProperty().asString());

//         Clear person details.
//        showPersonDetails(null);

//         Listen for selection changes and show the person details when changed.
//        processTable.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param application
     */
    public void setApplication(EntryPoint application) {
        this.application = application;
        processTable.setItems(application.getProcessList());
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

//    @FXML
//    private void handleDeletePerson() {
//        int selectedIndex = processTable.getSelectionModel().getSelectedIndex();
//        if (selectedIndex  > -1) {
//            processTable.getItems().remove(selectedIndex);
//        } else {
//            // Nothing selected.
//            Dialogs.create()
//                    .title("No Selection")
//                    .masthead("No Person Selected")
//                    .message("Please select a person in the table.")
//                    .showWarning();
//        }
//    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
//    @FXML
//    private void handleNewPerson() {
//        Person tempPerson = new Person();
//        boolean okClicked = application.showPersonEditDialog(tempPerson);
//        if (okClicked) {
//            application.getPersonData().add(tempPerson);
//        }
//    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
//    @FXML
//    private void handleEditPerson() {
//        Person selectedPerson = processTable.getSelectionModel().getSelectedItem();
//        if (selectedPerson != null) {
//            boolean okClicked = application.showPersonEditDialog(selectedPerson);
//            if (okClicked) {
//                showPersonDetails(selectedPerson);
//            }
//
//        } else {
//            // Nothing selected.
//            Dialogs.create()
//                    .title("No Selection")
//                    .masthead("No Person Selected")
//                    .message("Please select a person in the table.")
//                    .showWarning();
//        }
//    }
}
