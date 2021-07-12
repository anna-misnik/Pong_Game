import java.awt.*;

public class AIPaddle implements Paddle{
    double y, yVel;
    boolean upAccel, downAccel; //moving down or up faster
    int player, x; //player1 or player2
    final double GRAVITY = 0.94;
    Ball ball;

    //CONSTRUCTOR
    public AIPaddle(int player, Ball b){
        upAccel = false; downAccel = false;
        ball = b;
        y = 210; yVel = 0; //center postion and ball not moving
        if(player==1) x=20; //player 1 is on the left side of the applet
        else x = 660; //player 2 is on the right side of the applet
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, (int) y, 20, 80); //verticle paddle
    }

    @Override
    public void move() {
        y = ball.getY()-40;

        //handling screen issue
        //if paddle gone off the top of the applet
        if(y < 0){y = 0;}
        //height of the bar is 80 and applet's height is 500
        if(y > 420){ y = 420; }
    }

    @Override
    public int getY() {
        return (int) y;
    }

}
