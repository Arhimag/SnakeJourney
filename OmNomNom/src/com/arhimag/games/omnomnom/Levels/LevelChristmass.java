package com.arhimag.games.omnomnom.Levels;

import com.arhimag.games.omnomnom.Food;
import com.arhimag.games.omnomnom.PlayerSnake;
import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.SnakePart;
import com.arhimag.games.omnomnom.GameElements.Teleport;
import com.arhimag.games.omnomnom.Maps.GameMap;
import com.arhimag.games.omnomnom.framework.Point;

public class LevelChristmass extends GameLevel
{
	public LevelChristmass(  GameMap map )
	{
		super(map);
		
		egg1 = (long)1800 * (long)1000000000 - 1; // 1 час
		egg2 = (long)480 * (long)1000000000; // 8 минут
		egg3 = (long)180 * (long)1000000000; // 3 минута
		
		int snakeStartX = 14;
		int snakeStartY = 5;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX, snakeStartY - 1));
		snakes[0].lastx = snakeStartX;
		snakes[0].lasty = snakeStartY - 2;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 20;
		
		food = new Food[4];
		food[0] = new Food( 5, 3 );
		food[1] = new Food( 24, 3 );
		food[2] = new Food( 5, 13 );
		food[3] = new Food( 24, 13 );
		
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		
		finishes = new Point[2];
		
		finishes[0] = new Point(15,19);
		finishes[1] = new Point(16,19);
		
		teleports = new Teleport[14];
		teleports[0] = new Teleport(17, 7, 1);
		teleports[1] = new Teleport(10, 9, 13);
		teleports[2] = new Teleport(10, 11, 3);
		teleports[3] = new Teleport(25, 13, 12);
		teleports[4] = new Teleport(25, 15, 5);
		teleports[5] = new Teleport(16, 8, 0);
		
		teleports[6] = new Teleport(14, 7,7);
		teleports[7] = new Teleport(21, 9, 12);
		teleports[8] = new Teleport(21, 11, 9);
		teleports[9] = new Teleport(6, 13, 13);
		teleports[10] = new Teleport(6, 15, 11);
		teleports[11] = new Teleport(15, 8, 6);
		
		teleports[12] = new Teleport(4, 16, 13);
		teleports[13] = new Teleport(27, 16, 12);
		
		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
