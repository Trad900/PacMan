import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class PacManage extends JFrame implements Runnable, KeyListener
{
    Container con = getContentPane();
    Thread t = new Thread(this);
    PackMann p;
    Ghost gh4,gh1,gh2,gh3;
    ArrayList<GoldCoin> coins = new ArrayList<GoldCoin>();
    ArrayList<PowerPellets> pellets = new ArrayList<PowerPellets>();
    ArrayList<Rectangle> barriers = new ArrayList<Rectangle>();
    private int cx, cy, g, r;
    public PacManage()
    {
        p = new PackMann(200, 200);
        gh1 = new Ghost(625,350);
        gh2 = new Ghost(625,350);
        gh3 = new Ghost(625,350);
        gh4 = new Ghost(625,350);

        cx = 25;
        cy = 100;
        for(int f = 0; f < 13; f++)
        {
            for(int g = 0; g < 7; g++)
            {
                coins.add(new GoldCoin(cx, cy));
                cy+=100;
            }
            cx+=100;
            cy = 100; 
        } 

        cx = 150;
        cy = 75;
        for (int f = 0; f < 1; f++)
        {
            for(g = 0; g < 4; g++)
            {
                pellets.add(new PowerPellets(cx,cy));
                cx += 250;
                cy += 150;
            }
            cx += 200;
            cy += 100;
        }
        //top and bottom
        barriers.add(new Rectangle(20, 40, 1280, 10));
        barriers.add(new Rectangle(20, 720, 1280, 10));
        //left walls
        barriers.add(new Rectangle(20, 50, 10, 200));
        barriers.add(new Rectangle(20, 520, 10, 200));
        //right walls
        barriers.add(new Rectangle(1290, 50, 10, 200));
        barriers.add(new Rectangle(1290, 520, 10, 200));
        //indents left
        barriers.add(new Rectangle(20, 250, 150, 10));
        barriers.add(new Rectangle(170, 250, 10, 100));
        barriers.add(new Rectangle(20, 340, 150, 10));

        barriers.add(new Rectangle(20, 420, 150, 10));
        barriers.add(new Rectangle(170, 420, 10, 100));
        barriers.add(new Rectangle(20, 510, 150, 10));
        //indents right
        barriers.add(new Rectangle(1150, 250, 150, 10));
        barriers.add(new Rectangle(1140, 250, 10, 100));
        barriers.add(new Rectangle(1150, 340, 150, 10));

        barriers.add(new Rectangle(1150, 420, 150, 10));
        barriers.add(new Rectangle(1140, 420, 10, 100));
        barriers.add(new Rectangle(1150, 510, 150, 10));
        //top half
        barriers.add(new Rectangle(645, 50, 30, 80));

        barriers.add(new Rectangle(135, 120, 80, 50));
        barriers.add(new Rectangle(300, 120, 100, 50));
        barriers.add(new Rectangle(485, 120, 80, 50));

        barriers.add(new Rectangle(755, 120, 80, 50));
        barriers.add(new Rectangle(920, 120, 100, 50));
        barriers.add(new Rectangle(1105, 120, 80, 50));

        barriers.add(new Rectangle(300, 240, 80, 30));
        barriers.add(new Rectangle(940, 240, 80, 30));

        //////doubles
        barriers.add(new Rectangle(560, 225, 200, 35));
        barriers.add(new Rectangle(645, 260, 30, 70));

        barriers.add(new Rectangle(450, 240, 30, 175));
        barriers.add(new Rectangle(480, 338, 80, 20));

        barriers.add(new Rectangle(840, 240, 30, 175));
        barriers.add(new Rectangle(760, 338, 80, 20));

        //box
        barriers.add(new Rectangle(535, 575, 250, 35));
        barriers.add(new Rectangle(535, 495, 30, 80));
        barriers.add(new Rectangle(755, 495, 30, 80));
        barriers.add(new Rectangle(535, 465, 75, 30));
        barriers.add(new Rectangle(710, 465, 75, 30));

        //bottom half
        barriers.add(new Rectangle(335, 420, 30, 80));
        barriers.add(new Rectangle(955, 420, 30, 80));

        barriers.add(new Rectangle(135, 605, 200, 35));
        barriers.add(new Rectangle(985, 605, 200, 35));

        barriers.add(new Rectangle(435, 520, 30, 80));
        barriers.add(new Rectangle(855, 520, 30, 80));

        con.setLayout(new FlowLayout());
        addKeyListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.start();
    }

    public void run()
    {
        try{
            while(true)
            {
                t.sleep(33);//Smaller number == faster, larger == slower
                p.move();
                gh1.move();
                gh2.move();
                gh3.move();
                gh4.move();
                for(int x = 0; x < coins.size(); x++)
                {
                    if(p.getR().intersects(coins.get(x).getR()))
                    {
                        coins.get(x).eatCoin();
                    }
                }
                for(int x = 0; x < pellets.size(); x++)
                {
                    if(p.getR().intersects(pellets.get(x).getR()))
                    {
                        pellets.get(x).eatPellet();
                    }
                }
                for(int x = 0; x < barriers.size(); x++)
                {
                    if(p.getR().intersects(barriers.get(x)))
                    {
                        p.moveBack(barriers.get(x));
                    }
                }
                for(int x = 0; x < barriers.size(); x++)
                {
                    if(gh2.getR().intersects(barriers.get(x)))
                    {
                        gh2.goBack(barriers.get(x));
                    }

                }
                for(int x = 0; x < barriers.size(); x++)
                {
                    if(gh1.getR().intersects(barriers.get(x)))
                    {
                        gh1.goBack(barriers.get(x));
                    }

                }
                for(int x = 0; x < barriers.size(); x++)
                {
                    if(gh3.getR().intersects(barriers.get(x)))
                    {
                        gh3.goBack(barriers.get(x));
                    }

                }
                for(int x = 0; x < barriers.size(); x++)
                {
                    if(gh4.getR().intersects(barriers.get(x)))
                    {
                        gh4.goBack(barriers.get(x));
                    }

                }

                if(p.getR().intersects(gh4.getR()))
                {
                    p.lose();

                }
                if(p.getR().intersects(gh3.getR()))
                {
                    p.lose();

                }
                if(p.getR().intersects(gh2.getR()))
                {
                    p.lose();

                }
                if(p.getR().intersects(gh1.getR()))
                {
                    p.lose();

                }
                repaint();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void paint(Graphics gr)
    {
        Image i=createImage(getSize().width, getSize().height);
        Graphics2D painter = (Graphics2D)i.getGraphics();

        painter.fillRect(0, 0, 1320, 768);
        for(int x = 0; x < coins.size(); x++)
        {
            coins.get(x).drawCoin(painter);
        }
        for(int x = 0; x < pellets.size(); x++)
        {
            pellets.get(x).drawPellet(painter);
        }
        painter.setColor(Color.blue);
        painter.setStroke(new BasicStroke(4));
        for(int x = 0; x < barriers.size(); x++)
        {
            painter.draw(barriers.get(x));
        }

        p.drawPacMan(painter);
        gh1.drawGhost(painter);
        gh2.drawGhost(painter);
        gh3.drawGhost(painter);
        gh4.drawGhost(painter);
        painter.dispose();
        gr.drawImage(i, 0, 0, this);
    }

    public static void main(String[] args)
    {
        PacManage frame = new PacManage();
        frame.setSize(1320, 750);//determines size of screen
        frame.setVisible(true);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void keyPressed(KeyEvent k){if(k.getKeyCode() == 37){
            p.moveLeft();
        }
        if(k.getKeyCode() == 38){
            p.moveUp();
        }
        if(k.getKeyCode() == 39){
            p.moveRight();
        }
        if(k.getKeyCode() == 40){
            p.moveDown();
        }}

    public void keyReleased(KeyEvent k){

    }

    public void keyTyped(KeyEvent k){}
}