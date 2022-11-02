package com.example.asn3;

/**
 * The model that stores all elements of the state machine defined in the editor
 */
public class SMStateNode {
    double left, top, width, height;

    /**
     * Constructor method that creates the dimensions of the box
     * @param newLeft based on mouse click position
     * @param newTop based on mouse click position
     * @param newWidth new left pixel size
     * @param newHeight new left pixel size
     */
    public SMStateNode(double newLeft, double newTop, double newWidth, double newHeight) {
        left = newLeft;
        top = newTop;
        width = newWidth;
        height = newHeight;
    }

    public boolean checkHit(double x, double y) {
        return x >= left && x <= left+width && y >= top && y <= top+height;
    }

    public void move(double dX, double dY) {
        left += dX;
        top += dY;
    }
}
