package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.framework.Point;

public class LevelWelcomeToVisualDoj extends GameLevel
{
	public LevelWelcomeToVisualDoj(  GameMap map )
	{
		super(map);
		
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)200 * (long)1000000000; // 5 �����
		egg3 = (long)60 * (long)1000000000; // 1 ������
		
		int snakeStartX = 4;
		int snakeStartY = 1;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].lastx = snakeStartX - 2;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 2;
		
		finishes = new Point[1];
		
		finishes[0] = new Point(26,14);

		food = new Food[1];
		
		food[0] = new Food(0,0);
		
		generateNewFood(0);
		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
