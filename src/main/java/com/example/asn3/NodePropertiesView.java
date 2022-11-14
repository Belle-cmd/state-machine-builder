package com.example.asn3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class NodePropertiesView extends StackPane {
    public NodePropertiesView() {
        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(5));

        Label titleLbl = new Label("State");
        titleLbl.setAlignment(Pos.CENTER);
        titleLbl.setFont(new Font(30));
        titleLbl.setStyle("-fx-background-color: grey;");
        titleLbl.setMaxWidth(Double.MAX_VALUE);

        Label stateLbl = new Label("State Name: ");
        TextField textField = new TextField();

        vBox.getChildren().addAll(titleLbl, stateLbl, textField);
        vBox.setStyle("-fx-background-color: white;");
        this.getChildren().add(vBox);
    }
}
