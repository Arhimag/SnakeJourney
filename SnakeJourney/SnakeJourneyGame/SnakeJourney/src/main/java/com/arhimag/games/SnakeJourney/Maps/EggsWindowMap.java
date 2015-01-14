package com.arhimag.games.SnakeJourney.Maps;

import com.arhimag.games.SnakeJourney.framework.Point;

import android.graphics.Rect;


public class EggsWindowMap extends GameMap
{

	private static Rect menuBounds = new Rect(3,30,7,34);
    private static Rect vkBounds = new Rect(10,30,16,34);
    private static Rect retryBounds = new Rect(19,30,25,34);
    private static Rect fbBounds = new Rect(28,30,33,34);
    private static Rect nextBounds = new Rect(36,30,41,34);

	public static final Point ACHIVE_HOUR_1 = new Point(3,23);
	public static final Point ACHIVE_HOUR_2 = new Point(7,23);
	public static final Point ACHIVE_MINUTE_1 = new Point(13,23);
	public static final Point ACHIVE_MINUTE_2 = new Point(17,23);
	
	public static final Point PLAYER_HOUR_1 = new Point(25,23);
	public static final Point PLAYER_HOUR_2 = new Point(29,23);
	public static final Point PLAYER_MINUTE_1 = new Point(35,23);
	public static final Point PLAYER_MINUTE_2 = new Point(39,23);
	
	public static final Point EGG1 = new Point(6,12);
	public static final Point EGG2 = new Point(19,12);
	public static final Point EGG3 = new Point(32,12);
	
	
	public static final char[][][] Numbers = {
		{{'T','T','T'},
		 {'T','.','T'},
		 {'T','.','T'},
		 {'T','.','T'},
		 {'T','T','T'}},
		 
		{{'.','.','T'},
		 {'.','.','T'},
		 {'.','.','T'},
		 {'.','.','T'},
		 {'.','.','T'}},
		
		{{'T','T','T'},
		 {'.','.','T'},
		 {'T','T','T'},
		 {'T','.','.'},
		 {'T','T','T'}},
		 
		{{'T','T','T'},
		 {'.','.','T'},
		 {'T','T','T'},
		 {'.','.','T'},
		 {'T','T','T'}},
		
		{{'T','.','T'},
		 {'T','.','T'},
		 {'T','.','T'},
		 {'T','T','T'},
		 {'.','.','T'}},
		 
		{{'T','T','T'},
		 {'T','.',' '},
		 {'T','T','T'},
		 {'.','.','T'},
		 {'T','T','T'}},
		 
		{{'T','T','T'},
		 {'T','.','.'},
		 {'T','T','T'},
		 {'T','.','T'},
		 {'T','T','T'}},
		 
		{{'T','T','T'},
		 {'.','.','T'},
		 {'.','.','T'},
		 {'.','.','T'},
		 {'.','.','T'}},
		 
		{{'T','T','T'},
		 {'T','.','T'},
		 {'T','T','T'},
		 {'T','.','T'},
		 {'T','T','T'}},
		 
		{{'T','T','T'},
		 {'T','.','T'},
		 {'T','T','T'},
		 {'.','.','T'},
		 {'T','T','T'}}};
	
	public static final char[][] Egg = {
		 {'.','.','.','e','.','.','.',},
		 {'.','.','e','e','e','.','.',},
		 {'.','e','e','e','e','e','.',},
		 {'e','e','e','e','e','e','e',},
		 {'e','e','e','e','e','e','e',},
		 {'e','e','e','e','e','e','e',},
		 {'.','e','e','e','e','e','.',},
		 {'.','.','e','e','e','.','.',}};
	
