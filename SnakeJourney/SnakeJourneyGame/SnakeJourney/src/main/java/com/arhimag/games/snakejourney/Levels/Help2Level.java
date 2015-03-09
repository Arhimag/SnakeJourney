package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Maps.GameMap;

public class Help2Level extends GameLevel
{
	public Help2Level(GameMap map)
	{
		super(map);
		
        playerSnake  = -1;

	}
	
	@Override
	public void update(float deltaTime, int snakeId )
	{
	}
	
	@Override
	public boolean getMoved(int snakeid)
	{
		return false;
	}
	
}
