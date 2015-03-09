package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.GameElements.Teleport;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.framework.Point;

public class Level3 extends GameLevel
{
	public Level3(  GameMap map )
	{
		super(map);

        egg1 = (long)600 * (long)1000000000 - 1; // 1 ???
        egg2 = (long)300 * (long)1000000000; // 5 ?????
        egg3 = (long)90 * (long)1000000000; // 1 ??????

        this.pauseButton = false;
		
		int snakeStartX = 7;
		int snakeStartY = 3;
		snakes = new Snake[1];
		moved = new boolean[1];
		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].lastx = snakeStartX - 1;
		snakes[0].lasty = snakeStartY;
		snakes[0].finishSize = 40;
		snakes[0].direction = Snake.RIGHT;
		
		food = new Food[5];
		food[0] = new Food( snakeStartX + 3, snakeStartY);
		food[1] = new Food( snakeStartX + 7, snakeStartY);
		food[2] = new Food( snakeStartX + 11, snakeStartY);
		food[3] = new Food( snakeStartX + 17, snakeStartY);
		food[4] = new Food( snakeStartX + 21, snakeStartY);
		
		finishes = new Point[2];
		
		finishes[0] = new Point(17,13);
		finishes[1] = new Point(16,13);
		
		teleports = new Teleport[2];
		teleports[0] = new Teleport(7,7,1);
		teleports[1] = new Teleport(27,7,0);
		
		winLength = 40;
	}

}
