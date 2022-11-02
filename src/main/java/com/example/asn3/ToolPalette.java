package com.example.asn3;

import javafx.scene.layout.StackPane;

/**
 * A view that contains buttons for showing and selecting an editor tool
 */
public class ToolPalette extends StackPane implements IModelListener {
    private InteractionModel iModel;

    public ToolPalette() {

    }

    public void setInteractionModel(InteractionModel im) {
        iModel = im;
    }

    public void setController(AppController controller) {}

    public void iModelChanged() {}

    public void init() {
    }
}
