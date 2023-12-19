package com.game.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Gameover {
	
	Controller c;
	
	public Gameover(Controller c)
	{
		this.c=c;
	}
	BufferedImage titleImg,backImg,playImg, helpImg, quitImg;
	String backPath = "/Game over.png";

	public Rectangle playButton= new Rectangle(248,270,150,40);
	public Rectangle helpButton= new Rectangle(248,336,150,40);
	
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
		Font font = new Font("InaiMathi", Font.BOLD,30);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Final Score : "+Integer.toString(c.getEnemykilled()), 224, 230);
		
		
	}
	
}