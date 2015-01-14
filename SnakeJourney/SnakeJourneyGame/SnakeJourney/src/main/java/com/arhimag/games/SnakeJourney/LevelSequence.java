package com.arhimag.games.SnakeJourney;

import android.util.Log;

import com.arhimag.games.SnakeJourney.Levels.*;
import com.arhimag.games.SnakeJourney.Maps.*;
import java.lang.reflect.Constructor;


import com.arhimag.games.SnakeJourney.Levels.GameLevel;

public class LevelSequence 
{
    private static final  Class<?> levels[] = {
        //LevelCube.class,
        LevelBottle.class,      LevelBeginner.class,            LevelSnail.class,
        LevelBalcony.class,     LevelLabirinth.class,           LevelZigZag.class,
        LevelSlalom.class,      LevelMask.class,                LevelTorii.class,
        LevelClassic.class,     LevelForest.class,              LevelTrefoil.class,
        LevelHypno.class,       LevelMiniSquare.class,          MeetTeleportLevel.class,
        LevelCarpet.class,      LevelChristmass.class,          MeetAILevel.class,
        LevelBridge.class,      LevelCube.class,                LevelSprint.class,
        CircleLevel.class,      LevelBaskets.class,             Level4Rooms.class,
        LevelAIM.class,         SnakeLevel.class,               LevelHotMeet.class,
        LevelCrankshaft.class,  LevelBreath.class,              LevelWelcomeToVisualDoj.class,
        Level1.class,           Level2.class,                   Level3.class,
        Level4.class
    };

    private static final Class<?> maps[] = {
        //LevelCubeMap.class,
    	LevelBottleMap.class,           LevelBeginnerMap.class, 		LevelSnailMap.class,
        LevelBalconyMap.class,         	LevelLabirinthMap.class, 		LevelZigZagMap.class,
        LevelSlalomMap.class,       	MaskMap.class, 					LevelToriiMap.class,
        LevelClassicMap.class,          LevelForestMap.class, 			LevelTrefoilMap.class,
        LevelHypnoMap.class,            LevelMiniSquareMap.class,    	MeetTeleportMap.class,
        LevelCarpetMap.class,           LevelChristmassMap.class,      	MeetAIMap.class,
        LevelBridgeMap.class,           LevelCubeMap.class,             LevelSprintMap.class,
        CircleMap.class,                LevelBasketsMap.class,          Level4RoomsMap.class,
        LevelAIMMap.class,              SnakeMap.class,                 LevelHotMeetMap.class,
        LevelCrankshaftMap.class,       LevelBreathMap.class,           WelcomeToVisualDojMap.class,
        Level1Map.class,                Level2Map.class,                Level3Map.class,
        Level4Map.class
    };
    
    public static final int levelsCount = levels.length;
    
	private static Constructor<?> levelsConstructors[];
	
	private static Constructor<?> mapsConstructors[];
	
	public static final int getLevelsCount()
	{
		return levels.length;
	}
	
	public static void Initialize()
	{

		levelsConstructors = new Constructor[levelsCount];
		mapsConstructors = new Constructor[levelsCount];
		
		for( int i = 0; i < levelsCount; i++ )
		{
			try
			{
				levelsConstructors[i] = levels[i].getConstructor(GameMap.class);
				mapsConstructors[i] = maps[i].getConstructor((Class<?>[])null);
			}
			catch( Exception ex )
			{
				Log.d("SnakeFatal", "Can not initialize level " + i  + " constructor " + ex.toString());
			}
			if( levelsConstructors[i] == null)
				Log.d("SnakeFatal", "Level " + i + " constructor is null! ");
			if( mapsConstructors[i] == null)
				Log.d("SnakeFatal", "Level " + i + " constructor is null! ");
		}

	}
	
	public static int getLevelNum( GameLevel level )
	{
		for( int i = 0; i < levelsCount; i++ )
			if( level.getClass() == levels[i] )
				return i;
		return -1;
	}
	
	public static int getLevelTypeNum( Class<?> level )
	{
		for( int i = 0; i < levelsCount; i++ )
			if( level == levels[i] )
				return i;
		return -1;
	}
	
	public static int getMapNum( GameMap map )
	{
		for( int i = 0; i < levelsCount; i++ )
			if( map.getClass() == maps[i] )
				return i;
		return -1;
	}
	
	public static GameLevel createLevel( int levelNum )
	{
		try
		{
			return (GameLevel) levelsConstructors[levelNum].newInstance(mapsConstructors[levelNum].newInstance((Object[])null));
		}
		catch(Exception ex)
		{
			Log.d("SnakeFatal", "Can not load level " + levelNum + " " + ex.toString());
			return null;
		}
	}
	
	public static GameMap createMap( int levelNum )
	{
		try
		{
			return (GameMap) mapsConstructors[levelNum].newInstance();
		}
		catch(Exception ex)
		{
			Log.d("SnakeFatal", "Can not load map " + levelNum + " " + ex.toString());
			return null;
		}
	}
	
	public static GameLevel createSameLevel( GameLevel level )
	{
		int levelNum = getLevelNum( level );
		if ( levelNum >= 0 )
			return createLevel( levelNum );
		else 
			return null;
	}
	
	public static GameLevel createNextLevel( GameLevel level )
	{
		int levelNum = getLevelNum( level );
		if ( levelNum >= 0 && levelNum < levelsCount - 2)
			return createLevel( levelNum + 1 );
		else 
			return null;
	}
	
	public static GameLevel createLevelByMap( GameMap map )
	{
		int levelNum = -1;
		for( int i = 0; i < levelsCount; i++ )
			if( map.getClass() == maps[i] )
				levelNum = i;
		if( levelNum == -1 )
			return null;
		try
		{
			return (GameLevel) levelsConstructors[levelNum].newInstance(map);
		}
		catch(Exception ex)
		{
			Log.d("SnakeFatal", "Can not load level " + levelNum + " " + ex.toString());
			return null;
		}
	}
}
