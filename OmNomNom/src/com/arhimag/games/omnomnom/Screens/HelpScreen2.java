package com.arhimag.games.omnomnom.Screens;

import java.util.List;

import com.arhimag.games.omnomnom.Assets;
import com.arhimag.games.omnomnom.GameLevelDrawer;
import com.arhimag.games.omnomnom.Settings;
import com.arhimag.games.omnomnom.Levels.GameLevel;
import com.arhimag.games.omnomnom.Levels.Help2Level;
import com.arhimag.games.omnomnom.Maps.HelpScreen2Map;
import com.arhimag.games.omnomnom.framework.Game;
import com.arhimag.games.omnomnom.framework.Graphics;
import com.arhimag.games.omnomnom.framework.Input.TouchEvent;
import com.arhimag.games.omnomnom.framework.Screen;
import com.arhimag.games.omnomnom.framework.impl.AndroidGame;

public class HelpScreen2 extends Screen
{
	GameLevelDrawer levelDrawer;

	
	public HelpScreen2 ( Game game )
	{
		super(game);
		AndroidGame andrGame = (AndroidGame) game;

		levelDrawer = new GameLevelDrawer( new Help2Level(  new HelpScreen2Map()),andrGame.getDisplayWidth()  ,andrGame.getDisplayHeight(), game.getGraphics());
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
		levelDrawer.draw(deltaTime);		
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