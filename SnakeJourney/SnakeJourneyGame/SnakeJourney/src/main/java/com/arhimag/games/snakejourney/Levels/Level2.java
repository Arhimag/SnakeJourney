package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.SnakePart;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.framework.Point;

public class Level2 extends GameLevel
{
	public Level2(  GameMap map )
	{
		super(map);

        egg1 = (long)600 * (long)1000000000 - 1; // 1 ???
        egg2 = (long)150 * (long)1000000000; // 5 ?????
        egg3 = (long)90 * (long)1000000000; // 1 ??????

        this.pauseButton = false;
		
		int snakeStartX = 4;
		int snakeStartY = 1;
		snakes = new Snake[1];
		moved = new boolean[1];
		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX - 2, snakeStartY));
		snakes[0].lastx = snakeStartX - 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].finishSize = 35;
		snakes[0].direction = Snake.RIGHT;
		
		food = new Food[5];
		food[0] = new Food( snakeStartX + 3, snakeStartY);
		food[1] = new Food( snakeStartX + 7, snakeStartY);
		food[2] = new Food( snakeStartX + 11, snakeStartY);
		food[3] = new Food( snakeStartX + 17, snakeStartY);
		food[4] = new Food( snakeStartX + 21, snakeStartY);
		
		finishes = new Point[3];
		
		finishes[0] = new Point(22,28);
		finishes[1] = new Point(23,28);
		finishes[2] = new Point(24,28);
		
		winLength = 35;
	}

}
