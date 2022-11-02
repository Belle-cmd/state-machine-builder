package com.example.asn3;

import java.util.ArrayList;

/**
 * The interaction model that stores all information related to the app’s interaction state
 */
public class InteractionModel {
    private ArrayList<IModelListener> subscribers;

    public InteractionModel() {
        subscribers = new ArrayList<>();
    }

    public void addSubscriber(IModelListener sub) { // for version 2
        subscribers.add(sub);
    }

    private void notifySubscribers() {
        subscribers.forEach(s -> s.iModelChanged());
    }
}
