package com.arhimag.games.snakejourney.Screens;

import java.util.List;

import com.arhimag.games.snakejourney.Assets;
import com.arhimag.games.snakejourney.GameLevelDrawer;
import com.arhimag.games.snakejourney.Settings;
import com.arhimag.games.snakejourney.Levels.GameLevel;
import com.arhimag.games.snakejourney.Levels.SettingsLevel;
import com.arhimag.games.snakejourney.Maps.SettingsMap;
import com.arhimag.games.snakejourney.framework.Game;
import com.arhimag.games.snakejourney.framework.Graphics;
import com.arhimag.games.snakejourney.framework.Input.KeyEvent;
import com.arhimag.games.snakejourney.framework.Input.TouchEvent;
import com.arhimag.games.snakejourney.framework.Screen;
import com.arhimag.games.snakejourney.framework.impl.AndroidGame;

public class SettingsScreen extends Screen
{
	
	GameLevelDrawer levelDrawer;
	
	public SettingsScreen ( Game game )
	{
		super(game);
		AndroidGame andrGame = (AndroidGame) game;
		
		levelDrawer = new GameLevelDrawer( new SettingsLevel( new SettingsMap()), andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
		GameLevel.startPauseTimer();
	}

	@Override
	public void update (float deltaTime)
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		List<KeyEvent> keyEvents = game.getInput().getKeyEvents(); 
		
		int len = keyEvents.size();
		
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
                    if( Settings.isSoundEnabled() )
                        if( Settings.isViberEnabled() )
                        {
                            Settings.setSoundEnabled(false);
                            Settings.setViberEnabled(false);
                        }
                        else
                        {
                            Settings.setSoundEnabled(true);
                            Settings.setViberEnabled(true);
                        }
                    else
                        if( Settings.isViberEnabled() )
                        {
                            Settings.setSoundEnabled(true);
                            Settings.setViberEnabled(false);
                        }
                        else
                        {
                            Settings.setSoundEnabled(false);
                            Settings.setViberEnabled(true);
                        }
					if( Settings.isSoundEnabled())
                    {
                        Assets.eat.play(1);
                        Assets.menuMusic.play();
                    }
                    else
                    {
                        Assets.gameMusic.stop();
                        Assets.menuMusic.stop();
                    }

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
