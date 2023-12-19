package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Player extends GameObject implements EntityA{

	private Textures texture;
	
	private double vx = 0;
	private double vy = 0;
	private Game game;
	private Controller c;
	private int health=30;
	
	public Player(double x, double y, Textures texture, Game game, Controller c) {
		super(x,y);
		this.texture = texture;
		this.game=game;
		this.c=c;
	}
	
	public void tick() {
		x += vx;
		y += vy;
		
		//Collision with frame border
		if(x <= 0) {
			x = 0;
		}
		if(x >= 640 - 32) {
			x = 640 - 32;
		}
		if(y <= 0) {
			y = 0;
		}
		if(y >= 500 - 32) {
			y = 500 - 32;
		}
		
		for(int i=0;i<game.eb.size();i++)
		{
			EntityB tempEnt=game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt))
			{
				c.removeEntity(tempEnt, false);
				 health-= 3;
				c.setAsteroid_killed(c.getAsteroid_killed()+1);
			}
		}
	}
	
	public void reset()
	{
		setX(304);
		setY(420);
		setVX(0);
		setVY(0);
		setHealth(30);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 32,32);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int)x, (int)y+37, 30, 3);
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y+37, health, 3);
		g.drawImage(texture.player, (int)x, (int)y, null);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setVX(double vx) {
		this.vx = vx;
	}
	
	public void setVY(double vy) {
		this.vy = vy;
	}
	public void setHealth(int health) {
		this.health  = health;
	}
	public int getHealth() {
		return health;
	}

}

