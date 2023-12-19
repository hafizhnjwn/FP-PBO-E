package com.game.src.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.src.main.Game.STATE;

public class KeyboardPanel implements KeyListener {
	
	Textures t;
	Player p;
	Controller c;
	boolean is_shooting=false;
	
	
	public KeyboardPanel(Player p, Controller c, Textures t) {
		this.p = p;
		this.c=c;
		this.t=t;
	}
	
	public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();
			
			if(Game.state==STATE.GAME)
			{
				if(key == KeyEvent.VK_D) {
					p.setVX(5);
				} else if(key == KeyEvent.VK_A) {
					p.setVX(-5);
				} else if(key == KeyEvent.VK_W) {
					p.setVY(-5);
				} else if(key == KeyEvent.VK_S) {
					p.setVY(5);
				} else if(key == KeyEvent.VK_SPACE && !is_shooting) {
					c.addEntity(new Bullet(p.getX(), p.getY(), t));
					is_shooting = true;
				}
				
			}
	}
	
	public void keyReleased(KeyEvent e) {
	int key = e.getKeyCode();
			
			if(Game.state==STATE.GAME)
			{
				
				if(key == KeyEvent.VK_D) {
					p.setVX(0);
				} else if(key == KeyEvent.VK_A) {
					p.setVX(0);
				} else if(key == KeyEvent.VK_W) {
					p.setVY(0);
				} else if(key == KeyEvent.VK_S) {
					p.setVY(0);
				} else if(key == KeyEvent.VK_SPACE) {
					is_shooting = false;
				}
			}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
