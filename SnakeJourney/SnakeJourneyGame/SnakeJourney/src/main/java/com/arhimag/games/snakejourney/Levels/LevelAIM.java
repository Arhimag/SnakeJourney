package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.SnakePart;
import com.arhimag.games.snakejourney.AI.BFSAISnake;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.framework.Point;

public class LevelAIM extends GameLevel
{
	public LevelAIM(  GameMap map )
	{
		super(map);
		
		int snakeStartX = 4;
		int snakeStartY = 1;
	
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)600 * (long)1000000000; // 10 �����
		egg3 = (long)120 * (long)1000000000; // 2 ������
		
		snakes = new Snake[2];
		moved = new boolean[2];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX - 2, snakeStartY));
		snakes[0].lastx = snakeStartX - 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 15;
		
		snakes[1] = new BFSAISnake(this,this.map.getMapWidth() - snakeStartX - 4 - 1, snakeStartY, 7);
		snakes[1].parts.add(new SnakePart(this.map.getMapWidth() - snakeStartX - 3 - 1, snakeStartY));
		snakes[1].parts.add(new SnakePart(this.map.getMapWidth() - snakeStartX - 2 - 1, snakeStartY));
		snakes[1].lastx = this.map.getMapWidth() - snakeStartX - 1 - 1;
		snakes[1].lasty = snakeStartY;
		snakes[1].direction = Snake.LEFT;
		snakes[1].setBodyColor( 0xffaaeeee );
		snakes[1].finishSize = 20;
		
		food = new Food[4];
		food[0] = new Food( snakeStartX + 3, snakeStartY);
		food[1] = new Food( snakeStartX + 4, snakeStartY);
		food[2] = new Food( snakeStartX + 5, snakeStartY);
		food[3] = new Food( snakeStartX + 6, snakeStartY);
		
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		
		finishes = new Point[4];
		
		finishes[0] = new Point(this.map.getMapWidth()/2 - 1,map.getMapHeight() - 1);
		finishes[1] = new Point(this.map.getMapWidth()/2 ,map.getMapHeight() - 1);
		finishes[2] = new Point(this.map.getMapWidth()/2 + 1,map.getMapHeight() - 1);
		finishes[3] = new Point(this.map.getMapWidth()/2 + 2,map.getMapHeight() - 1);
		
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
