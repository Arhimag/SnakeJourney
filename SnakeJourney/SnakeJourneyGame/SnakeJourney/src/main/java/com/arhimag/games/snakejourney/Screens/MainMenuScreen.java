package com.arhimag.games.snakejourney.Screens;

import java.util.List;


import com.arhimag.games.snakejourney.Assets;
import com.arhimag.games.snakejourney.GameLevelDrawer;
import com.arhimag.games.snakejourney.Settings;
import com.arhimag.games.snakejourney.Levels.GameLevel;
import com.arhimag.games.snakejourney.Levels.MainMenuLevel;
import com.arhimag.games.snakejourney.Maps.MainMenuMap;
import com.arhimag.games.snakejourney.framework.Game;
import com.arhimag.games.snakejourney.framework.Graphics;
import com.arhimag.games.snakejourney.framework.Input;
import com.arhimag.games.snakejourney.framework.Input.TouchEvent;
import com.arhimag.games.snakejourney.framework.Input.KeyEvent;
import com.arhimag.games.snakejourney.framework.Screen;
import com.arhimag.games.snakejourney.framework.impl.AndroidGame;


public class MainMenuScreen extends Screen
{
	GameLevelDrawer levelDrawer;
    boolean exitflag = false;
		
	
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
        List<KeyEvent> keyEvents = game.getInput().getKeyEvents();

        int len = keyEvents.size();

        for(int i = 0; i < len; i++)
        {
            Input.KeyEvent event = keyEvents.get(i);
            if( event.type == Input.KeyEvent.KEY_UP )
            {
                if( event.keyCode == android.view.KeyEvent.KEYCODE_BACK)
                {
                    if( exitflag )
                        ((AndroidGame)game).finish();
                    else
                        exitflag = true;
                }
                else
                    exitflag = false;
            }

        }


        len = touchEvents.size();


		this.levelDrawer.getLevel().update(deltaTime,this.levelDrawer.getLevel().getPlayerSnake());



		for(int i = 0; i < len; i++)
		{
            exitflag = false;

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
				if( levelDrawer.inBounds(event, MainMenuMap.getAchievementsBounds()))
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
		levelDrawer.draw();
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
