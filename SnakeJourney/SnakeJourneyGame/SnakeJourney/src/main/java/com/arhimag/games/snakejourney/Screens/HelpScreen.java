package com.arhimag.games.snakejourney.Screens;

import java.util.List;
import java.util.Locale;

import com.arhimag.games.snakejourney.Assets;
import com.arhimag.games.snakejourney.GameLevelDrawer;
import com.arhimag.games.snakejourney.Maps.HelpScreenRUMap;
import com.arhimag.games.snakejourney.Settings;
import com.arhimag.games.snakejourney.Levels.GameLevel;
import com.arhimag.games.snakejourney.Levels.HelpLevel;
import com.arhimag.games.snakejourney.Maps.HelpScreenENMap;
import com.arhimag.games.snakejourney.framework.Game;
import com.arhimag.games.snakejourney.framework.Graphics;
import com.arhimag.games.snakejourney.framework.Input.TouchEvent;
import com.arhimag.games.snakejourney.framework.Screen;
import com.arhimag.games.snakejourney.framework.impl.AndroidGame;

public class HelpScreen extends Screen
{
	
	GameLevelDrawer levelDrawer;
	
	public HelpScreen ( Game game )
	{
		super(game);
		AndroidGame andrGame = (AndroidGame) game;
        if ( Locale.getDefault().getLanguage().equals("ru") )
            levelDrawer = new GameLevelDrawer( new HelpLevel( new HelpScreenRUMap()), andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
        else
		    levelDrawer = new GameLevelDrawer( new HelpLevel( new HelpScreenENMap()), andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
		GameLevel.startPauseTimer();
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

                game.setScreen(new HelpScreen2(game));
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
