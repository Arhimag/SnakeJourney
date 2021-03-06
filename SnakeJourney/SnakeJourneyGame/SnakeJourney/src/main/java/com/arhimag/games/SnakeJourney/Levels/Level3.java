package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.GameElements.Teleport;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.framework.Point;

public class Level3 extends GameLevel
{
	public Level3(  GameMap map )
	{
		super(map);
		
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
