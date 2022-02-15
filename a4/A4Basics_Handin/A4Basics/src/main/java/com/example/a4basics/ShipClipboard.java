package com.example.a4basics;

import java.util.ArrayList;

public class ShipClipboard {
    ArrayList<Groupable> items;

    public ShipClipboard() {
        items = new ArrayList<>();
    }

    private void clearClipboard() {
        items = new ArrayList<>();
    }

    private void setItems(ArrayList<Groupable> newItems) {
        clearClipboard();
        newItems.forEach(item -> {
            items.add(item.duplicate());
        });
    }

    public ArrayList<Groupable> getItems() {
        ArrayList<Groupable> result = new ArrayList<>();
        items.forEach(item -> {
            result.add(item.duplicate());
        });
        return result;
    }

    public void cut(ArrayList<Groupable> selectedItems) {
        setItems(selectedItems);
    }

    public void copy(ArrayList<Groupable> selectedItems) {
        setItems(selectedItems);
    }
}
