package com.example.a4basics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class ShipModel {
    public ArrayList<Groupable> items;
    ArrayList<ShipModelSubscriber> subscribers;
    private int nextZ;

    public ShipModel() {
        subscribers = new ArrayList<>();
        items = new ArrayList<>();
        this.nextZ = 0;
    }

    public Ship createShip(double x, double y) {
        Ship s = new Ship(x,y, nextZ++);
        items.add(s);
        notifySubscribers();
        return s;
    }

    public void createShipGroup(ShipGroup g) {
        items.add(g);
        notifySubscribers();
    }

    public Optional<Groupable> detectHit(double x, double y) {
        return items.stream().filter(s -> s.contains(x, y)).reduce((first, second) -> second);
    }

    public void moveItem(Groupable item, double dX, double dY) {
        item.move(dX,dY);
        notifySubscribers();
    }

    public void addSubscriber (ShipModelSubscriber aSub) {
        subscribers.add(aSub);
    }

    private void notifySubscribers() {
        subscribers.forEach(sub -> sub.modelChanged());
    }

    public void raiseItem(Groupable item) {
        item.setZ(nextZ++);
        items.sort(Comparator.comparingInt(Groupable::getZ));
        notifySubscribers();
    }

    public ArrayList<Groupable> getItemsContainWithin(double left, double right, double top, double bottom) {
        ArrayList<Groupable> result = new ArrayList<>();
        items.forEach(s-> {
            if (s.isContained(left, right, top, bottom)) {
                result.add(s);
            }
        });
        return result;
    }
}
