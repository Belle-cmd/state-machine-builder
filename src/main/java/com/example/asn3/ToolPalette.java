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
        root.setStyle("-fx-background-color: white;");
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

    public void setController(AppController controller) {
        buttons.forEach(b -> {
            b.setOnAction(e -> {
                b.setButtonStatus(true);
                controller.handleButtonClick(b.getButtonStatus());
            });
        });
    }

    public void iModelChanged() {
        buttons.forEach(b -> {
            b.unselect();
            if (b.getButtonStatus() == iModel.getButtonSelection()) {
                b.select();
            }
            b.setButtonStatus(false);  // enable only 1 tool mode to be selected
        });
    }

    public void init() {
        buttons.get(0).fire();
    }
}
