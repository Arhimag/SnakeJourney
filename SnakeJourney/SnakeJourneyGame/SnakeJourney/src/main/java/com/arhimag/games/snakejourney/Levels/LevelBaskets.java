package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.SnakePart;
import com.arhimag.games.snakejourney.AI.BFSAISnake;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.framework.Point;

public class LevelBaskets extends GameLevel
{
	public LevelBaskets(  GameMap map )
	{
		super(map);
		
		egg1 = (long)1800 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)300 * (long)1000000000; // 8 �����
		egg3 = (long)120 * (long)1000000000; // 3 ������
		
		int snakeStartX = 6;
		int snakeStartY = 2;
	
		snakes = new Snake[2];
		moved = new boolean[2];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX - 2, snakeStartY));
		snakes[0].lastx = snakeStartX - 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 15;
	
		snakes[1] = new BFSAISnake(this, map.getMapWidth() - snakeStartX, snakeStartY, 7);
		snakes[1].parts.add(new SnakePart(map.getMapWidth() - snakeStartX + 1 , snakeStartY));
		snakes[1].parts.add(new SnakePart(map.getMapWidth() - snakeStartX + 2, snakeStartY));
		snakes[1].lastx = map.getMapWidth() - snakeStartX + 3;
		snakes[1].lasty = snakeStartY;
		snakes[1].direction = Snake.UP;
		snakes[1].setBodyColor( 0xffeeeeaa );
		snakes[1].finishSize = 10000;
		
		food = new Food[10];
		food[0] = new Food( snakeStartX + 3, snakeStartY);
		food[1] = new Food( snakeStartX + 5, snakeStartY);
		food[2] = new Food( snakeStartX + 7, snakeStartY);
		food[3] = new Food( snakeStartX + 9, snakeStartY);
		food[4] = new Food( snakeStartX + 10, snakeStartY);
		food[5] = new Food( snakeStartX + 3, snakeStartY);
		food[6] = new Food( snakeStartX + 5, snakeStartY);
		food[7] = new Food( snakeStartX + 7, snakeStartY);
		food[8] = new Food( snakeStartX + 9, snakeStartY);
		food[9] = new Food( snakeStartX + 10, snakeStartY);
		
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
		
		finishes = new Point[4];
		
		finishes[0] = new Point(this.map.getMapWidth()/2 - 2,map.getMapHeight() - 1);
		finishes[1] = new Point(this.map.getMapWidth()/2 - 1,map.getMapHeight() - 1);
		finishes[2] = new Point(this.map.getMapWidth()/2 ,map.getMapHeight() - 1);
		finishes[3] = new Point(this.map.getMapWidth()/2 + 1,map.getMapHeight() - 1);
		
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

}
