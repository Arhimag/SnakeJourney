package com.arhimag.games.snakejourney.Screens;

import java.util.List;

import com.arhimag.games.snakejourney.Assets;
import com.arhimag.games.snakejourney.GameLevelDrawer;
import com.arhimag.games.snakejourney.Settings;
import com.arhimag.games.snakejourney.Levels.GameOverLevel;
import com.arhimag.games.snakejourney.Maps.GameOverMap;
import com.arhimag.games.snakejourney.framework.Game;
import com.arhimag.games.snakejourney.framework.Graphics;
import com.arhimag.games.snakejourney.framework.Input.TouchEvent;
import com.arhimag.games.snakejourney.framework.Screen;
import com.arhimag.games.snakejourney.framework.impl.AndroidGame;

public class GameOverScreen extends Screen
{
	GameLevelDrawer levelDrawer;

	
	public GameOverScreen ( Game game )
	{
		super(game);
		AndroidGame andrGame = (AndroidGame) game;

		levelDrawer = new GameLevelDrawer( new GameOverLevel(  new GameOverMap()),andrGame.getDisplayWidth()  ,andrGame.getDisplayHeight(), game.getGraphics());
	}

	@Override
	public void update (float deltaTime)
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		
		for( int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP)
			{
				game.setScreen(new MainMenuScreen(game));
				if( Settings.isSoundEnabled())
					Assets.eat.play(1);
				return;
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