package com.game.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Menu {
	BufferedImage titleImg,backImg,playImg, helpImg, quitImg;
	String backPath = "/Main menu.png";

	public Rectangle playButton= new Rectangle(264,408,120,50);
	public Rectangle helpButton= new Rectangle(90,415,90,40);
	public Rectangle quitButton= new Rectangle(468,415,90,40);
	
	public void render(Graphics g)
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			backImg = loader.loadImage(backPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Graphics2D g2d= (Graphics2D) g;
		g.setColor(Color.WHITE);
		
		g2d.drawImage(backImg, null, null);
	}
	
}
