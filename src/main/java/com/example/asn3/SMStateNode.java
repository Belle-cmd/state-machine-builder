package com.example.asn3;

/**
 * The model that stores all elements of the state machine defined in the editor
 */
public class SMStateNode {
    private SMModel model;

    private InteractionModel iModel;

    public SMStateNode() {}

    public void setModel(SMModel model) {
        this.model = model;
    }

    public void setInteractionModel(InteractionModel iModel) {
        this.iModel = iModel;
    }

    public void setController(AppController controller) {
    }
}
