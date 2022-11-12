package com.example.asn3;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

/**
 * The controller to handle events from the view classes
 */
public class AppController {
    /**
     * All the state of the program that relates to the nodes' side effects, events and contexts
     */
    private enum NodeState {
        READY, DRAGGING, PREPARE_CREATE
    }

    /**
     * All the state of the program that relates to the transition links' side effects, events, and contexts
     */
    private enum LinkState {
        READY, PREPARE_CREATION, MOVING, FINISH_CREATION
    }

    /**
     * Reference to the interaction model
     */
    private InteractionModel iModel;

    /**
     * Reference to the model for the state machines
     */
    private SMModel model;

    /**
     * The current state of the program's state machine nodes, based on state machine model
     */
    private NodeState currentNodeState;

    /**
     * The current state of the program's transition links, based on state machine model
     */
    private LinkState currentLinkState;

    /**
     * previous mouseX and mouseY positions for state machine nodes
     */
    double prevX, prevY;

    /**
     * previous mouseX nad mouseY position for line links
     */
    double prevLineX, prevLineY;


    /**
     * Constructor method
     */
    public AppController() {
        currentNodeState = NodeState.READY;
    }



    public void setModel(SMModel newModel) {
        this.model = newModel;
    }

    public void setInteractionModel(InteractionModel newIModel) {
        this.iModel = newIModel;
    }




    // Methods called by the palette view

    /**
     * Called by ToolPalette view class when the user selects a tool
     * @param oldStatus boolean
     */
    public void handleButtonClick(boolean oldStatus) {
        iModel.setButtonStatus(oldStatus);
    }

    /**
     * Perform tool operations at tool button click while changing mouse cursor.
     * Set variable values based on the user's chosen tool.
     * @param scene current scene
     * @param toolName
     */
    public void ToggleTool(Scene scene, String toolName) {
        if (Objects.equals(toolName, "arrow")) {
            if (!iModel.getNodeControl()) iModel.setNodeControl(true);
            if (iModel.getTransitionLinkControl()) iModel.setTransitionLinkControl(false);
            scene.setCursor(Cursor.DEFAULT);

        } else if (Objects.equals(toolName, "pan")) {
            if (iModel.getNodeControl()) iModel.setNodeControl(false);
            if (iModel.getTransitionLinkControl()) iModel.setTransitionLinkControl(false);
            scene.setCursor(Cursor.MOVE);

        } else if (Objects.equals(toolName, "link")) {
            if (iModel.getNodeControl()) iModel.setNodeControl(false);
            if (!iModel.getTransitionLinkControl()) iModel.setTransitionLinkControl(true);
            scene.setCursor(Cursor.CROSSHAIR);
        }
    }



    // methods called by the diagram view, where canvas is in


    /**
     * Differentiates between which state machine model should be triggered based on the active tool button
     * @param mouseEvent triggered mouse event
     * @param nx mouse X coordinate
     * @param ny mouse Y coordinate
     */
    public void handleCanvasPressed(MouseEvent mouseEvent, double nx, double ny) {
        // only run this code for state machine node when nodeControl is set to true
        if (iModel.getNodeControl()) {
            nodeHandlePress(mouseEvent, nx, ny);
        }

        // THIS IS NULL RIGHT NOW FIX IT ASAP
        if (iModel.getTransitionLinkControl()) {
            System.out.println("transitionLinkControl status: " + iModel.getTransitionLinkControl());
            linkHandlePress(mouseEvent, nx, ny);
        }
    }



    /**
     * Starts creating a line at starting point when the user clicks on a node
     * @param mouseEvent triggered mouse event
     * @param nx mouse X coordinate
     * @param ny mouse Y coordinate
     */
    private void linkHandlePress(MouseEvent mouseEvent, double nx, double ny) {
        prevLineX = nx;
        prevLineY = ny;

        switch (currentLinkState) {
            case READY -> {
                boolean nodeHit = model.checkHit(nx, ny);
                if (nodeHit) {
                    // the mouse clicked on an existing node
                    SMStateNode n = model.whichNode(nx,ny);
                    iModel.setSelectedNode(n);  // notifies the iModel about the new node selected


                }
            }

            case PREPARE_CREATION -> {

            }
        }
    }

    /**
     * Creates a new state machine node when the user clicks on an empty canvas
     * @param mouseEvent event
     * @param nx mouseX
     * @param ny mouseY
     */
    private void nodeHandlePress(MouseEvent mouseEvent, double nx, double ny) {
        prevX = nx;
        prevY = ny;

        switch (currentNodeState) {
            case READY -> {
                // context: user selected on a state machine node
                // side effect: set node selection

                // if a node is hit, set selection and move to new state
                boolean nodeHit = model.checkHit(nx, ny);
                if (nodeHit) {
                    SMStateNode n = model.whichNode(nx,ny);
                    iModel.setSelectedNode(n);  // notifies the iModel about the new node selected

                    currentNodeState = NodeState.DRAGGING;  // move to a new state
                } else {
                    // context: user selected the canvas
                    // side effects: none
                    currentNodeState = NodeState.PREPARE_CREATE;  // move to a new state
                }
            }
        }
    }



    /**
     * Manages the dragging state and prepare_create state. During dragging state, nodes being dragged can be
     * moved. If the user dragged on a canvas instead of a node, nothing occurs and the state simply switched to
     * the ready state.
     * @param mouseEvent event
     * @param nx mouseX
     * @param ny mouseY
     */
    public void handleDragged(MouseEvent mouseEvent, double nx, double ny) {
        // only run this code when nodeControl is set to true
        if (!iModel.getNodeControl()) {return;}

        double dX = nx - prevX;
        double dY = ny - prevY;
        prevX = nx;
        prevY = ny;

        switch (currentNodeState) {
            case PREPARE_CREATE -> {
                // go back to ready state since user just pressed the canvas (not a node) and dragged somewhere
                currentNodeState = NodeState.READY;
            }

            case DRAGGING -> {
                // update the coordinates to reposition the node
                model.moveNode(iModel.getSelectedNode(), dX, dY);
            }
        }
    }

    /**
     * Manages the dragging state release event (when the user is dragging a node and has released the mouse, so
     * that the node will be set in place in the canvas) and prepare_create state's release event (when the user
     * has clicked on the canvas/node; if released on the canvas, a new node is created, otherwise place the node
     * on the canvas)
     * @param mouseEvent event
     * @param nx mouseX
     * @param ny mouseY
     */
    public void handleReleased(MouseEvent mouseEvent, double nx, double ny) {
        // only run this code when nodeControl is set to true
        if (!iModel.getNodeControl()) {return;}

        switch (currentNodeState) {
            case PREPARE_CREATE -> {
                // user releases the mouse while holding a node; place node into the canvas
                // model will increase its blob which will initiate view to draw blob on canvas
                model.createNode(nx-0.05, ny-0.05, 0.1, 0.1);
                currentNodeState = NodeState.READY;
            }
            case DRAGGING -> {
                // user releases the mouse when it isn't holding a node, just go back to ready state
                iModel.unselectNode();
                currentNodeState = NodeState.READY;
            }
        }
    }
}