	private static final char[][] FlatMapForWindow = {
			 {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			 {'.','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','Y','.','.','.','Y','.','.','Y','Y','Y','.','.','Y','.','.','.','Y','.','.','.','.','.','Y','.','.','.','Y','.','Y','.','Y','.','.','.','Y','.','.','.','B','.'},
			 {'.','B','.','.','.','Y','.','.','.','Y','.','Y','.','.','.','Y','.','Y','.','.','.','Y','.','.','.','.','.','Y','.','.','.','Y','.','Y','.','Y','Y','.','.','Y','.','.','.','B','.'},
			 {'.','B','.','.','.','.','Y','.','Y','.','.','Y','.','.','.','Y','.','Y','.','.','.','Y','.','.','.','.','.','Y','.','Y','.','Y','.','Y','.','Y','.','Y','.','Y','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','Y','.','.','.','Y','.','.','.','Y','.','Y','.','.','.','Y','.','.','.','.','.','Y','.','Y','.','Y','.','Y','.','Y','.','.','Y','Y','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','Y','.','.','.','.','Y','Y','Y','.','.','.','Y','Y','Y','Y','.','.','.','.','.','.','Y','.','Y','.','.','Y','.','Y','.','.','.','Y','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','E','.','.','.','.','.','.','.','.','.','.','.','.','E','.','.','.','.','.','.','.','.','.','.','.','.','E','.','.','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','E','E','E','.','.','.','.','.','.','.','.','.','.','E','E','E','.','.','.','.','.','.','.','.','.','.','E','E','E','.','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','E','E','E','E','E','.','.','.','.','.','.','.','.','E','E','E','E','E','.','.','.','.','.','.','.','.','E','E','E','E','E','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','E','E','E','E','E','E','E','.','.','.','.','.','.','E','E','E','E','E','E','E','.','.','.','.','.','.','E','E','E','E','E','E','E','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','E','E','E','E','E','E','E','.','.','.','.','.','.','E','E','E','E','E','E','E','.','.','.','.','.','.','E','E','E','E','E','E','E','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','E','E','E','E','E','E','E','.','.','.','.','.','.','E','E','E','E','E','E','E','.','.','.','.','.','.','E','E','E','E','E','E','E','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','E','E','E','E','E','.','.','.','.','.','.','.','.','E','E','E','E','E','.','.','.','.','.','.','.','.','E','E','E','E','E','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','E','E','E','.','.','.','.','.','.','.','.','.','.','E','E','E','.','.','.','.','.','.','.','.','.','.','E','E','E','.','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','B','.'},
			 {'.','B','.','T','T','T','.','T','T','T','.','.','.','T','T','T','.','T','T','T','.','.','C','C','.','t','t','t','.','t','t','t','.','.','.','t','t','t','.','t','t','t','.','B','.'},
			 {'.','B','.','T','.','T','.','T','.','T','.','T','.','T','.','T','.','T','.','T','.','C','.','.','.','t','.','t','.','t','.','t','.','t','.','t','.','t','.','t','.','t','.','B','.'},
			 {'.','B','.','T','T','T','.','T','T','T','.','.','.','T','T','T','.','T','T','T','.','.','C','.','.','t','t','t','.','t','t','t','.','.','.','t','t','t','.','t','t','t','.','B','.'},
			 {'.','B','.','T','.','T','.','T','.','T','.','T','.','T','.','T','.','T','.','T','.','.','.','C','.','t','.','t','.','t','.','t','.','t','.','t','.','t','.','t','.','t','.','B','.'},
			 {'.','B','.','T','T','T','.','T','T','T','.','.','.','T','T','T','.','T','T','T','.','C','C','.','.','t','t','t','.','t','t','t','.','.','.','t','t','t','.','t','t','t','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','B','.'},
			 {'.','B','.','M','M','M','M','M','.','.','M','.','M','.','M','.','M','.','.','.','M','M','M','M','M','.','.','.','.','M','.','M','.','.','.','.','M','.','.','M','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','M','.','M','.','M','.','M','.','.','M','.','.','.','.','.','M','.','.','M','.','.','M','.','.','.','.','.','M','.','.','M','.','.','B','.'},
			 {'.','B','.','M','M','M','M','M','.','.','M','.','M','.','M','M','.','.','.','M','.','.','.','M','.','M','.','.','M','M','.','M','M','.','.','.','.','.','M','.','.','M','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','M','.','M','.','M','.','M','.','.','M','.','.','M','M','M','.','.','.','M','.','.','M','.','M','.','.','.','M','.','.','M','.','.','B','.'},
			 {'.','B','.','M','M','M','M','M','.','.','.','M','.','.','M','.','M','.','.','.','M','.','.','M','.','.','.','.','M','.','.','.','M','M','.','.','M','.','.','M','.','.','.','B','.'},
			 {'.','B','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','B','.'},
			 {'.','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','B','.'},
			 {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'}};
;
	
	public EggsWindowMap( )
	{
		super( );
		FlatMap = FlatMapForWindow;
		mapWidth = FlatMapForWindow[0].length;
		mapHeight = FlatMapForWindow.length;

	}
	
	public int getMapWidth()
	{
		return FlatMapForWindow[0].length;
	}
	
	public int getMapHeight()
	{
		return  FlatMapForWindow.length;
	}
	
	public char getFlatMap(int x, int y)
	{
		return FlatMapForWindow[y][x];
	}
	
	public static int getMapWidthStatic()
	{
		return FlatMapForWindow[0].length;
	}
	
	public static int getMapHeightStatic()
	{
		return  FlatMapForWindow.length;
	}
	
	public static char getFlatMapStatic(int x, int y)
	{
		return FlatMapForWindow[y][x];
	}
	
	public static Rect getMenuBounds()
	{
		return menuBounds;
	}
	
	public static Rect getRetryBounds()
	{
		return retryBounds;
	}
	
	public static Rect getNextBounds()
	{
		return nextBounds;
	}

    public static Rect getVkBounds()
    {
        return vkBounds;
    }

    public static Rect getFbBounds()
    {
        return fbBounds;
    }
}
