package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import listener_loader.BufferedImageLoader;

public class Main extends Canvas implements Runnable{

    private static final long serialVersionUID=1L;

    public static final int WIDTH= 320;
    public static final int HEIGHT=WIDTH/2;
    public static final int SCALE=2;
    public final String TITLE= "Guardian of The Galaxy";

    public boolean running=false;
    private Thread thread;

    private BufferedImage image= new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage asteroid;
    private String asteroidPath= "/asteroid.png";

    public void init()
    {
        BufferedImageLoader loader= new BufferedImageLoader();

        asteroid=loader.loadImage(asteroidPath);
        
    }
    private synchronized void start()
    {
        if(running) return;

        running=true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop()
    {
        if(!running) return;

        running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        init();
        long lastTime=System.nanoTime();
        final double amountOfTicks=60.0;
        double ns= 1000000000/amountOfTicks;
        double delta=0;
        int updates=0;
        int frames=0;
        long timer= System.currentTimeMillis();


        while(running)
        {
            long now= System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            if(delta>=1)
            {
                tick();
                updates++; 
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis()-timer>1000)
            {
                timer +=1000;
                System.out.println(updates+" Ticks, Fps "+frames);
                updates=0;
                frames=0;
            }
        }
        stop();
    }

    private void tick()
    {

    }
    private void render()
    {
        BufferStrategy bs= this.getBufferStrategy();

        if(bs==null)
        {
            createBufferStrategy(3);
            return;
        }

        Graphics g= bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(),getHeight(), this);

        //g.drawImage(asteroid, 28, 32, null);
        
        g.dispose();
        bs.show();

    }
    

    public static void main(String args[] )
    {
        Main main= new Main();

        main.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        main.setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        main.setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));

        JFrame frame= new JFrame(main.TITLE);
        frame.add(main);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        main.start();
    }

    
}