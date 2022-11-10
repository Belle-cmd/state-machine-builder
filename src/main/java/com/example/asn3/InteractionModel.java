package com.example.asn3;

import java.util.ArrayList;

/**
 * The interaction model that stores all information related to the app’s interaction state
 */
public class InteractionModel {
    /**
     * List of subscribers that notify changes for the tools
     */
    private ArrayList<IModelListener> toolsSubscribers;

    /**
     * List of subscribers that notify changes for canvas
     * - need to separate objects from the tool palette and objects in the canvas to avoid the tool selection
     * and the canvas selections to both be affected by a single data alteration in the interaction model
     */
    private ArrayList<IModelListener> canvasSubscribers;


    /**
     * signify that a button is currently selected if its value is true, false otherwise
     */
    private Boolean buttonSelection;

    private SMStateNode selectedNode;



    /**
     * Constructor method
     */
    public InteractionModel() {
        selectedNode = null;
        toolsSubscribers = new ArrayList<>();
        canvasSubscribers = new ArrayList<>();
    }

    public void addToolSubscriber(IModelListener sub) {
        toolsSubscribers.add(sub);
    }

    public void addCanvasSubscriber(IModelListener sub) {
        canvasSubscribers.add(sub);
    }

    private void notifySubscribers() {
        toolsSubscribers.forEach(IModelListener::iModelChanged);
    }

    private void notifyCanvasSubscribers() {
        canvasSubscribers.forEach(IModelListener::iModelChanged);
    }



    // methods called by the controller

    /**
     * Called by the controller to store which tool gets selected
     */
    public void setButtonStatus(boolean oldStatus) {
        buttonSelection = oldStatus;  // change status of the button to selected if unselected at first (vice versa)
        notifySubscribers();
    }

    /**
     * Retrieves the value of the button status
     * @return boolean selection of a button (true = selected; false = unselected)
     */
    public Boolean getButtonSelection() {
        return buttonSelection;
    }

    /**
     * Saves the current node selected by the user in the canvas
     * @param n node selected by the user
     */
    public void setSelectedNode(SMStateNode n) {
        System.out.println("SELECTED NODE IS STORED IN IMODEL:" + n);
        selectedNode = n;
        notifyCanvasSubscribers();
    }

    /**
     * Get current selected node by the user
     * @return selected node object
     */
    public SMStateNode getSelectedNode() {
        return selectedNode;
    }

    public void unselectNode() {
        selectedNode = null;
    }
}
