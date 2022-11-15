package com.example.asn3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NodePropertiesView extends StackPane {
    /** Stores the new state name inputted by the user */
    private TextField textField;

    /** Reference to the interaction model class */
    private InteractionModel iModel;


    /**
     * Constructor method
     */
    public NodePropertiesView() {
        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(5));

        Label titleLbl = new Label("State");
        titleLbl.setAlignment(Pos.CENTER);
        titleLbl.setFont(new Font(30));
        titleLbl.setStyle("-fx-background-color: grey;");
        titleLbl.setMaxWidth(Double.MAX_VALUE);

        Label stateLbl = new Label("State Name: ");
        textField = new TextField();

        vBox.getChildren().addAll(titleLbl, stateLbl, textField);
        vBox.setStyle("-fx-background-color: white;");
        this.setPrefWidth(230);
        this.getChildren().add(vBox);
    }

    public void setInteractionModel(InteractionModel newIModel) {
        this.iModel = newIModel;
    }

    public void setController(AppController controller) {
        textField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                System.out.println("Enter Pressed in NodePropertiesView()");
                System.out.println("Canvas mouseX: "+ iModel.getCanvasMouseX());
                System.out.println("Canvas mouseY: " + iModel.getCanvasMouseY());
                controller.handleStateNameKeyPressEnter(textField, iModel.getCanvasMouseX(), iModel.getCanvasMouseY());
            }
        });
    }
}
