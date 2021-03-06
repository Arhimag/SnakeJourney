package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.AI.BFSAISnake;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.framework.Point;

public class Level4Rooms extends GameLevel
{
	public Level4Rooms(  GameMap map )
	{
		super(map);
		
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)600 * (long)1000000000; // 10 �����
		egg3 = (long)120 * (long)1000000000; // 2 ������
		
		int snakeStartX = 4;
		int snakeStartY = 4;
	
		snakes = new Snake[2];
		moved = new boolean[2];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX, snakeStartY - 1));
		snakes[0].lastx = snakeStartX;
		snakes[0].lasty = snakeStartY - 2;
		snakes[0].direction = Snake.DOWN;
		snakes[0].finishSize = 10;
		
		
		snakes[1] = new BFSAISnake(this, map.getMapWidth() - 1 - snakeStartX, map.getMapHeight() - 1 - snakeStartY,5);
		snakes[1].parts.add(new SnakePart(map.getMapWidth() - 1 - snakeStartX,map.getMapHeight() - 1 - (snakeStartY - 1)));
		snakes[1].lastx = map.getMapWidth() - 1 - snakeStartX;
		snakes[1].lasty = map.getMapHeight() - 1 - (snakeStartY - 2);
		snakes[1].direction = Snake.UP;
		snakes[1].finishSize = 100;
		
		food = new Food[4];
		food[0] = new Food( 14, 14 );
		food[1] = new Food( 15, 14 );
		food[2] = new Food( 14, 13 );
		food[3] = new Food( 15, 13 );
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		finishes = new Point[4];
		
		finishes[0] = new Point(14,19);
		finishes[1] = new Point(13,19);
		finishes[2] = new Point(17,19);
		finishes[3] = new Point(18,19);
		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		if(!botMoved)
		{
			((BFSAISnake)snakes[1]).nextTurn();
			botMoved = true;
		}
		super.update(deltaTime, snake_id);
	}

	public float getTickTime()
	{
		return ticksEverySecs - 0.003f * this.snakes[playerSnake].parts.size();
	}
	
}
