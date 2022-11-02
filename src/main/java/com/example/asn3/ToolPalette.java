package com.example.asn3;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * A view that contains buttons for showing and selecting an editor tool
 */
public class ToolPalette extends StackPane implements IModelListener {
    private InteractionModel iModel;

    ArrayList<ToolButton> buttons;

    public ToolPalette() {
        VBox root = new VBox();
        buttons = new ArrayList<>();

        ToolButton selectTool = new ToolButton("arrow.png");
        buttons.add(selectTool);
        ToolButton linkTool = new ToolButton("link.png");
        buttons.add(linkTool);
        ToolButton panTool = new ToolButton("pan.png");
        buttons.add(panTool);

        root.getChildren().addAll(buttons);
        this.getChildren().add(root);
    }

    public void setInteractionModel(InteractionModel im) {
        iModel = im;
    }

    public void setController(AppController controller) {}

    public void iModelChanged() {}

    public void init() {
    }
}
