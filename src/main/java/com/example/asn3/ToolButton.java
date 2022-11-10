package com.example.asn3;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ToolButton extends Button {
    /** boolean indicating if the button is selected (true) or not (false) **/
    private Boolean buttonStatus;

    private Background selectedBG;
    private Background unselectedBG;

    private String toolName;

    /**
     * Constructor method
     * @param imgName name of image file that gets attached to the button
     * @param newToolName name of tool
     */
    public ToolButton(String imgName, String newToolName) {
        super();

        // add image to button
        ImageView view = new ImageView(new Image(imgName));
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        this.setGraphic(view);

        // add colour to button
        Color color = Color.web("#36c7c4");
        Color darkColor = Color.web("#22a19e");
        selectedBG = new Background(new BackgroundFill(darkColor,new CornerRadii(10),null));
        unselectedBG = new Background(new BackgroundFill(color,new CornerRadii(10),new Insets(5,5,5,5)));
        setBackground(unselectedBG);

        this.toolName = newToolName;

        this.buttonStatus = false;
    }

    /**
     * Enlarge button insets
     */
    public void select() {
        setBackground(selectedBG);
    }

    /**
     * Minimize button insets
     */
    public void unselect() {
        setBackground(unselectedBG);
    }

    /**
     * Shows the boolean value indicating if the button is currently selected or not
     * @return boolean value; if true, button is selected and false otherwise
     */
    public Boolean getButtonStatus() {
        return this.buttonStatus;
    }

    /**
     * Changes the boolean status of the button selection.
     * @param buttonStatus If true = button is selected, false otherwise
     */
    public void setButtonStatus(Boolean buttonStatus) {
        this.buttonStatus = buttonStatus;
    }

    public String getToolName() {
        return toolName;
    }
}
