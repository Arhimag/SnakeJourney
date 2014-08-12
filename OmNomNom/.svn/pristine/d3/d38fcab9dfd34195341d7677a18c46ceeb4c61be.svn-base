package com.arhimag.games.omnomnom.Levels;

import com.arhimag.games.omnomnom.PlayerSnake;
import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.SnakePart;
import com.arhimag.games.omnomnom.Maps.GameMap;

public class HelpLevel extends GameLevel
{
	public HelpLevel(GameMap map)
	{
		super(map);
		
		int snakeStartX = 26;
		int snakeStartY = 15;
		
		snakes = new Snake[1];
		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		for( int i = 0; i < 8; i++)
			snakes[0].parts.add(new SnakePart(snakeStartX - 1 - i, snakeStartY));		
		snakes[0].lastx = snakeStartX - 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
	}
	
	@Override
	public void update(float deltaTime, int snakeid  )
	{
	}
	
	@Override
	public boolean getMoved( int snakeid)
	{
		return false;
	}
	
}
