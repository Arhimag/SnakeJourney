package com.arhimag.games.omnomnom.Maps;

import android.graphics.Rect;

public class PauseMenuMap extends GameMap
{

/*	private static Rect continueBounds = new Rect(4,2,44,7);
	//private static Rect hightScoreBounds = new Rect(4,11,4+47,11+6);
	private static Rect helpBounds = new Rect(6,22,41,28);
	private static Rect levelsListBounds = new Rect(6,8,42,14);
	private static Rect settingsBounds = new Rect(5,15,44,21); */
	private static Rect continueBounds = new Rect(2,1,44,17);
	//private static Rect hightScoreBounds = new Rect(4,11,4+47,11+6);
	private static Rect helpBounds = new Rect(36,20,44,28);
	private static Rect levelsListBounds = new Rect(3,20,11,28);
	private static Rect settingsBounds = new Rect(25,20,33,28);
	private static Rect achivementsBounds = new Rect(14,20,22,28);
	
	public PauseMenuMap( )
	{
		super( );
/*		FlatMap = new char[][]{			
			{'R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R'},
			{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ','#','#',' ',' ',' ','#','#',' ',' ','#',' ',' ',' ','#',' ','#','#','#','#','#',' ','#',' ','#',' ',' ',' ','#',' ','#',' ',' ','#',' ',' ','#','#','#',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ','#',' ',' ','#',' ','#',' ',' ','#',' ','#','#',' ',' ','#',' ',' ',' ','#',' ',' ',' ','#',' ','#','#',' ',' ','#',' ','#',' ',' ','#',' ','#',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ','#',' ',' ',' ',' ','#',' ',' ','#',' ','#',' ','#',' ','#',' ',' ',' ','#',' ',' ',' ','#',' ','#',' ','#',' ','#',' ','#',' ',' ','#',' ',' ','#','#',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ','#',' ',' ','#',' ','#',' ',' ','#',' ','#',' ',' ','#','#',' ',' ',' ','#',' ',' ',' ','#',' ','#',' ',' ','#','#',' ','#',' ',' ','#',' ','#',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ','#','#',' ',' ',' ','#','#',' ',' ','#',' ',' ',' ','#',' ',' ',' ','#',' ',' ',' ','#',' ','#',' ',' ',' ','#',' ',' ','#','#',' ',' ',' ','#','#','#',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ','W',' ',' ',' ',' ',' ',' ','W','W','W',' ',' ',' ','W',' ','W',' ',' ',' ',' ','W','W','W',' ',' ',' ','W',' ',' ',' ',' ',' ',' ','W','W',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ','W',' ',' ',' ',' ',' ',' ','W',' ',' ',' ',' ',' ','W',' ','W',' ',' ',' ',' ','W',' ',' ',' ',' ',' ','W',' ',' ',' ',' ',' ','W',' ',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ','W',' ',' ',' ',' ',' ',' ','W','W',' ',' ',' ',' ','W',' ','W',' ',' ',' ',' ','W','W',' ',' ',' ',' ','W',' ',' ',' ',' ',' ',' ','W',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ','W',' ',' ',' ',' ',' ',' ','W',' ',' ',' ',' ',' ','W',' ','W',' ',' ',' ',' ','W',' ',' ',' ',' ',' ','W',' ',' ',' ',' ',' ',' ',' ','W',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ','W','W','W',' ',' ',' ',' ','W','W','W',' ',' ',' ',' ','W',' ',' ',' ',' ',' ','W','W','W',' ',' ',' ','W','W','W',' ',' ',' ','W','W',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ','G','G',' ',' ','G','G','G',' ',' ','G','G','G',' ',' ','G','G','G',' ',' ','G',' ',' ','G',' ',' ','G',' ',' ',' ','G','G',' ',' ',' ',' ','G','G',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ','G',' ',' ',' ',' ','G',' ',' ',' ',' ',' ','G',' ',' ',' ',' ','G',' ',' ',' ',' ',' ',' ','G','G',' ','G',' ',' ','G',' ',' ','G',' ',' ','G',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ','G',' ',' ',' ','G','G',' ',' ',' ',' ','G',' ',' ',' ',' ','G',' ',' ',' ','G',' ',' ','G',' ','G','G',' ',' ','G',' ',' ',' ',' ',' ',' ','G',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ',' ','G',' ',' ','G',' ',' ',' ',' ',' ','G',' ',' ',' ',' ','G',' ',' ',' ','G',' ',' ','G',' ',' ','G',' ',' ','G',' ','G','G',' ',' ',' ',' ','G',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ','G','G',' ',' ',' ','G','G','G',' ',' ',' ','G',' ',' ',' ',' ','G',' ',' ',' ','G',' ',' ','G',' ',' ','G',' ',' ',' ','G','G',' ',' ',' ','G','G',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ','Q',' ',' ',' ',' ','Q',' ',' ',' ','Q','Q','Q','Q','Q','Q',' ',' ',' ','Q',' ',' ',' ',' ',' ',' ',' ',' ','Q','Q','Q','Q','Q','Q',' ',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ','Q',' ',' ',' ',' ','Q',' ',' ',' ','Q',' ',' ',' ',' ',' ',' ',' ',' ','Q',' ',' ',' ',' ',' ',' ',' ',' ','Q',' ',' ',' ',' ',' ','Q',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ','Q','Q','Q','Q','Q','Q',' ',' ',' ','Q','Q','Q','Q',' ',' ',' ',' ',' ','Q',' ',' ',' ',' ',' ',' ',' ',' ','Q',' ',' ',' ',' ',' ','Q',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ','Q',' ',' ',' ',' ','Q',' ',' ',' ','Q',' ',' ',' ',' ',' ',' ',' ',' ','Q',' ',' ',' ',' ','Q',' ',' ',' ','Q','Q','Q','Q','Q','Q',' ',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ','Q',' ',' ',' ',' ','Q',' ',' ',' ','Q','Q','Q','Q','Q','Q',' ',' ',' ','Q','Q','Q','Q','Q','Q',' ',' ',' ','Q',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
			{'R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R'}}; */
		FlatMap = new char[][]{
				{'!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!'},
				{'!','.','.','.','.','.','.','.','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','.','.','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P','P','P','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P','P','P','P','P','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P','P','P','P','P','P','P','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P','P','P','P','P','P','P','P','P','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P','P','P','P','P','P','P','P','P','P','P','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P','P','P','P','P','P','P','P','P','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P','P','P','P','P','P','P','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P','P','P','P','P','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P','P','P','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','.','P',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','P','.','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','.','.','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','.','.','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','!'},
				{'!','.','.','L','L','L','L','L','L','L','L','L','.','.','A','A','A','A','A','A','A','A','A','.','.','.','.','s','s','.','.','s','.','.','.','.','.','.','.','H','H','H','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','.','.','.','.','.','.','.','.','A','A','A','A','A','A','A','A','A','.','.','.','s','s','s','.','s','.','.','.','.','.','.','H','H','H','H','H','H','H','.','.','.','!'},
				{'!','.','.','L','L','L','L','L','L','L','L','L','.','.','.','A','A','A','A','A','A','A','.','.','.','s','s','s','.','.','.','s','.','s','.','.','H','H','H','H','.','H','H','H','H','.','.','!'},
				{'!','.','.','L','L','L','L','L','L','L','L','L','.','.','.','A','A','A','A','A','A','A','.','.','.','s','s','.','s','.','s','.','s','.','.','.','H','H','.','.','.','.','.','H','H','.','.','!'},
				{'!','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','A','A','A','A','A','.','.','.','.','s','.','.','.','s','.','.','.','.','.','.','.','.','.','.','.','H','H','H','H','.','.','!'},
				{'!','.','.','L','L','L','L','L','L','L','L','L','.','.','.','.','.','.','A','.','.','.','.','.','.','.','.','.','s','.','s','.','.','.','.','.','.','.','.','H','H','H','H','H','.','.','.','!'},
				{'!','.','.','L','L','L','L','L','L','L','L','L','.','.','.','.','.','.','A','.','.','.','.','.','.','.','.','s','.','.','.','s','.','.','.','.','.','.','.','H','H','H','.','.','.','.','.','!'},
				{'!','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','A','A','A','.','.','.','.','.','.','s','.','.','.','.','.','s','.','.','.','.','.','.','.','.','.','.','.','.','.','.','!'},
				{'!','.','.','L','L','L','L','L','L','L','L','L','.','.','.','A','A','A','A','A','A','A','.','.','.','s','.','.','.','.','.','.','.','s','.','.','.','.','.','H','H','H','.','.','.','.','.','!'},
				{'!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!','!'}};

		this.mapHeight = this.FlatMap.length;
		this.mapWidth = this.FlatMap[0].length;
	}
	
	public static Rect getContinueBounds()
	{
		return continueBounds;
	}
	
	/*public static Rect getHightScoreBounds()
	{
		return hightScoreBounds;
	}*/
	
	public static Rect getLevelsListBounds()
	{
		return levelsListBounds;
	}
	
	
	public static Rect getSettingsBounds()
	{
		return settingsBounds;
	}
	public static Rect getHelpBounds()
	{
		return helpBounds;
	}
}

