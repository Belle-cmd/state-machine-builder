package com.example.asn3;

/**
 * The controller to handle events from the view classes
 */
public class AppController {

    /** Reference to the interaction model **/
    private InteractionModel iModel;

    public void setModel(SMModel model) {}

    public void setInteractionModel(InteractionModel newIModel) {
        this.iModel = newIModel;
    }

    // Methods called by the views

    /**
     *
     */
    public void handleButtonClick(boolean oldStatus) {
        iModel.setButtonStatus(oldStatus);
        System.out.println("now in controller! called by view");
    }
}
