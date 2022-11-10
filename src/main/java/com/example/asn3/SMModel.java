package com.example.asn3;

import java.util.ArrayList;
import java.util.List;

/**
 * The model that stores all elements of the state machine defined in the editor
 */
public class SMModel {
    /**
     * list of state machine nodes created in the canvas
     */
    private List<SMStateNode> nodes;

    /**
     * list of subscribers that listen for changes in this model
     */
    private List<SMModelListener> subscribers;


    /**
     * Constructor method
     */
    public SMModel() {
        subscribers = new ArrayList<>();
        nodes = new ArrayList<>();
    }


    public void addSubscriber(SMModelListener sub) {
        subscribers.add(sub);
    }

    private void notifySubscribers() {
        subscribers.forEach(SMModelListener::modelChanged);
    }

    /**
     * Checks if a node has been selected by the user or not
     * @param x mouseX position
     * @param y mouseY position
     * @return
     */
    public boolean checkHit(double x, double y) {
        for (SMStateNode n : nodes) {
            if (n.contains(x, y)) return true;
        }
        return false;
    }

    /**
     * Finds which node has been selected
     * @param x mouseX position
     * @param y mouseY position
     * @return the node object that the user selected, otherwise null
     */
    public SMStateNode whichHit(double x, double y) {
        for (SMStateNode n : nodes) {
            if (n.contains(x,y)) return n;
        }
        return null;
    }

    /**
     * Returns all the state machine nodes created in the canvas
     * @return list of nodes
     */
    public List<SMStateNode> getNodes() {
        return nodes;
    }

    /**
     * Create a new state machine node
     * @param x mouseX position where node will be created
     * @param y mouseY position where node will be created
     */
    public void createNode(double left, double top, double width, double bottom) {
        nodes.add(new SMStateNode(left, top, width, bottom));
        notifySubscribers();
    }


}
