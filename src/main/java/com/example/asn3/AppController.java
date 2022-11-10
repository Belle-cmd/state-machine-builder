package com.example.asn3;

import javafx.scene.input.MouseEvent;

/**
 * The controller to handle events from the view classes
 */
public class AppController {
    /**
     * All the state of the program that relates to side effects, events and context
     */
    private enum State {
        READY, DRAGGING, PREPARE_CREATE
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
     * The current state of the program
     */
    private State currentState;

    /**
     * previous mouseX and mouseY positions
     */
    double prevX, prevY;


    /**
     * Constructor method
     */
    public AppController() {
        currentState = State.READY;
    }



    public void setModel(SMModel newModel) {
        this.model = newModel;
    }

    public void setInteractionModel(InteractionModel newIModel) {
        this.iModel = newIModel;
    }




    // Methods called by the views

    /**
     * Called by ToolPalette view class when the user selects a tool
     * @param oldStatus boolean
     */
    public void handleButtonClick(boolean oldStatus) {
        iModel.setButtonStatus(oldStatus);
        System.out.println("now in controller! called by view");
    }

    /**
     * Creates a new state machine box when the user clicks on an empty canvas
     * @param mouseEvent event
     * @param nx mouseX
     * @param ny mouseY
     */
    public void handlePressed(MouseEvent mouseEvent, double nx, double ny) {
        System.out.println("Now in handlePressed() in controller!");
        prevX = nx;
        prevY = ny;

        switch (currentState) {
            case READY -> {
                // context: user selected on a state machine node
                // side effect: set node selection

                // if a node is hit, set selection and move to new state

                if (model.checkHit(mouseEvent.getX(), mouseEvent.getY())) {
                    SMStateNode n = model.whichHit(mouseEvent.getX(),mouseEvent.getY());
                    iModel.setSelected(n);  // notifies the iModel about the new node selected

                    prevX = mouseEvent.getX();
                    prevY = mouseEvent.getY();

                    currentState = State.DRAGGING;  // move to a new state
                } else {
                    // context: user selected the canvas
                    // side effects: none

                    currentState = State.PREPARE_CREATE;  // move to a new state
                }
            }
        }
    }

    /**
     *
     * @param mouseEvent event
     * @param nx mouseX
     * @param ny mouseY
     */
    public void handleDragged(MouseEvent mouseEvent, double nx, double ny) {
        double dX = nx - prevX;
        double dY = ny - prevY;
        prevX = nx;
        prevY = ny;

        switch (currentState) {
            case PREPARE_CREATE -> {
                // go back to ready state since user just pressed the canvas (not a node) and dragged somewhere
                currentState = State.READY;
            }

            case DRAGGING -> {
                // update the coordinates to reposition the node
                model.moveNode(iModel.selectedNode, dX, dY);
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
        switch (currentState) {
            case PREPARE_CREATE -> {
                // user releases the mouse while holding a node; place node into the canvas
                // model will increase its blob which will initiate view to draw blob on canvas
                model.createNode(nx-0.05, ny-0.05, 0.1, 0.1);
                currentState = State.READY;
            }
            case DRAGGING -> {
                // user releases the mouse when it isn't holding a node, just go back to ready state
                iModel.unselectNode();
                currentState = State.READY;
            }
        }
    }
}
