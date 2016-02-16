package com.shadowbring.controller;

import com.shadowbring.TasklistApp;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class RootLayoutController {

    private TasklistApp application;

    public void setApplication(TasklistApp application) {
        this.application = application;
    }

    @FXML
    private void handleReimport() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File xmlFile = fileChooser.showOpenDialog(application.getPrimaryStage());

        if (xmlFile != null) {
            application.reimportTasklistFromXml(xmlFile);
        }
    }

    @FXML
    private void handleExport() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File xmlFile = fileChooser.showSaveDialog(application.getPrimaryStage());

        if (xmlFile != null && !xmlFile.getPath().endsWith(".xml")) {
            xmlFile = new File(xmlFile.getPath() + ".xml");
        }
        application.exportTasklistToXml(xmlFile);
    }

    @FXML
    private void handleXlsExport() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("Microsoft Excel files (*.xls, *.xlsx)", "*.xls", "*.xlsx");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File xlsFile = fileChooser.showSaveDialog(application.getPrimaryStage());

        if (xlsFile != null && !xlsFile.getPath().endsWith(".xls")) {
            xlsFile = new File(xlsFile.getPath() + ".xls");
        }
        application.exportTasklistToXls(xlsFile);
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void handleShowMemoryUsageStatistics() {
        application.showMemoryUsageStatistics();
    }
}
