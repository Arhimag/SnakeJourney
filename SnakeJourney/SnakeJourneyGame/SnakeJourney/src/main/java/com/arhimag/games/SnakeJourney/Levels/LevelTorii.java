package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.framework.Point;

public class LevelTorii extends GameLevel
{
	public LevelTorii(  GameMap map )
	{
		super(map);
		
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)300 * (long)1000000000; // 5 �����
		egg3 = (long)120 * (long)1000000000; // 1 ������
		
		int snakeStartX = 15;
		int snakeStartY = 14;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX, snakeStartY + 1));
		snakes[0].parts.add(new SnakePart(snakeStartX + 1, snakeStartY + 1));
		snakes[0].lastx = snakeStartX + 1;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 20;
		
		food = new Food[6];
		food[0] = new Food( 4, 6 );
		food[1] = new Food( 11, 2 );
		food[2] = new Food( 7, 10 );
		food[3] = new Food( 20, 6 );
		food[4] = new Food( 20, 6 );
		food[5] = new Food( 20, 6 );
		
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		generateNewFood(4);
		generateNewFood(5);
		
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
