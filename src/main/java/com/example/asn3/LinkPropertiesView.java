package com.example.asn3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LinkPropertiesView extends StackPane {
    public LinkPropertiesView() {
        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(5));

        Label titleLbl = new Label("Transition");
        titleLbl.setAlignment(Pos.CENTER);
        titleLbl.setFont(new Font(30));
        titleLbl.setStyle("-fx-background-color: grey;");
        titleLbl.setPrefWidth(Double.MAX_VALUE);

        Label eventLbl = new Label("Event");
        TextField eventTxtFld = new TextField();

        Label contextLbl = new Label("Context");
        TextArea contextTxtArea = new TextArea();

        Label sfxLbl = new Label("Side Effects:");
        TextArea sfxTxtArea = new TextArea();

        Button updateBtn = new Button("Update");

        vBox.getChildren().addAll(
                titleLbl, eventLbl, eventTxtFld, contextLbl, contextTxtArea, sfxLbl, sfxTxtArea, updateBtn);
        vBox.setStyle("-fx-background-color: white;");
        this.setPrefWidth(230);
        this.getChildren().add(vBox);
    }
}
