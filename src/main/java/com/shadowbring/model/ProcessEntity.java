package com.shadowbring.model;

import javafx.beans.property.*;

public class ProcessEntity {

    private StringProperty name;
    private IntegerProperty pid;
    private LongProperty usedMemory;

    public ProcessEntity(String name, int pid, long usedMemory) {
        this.name.set(name);
        this.pid.set(pid);
        this.usedMemory.set(usedMemory);
    }

    public ProcessEntity() {
        this.name = new SimpleStringProperty();
        this.pid = new SimpleIntegerProperty();
        this.usedMemory = new SimpleLongProperty();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getPid() {
        return pid.get();
    }

    public void setPid(int pid) {
        this.pid.set(pid);
    }

    public long getUsedMemory() {
        return usedMemory.get();
    }

    public void setUsedMemory(long usedMemory) {
        this.usedMemory.set(usedMemory);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty pidProperty() {
        return pid;
    }

    public LongProperty usedMemoryProperty() {
        return usedMemory;
    }

    @Override
    public String toString() {
        return "Image Name: " + name.get() + " PID: " + pid.get() + " Used Memory: " + usedMemory.get();
    }

    public static ProcessEntity parseProcess(String inputLine) {
        int imageNameColLength = 25;
        int pidColLength = 8;
        int sessionNameColLength = 16;
        int sessionColLength = 11;
        int usedMemoryColLength = 12;
        ProcessEntity process = new ProcessEntity();
        process.setName(inputLine.substring(0, imageNameColLength).trim());
        process.setPid(Integer.parseInt(inputLine.substring(imageNameColLength + 1, imageNameColLength + 1 + pidColLength).trim()));
        process.setUsedMemory(Long.parseLong(inputLine.substring(imageNameColLength + pidColLength +
                sessionNameColLength + sessionColLength + 4, imageNameColLength + pidColLength +
                sessionNameColLength + sessionColLength + 4 + usedMemoryColLength - 1).trim().replaceAll("\\W+", "")));
        return process;
    }
}
