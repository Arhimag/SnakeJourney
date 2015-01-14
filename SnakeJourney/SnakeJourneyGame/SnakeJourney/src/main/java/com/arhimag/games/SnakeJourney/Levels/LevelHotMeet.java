package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.AI.BFSAISnake;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.framework.Point;

public class LevelHotMeet extends GameLevel
{
	public LevelHotMeet(  GameMap map )
	{
		super(map);
		
		int snakeStartX = 4;
		int snakeStartY = 1;
	
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)600 * (long)1000000000; // 10 �����
		egg3 = (long)180 * (long)1000000000; // 2 ������
		
		snakes = new Snake[3];
		moved = new boolean[3];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX - 2, snakeStartY));
		snakes[0].lastx = snakeStartX - 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 15;
		
		//snakes[1] = new HeapDeykstraAISnake(this, map.getMapWidth() - snakeStartX - 1 - 3, map.getMapHeight() - snakeStartY - 1);
		//snakes[1].parts.add(new SnakePart(map.getMapWidth() - snakeStartX - 1 - 2, map.getMapHeight() - snakeStartY - 1));
		//snakes[1].parts.add(new SnakePart(map.getMapWidth() - snakeStartX - 1 - 1, map.getMapHeight() - snakeStartY - 1));
		//snakes[1].lastx = map.getMapWidth() - snakeStartX - 1;
		//snakes[1].lasty = map.getMapHeight() - snakeStartY - 1;
		//snakes[1].direction = Snake.LEFT;
		snakes[1] = new BFSAISnake(this,this.map.getMapWidth() - snakeStartX - 4 - 1, snakeStartY, 7);
		snakes[1].parts.add(new SnakePart(this.map.getMapWidth() - snakeStartX - 3 - 1, snakeStartY));
		snakes[1].parts.add(new SnakePart(this.map.getMapWidth() - snakeStartX - 2 - 1, snakeStartY));
		snakes[1].lastx = this.map.getMapWidth() - snakeStartX - 1 - 1;
		snakes[1].lasty = snakeStartY;
		snakes[1].direction = Snake.LEFT;
		snakes[1].setBodyColor( 0xffaaeeee );
		snakes[1].finishSize = 25;
		
		snakes[2] = new BFSAISnake(this, map.getMapWidth() /2, map.getMapHeight() - 4 - 1, 11);
		snakes[2].parts.add(new SnakePart(map.getMapWidth() /2 , map.getMapHeight() - 3 - 1));
		snakes[2].parts.add(new SnakePart(map.getMapWidth() /2 , map.getMapHeight() - 2 - 1));
		snakes[2].lastx = map.getMapWidth() /2;
		snakes[2].lasty = map.getMapHeight() - 1 - 1;
		snakes[2].direction = Snake.UP;
		snakes[2].setBodyColor( 0xffeeeeaa );
		snakes[2].finishSize = 25;
		
		food = new Food[5];
		food[0] = new Food( snakeStartX + 2, snakeStartY);
		food[1] = new Food( snakeStartX + 3, snakeStartY);
		food[2] = new Food( snakeStartX + 4, snakeStartY);
		food[3] = new Food( snakeStartX + 5, snakeStartY);
		food[4] = new Food( snakeStartX + 6, snakeStartY);
		
		finishes = new Point[4];
		
		finishes[0] = new Point(this.map.getMapWidth()/2 - 1,2);
		finishes[1] = new Point(this.map.getMapWidth()/2 ,2);
		finishes[2] = new Point(this.map.getMapWidth()/2 + 1,2);
		finishes[3] = new Point(this.map.getMapWidth()/2 + 2,2);
		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		if(!botMoved)
		{
			((BFSAISnake)snakes[1]).nextTurn();
			((BFSAISnake)snakes[2]).nextTurn();
			botMoved = true;
		}
		super.update(deltaTime, snake_id);
	}

}