/*This code was implemented by following the tutorial:
* https://www.youtube.com/watch?v=xIqeK2hzx1I&t=450s
* ANNA MISNIK 12.07.2021*/
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Image;
/* ABOUT THE APPLET
A main() method is not invoked on an applet, and an applet class will not define main().
* When a user views an HTML page that contains an applet,
* the code for the applet is downloaded to the user's machine.*/

public class Tennis extends Applet implements Runnable, KeyListener {
    final int HEIGHT = 500, WIDTH = 700; //size of the playground
    Thread thread;
    HumanPaddle player1; //human player
    Ball b1; //the ball
    AIPaddle player2; //computer as the 2nd player
    boolean gameStarted; //press ENTER button - game starts
    Graphics gfx; //those 2 needed to fix the blinking issue
    Image img; //

    public void init() {
        this.resize(WIDTH, HEIGHT); //size of the applet
        gameStarted=false; //when the applet starts the actual game is in STOPPED position
        this.addKeyListener(this); //check keyboard events
        player1 = new HumanPaddle(1);
        b1 = new Ball();
        img = createImage(WIDTH,HEIGHT);
        gfx = img.getGraphics();
        player2 = new AIPaddle(2, b1);
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics graphics) {
        gfx.setColor(Color.black); //background
        gfx.fillRect(0, 0, WIDTH, HEIGHT); //size to be filled with the color

        //check if the ball is outside the side borders
        // if yes, then GAME OVER is displayed
        if(b1.getX() < -10 || b1.getX() > 710){ //take the size of the playground and add the radius of the ball
            gfx.setColor(Color.red);
           gfx.drawString("Game Over", 350, 250);
        }
        //the actual game
        else{
            player1.draw(gfx);
            b1.draw(gfx);
            player2.draw(gfx);
        }
        //the start of the game
        if(!gameStarted){
            gfx.setColor(Color.white);
            gfx.drawString("Tennis", 340, 100);
            gfx.drawString("Press ENTER to begin...", 310, 130);
        }
        graphics.drawImage(img,0,0,this);
    }

    public void update(Graphics graphics) {
        paint(graphics); //every time it updates, it paints
    }

    @Override
    public void run() {
        while (true) { //infinite loop
            if(gameStarted) {
                //player and the ball should move
                player1.move();
                player2.move();
                b1.move();
                b1.checkCollision(player1, player2);
            }
            repaint(); //performs a redraw of the components
            try {
                Thread.sleep(10); //pauses current execution for a specific period. allows other thread or application to run
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_UP){ //if I press the UP key, then do this...
            player1.setUpAccel(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            player1.setDownAccel(true);
        }
        else if (e.getKeyCode()==KeyEvent.VK_ENTER){
            gameStarted=true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_UP){ //if I press the UP key, then do this...
            player1.setUpAccel(false);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            player1.setDownAccel(false);
        }
    }
}
