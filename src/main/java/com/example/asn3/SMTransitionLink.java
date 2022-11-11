package com.example.asn3;

/**
 * A class to represent a transition link in the diagram
 */
public class SMTransitionLink {
    /**
     * x coordinate of a line (x1,y1 = line starting coordinate; x2, y2 = line ending coordinate)
     */
    double x1,y1,x2,y2;

    /**
     * coordinates needed to perform rotation, scaling, translation
     */
    double tx1, ty1, tx2, ty2;

    /**
     * Translation coordinate
     */
    double translateX, translateY;

    /**
     * angle of rotation in radians
     */
    double rotateRadians;

    /**
     * ratio scale between the original object and the new object
     * - larger value means the object became X times larger than its original size
     * - smaller value means the object became X times smaller than its original size
     */
    double scaleFactor;

    /**
     * Values used to find if a mouse is closed enough to be considered selecting a line
     */
    double ratioA, ratioB, ratioC;

    double hitWidth;

    double length;


    /**
     * Constructor method that creates a line
     * @param nx1 starting x coordinate
     * @param ny1 starting y coordinate
     * @param nx2 ending x coordinate
     * @param ny2 ending y coordinate
     */
    public SMTransitionLink(double nx1, double ny1, double nx2, double ny2) {
        // calculate the midpoint
        translateX = (nx1 + nx2) / 2;
        translateY = (ny1 + ny2) / 2;

        // line coordinates
        x1 = nx1 - translateX;
        y1 = ny1 - translateY;
        x2 = nx2 - translateX;
        y2 = ny2 - translateY;

        // values used for selection detection, scaling, rotating
        hitWidth = 10;
        scaleFactor = 1.0;
        rotateRadians = 0.0;
    }



    /**
     * Checks if the specified mouse position is close enough to select the object
     * Uses
     * @param mx mouseX position
     * @param my mouseY position
     * @return true if the mouse is on the line, false otherwise
     */
    public boolean contains(double mx, double my) {
        double distToLine = Math.abs(distanceFromLine(mx,my));
        double distToP1 = dist(mx,my,tx1,ty1);
        double distToP2 = dist(mx,my,tx2,ty2);
        return (distToLine < hitWidth && distToP1 <= length && distToP2 <= length);
    }


    // helper methods for checking if the mouse is on a line


    /**
     * Calculates the distance of the mouse coordinates to a line coordinate
     * @param x1 starting x coordinate
     * @param y1 starting y coordinate
     * @param x2 ending x coordinate
     * @param y2 ending y coordinate
     * @return Returns 0 for points on the line; returns negative for points to the left or above the line; returns
     * positive for points to the right or below the line
     */
    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
    }

    /**
     * Calculates the ratio signifying the distance from a point to an object based on the length of the line
     * @param mx
     * @param my
     * @return distance from a point to an object
     */
    private double distanceFromLine(double mx, double my) {
        return ratioA * mx + ratioB * my + ratioC;
    }

    /**
     * Calculates if the mouse is close enough to be considered selecting a line.
     * Tells how far away we are and if we're above or below, depending on the output.
     */
    private void calculateRatios() {
        length = dist(tx1,ty1,tx2,ty2);
        ratioA = (ty1-ty2) / length;
        ratioB = (tx2-tx1) / length;
        ratioC = -1 * ((ty1-ty2) * tx1 + (tx2-tx1) * ty1) / length;
    }



    // methods for scaling, rotating, translating


    /**
     * Rotates a line, 1 coordinate unit at a time.
     * By the end of this function, all coordinates of the line will be rotated (start point and end point).
     */
    private void rotate() {
        tx1 = Math.cos(rotateRadians) * x1 - Math.sin(rotateRadians) * y1;
        ty1 = Math.sin(rotateRadians) * x1 + Math.cos(rotateRadians) * y1;
        tx2 = Math.cos(rotateRadians) * x2 - Math.sin(rotateRadians) * y2;
        ty2 = Math.sin(rotateRadians) * x2 + Math.cos(rotateRadians) * y2;
    }

    /**
     * Scale a line through coordinates
     */
    private void scale() {
        tx1 *= scaleFactor;
        ty1 *= scaleFactor;
        tx2 *= scaleFactor;
        ty2 *= scaleFactor;
    }

    /**
     * Move a line to a new position through coordinates
     */
    private void translate() {
        tx1 += translateX;
        ty1 += translateY;
        tx2 += translateX;
        ty2 += translateY;
    }
}
