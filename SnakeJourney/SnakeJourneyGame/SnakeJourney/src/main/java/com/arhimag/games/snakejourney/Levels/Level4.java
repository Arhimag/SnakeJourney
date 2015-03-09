package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.SnakePart;
import com.arhimag.games.snakejourney.GameElements.Teleport;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.framework.Point;

public class Level4 extends GameLevel
{
	public Level4(  GameMap map )
	{
		super(map);

        egg1 = (long)900 * (long)1000000000 - 1; // 1 ???
        egg2 = (long)300 * (long)1000000000; // 5 ?????
        egg3 = (long)120 * (long)1000000000; // 1 ??????

        this.pauseButton = false;
		int snakeStartX = 6;
		int snakeStartY = 4;
		snakes = new Snake[1];
		moved = new boolean[1];
		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX + 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX + 2, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX + 3, snakeStartY));
		snakes[0].lastx = snakeStartX + 4;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.LEFT;
		snakes[0].finishSize = 30;
		
		food = new Food[5];
		food[0] = new Food( snakeStartX + 3, snakeStartY);
		food[1] = new Food( snakeStartX + 7, snakeStartY);
		food[2] = new Food( snakeStartX + 11, snakeStartY);
		food[3] = new Food( snakeStartX + 17, snakeStartY);
		food[4] = new Food( snakeStartX + 21, snakeStartY);
		
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		generateNewFood(4);
		
		finishes = new Point[2];
		
		finishes[0] = new Point(14,32);
		finishes[1] = new Point(22,32);
		
		teleports = new Teleport[4];
		teleports[0] = new Teleport(9,11,1);
		teleports[1] = new Teleport(28,28,2);
		teleports[2] = new Teleport(28,11,3);
		teleports[3] = new Teleport(9,28,0);
		
		winLength = 30;
		
	}

}
