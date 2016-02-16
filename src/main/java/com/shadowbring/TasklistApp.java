package com.shadowbring;

import com.shadowbring.controller.MemoryUsageStatisticsController;
import com.shadowbring.controller.RootLayoutController;
import com.shadowbring.controller.TasklistController;
import com.shadowbring.model.Task;
import com.shadowbring.model.TaskListWrapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TasklistApp extends Application {

    private static final Logger LOGGER = Logger.getLogger(TasklistApp.class.getClass().getName());
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Task> taskList = FXCollections.observableArrayList();
    private ObservableList<Task> importedTaskList = FXCollections.observableArrayList();

    public TasklistApp() throws IOException {
        //chcp 65001 switches cmd.exe to utf8-compatible mode
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "chcp 65001 && tasklist");
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        taskList.addAll(bufferedReader.lines()
                //skipping table header
                .skip(4)
                .map(Task::parseTask)
                .sorted(((o1, o2) -> (int) (o1.getMemory() - o2.getMemory())))
                .collect(Collectors.toList()));
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Task> getTaskList() {
        return taskList;
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
            loader.setLocation(TasklistApp.class.getResource("/view/TasklistLayout.fxml"));
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
            loader.setLocation(TasklistApp.class.getResource("/view/RootLayout.fxml"));
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

    public void exportTasklistToXml(File xmlFile) {
        try {
            JAXBContext context = JAXBContext.newInstance(TaskListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            TaskListWrapper wrapper = new TaskListWrapper();
            clearDuplicates();
            wrapper.setTasks(taskList);

            marshaller.marshal(wrapper, xmlFile);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Export to file: " + xmlFile.getPath() + " was failed!");
        }
    }

    public void clearDuplicates() {
        Map<String, List<Task>> tasksByName = taskList.stream().collect(Collectors.groupingBy(Task::getName));
        taskList.clear();
        taskList.addAll(
                tasksByName.values().stream().map(sameTasks -> sameTasks.stream().reduce(new Task(),
                        ((task, task2) -> {
                            task.setName(task2.getName());
                            task.setPid(task2.getPid());
                            task.setMemory(task.getMemory() + task2.getMemory());
                            return task;
                        })))
                        .sorted((o1, o2) -> (int) (o1.getMemory() - o2.getMemory()))
                        .collect(Collectors.toList())
        );
    }

    public void reimportTasklistFromXml(File xmlFile) {
        try {
            JAXBContext context = JAXBContext.newInstance(TaskListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            TaskListWrapper wrapper = (TaskListWrapper) um.unmarshal(xmlFile);

            importedTaskList.clear();
            importedTaskList.addAll(wrapper.getTasks());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Import from file: " + xmlFile.getPath() + " was failed!");
        }
    }

    public void exportTasklistToXls(File xlsFile) throws IOException {
        Workbook tasklist = new HSSFWorkbook();
        Sheet tasklistSheet = tasklist.createSheet("TaskList");
        Row headerRow = tasklistSheet.createRow(0);
        Cell nameCell = headerRow.createCell(0);
        Cell pidCell = headerRow.createCell(1);
        Cell usedMemoryCell = headerRow.createCell(2);
        nameCell.setCellValue("Name");
        pidCell.setCellValue("PID");
        usedMemoryCell.setCellValue("Used Memory");
        for (Task task : taskList) {
            Row dataRow = tasklistSheet.createRow(taskList.indexOf(task) + 1);
            dataRow.createCell(0).setCellValue(task.getName());
            dataRow.createCell(1).setCellValue(task.getPid());
            dataRow.createCell(2).setCellValue(task.getMemory());
        }
        FileOutputStream fileOutputStream = new FileOutputStream(xlsFile);
        tasklist.write(fileOutputStream);
        fileOutputStream.close();
    }

    public void showMemoryUsageStatistics() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TasklistApp.class.getResource("/view/MemoryUsageStatisticsLayout.fxml"));
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Memory Usage Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            MemoryUsageStatisticsController controller = loader.getController();
            controller.setTaskList(taskList);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
