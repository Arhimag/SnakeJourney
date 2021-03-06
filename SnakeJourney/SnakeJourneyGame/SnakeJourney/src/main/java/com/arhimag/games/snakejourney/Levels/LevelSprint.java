package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.SnakePart;
import com.arhimag.games.snakejourney.AI.BFSAISnake;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.framework.Point;

public class LevelSprint extends GameLevel
{
	private int currentPlayerFoodId = 0;
	private int playerFoodDirection = -1;
	private int currentAIFoodId = 0;
	private int aIFoodDirection = -1;
	private static int playerFoods[][] =
		{
		{5,15},
		{10, 14},
		{14,13},
		{14,11},
		{2, 10},
		{14, 7},
		{8,6},
		};
	
	private static int aiFoods[][] =
		{
		{30, 18},
		{30, 16},
		{30, 13},
		{30, 9},
		{30, 7},
		{30, 5},
		{30, 3},
		};
	
	public LevelSprint(  GameMap map )
	{
		super(map);
		
		int snakeStartX = 10;
		int snakeStartY = 18;
	
		snakes = new Snake[2];
		moved = new boolean[2];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX + 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX + 2, snakeStartY));
		snakes[0].lastx = snakeStartX + 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.LEFT;
		snakes[0].finishSize = 8;
	
		snakes[1] = new BFSAISnake(this, 21, snakeStartY, 7);
		snakes[1].parts.add(new SnakePart(20 , snakeStartY));
		snakes[1].parts.add(new SnakePart(19, snakeStartY));
		snakes[1].parts.add(new SnakePart(18, snakeStartY));
		snakes[1].lastx = 17;
		snakes[1].lasty = snakeStartY;
		snakes[1].direction = Snake.RIGHT;
		snakes[1].setBodyColor( 0xffeeeeaa );
		snakes[1].finishSize = 9;
		
		food = new Food[2];
		food[0] = new Food( snakeStartX + 3, snakeStartY);
		food[1] = new Food( snakeStartX + 5, snakeStartY);
		
		generateNewFood(0);
		generateNewFood(1);
		
		finishes = new Point[4];
		
		finishes[0] = new Point(15,3);
		finishes[1] = new Point(16,3);
		finishes[2] = new Point(15,18);
		finishes[3] = new Point(16,18);
		
		egg1 = (long)500 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)100 * (long)1000000000; // 8 �����
		egg3 = (long)30 * (long)1000000000; // 3 ������
		
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

	protected void generateNewFood( int i )
	{
		if( i == 0 ) // ��� ������
		{
			if( currentPlayerFoodId == playerFoods.length - 1  || currentPlayerFoodId == 0 )
				playerFoodDirection *= -1;
			currentPlayerFoodId += playerFoodDirection;
			food[i].x = playerFoods[currentPlayerFoodId][0];
			food[i].y = playerFoods[currentPlayerFoodId][1];
		}
		else
		{
			if( currentAIFoodId == aiFoods.length - 1  || currentAIFoodId == 0 )
				aIFoodDirection *= -1;
			currentAIFoodId += aIFoodDirection;
			food[i].x = aiFoods[currentAIFoodId][0];
			food[i].y = aiFoods[currentAIFoodId][1];
		}
	}
}
