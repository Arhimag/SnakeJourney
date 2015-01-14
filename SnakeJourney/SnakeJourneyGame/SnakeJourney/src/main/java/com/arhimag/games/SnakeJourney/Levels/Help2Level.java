package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.Maps.GameMap;

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
