package com.example.asn3;

/**
 * The model that stores all elements of the state machine defined in the editor
 */
public class SMStateNode {
    /**
     *  dimensions of the node
     */
    double left, top, width, height;
    private String stateName;

    /**
     * Constructor method that creates the dimensions of the box
     * @param newLeft based on mouse click position
     * @param newTop based on mouse click position
     * @param newWidth new left pixel size
     * @param newHeight new left pixel size
     */
    public SMStateNode(double newLeft, double newTop, double newWidth, double newHeight) {
        this.left = newLeft;
        this.top = newTop;
        this.width = newWidth;
        this.height = newHeight;
        this.stateName = "Default";
    }


    /**
     * Moves the position of the node based on a new mouse position
     * @param dX
     * @param dY
     */
    public void move(double dX, double dY) {
        left += dX;
        top += dY;
    }

    /**
     * Checks if the user has clicked on a state machine node
     * @param x mouseX position
     * @param y mouseY position
     * @return boolean true if node is clicked by the user, false otherwise
     */
    public boolean checkNodeHitBox(double x, double y) {
        return x >= left && x <= left+width && y >= top && y <= top+height;
    }

    /**
     * Getter method for the state machine node's state name
     * @return string state name
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * Setter method for the state machine node's state name
     * @param stateName new string state name
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
