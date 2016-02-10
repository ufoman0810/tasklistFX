package com.shadowbring.controller;

import com.shadowbring.EntryPoint;
import javafx.fxml.FXML;

public class RootLayoutController {
    // Reference to the main application
    private EntryPoint application;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param application
     */
    public void setApplication(EntryPoint application) {
        this.application = application;
    }

    /**
     * Creates an empty address book.
     */
//    @FXML
//    private void handleNew() {
//        application.getPersonData().clear();
//        application.setPersonFilePath(null);
//    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
//    @FXML
//    private void handleOpen() {
//        FileChooser fileChooser = new FileChooser();
//
//        // Set extension filter
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
//                "XML files (*.xml)", "*.xml");
//        fileChooser.getExtensionFilters().add(extFilter);
//
//        // Show save file dialog
//        File file = fileChooser.showOpenDialog(application.getPrimaryStage());
//
//        if (file != null) {
//            application.loadPersonDataFromFile(file);
//        }
//    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
//    @FXML
//    private void handleSave() {
//        File personFile = application.getPersonFilePath();
//        if (personFile != null) {
//            application.savePersonDataToFile(personFile);
//        } else {
//            handleSaveAs();
//        }
//    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
//    @FXML
//    private void handleSaveAs() {
//        FileChooser fileChooser = new FileChooser();
//
//        // Set extension filter
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
//                "XML files (*.xml)", "*.xml");
//        fileChooser.getExtensionFilters().add(extFilter);
//
//        // Show save file dialog
//        File file = fileChooser.showSaveDialog(application.getPrimaryStage());
//
//        if (file != null) {
//            // Make sure it has the correct extension
//            if (!file.getPath().endsWith(".xml")) {
//                file = new File(file.getPath() + ".xml");
//            }
//            application.savePersonDataToFile(file);
//        }
//    }

    /**
     * Opens an about dialog.
     */
//    @FXML
//    private void handleAbout() {
//        Dialogs.create()
//                .title("AddressApp")
//                .masthead("About")
//                .message("Author: Marco Jakob\nWebsite: http://code.makery.ch")
//                .showInformation();
//    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

    /**
     * Opens the birthday statistics.
     */
//    @FXML
//    private void handleShowBirthdayStatistics() {
//        application.showBirthdayStatistics();
//    }
}
