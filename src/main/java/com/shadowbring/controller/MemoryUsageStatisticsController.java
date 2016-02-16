package com.shadowbring.controller;

import com.shadowbring.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;

import java.util.stream.Collectors;

public class MemoryUsageStatisticsController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;
    private ObservableList<Task> taskList = FXCollections.observableArrayList();
    private ObservableList<String> taskNames = FXCollections.observableArrayList();

    public void setTaskList(ObservableList<Task> taskList) {
        this.taskList = taskList;
    }

    @FXML
    private void initialize() {
        // Get an array with the English month names.
//        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
//        // Convert it to a list and add it to our ObservableList of months.
//        monthNames.addAll(Arrays.asList(months));

        // Assign the month names as categories for the horizontal axis.
//        taskNames.clear();
        taskNames.addAll(taskList.stream().map(Task::getName).collect(Collectors.toList()));
        System.out.println(taskNames.size());
        taskNames.forEach(System.out::println);
//        taskNames.addAll(taskList.stream().map(Task::getName).collect(Collectors.toList()));
        xAxis.setCategories(taskNames);
    }
}
