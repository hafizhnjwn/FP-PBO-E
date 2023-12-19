package com.game.src.main;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.game.src.main.Game.STATE;

public class MouseInput implements MouseListener{
	
	private Controller c;
	
	public MouseInput(Controller c)
	{
		this.c=c;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int mx=e.getX();
		int my=e.getY();
		
//		public Rectangle playButton= new Rectangle(264,408,120,50);
//		public Rectangle helpButton= new Rectangle(90,415,90,40);
//		public Rectangle quitButton= new Rectangle(468,415,90,40);
		
		if(Game.state== STATE.MENU)
		{
			
			if(mx>=264 && mx<= 384)
			{
				if(my>=408 && my<= 458)
				{
					Game.state=Game.STATE.GAME;
				}
			}
			if(mx>=90 && mx<= 180)
			{
				if(my>=415 && my<= 455)
				{
					Game.state=Game.STATE.HELP;
				}
			}
			if(mx>=468 && mx<= 558)
			{
				if(my>=415 && my<= 455)
				{
					System.exit(1);
				}
			}
		}
//		public Rectangle playButton= new Rectangle(248,270,150,40);
//		public Rectangle helpButton= new Rectangle(248,336,150,40);
		if(Game.state== STATE.GAMEOVER)
		{
			
			if(mx>=248 && mx<= 398)
			{
				if(my>=270 && my<= 310)
				{
					c.reset();
					Game.state=Game.STATE.GAME;
				}
			}
			
			if(mx>=248 && mx<= 398)
			{
				if(my>=336 && my<= 376)
				{
					c.reset();
					Game.state=Game.STATE.MENU;
				}
			}
		}
//		public Rectangle playButton= new Rectangle(238,225,165,50);
		if(Game.state==STATE.HELP)
		{
			if(mx>=238 && mx<= 403)
			{
				if(my>=225 && my<= 275)
				{
					Game.state=Game.STATE.MENU;
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
