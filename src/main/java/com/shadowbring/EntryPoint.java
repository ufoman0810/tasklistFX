package com.shadowbring;

import com.shadowbring.controller.RootLayoutController;
import com.shadowbring.controller.TasklistController;
import com.shadowbring.model.ProcessEntity;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class EntryPoint extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<ProcessEntity> processList = FXCollections.observableArrayList();

    public EntryPoint() throws IOException {
        //chcp 65001 switches cmd to utf8-compatible mode
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "chcp 65001 && tasklist");
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        processList.addAll(bufferedReader.lines()
                //skipping table header
                .skip(4)
                .map(ProcessEntity::parseProcess)
                .sorted(((o1, o2) -> (int) (o1.getUsedMemory() - o2.getUsedMemory())))
                .collect(Collectors.toList()));
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    public ObservableList<ProcessEntity> getProcessList() {
        return processList;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TasklistFX");

        initRootLayout();

        showTasklist();
    }

    private void showTasklist() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EntryPoint.class.getResource("/view/TasklistLayout.fxml"));
            AnchorPane tasklist = loader.load();
            rootLayout.setCenter(tasklist);
            TasklistController controller = loader.getController();
            controller.setApplication(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EntryPoint.class.getResource("/view/RootLayout.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            RootLayoutController controller = loader.getController();
            primaryStage.setScene(scene);
            controller.setApplication(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
