package com.arhimag.games.SnakeJourney.Screens;

import android.util.Log;

import java.util.List;
import java.util.Locale;

import com.arhimag.games.SnakeJourney.Assets;
import com.arhimag.games.SnakeJourney.GameLevelDrawer;
import com.arhimag.games.SnakeJourney.Maps.CircleMap;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenENAccelMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenENChooseComfortMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenENDeveloperMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenENIdeaMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenENMusicMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenENSlideMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenENThanksMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenENUDLRMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenRUAccelMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenRUChooseComfortMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenRUDeveloperMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenRUIdeaMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenRULRMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenRUMusicMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenRUSlideMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenRUThanksMap;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenRUUDLRMap;
import com.arhimag.games.SnakeJourney.Maps.Level1Map;
import com.arhimag.games.SnakeJourney.Maps.Level2Map;
import com.arhimag.games.SnakeJourney.Maps.Level3Map;
import com.arhimag.games.SnakeJourney.Maps.Level4Map;
import com.arhimag.games.SnakeJourney.Maps.Level4RoomsMap;
import com.arhimag.games.SnakeJourney.Maps.LevelAIMMap;
import com.arhimag.games.SnakeJourney.Maps.LevelBalconyMap;
import com.arhimag.games.SnakeJourney.Maps.LevelBasketsMap;
import com.arhimag.games.SnakeJourney.Maps.LevelBeginnerMap;
import com.arhimag.games.SnakeJourney.Maps.LevelBottleMap;
import com.arhimag.games.SnakeJourney.Maps.LevelBreathMap;
import com.arhimag.games.SnakeJourney.Maps.LevelBridgeMap;
import com.arhimag.games.SnakeJourney.Maps.LevelCarpetMap;
import com.arhimag.games.SnakeJourney.Maps.LevelChristmassMap;
import com.arhimag.games.SnakeJourney.Maps.LevelClassicMap;
import com.arhimag.games.SnakeJourney.Maps.LevelCrankshaftMap;
import com.arhimag.games.SnakeJourney.Maps.LevelCubeMap;
import com.arhimag.games.SnakeJourney.Maps.LevelForestMap;
import com.arhimag.games.SnakeJourney.Maps.LevelHotMeetMap;
import com.arhimag.games.SnakeJourney.Maps.LevelHypnoMap;
import com.arhimag.games.SnakeJourney.Maps.LevelLabirinthMap;
import com.arhimag.games.SnakeJourney.Maps.LevelMiniSquareMap;
import com.arhimag.games.SnakeJourney.Maps.LevelSlalomMap;
import com.arhimag.games.SnakeJourney.Maps.LevelSnailMap;
import com.arhimag.games.SnakeJourney.Maps.LevelSprintMap;
import com.arhimag.games.SnakeJourney.Maps.LevelToriiMap;
import com.arhimag.games.SnakeJourney.Maps.LevelTrefoilMap;
import com.arhimag.games.SnakeJourney.Maps.LevelZigZagMap;
import com.arhimag.games.SnakeJourney.Maps.MaskMap;
import com.arhimag.games.SnakeJourney.Maps.MeetAIMap;
import com.arhimag.games.SnakeJourney.Maps.MeetTeleportMap;
import com.arhimag.games.SnakeJourney.Maps.SnakeMap;
import com.arhimag.games.SnakeJourney.Maps.WelcomeToVisualDojMap;
import com.arhimag.games.SnakeJourney.Settings;
import com.arhimag.games.SnakeJourney.Levels.GameLevel;
import com.arhimag.games.SnakeJourney.Levels.Help2Level;
import com.arhimag.games.SnakeJourney.Maps.HelpScreenENLRMap;
import com.arhimag.games.SnakeJourney.framework.Game;
import com.arhimag.games.SnakeJourney.framework.Graphics;
import com.arhimag.games.SnakeJourney.framework.Input.TouchEvent;
import com.arhimag.games.SnakeJourney.framework.Screen;
import com.arhimag.games.SnakeJourney.framework.impl.AndroidGame;

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