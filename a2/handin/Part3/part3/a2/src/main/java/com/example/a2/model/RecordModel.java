package com.example.a2.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class RecordModel {

    private final ObservableList<Record> records;
    private final ArrayList<RecordModelSubscriber> subs;

    public RecordModel() {
        this.records = FXCollections.observableArrayList();
        this.subs = new ArrayList<>();
    }

    public void addSubscriber(RecordModelSubscriber sub) {
        this.subs.add(sub);
    }

    public void addRecord(String title, String project) {
        Record newRecord = new Record(title, project);
        this.records.add(newRecord);
        notifySubscribers();
    }

    public void removeRecord(int recordIndex) {
        this.records.get(recordIndex).stopTimer(); // don't use stopTimer since it will notify subs
        this.records.remove(recordIndex);
        notifySubscribers();
    }

    public void stopTimer(int recordIndex) {
        this.records.get(recordIndex).stopTimer();
        notifySubscribers();
    }

    private void notifySubscribers() {
        this.subs.forEach(RecordModelSubscriber::modelUpdated);
    }

    public ObservableList<Record> getRecords() {
        return records;
    }

}
