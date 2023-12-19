package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Asteroid extends GameObject implements EntityB{

	private Textures texture;
	Random r=new Random();
	private int speed=r.nextInt(3)+1;
	private Game game;
	private Controller c;
	
	public Asteroid(double x, double y, Textures texture, Controller c, Game game) {
		super(x,y);
		this.texture = texture;
		this.c=c;
		this.game=game;
	}
	
	public void tick() {
		y += speed;
		
		if(y>(Game.HEIGHT*Game.SCALE))
		{
			speed=r.nextInt(3)+1;
			y=-20;
			x= r.nextInt(Game.WIDTH*Game.SCALE);
		}
		
		for(int i=0; i<game.ea.size(); i++)
		{
			EntityA tempEnt=game.ea.get(i);
			
			if(Physics.Collision(this, tempEnt))
			{
				c.removeEntity(tempEnt);
				c.removeEntity(this, true);
				c.setAsteroid_killed(c.getAsteroid_killed()+1);
			}
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(texture.asteroid, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 32,32);
	}
	
	public double getY()
	{
		return y;
	}
	public double getX()
	{
		return x;
	}
	public void setY(double a)
	{
		this.y=a;
	}
}
