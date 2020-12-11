import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Ghost 
{
    private Color c;
    private int x, y, arcAngle, xv, yv, mouth;
    private Rectangle r;
    public Ghost(int xx, int yy)
    {
        c = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        x = xx;
        y = yy;
        xv = 10;
        yv = 0;

        r = new Rectangle(x, y, 56, 52);
    }

    public Rectangle getR()
    {
        return r;
    }

    public void drawGhost(Graphics2D gr)
    {
        gr.setColor(c);
        gr.fillRect(x,y+24,4,28);
        gr.fillRect(x+4,y+12,4,36);
        gr.fillRect(x+8,y+8,4,36);
        gr.fillRect(x+12,y+4,4,44);
        gr.fillRect(x+16,y+4,4,48);
        gr.fillRect(x+20,y,4,52);
        gr.fillRect(x+24,y,4,44);
        gr.fillRect(x+28,y,4,44);
        gr.fillRect(x+32,y,4,52);
        gr.fillRect(x+36,y+4,4,48);
        gr.fillRect(x+40,y+4,4,44);
        gr.fillRect(x+44,y+8,4,32);
        gr.fillRect(x+48,y+12,4,32);
        gr.fillRect(x+52,y+24,4,28);

    }

    public void move()
    {
        x += xv;
        y += yv;
        if(x>1360||x<-40||y>808||y<-40){x=625;y=350;}
        r.setLocation(x, y);
    }

    public void moveUp(){
        xv = 0;
        yv = -5;
        y+=yv;
    }

    public void moveDown(){
        xv = 0;
        yv = 5;
        y+=yv;
    }

    public void moveLeft(){
        xv = -5;
        yv = 0;
       x+=xv;
    }

    public void moveRight(){
        xv = 5;
        yv = 0;
        x+=xv;
    }

    public void goBack(Rectangle xr){
        x += (xv*-1);
        y += (yv*-1);
        while(r.intersects(xr))
        {
            int u = (int)(Math.random()*4);
            if(u==0){
             moveUp();
            } else if(u==1){
             moveDown();
            }else if(u==2){
             moveRight();
            }else if(u==3){
             moveLeft();
            }
            
            r.setLocation(x,y);

        }
    }
    public void eaten()
    {
     x= 625;
     y=500;
    }
}