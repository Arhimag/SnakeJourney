package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.framework.Point;

public class LevelHypno extends GameLevel
{
	public LevelHypno(  GameMap map )
	{
		super(map);
		
		egg1 = (long)300 * (long)1000000000 - 1; //5 ���
		egg2 = (long)120 * (long)1000000000; // 2 �����
		egg3 = (long)45 * (long)1000000000; // .5 ������
		
		int snakeStartX = 15;
		int snakeStartY = 10;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].lastx = snakeStartX - 2;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 7;
		
		food = new Food[10];
		food[0] = new Food( 14, 14 );
		food[1] = new Food( 15, 14 );
		food[2] = new Food( 14, 13 );
		food[3] = new Food( 15, 13 );
		food[4] = new Food( 14, 14 );
		food[5] = new Food( 15, 14 );
		food[6] = new Food( 14, 13 );
		food[7] = new Food( 15, 13 );
		food[8] = new Food( 14, 14 );
		food[9] = new Food( 15, 14 );
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		generateNewFood(4);
		generateNewFood(5);
		generateNewFood(6);
		generateNewFood(7);
		generateNewFood(8);
		generateNewFood(9);
		
		finishes = new Point[3];
		
		finishes[0] = new Point(13,0);
		finishes[1] = new Point(12,0);
		finishes[2] = new Point(11,0);
		
		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
