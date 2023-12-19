package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.src.main.classes.*;

public class Controller {
	
	private LinkedList<EntityA> ea = new LinkedList<EntityA>(); 
	private LinkedList<EntityB> eb = new LinkedList<EntityB>(); 
	
	private int asteroid_count=5;
	private int asteroid_killed=0;
	
	private Game game;
	private Textures texture;
	Random rand=new Random();
	
	EntityA enta;
	EntityB entb;
	private int enemy_killed;
	
	public Controller(Textures texture,Game game)
	{
		this.texture=texture;
		this.game=game;
	}
	
	public void createAsteroid(int asteroid_count)
	{
		for (int i=0; i<asteroid_count;i++)
		{
			addEntity(new Asteroid(rand.nextInt(640),-10,texture,this,game));
		}
	}
	 
	
	public void tick() {
		for(int i=0;i<ea.size();i++)
		{
			enta=ea.get(i);
			
			enta.tick();
		}
		
		for(int i=0;i<eb.size();i++)
		{
			entb=eb.get(i);
			
			entb.tick();
		}
		
		if(asteroid_killed==asteroid_count)
		{
			asteroid_count+=2;
			asteroid_killed=0;
			createAsteroid(asteroid_count);
		}
	}
	
	public void render(Graphics g) {
			
		Font font = new Font("InaiMathi", Font.BOLD,30);
		g.setFont(font);
		g.setColor(Color.GRAY);
		g.drawString(Integer.toString(enemy_killed), 570, 50);

		for (int i = 0; i < ea.size(); i++) {

			enta = ea.get(i);
			enta.render(g);
		}

		for (int i = 0; i < eb.size(); i++) {

			entb = eb.get(i);
			entb.render(g);
		}

	}
	
	public void reset() {

		ea.removeAll(ea);
		eb.removeAll(eb);
		this.asteroid_count = 5;
		this.asteroid_killed = 5;
		this.enemy_killed = 0;
		game.getPlayer().reset();
	}
	
	public void addEntity(EntityA block)
	{
		ea.add(block);
	}
	public void removeEntity(EntityA block)
	{
		ea.remove(block);
	}
	
	public void addEntity(EntityB block)
	{
		eb.add(block);
	}
	
	public void removeEntity(EntityB block, boolean shotBySpaceship) {
        if (!shotBySpaceship) {
            // If it's not shot by the spaceship, do not increment the enemy_killed count
            eb.remove(block);
        } else {
            eb.remove(block);
            enemy_killed += 1;
        }
	}
	
	public LinkedList<EntityB> getEntityB()
	{
		return eb;
	}
	
	public LinkedList<EntityA> getEntityA()
	{
		return ea;
	}
	
	public void countKilled() {
		enemy_killed++;
	}
	public int getAsteroid_count()
	{
		return asteroid_count;
	}
	
	public void setAsteroid_count(int asteroid_count)
	{
		this.asteroid_count=asteroid_count;
	}
	
	public int getAsteroid_killed()
	{
		return asteroid_killed;
	}
	
	public void setAsteroid_killed(int asteroid_killed)
	{
		this.asteroid_killed=asteroid_killed;
	}
	public int getEnemykilled()
	{
		return enemy_killed;
	}
	
}
