package com.arhimag.games.SnakeJourney.Screens;

import java.util.List;


import com.arhimag.games.SnakeJourney.Assets;
import com.arhimag.games.SnakeJourney.GameLevelDrawer;
import com.arhimag.games.SnakeJourney.Settings;
import com.arhimag.games.SnakeJourney.Levels.GameLevel;
import com.arhimag.games.SnakeJourney.Levels.PauseMenuLevel;
import com.arhimag.games.SnakeJourney.Maps.PauseMenuMap;
import com.arhimag.games.SnakeJourney.framework.Game;
import com.arhimag.games.SnakeJourney.framework.Graphics;
import com.arhimag.games.SnakeJourney.framework.Input;
import com.arhimag.games.SnakeJourney.framework.Input.TouchEvent;
import com.arhimag.games.SnakeJourney.framework.Screen;
import com.arhimag.games.SnakeJourney.framework.impl.AndroidGame;


public class PauseMenuScreen extends Screen
{
	GameLevelDrawer levelDrawer;
    boolean exitflag = false;

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
        List<Input.KeyEvent> keyEvents = game.getInput().getKeyEvents();

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
