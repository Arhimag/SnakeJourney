package com.arhimag.games.omnomnom.Screens;

import java.util.List;


import com.arhimag.games.omnomnom.Assets;
import com.arhimag.games.omnomnom.GameLevelDrawer;
import com.arhimag.games.omnomnom.Settings;
import com.arhimag.games.omnomnom.Levels.GameLevel;
import com.arhimag.games.omnomnom.Levels.PauseMenuLevel;
import com.arhimag.games.omnomnom.Maps.PauseMenuMap;
import com.arhimag.games.omnomnom.framework.Game;
import com.arhimag.games.omnomnom.framework.Graphics;
import com.arhimag.games.omnomnom.framework.Input.TouchEvent;
import com.arhimag.games.omnomnom.framework.Screen;
import com.arhimag.games.omnomnom.framework.impl.AndroidGame;


public class PauseMenuScreen extends Screen
{
	GameLevelDrawer levelDrawer;
		
	/*
	public PauseMenuScreen(Game game)
	{
		super(game);
		AndroidGame andrGame = (AndroidGame) game;
		
		levelDrawer = new GameLevelDrawer( new PauseMenuLevel( new PauseMenuMap()),andrGame.getDisplayWidth(),andrGame.getDisplayHeight() , game.getGraphics());
	}
	*/
	public PauseMenuScreen(Game game, GameLevel lvl, int lvlnum)
	{
		super(game);
		AndroidGame andrGame = (AndroidGame) game;
		
		levelDrawer = new GameLevelDrawer( new PauseMenuLevel( new PauseMenuMap(), lvl, lvlnum),andrGame.getDisplayWidth(),andrGame.getDisplayHeight() , game.getGraphics());
		GameLevel.startPauseTimer();
	}

	@Override
	public void update(float deltaTime)
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		
				
		this.levelDrawer.getLevel().update(deltaTime,this.levelDrawer.getLevel().getPlayerSnake());
		
			
		
		for(int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			
			if(event.type == TouchEvent.TOUCH_UP)
			{
				if( levelDrawer.inBounds(event, PauseMenuMap.getContinueBounds()))
				{
					game.setScreen(new GameScreen(game,((PauseMenuLevel)levelDrawer.getLevel()).getTempLevel(),((PauseMenuLevel)levelDrawer.getLevel()).getTempLevelNum()));
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
					return;
				}
				if( levelDrawer.inBounds(event, PauseMenuMap.getSettingsBounds()))
				{
					game.setScreen(new SettingsScreen(game));
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
					return;
				}
				if( levelDrawer.inBounds(event, PauseMenuMap.getLevelsListBounds()))
				{
					game.setScreen(new LevelsScreen(game));
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
					return;
				}
				if( levelDrawer.inBounds(event, PauseMenuMap.getHelpBounds()))
				{
					game.setScreen(new HelpScreen(game));
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
					return;
				}
				if( levelDrawer.inBounds(event, PauseMenuMap.getAchievementsBounds()))
				{
					game.setScreen(new AchievementsScreen(game));
					Assets.eat.play(1);
					return;
				}
			}
		}
		
	}


	@Override
	public void present(float deltaTime)
	{
		Graphics g = game.getGraphics();
		g.clear(0);
		levelDrawer.draw(deltaTime);
	}

	@Override
	public void pause()
	{
		Settings.save(game.getFileIO());
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
