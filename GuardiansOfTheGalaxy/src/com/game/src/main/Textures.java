package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {
	
	public BufferedImage player, bullet, asteroid;
	public SpriteSheet ss =  null;
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	private void getTextures() {
		player = ss.grabImage(2, 1, 32, 32);
		bullet = ss.grabImage(3, 1, 32, 32);
		asteroid = ss.grabImage(1, 1, 32, 32);
	}
}
