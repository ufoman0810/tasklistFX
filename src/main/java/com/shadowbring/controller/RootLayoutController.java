package com.shadowbring.controller;

import com.shadowbring.TasklistApp;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class RootLayoutController {

    private TasklistApp application;

    public void setApplication(TasklistApp application) {
        this.application = application;
    }

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
    @FXML
    private void handleExport() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(application.getPrimaryStage());

        if (file != null && !file.getPath().endsWith(".xml")) {
            file = new File(file.getPath() + ".xml");
        }
        application.exportTasklistToXml(file);
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

//    @FXML
//    private void handleShowBirthdayStatistics() {
//        application.showBirthdayStatistics();
//    }
}
