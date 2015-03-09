package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.SnakePart;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.framework.Point;

public class LevelLabirinth extends GameLevel
{
	public LevelLabirinth(  GameMap map )
	{
		super(map);
		
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)300 * (long)1000000000; // 5 �����
		egg3 = (long)60 * (long)1000000000; // 1 ������
		
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
