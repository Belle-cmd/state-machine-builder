package com.example.asn3;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ToolButton extends Button {
    public ToolButton(String imgName) {
        ImageView view = new ImageView(new Image(imgName));
        view.setFitHeight(40);
        view.setPreserveRatio(true);

        this.setGraphic(view);
    }

    /**
     *
     */
    public void select() {
        this.setPadding(new Insets(5));
    }

    /**
     *
     */
    public void unselect() {
        this.setPadding(new Insets(0));
    }
}
