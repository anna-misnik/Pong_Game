import java.awt.*;

public class Ball {
    double xVel, yVel, x, y;

    public Ball(){
        x=350;
        y=250;
        xVel= getRandomSpeed()*getRandomDirection(); 
        yVel= getRandomSpeed()*getRandomDirection();
    }
    public double getRandomSpeed(){
        return  (Math.random()*3 +2);
    }
    public double getRandomDirection(){
        int rand = (int)(Math.random()*2);
        if(rand == 1)
        return 1;
        else
            return -1;
    }
    public void  draw(Graphics graphics){
        graphics.setColor(Color.white);
        graphics.fillOval((int)x-10, (int) y-10,20,20); //x,y represent center of the circle 20-10
    }

    public void checkCollision(Paddle p1, Paddle p2){ //input: positions of the bars
        if (x <= 50){ //radius of the ball is 10, the bar width is 20, and the side is another 20
          if(y >= p1.getY()&& y <= p1.getY() + 80){
              xVel = -xVel; //reverse direction
          }
        }
        else if (x >= 650){
            if(y >= p2.getY()&& y <= p2.getY() + 80){
                xVel = -xVel;
            }
        }
    }

    public void move(){
        x +=xVel;
        y += yVel;

        if(y<10) yVel = -yVel; //when the ball hits the top it starts go down
        if(y>490) yVel = -yVel;
    }

    public int getX(){
        return (int) x;
    }
    public int getY(){
        return (int) y;
    }
}
