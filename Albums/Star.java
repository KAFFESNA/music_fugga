/* Based on the ecs 100 template
 * Code for DTC 
 * Name: Nathan Kaffes
 * Date: 3/09/2020
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 * Class star will print stars on the screen during a recommendation.
 *
 * @author Nathan Kaffes
 * @version 0.01
 */
public class Star {
    //Where to place the stars on the screen
    private double posX, posY;
    //How big the star is
    private static final int SIZE = 50;
    private static final int BUFFER = 25;
    /**
     * Constructor for objects of class Star
     */
    public Star(double x, double y) {
        this.posX = x;
        this.posY = y;
        this.drawStar(); //when the star is created, draw itself
    }
    
    /**
     * will draw a star 3 times on the recommendation screen
     */
    public void drawStar() {
        UI.drawImage("img\\star.png", this.posX, this.posY, SIZE, SIZE);
    }
    
    /**
     * Returns true and the co-ordinates if clicked on a star
     */
    public boolean onStar(double x, double y){
        if (x >= this.posX && x <= this.posX + SIZE && y >= this.posY && y <= this.posY + SIZE){
            return true;
        } else {
            return false;
        }
    }
}
