package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.framework.Point;

public class LevelMiniSquare extends GameLevel
{
	public LevelMiniSquare(  GameMap map )
	{
		super(map);
		
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)300 * (long)1000000000; // 5 �����
		egg3 = (long)120 * (long)1000000000; // 1 ������
		
		int snakeStartX = 3;
		int snakeStartY = 2;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].lastx = snakeStartX - 2;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 35;
		
		food = new Food[8];
		food[0] = new Food( 4, 6 );
		food[1] = new Food( 11, 2 );
		food[2] = new Food( 7, 10 );
		food[3] = new Food( 10, 6 );
		food[4] = new Food( 4, 6 );
		food[5] = new Food( 11, 2 );
		food[6] = new Food( 7, 10 );
		food[7] = new Food( 10, 6 );
		
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		generateNewFood(4);
		generateNewFood(5);
		generateNewFood(6);
		generateNewFood(7);
		
		
		finishes = new Point[4];
		
		finishes[0] = new Point(6,16);
		finishes[1] = new Point(7,16);
		finishes[2] = new Point(8,16);
		finishes[3] = new Point(9,16);
		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
