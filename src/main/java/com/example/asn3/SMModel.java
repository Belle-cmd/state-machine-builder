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
     * @return boolean true if node is selected, false if not
     */
    public boolean checkHit(double x, double y) {
        return nodes.stream().anyMatch(n -> n.checkNodeHitBox(x, y));
    }

    /**
     * Find which node has been selected
     * @param x mouseX position
     * @param y mouseY position
     * @return the node object that the user selected, otherwise null
     */
    public SMStateNode whichNode(double x, double y) {
        SMStateNode found = null;
        for (SMStateNode n : nodes) {
            if (n.checkNodeHitBox(x,y)) found = n;
        }
        return found;
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
     */
    public void createNode(double left, double top, double width, double bottom) {
        nodes.add(new SMStateNode(left, top, width, bottom));
        notifySubscribers();
    }

    /**
     * Move the state machine node to a new position when the user is dragging the node
     * @param n node to be moved
     * @param dX mouseX position
     * @param dY mouseY position
     */
    public void moveNode(SMStateNode n, double dX, double dY) {
        n.move(dX, dY);
        notifySubscribers();
    }


}
