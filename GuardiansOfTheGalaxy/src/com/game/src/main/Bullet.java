package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA{

	private Textures texture;
	
	public Bullet (double x, double y, Textures texture) {
		super(x,y);
		this.texture = texture;
	}
	
	public void tick() {
		y -= 10;
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 32,32);
	}
	
	public void render(Graphics g) {
		g.drawImage(texture.bullet, (int)x, (int)y, null);
	}
	
	public double getY() {
		return y;
	}
	public double getX()
	{
		return x;
	}
}
