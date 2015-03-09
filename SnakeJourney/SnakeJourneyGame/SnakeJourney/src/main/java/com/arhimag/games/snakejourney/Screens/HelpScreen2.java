package com.arhimag.games.snakejourney.Screens;

import android.util.Log;

import java.util.List;
import java.util.Locale;

import com.arhimag.games.snakejourney.Assets;
import com.arhimag.games.snakejourney.GameLevelDrawer;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenENAccelMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenENChooseComfortMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenENDeveloperMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenENIdeaMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenENMusicMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenENSlideMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenENThanksMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenENUDLRMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenRUAccelMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenRUChooseComfortMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenRUDeveloperMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenRUIdeaMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenRULRMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenRUMusicMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenRUSlideMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenRUThanksMap;
import com.arhimag.games.snakejourney.Maps.HelpScreenRUUDLRMap;
import com.arhimag.games.snakejourney.Settings;
import com.arhimag.games.snakejourney.Levels.GameLevel;
import com.arhimag.games.snakejourney.Levels.Help2Level;
import com.arhimag.games.snakejourney.Maps.HelpScreenENLRMap;
import com.arhimag.games.snakejourney.framework.Game;
import com.arhimag.games.snakejourney.framework.Graphics;
import com.arhimag.games.snakejourney.framework.Input.TouchEvent;
import com.arhimag.games.snakejourney.framework.Screen;
import com.arhimag.games.snakejourney.framework.impl.AndroidGame;

public class HelpScreen2 extends Screen
{
    int currentpage = 0;

	GameLevelDrawer levelDrawer;
    private static final Class<?> enMaps[] = {
            HelpScreenENChooseComfortMap.class,
            HelpScreenENAccelMap.class,
            HelpScreenENSlideMap.class,
            HelpScreenENUDLRMap.class,
            HelpScreenENLRMap.class,
            HelpScreenENDeveloperMap.class,
            HelpScreenENIdeaMap.class,
            HelpScreenENMusicMap.class,
            HelpScreenENThanksMap.class
    };
    private static final Class<?> ruMaps[] = {
            HelpScreenRUChooseComfortMap.class,
            HelpScreenRUAccelMap.class,
            HelpScreenRUSlideMap.class,
            HelpScreenRUUDLRMap.class,
            HelpScreenRULRMap.class,
            HelpScreenRUDeveloperMap.class,
            HelpScreenRUIdeaMap.class,
            HelpScreenRUMusicMap.class,
            HelpScreenRUThanksMap.class
    };
	
	public HelpScreen2 ( Game game )
	{
		super(game);
		AndroidGame andrGame = (AndroidGame) game;

        try
        {

            if ( Locale.getDefault().getLanguage().equals("ru") )
                levelDrawer = new GameLevelDrawer(new Help2Level((GameMap) ruMaps[0].getConstructor((Class<?>[]) null).newInstance()), andrGame.getDisplayWidth(), andrGame.getDisplayHeight(), game.getGraphics());
            else
                levelDrawer = new GameLevelDrawer(new Help2Level((GameMap) enMaps[0].getConstructor((Class<?>[]) null).newInstance()), andrGame.getDisplayWidth(), andrGame.getDisplayHeight(), game.getGraphics());

            currentpage = 0;
        }
        catch(Exception ex)
        {
            Log.d("SnakeFatal", "Can not load map  " + ex.toString());
        }

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
                try
                {
                    if ( currentpage < enMaps.length - 1 )
                        if (Locale.getDefault().getLanguage().equals("ru") )
                            this.levelDrawer.getLevel().setMap((GameMap) ruMaps[++currentpage].getConstructor((Class<?>[]) null).newInstance());
                        else
                            this.levelDrawer.getLevel().setMap((GameMap) enMaps[++currentpage].getConstructor((Class<?>[]) null).newInstance());
                    else
                        game.setScreen(new MainMenuScreen(game));
                }
                catch(Exception ex)
                {
                    Log.d("SnakeFatal", "Can not load map  " + ex.toString());
                }

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