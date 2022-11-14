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

    /**
     * Temporary storage for starting node and ending node to create a link
     */
    private SMStateNode startingNode, endingNode;



    private boolean linkDragging;

    // the ff boolean values below are used to manage tool buttons to help organize how controller runs!

    /**
     * Boolean signifying that a tool button is currently selected if its value is true, false otherwise
     */
    private Boolean buttonSelection;

    /**
     * Boolean indicating if the user can create a node in a canvas location, move a selected node, or select a node.
     * This is activated when the POINTER TOOL is selected to enable the user to manipulate a state machine node.
     * If set to false, the user can't create a node in a location, move a selected node, select a node
     */
    private Boolean nodeControl;

    /**
     * Boolean indicating if the user can create transition links (true) or not (false).
     * This is activated when the LINK TOOL is selected to enable the user to manipulate a state machine node.
     */
    private Boolean transitionLinkControl;


    /**
     * Constructor method
     */
    public InteractionModel() {
        selectedNode = null;  // saves the node selected by the user
        nodeControl = false;  // dictates when the user can manipulate state machine nodes, based on arrow tool button
        transitionLinkControl = false;  // dictates when the user can create links, based on link tool button

        startingNode = null;
        endingNode = null;

        toolsSubscribers = new ArrayList<>();
        canvasSubscribers = new ArrayList<>();
    }


    // methods used for publish-subscribe model


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


    // getter and setter methods for data stored in interaction model


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
     * The value of nodeControl is checked at the controller to perform selection, position, node creation
     * (ARROW TOOL BUTTON).
     * @return if true, user can manipulate node creation, position, selection; false otherwise
     */
    public Boolean getNodeControl() {
        return nodeControl;
    }

    /**
     * Change the value of the nodeControl variable (indicates if node interaction and manipulation is allowed on
     * ARROW TOOL BUTTON)
     * @param nodeControl new boolean value indicating if node movement, creation, selection is allowed
     */
    public void setNodeControl(Boolean nodeControl) {
        this.nodeControl = nodeControl;
    }

    /**
     * Retrieve the transitionLinkControl value that indicates if the link tool button is activated by the user (for
     * LINK TOOL BUTTON)
     * @return when true, user is able to create line links to state machine nodes, false otherwise
     */
    public Boolean getTransitionLinkControl() {
        return transitionLinkControl;
    }

    /**
     * Changes the value of transitionLinkControl variable indicating if the link tool button is activated by the user
     * (FOR LINK TOOL BUTTON)
     * @param transitionLinkControl if set to true, user can create line links to state machine nodes, false otherwise
     */
    public void setTransitionLinkControl(Boolean transitionLinkControl) {
        this.transitionLinkControl = transitionLinkControl;
    }

    public boolean isLinkDragging() {
        return linkDragging;
    }

    public void setLinkDragging(boolean linkDragging) {
        this.linkDragging = linkDragging;
    }

    public SMStateNode getStartingNode() {
        return startingNode;
    }

    public void setStartingNode(SMStateNode startingNode) {
        this.startingNode = startingNode;
    }

    public SMStateNode getEndingNode() {
        return endingNode;
    }

    public void setEndingNode(SMStateNode endingNode) {
        this.endingNode = endingNode;
    }
}
