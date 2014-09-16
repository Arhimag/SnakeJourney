package com.arhimag.games.omnomnom.Screens;

import java.util.List;


import com.arhimag.games.omnomnom.Assets;
import com.arhimag.games.omnomnom.GameLevelDrawer;
import com.arhimag.games.omnomnom.Settings;
import com.arhimag.games.omnomnom.Levels.GameLevel;
import com.arhimag.games.omnomnom.Levels.MainMenuLevel;
import com.arhimag.games.omnomnom.Maps.MainMenuMap;
import com.arhimag.games.omnomnom.framework.Game;
import com.arhimag.games.omnomnom.framework.Graphics;
import com.arhimag.games.omnomnom.framework.Input.TouchEvent;
import com.arhimag.games.omnomnom.framework.Screen;
import com.arhimag.games.omnomnom.framework.impl.AndroidGame;


public class MainMenuScreen extends Screen
{
	GameLevelDrawer levelDrawer;
		
	
	public MainMenuScreen(Game game)
	{
		super(game);
		AndroidGame andrGame = (AndroidGame) game;
		
		levelDrawer = new GameLevelDrawer( new MainMenuLevel( new MainMenuMap()),andrGame.getDisplayWidth(),andrGame.getDisplayHeight() , game.getGraphics());
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
				if( levelDrawer.inBounds(event, MainMenuMap.getNewGameBounds()))
				{
					game.setScreen(new GameScreen(game));
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
					return;
				}
				if( levelDrawer.inBounds(event, MainMenuMap.getSettingsBounds()))
				{
					game.setScreen(new SettingsScreen(game));
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
					return;
				}
				if( levelDrawer.inBounds(event, MainMenuMap.getLevelsListBounds()))
				{
					game.setScreen(new LevelsScreen(game));
					if( Settings.isSoundEnabled())
						Assets.eat.play(1);
					return;
				}
				if( levelDrawer.inBounds(event, MainMenuMap.getHelpBounds()))
				{
					game.setScreen(new HelpScreen(game));
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
