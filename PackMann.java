import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class PackMann
{
    private Color c;
    private int x, y, arcAngle, xv, yv, mouth;
    private Rectangle r;
    public PackMann(int xx, int yy)
    {
        c = Color.yellow;
        x = xx;
        y = yy;
        xv = 10;
        yv = 0;
        mouth = 6;
        arcAngle = 321;
        r = new Rectangle(x, y, 40, 40);
    }

    public Rectangle getR()
    {
        return r;
    }

    public void drawPacMan(Graphics2D gr)
    {
        gr.setColor(c);
        if(xv>0)
            gr.fillArc(x, y, 40, 40, 0, arcAngle);
        if(xv<0)
            gr.fillArc(x, y, 40, 40, 180, arcAngle);
        if(yv>0)
            gr.fillArc(x, y, 40, 40, 270, arcAngle);
        if(yv<0)
            gr.fillArc(x, y, 40, 40, 90, arcAngle);

        arcAngle += mouth;
        if(arcAngle >360 || arcAngle<320)
            mouth *= -1;
    }
    public void lose(){x=100000;y=30000;}

    public void move()
    {
        x += xv;
        y += yv;
        
        if(x<0)
        {
            x = 1325;
        }
        if(x>1325)
        {
            x = 0;
        }
        if(y<0)
        {
            y = 1325;
        }
        if(y>718)
        {
            y = 0;
        }
        r.setLocation(x, y);
    }

    public void moveUp(){
        xv = 0;
        yv = -10;
    }

    public void moveDown(){
        xv = 0;
        yv = 10;
    }

    public void moveLeft(){
        xv = -10;
        yv = 0;
    }

    public void moveRight(){
        xv = 10;
        yv = 0;
    }

    public void moveBack(Rectangle xr){
        while(r.intersects(xr))
        {
            x-=xv;
            y-=yv;
            r.setLocation(x,y);

        }
    }
}