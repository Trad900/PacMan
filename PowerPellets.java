import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class PowerPellets
{
    private Color c;
    private int x, y;
    private Rectangle r;
    private boolean visible;
    public PowerPellets(int xx, int yy)
    {
        c = new Color(228,54,216);
        x = xx;
        y = yy;
        visible = true;
        r = new Rectangle(x, y, 15, 15);
    }

    public Rectangle getR()
    {
        return r;
    }

    public void drawPellet(Graphics2D gr)
    {
        gr.setColor(c);
        if(visible)
            gr.fillOval(x, y, 30, 30);
    }

    public void eatPellet()
    {
        visible = false;
        r = new Rectangle(0,0,0,0);
    }
}
