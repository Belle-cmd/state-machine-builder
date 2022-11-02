package com.example.asn3;

import java.util.ArrayList;

/**
 * The interaction model that stores all information related to the app’s interaction state
 */
public class InteractionModel {
    private ArrayList<IModelListener> subscribers;

    /** signify that a button is currently selected if its value is true, false otherwise **/
    private Boolean buttonSelection;

    /**
     * Constructor method
     */
    public InteractionModel() {
        subscribers = new ArrayList<>();
    }

    public void addSubscriber(IModelListener sub) {
        subscribers.add(sub);
    }

    private void notifySubscribers() {
        subscribers.forEach(IModelListener::iModelChanged);
    }



    // methods called by the controller

    /**
     * Called by the controller to store which tool gets selected
     */
    public void setButtonStatus(boolean oldStatus) {
        buttonSelection = oldStatus;  // change status of the button to selected if unselected at first (vice versa)
        notifySubscribers();
        System.out.println("Now in iModel. Called by controller!");
    }

    /**
     * Retrieves the value of the button status
     * @return boolean selection of a button (true = selected; false = unselected)
     */
    public Boolean getButtonSelection() {
        return buttonSelection;
    }
}
