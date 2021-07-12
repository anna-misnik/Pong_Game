import java.awt.*;

public class HumanPaddle implements Paddle{
    double y, yVel;
    boolean upAccel, downAccel; //moving down or up faster
    int player, x; //player1 or player2
    final double GRAVITY = 0.94;

    //CONSTRUCTOR
    public HumanPaddle(int player){
        upAccel = false; downAccel = false;
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
    if(upAccel){
        yVel -= 2;
    }
    else if(downAccel){
        yVel +=2;
    }
    else if (!upAccel && !downAccel){ //if not moving, paddle should slow down
        yVel *= GRAVITY;
    }
    //handling speed issue
    if(yVel >= 5){ yVel = 5; }
    else if (yVel <= -5){ yVel= -5; }

    y += yVel; //y represents the position

    //handling screen issue
        //if paddle gone off the top of the applet
        if(y < 0){y = 0;}
        //height of the bar is 80 and applet's height is 500
        if(y > 420){ y = 420; }
    }

    public void setUpAccel(boolean upAccel) {
        this.upAccel = upAccel;
    }

    public void setDownAccel(boolean downAccel) {
        this.downAccel = downAccel;
    }

    @Override
    public int getY() {
        return (int) y;
    }

}
