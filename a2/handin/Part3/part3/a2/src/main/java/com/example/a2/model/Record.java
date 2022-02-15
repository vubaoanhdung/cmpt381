package com.example.a2.model;

import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Record {
    private final SimpleStringProperty start;
    private final SimpleStringProperty title;
    private final SimpleStringProperty project;
    private final SimpleStringProperty duration;

    int durationTime; // number representation of the duration column

    private final Timer timer = new Timer();
    private final TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            increaseDurationTime();
        }
    };

    public Record(String newTitle, String newProject) {

        this.start =  new SimpleStringProperty(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm - MMMM dd")));
        this.title = new SimpleStringProperty(newTitle);
        if (!newProject.equals("")) {
            this.project = new SimpleStringProperty(newProject);
        } else {
            this.project = new SimpleStringProperty("Personal");
        }
        this.durationTime = -1;
        this.duration = new SimpleStringProperty(Integer.toString(this.durationTime));

        startTimer();
    }

    private void startTimer() {
        this.timer.schedule(this.timerTask,0,60*1000);
    }

    public void stopTimer() {
        this.timer.cancel();
    }

    private void increaseDurationTime() {
        this.setDurationTime(this.durationTime + 1);
        this.setDuration(Integer.toString(this.durationTime));
    }

    @Override
    public String toString() {
        return "Record{" +
                "start=" + start +
                ", title=" + title +
                ", project=" + project +
                ", duration=" + duration +
                '}';
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public SimpleStringProperty projectProperty() {
        return project;
    }

    public SimpleStringProperty startProperty() {
        return start;
    }

    public SimpleStringProperty durationProperty() {
        return duration;
    }

}
