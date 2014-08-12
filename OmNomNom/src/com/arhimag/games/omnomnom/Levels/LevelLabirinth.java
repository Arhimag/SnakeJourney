package com.arhimag.games.omnomnom.Levels;

import com.arhimag.games.omnomnom.Food;
import com.arhimag.games.omnomnom.PlayerSnake;
import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.SnakePart;
import com.arhimag.games.omnomnom.Maps.GameMap;
import com.arhimag.games.omnomnom.framework.Point;

public class LevelLabirinth extends GameLevel
{
	public LevelLabirinth(  GameMap map )
	{
		super(map);
		
		int snakeStartX = 3;
		int snakeStartY = 2;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].lastx = snakeStartX - 2;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 10;
		
		food = new Food[4];
		food[0] = new Food( 4, 6 );
		food[1] = new Food( 11, 2 );
		food[2] = new Food( 7, 10 );
		food[3] = new Food( 20, 6 );
		
		finishes = new Point[2];
		
		finishes[0] = new Point(14,16);
		finishes[1] = new Point(15	,16);
		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
