 package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.game.src.main.classes.*;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = 250;
	public static final int SCALE = 2;
	public final String TITLE = "Guardians of The Galaxy";
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	 
	private Player p;
	private Controller c; 
	private Textures texture;
	private Menu menu;
	private Gameover gameover;
	private Help help;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	public static enum STATE
	{
		MENU,
		GAMEOVER,
		GAME,
		HELP
	};
	
	public static STATE state= STATE.MENU;
	
	public void init() {
		BufferedImageLoader load = new BufferedImageLoader();
		try {
			spriteSheet = load.loadImage("/sprite_sheet.png");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		texture = new Textures(this);
		
		c = new Controller( texture,this);
		p = new Player(304, 420, texture,this,c);
		menu=new Menu();
		gameover=new Gameover(c);
		help=new Help();
		
		ea=c.getEntityA();
		eb=c.getEntityB();
		
		
		requestFocus();
		addKeyListener(new KeyboardPanel(p,c,texture));
		addMouseListener(new MouseInput(c));
		
		c.createAsteroid(c.getAsteroid_count());
	}
	
	private synchronized void start() {
		if(running) return;
		
		running = true;
		thread = new Thread(this);
		thread.start(); 
	}
	
	private synchronized void stop() {
		if(!running) return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double Ticksamount = 60.0;
		double ns = 1000000000 / Ticksamount;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(running) {
			// game loop
			long now = System.nanoTime();
			delta += (now - lastTime)/ ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, FPS " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if(state==STATE.GAME)
		{
			p.tick();
			c.tick();
			
			if(p.getHealth()<=0)
			{
				Game.state=STATE.GAMEOVER;
			}

		}
		
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		if(state==STATE.GAME)
		{
			
			p.render(g);
			c.render(g);
			
			for(int i = 0; i< 1000; i++)
			{
				g.setColor(Color.WHITE);
				int posX = (int) (i * (Math.random()*1000));
				int posY = (int) (i * (Math.random()*1000));
				g.drawRect(posX,posY, 1,10);
			}
		}
		else if(state==STATE.GAMEOVER)
		{
			gameover.render(g);
		}
		else if(state==STATE.MENU)
		{
			menu.render(g);
		}
		else if(state==STATE.HELP)
		{
			help.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
		
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	
	public Player getPlayer()
	{
		return p;
	}
}
