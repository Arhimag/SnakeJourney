package com.arhimag.games.SnakeJourney.Screens;

import java.util.List;


import com.arhimag.games.SnakeJourney.Assets;
import com.arhimag.games.SnakeJourney.GameLevelDrawer;
import com.arhimag.games.SnakeJourney.LevelSequence;
import com.arhimag.games.SnakeJourney.Settings;
import com.arhimag.games.SnakeJourney.Levels.GameLevel;
import com.arhimag.games.SnakeJourney.Levels.LevelLevelsList;
import com.arhimag.games.SnakeJourney.Maps.LevelsListMap;
import com.arhimag.games.SnakeJourney.framework.Game;
import com.arhimag.games.SnakeJourney.framework.Graphics;
import com.arhimag.games.SnakeJourney.framework.Input.KeyEvent;
import com.arhimag.games.SnakeJourney.framework.Input.TouchEvent;
import com.arhimag.games.SnakeJourney.framework.Screen;
import com.arhimag.games.SnakeJourney.framework.impl.AndroidGame;

public class LevelsScreen extends Screen
{
	
	GameLevelDrawer levelDrawer;
	AndroidGame andrGame = (AndroidGame) game;
	
	public LevelsScreen ( Game game )
	{
		super(game);
		andrGame = (AndroidGame) game;
		
		levelDrawer = new GameLevelDrawer( new LevelLevelsList( new LevelsListMap()), andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
		GameLevel.startPauseTimer();
	}

/*	private GameLevel getLevel( GameMap map )
	{
		if( map instanceof Level1Map )
			return new Level1(map);
		else if( map instanceof Level2Map )
			return new Level2(map);
		else if( map instanceof Level3Map )
			return new Level3(map);
		else if( map instanceof Level4Map )
			return new Level4(map);
		else if( map instanceof LevelCarpetMap )
			return new LevelCarpet(map);
		else if( map instanceof LevelLabirinthMap )
			return new LevelLabirinth(map);
		else if( map instanceof MaskMap )
			return new LevelMask(map);
		else if( map instanceof MeetAIMap )
			return new MeetAILevel(map);
		else if( map instanceof MeetTeleportMap)
			return new MeetTeleportLevel(map);
		else if( map instanceof CircleMap)
			return new CircleLevel(map);
		else if( map instanceof SnakeMap)
			return new SnakeLevel(map);
		else
			return new Level1(map);
	} */
	
	private void goToLevelTL()
	{
		if( ((LevelLevelsList)this.levelDrawer.getLevel()).getTopLeft() != null )
			game.setScreen(new GameScreen(game,LevelSequence.createLevelByMap(((LevelLevelsList)this.levelDrawer.getLevel()).getTopLeft()),((LevelLevelsList)this.levelDrawer.getLevel()).getListNumber() * 4));
	}
	
	private void goToLevelTR()
	{
		if( ((LevelLevelsList)this.levelDrawer.getLevel()).getTopRight() != null )
			game.setScreen(new GameScreen(game,LevelSequence.createLevelByMap(((LevelLevelsList)this.levelDrawer.getLevel()).getTopRight()),((LevelLevelsList)this.levelDrawer.getLevel()).getListNumber() * 4 + 1));
	}
	
	private void goToLevelBL()
	{
		if( ((LevelLevelsList)this.levelDrawer.getLevel()).getBottomLeft() != null )
			game.setScreen(new GameScreen(game,LevelSequence.createLevelByMap(((LevelLevelsList)this.levelDrawer.getLevel()).getBottomLeft()),((LevelLevelsList)this.levelDrawer.getLevel()).getListNumber() * 4 + 2));
	}
	
	private void goToLevelBR()
	{
		if( ((LevelLevelsList)this.levelDrawer.getLevel()).getBottomRight() != null )
			game.setScreen(new GameScreen(game,LevelSequence.createLevelByMap(((LevelLevelsList)this.levelDrawer.getLevel()).getBottomRight()),((LevelLevelsList)this.levelDrawer.getLevel()).getListNumber() * 4 + 3));
	}
	@Override
	public void update (float deltaTime)
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		List<KeyEvent> keyEvents = game.getInput().getKeyEvents(); 
		
		int len = keyEvents.size();
		
		this.levelDrawer.getLevel().update(deltaTime,this.levelDrawer.getLevel().getPlayerSnake());
		
		for(int i = 0; i < len; i++)
		{
			KeyEvent event = keyEvents.get(i);
			
			if(event.type == KeyEvent.KEY_UP)
			{
				if(event.keyCode == android.view.KeyEvent.KEYCODE_BACK)
				{
					game.setScreen(new MainMenuScreen(game));
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
					return;
				}
			}
		}
		
		len = touchEvents.size();
		for(int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
		
			if(event.type == TouchEvent.TOUCH_UP)
			{
				if (((LevelLevelsList)this.levelDrawer.getLevel()).isSlide())
				{
					if (java.lang.Math.abs(event.x - ((LevelLevelsList)this.levelDrawer.getLevel()).getSlideStartX()) > andrGame.getDisplayWidth()/10 )
					{
						if( event.x > ((LevelLevelsList)this.levelDrawer.getLevel()).getSlideStartX()) 
							((LevelLevelsList)this.levelDrawer.getLevel()).startAnimationLeft();
						else
							((LevelLevelsList)this.levelDrawer.getLevel()).startAnimationRight();
					}
					((LevelLevelsList)this.levelDrawer.getLevel()).setSlide(false);
				}
				else
				{ 
					if( levelDrawer.inBounds(event, ((LevelLevelsList)this.levelDrawer.getLevel()).getLtBounds()))
					{
						if( Settings.isSoundEnabled())
							Assets.eat.play(1);
						goToLevelTL();
						return;
					}

					if( levelDrawer.inBounds(event, ((LevelLevelsList)this.levelDrawer.getLevel()).getRtBounds()))
					{
						if( Settings.isSoundEnabled())
							Assets.eat.play(1);
						goToLevelTR();
						return;
					}

					if( levelDrawer.inBounds(event, ((LevelLevelsList)this.levelDrawer.getLevel()).getLbBounds()))
					{
						if( Settings.isSoundEnabled())
							Assets.eat.play(1);
						goToLevelBL();
						return;
					}

					if( levelDrawer.inBounds(event, ((LevelLevelsList)this.levelDrawer.getLevel()).getRbBounds()))
					{
						if( Settings.isSoundEnabled())
							Assets.eat.play(1);
						goToLevelBR();
						return;
					}

				}
			}
			else if( event.type == TouchEvent.TOUCH_DOWN )
			{
				/*
				 *  ��� ��������� ������� ������ �� ������� �� ������ �� ������� ������ ��������� ����������, � ������ �����,
				 *  ���� ����� ��������� �� �������-�� ��������� �� ������ ������ ������� ����� ������. 
				 */
				((LevelLevelsList)this.levelDrawer.getLevel()).activateSlide(event.x);
				((LevelLevelsList)this.levelDrawer.getLevel()).setSlide(false);
			}
			else if( event.type == TouchEvent.TOUCH_DRAGGED )
				if((java.lang.Math.abs(event.x - ((LevelLevelsList)this.levelDrawer.getLevel()).getSlideStartX()) > andrGame.getDisplayWidth() / 100 ))
				{
					((LevelLevelsList)this.levelDrawer.getLevel()).setSlide(true);
					((LevelLevelsList)this.levelDrawer.getLevel()).setSlideX(event.x);
				}
				
		}
		/*len = touchEvents.size();
		
		for( int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP)
			{
				if( levelDrawer.inBounds(event, SettingsMap.getCloseBounds()))
				{
					game.setScreen(new MainMenuScreen(game));
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
					return;
				}
				if( levelDrawer.inBounds(event, SettingsMap.getSoundBounds()))
				{
					Settings.setSoundEnabled( ! Settings.isSoundEnabled() ); 
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
				}
				if( levelDrawer.inBounds(event, SettingsMap.getSlideBounds()))
				{
					Settings.setControl( 0 ); 
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
				}
				if( levelDrawer.inBounds(event, SettingsMap.getAccelBounds()))
				{
					Settings.setControl( 1 ); 
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
				}
				if( levelDrawer.inBounds(event, SettingsMap.getUprightdownleftBounds()))
				{
					Settings.setControl( 4 ); 
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
				}
				if( levelDrawer.inBounds(event, SettingsMap.getLeftrightBounds()))
				{
					Settings.setControl( 2 ); 
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
				}
				return;
			}
		}*/
	}
	
	@Override
	public void present(float deltaTime)
	{
		Graphics g = game.getGraphics();
		g.clear(0);
		levelDrawer.draw();
	}
	
	@Override
	public void pause()
	{
	}
	
	@Override
	public void resume()
	{
	}
	
	@Override
	public void dispose()
	{
	}
	
}
