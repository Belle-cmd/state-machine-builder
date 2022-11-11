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
     * Current state machine node selected by the user
     */
    private SMStateNode selectedNode;

    // the ff boolean values below are used to manage tool buttons to help organize how controller runs!

    /**
     * Boolean signifying that a tool button is currently selected if its value is true, false otherwise
     */
    private Boolean buttonSelection;

    /**
     * Boolean indicating if the user can create a node in a canvas location, move a selected node, or select a node.
     * This is activated when the pointer tool is selected to enable the user to manipulate a state machine node.
     * If set to false, the user can't create a node in a location, move a selected node, select a node
     */
    private Boolean nodeControl;

    /**
     * Boolean indicating if the user can create transition links (true) or not (false).
     */
    private Boolean transitionLinkControl;


    /**
     * Constructor method
     */
    public InteractionModel() {
        selectedNode = null;
        nodeControl = false;
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

    /**
     * Deselect a state machine node
     */
    public void unselectNode() {
        selectedNode = null;
    }

    /**
     * The value of nodeControl is manipulated by the ToolPalette view based on the tool buttons.
     * The value of nodeControl is checked at the controller to perform selection, position, node creation.
     * @return if true, user can manipulate node creation, position, selection; false otherwise
     */
    public Boolean getNodeControl() {
        return nodeControl;
    }

    /**
     * Change the value of the nodeControl variable (indicates if node interaction and manipulation is allowed)
     * @param nodeControl new boolean value indicating if node movement, creation, selection is allowed
     */
    public void setNodeControl(Boolean nodeControl) {
        this.nodeControl = nodeControl;
    }

    /**
     *
     * @return
     */
    public Boolean getTransitionLinkControl() {
        return transitionLinkControl;
    }

    /**
     *
     * @param transitionLinkControl
     */
    public void setTransitionLinkControl(Boolean transitionLinkControl) {
        this.transitionLinkControl = transitionLinkControl;
    }
}
