package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.SnakePart;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.framework.Point;

public class Level1 extends GameLevel
{
	public Level1(  GameMap map )
	{
		super(map);
		
		int snakeStartX = 4;
		int snakeStartY = 1;

        egg1 = (long)3600 * (long)1000000000 - 1; // 1 ???
        egg2 = (long)300 * (long)1000000000; // 5 ?????
        egg3 = (long)60 * (long)1000000000; // 1 ??????


        snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX - 2, snakeStartY));
		snakes[0].lastx = snakeStartX - 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 30;
		

		food = new Food[5];
		food[0] = new Food( snakeStartX + 3, snakeStartY);
		food[1] = new Food( snakeStartX + 5, snakeStartY);
		food[2] = new Food( snakeStartX + 7, snakeStartY);
		food[3] = new Food( snakeStartX + 9, snakeStartY);
		food[4] = new Food( snakeStartX + 10, snakeStartY);
		
		finishes = new Point[4];
		
		finishes[0] = new Point(this.map.getMapWidth()/2 - 1,map.getMapHeight()-1);
		finishes[1] = new Point(this.map.getMapWidth()/2 ,map.getMapHeight()-1);
		finishes[2] = new Point(this.map.getMapWidth()/2 + 1,map.getMapHeight()-1);
		finishes[3] = new Point(this.map.getMapWidth()/2 + 2,map.getMapHeight()-1);
		
		aqua = true;
		pauseButton = false;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
