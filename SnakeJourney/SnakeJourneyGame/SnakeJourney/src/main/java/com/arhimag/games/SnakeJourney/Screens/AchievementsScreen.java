package com.arhimag.games.SnakeJourney.Screens;

import java.util.List;


import com.arhimag.games.SnakeJourney.Assets;
import com.arhimag.games.SnakeJourney.GameLevelDrawer;
import com.arhimag.games.SnakeJourney.Settings;
import com.arhimag.games.SnakeJourney.Levels.GameLevel;
import com.arhimag.games.SnakeJourney.Levels.LevelAchievementsList;
import com.arhimag.games.SnakeJourney.Maps.AchievementsListMap;
import com.arhimag.games.SnakeJourney.framework.Game;
import com.arhimag.games.SnakeJourney.framework.Graphics;
import com.arhimag.games.SnakeJourney.framework.Input.KeyEvent;
import com.arhimag.games.SnakeJourney.framework.Input.TouchEvent;
import com.arhimag.games.SnakeJourney.framework.Screen;
import com.arhimag.games.SnakeJourney.framework.impl.AndroidGame;

public class AchievementsScreen extends Screen
{
	
	GameLevelDrawer levelDrawer;
	AndroidGame andrGame = (AndroidGame) game;
	
	public AchievementsScreen ( Game game )
	{
		super(game);
		andrGame = (AndroidGame) game;
		
		levelDrawer = new GameLevelDrawer( new LevelAchievementsList( new AchievementsListMap()), andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
		GameLevel.startPauseTimer();
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
				if (((LevelAchievementsList)this.levelDrawer.getLevel()).isSlide())
				{
					if (java.lang.Math.abs(event.y - ((LevelAchievementsList)this.levelDrawer.getLevel()).getSlideStartY()) > andrGame.getDisplayHeight()/10 )
					{
						if( event.y > ((LevelAchievementsList)this.levelDrawer.getLevel()).getSlideStartY()) 
							((LevelAchievementsList)this.levelDrawer.getLevel()).startAnimationDown();
						else
							((LevelAchievementsList)this.levelDrawer.getLevel()).startAnimationUP();
					}
					((LevelAchievementsList)this.levelDrawer.getLevel()).setSlide(false);
				}
				else
				{
					if((((LevelAchievementsList)levelDrawer.getLevel()).getAchievemntId() + event.y / (levelDrawer.getScreenHeight() / LevelAchievementsList.achievementsPerList ) < Settings.achievementsStatus.length ) 
						&& Settings.achievementsStatus[((LevelAchievementsList)levelDrawer.getLevel()).getAchievemntId() + event.y / (levelDrawer.getScreenHeight() / LevelAchievementsList.achievementsPerList )].isAchievementReached() 	)
					{
						Settings.setSnakeEvenColor(Settings.achievementsStatus[((LevelAchievementsList)levelDrawer.getLevel()).getAchievemntId() + event.y / (levelDrawer.getScreenHeight() / LevelAchievementsList.achievementsPerList )].color1);
						Settings.setSnakeOddColor(Settings.achievementsStatus[((LevelAchievementsList)levelDrawer.getLevel()).getAchievemntId() + event.y / (levelDrawer.getScreenHeight() / LevelAchievementsList.achievementsPerList )].color2);
					}
				}
			}
			else if( event.type == TouchEvent.TOUCH_DOWN )
			{
				 //  ��� ��������� ������� ������ �� ������� �� ������ �� ������� ������ ��������� ����������, � ������ �����,
				 //  ���� ����� ��������� �� �������-�� ��������� �� ������ ������ ������� ����� ������. 
				 
				((LevelAchievementsList)this.levelDrawer.getLevel()).activateSlide(event.y);
				((LevelAchievementsList)this.levelDrawer.getLevel()).setSlide(false);
			}
			else if( event.type == TouchEvent.TOUCH_DRAGGED )
				if((java.lang.Math.abs(event.y - ((LevelAchievementsList)this.levelDrawer.getLevel()).getSlideStartY()) > andrGame.getDisplayHeight() / 100 ))
				{
					((LevelAchievementsList)this.levelDrawer.getLevel()).setSlide(true);
					((LevelAchievementsList)this.levelDrawer.getLevel()).setSlideY(event.y);
				}
		}
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
