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
     */
    public void handleButtonClick(boolean oldStatus) {
        iModel.setButtonStatus(oldStatus);
        System.out.println("now in controller! called by view");
    }

    /**
     * Creates a new state machine box when the user clicks on an empty canvas
     */
    public void handlePressed(MouseEvent mouseEvent, double nx, double ny) {
        prevX = nx;
        prevY = ny;

        switch (currentState) {
            case READY -> {
                // context: selection on a state machine node
                // side effect: set node selection
                if (model.checkHit(mouseEvent.getX(), mouseEvent.getY())) {
                    SMStateNode n = model.whichHit(mouseEvent.getX(),mouseEvent.getY());
                    iModel.setSelected(n);  // notifies the iModel about the new node selected
                    prevX = mouseEvent.getX();
                    prevY = mouseEvent.getY();

                    // move eto the new state
                    currentState = State.DRAGGING;
                } else {
                    currentState = State.PREPARE_CREATE;
                }
            }
        }
    }


    public void handleDragged(MouseEvent mouseEvent, double nx, double ny) {

    }
    public void handleReleased(MouseEvent mouseEvent, double nx, double ny) {

    }
}
