package com.arhimag.games.snakejourney.Maps;

import android.graphics.Rect;

public class LevelsListMap extends GameMap
{


	public LevelsListMap( )
	{
		super( );

		FlatMap = new char[][]{
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',} ,
				{'_',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ',' ',' ','L','L',' ',' ',' ','L',' ','L',' ',' ',' ',' ','L','L',' ',' ',' ','L',' ',' ',' ',' ',' ',' ',' ','L','L',' ',' ',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ','L',' ','L',' ',' ',' ','L',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ',' ',' ','L',' ',' ',' ',' ','L',' ','L',' ',' ',' ',' ','L',' ',' ',' ',' ','L',' ',' ',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ','L',' ','L',' ',' ',' ','L',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ',' ',' ','L','L','L','L',' ',' ',' ',' ','L','L',' ',' ',' ',' ','L',' ',' ',' ',' ',' ','L','L',' ',' ',' ','L','L','L','L',' ',' ',' ','L','L',' ',' ',' ',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','_','_','_',' ',' ',' ',' ','_',},
				{'_',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','_',},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_',}};
		
		this.mapHeight = this.FlatMap.length;
		this.mapWidth = this.FlatMap[0].length;
	}
	
	private static Rect nextBounds = new Rect(46,13,46+3,13+10);
	public static final char[][] nextlabel = 
		{
		{'L',' ',' '},
		{'L',' ',' '},
		{' ','L',' '},
		{' ','L',' '},
		{' ',' ','L'},
		{' ',' ','L'},
		{' ','L',' '},
		{' ','L',' '},
		{'L',' ',' '},
		{'L',' ',' '},
		};
	public static Rect getNextBounds()
	{
		return nextBounds;
	}
	
	private static Rect previousBounds = new Rect(0,13,0+3,13+10);
	public static final char[][] previouslabel = 
		{
		{' ',' ','L'},
		{' ',' ','L'},
		{' ','L',' '},
		{' ','L',' '},
		{'L',' ',' '},
		{'L',' ',' '},
		{' ','L',' '},
		{' ','L',' '},
		{' ',' ','L'},
		{' ',' ','L'},
		};
	public static Rect getPreviousBounds()
	{
		return previousBounds;
	}

}

