/*INTERFACE
* methods by default abstract (only method signature, no body).
* - objects cannot be created;
* - multiple inheritance;
* - NO constructs;
* - all methods and variables are PUBLIC;
* - all variables are STATIC & PUBLIC
* - describes WHAT to do, NOT HOW;*/

import java.awt.*;

public interface Paddle {
    public void draw(Graphics g);
    public void move();
    public int getY();
}
