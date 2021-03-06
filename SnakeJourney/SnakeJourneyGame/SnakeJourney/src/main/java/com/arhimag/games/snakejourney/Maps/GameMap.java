package com.arhimag.games.snakejourney.Maps;

import com.arhimag.games.snakejourney.GameLevelDrawer;

public abstract class GameMap
{
	protected int mapWidth = 48;
	protected int mapHeight = 29;
	protected char[][] FlatMap;
	protected int[][] FlatMapColors = null;
	protected boolean colorsInited = false;

	
	public GameMap()
	{
		
	}
	
	public int getMapWidth()
	{
		return mapWidth;
	}
	
	public int getMapHeight()
	{
		return  mapHeight;
	}
	
	public char getFlatMap(int x, int y)
	{
		return FlatMap[y][x];
	}
	
	public int getFlatMapColor(int x, int y)
	{
		return FlatMapColors[y][x]; 
	}
	
	public void setFlatMapColor(int x, int y, int color)
	{
		FlatMapColors[y][x] = color;
	}
	
	public void initFlatMapColor()
	{
		FlatMapColors = new int[mapHeight][mapWidth];
		for(int i = 0; i < mapWidth; i++)
			for( int j = 0; j < mapHeight; j++)
				FlatMapColors[j][i] = GameLevelDrawer.getColor(getFlatMap(i,j), i, j);
		colorsInited = true; 
	}
	
	public boolean isColorsInited()
	{
		return colorsInited;
	}
}
