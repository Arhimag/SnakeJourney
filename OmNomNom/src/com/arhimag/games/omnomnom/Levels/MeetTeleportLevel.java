package com.arhimag.games.omnomnom.Levels;

import com.arhimag.games.omnomnom.Food;
import com.arhimag.games.omnomnom.PlayerSnake;
import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.SnakePart;
import com.arhimag.games.omnomnom.AI.Deykstra2AISnake;
import com.arhimag.games.omnomnom.AI.SimpleAISnake;
import com.arhimag.games.omnomnom.GameElements.Teleport;
import com.arhimag.games.omnomnom.Maps.GameMap;
import com.arhimag.games.omnomnom.framework.Point;

public class MeetTeleportLevel extends GameLevel
{
	public MeetTeleportLevel(  GameMap map )
	{
		super(map);
		
		int snakeStartX = 4;
		int snakeStartY = 1;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX - 2, snakeStartY));
		snakes[0].lastx = snakeStartX - 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 20;


		food = new Food[3];
		food[0] = new Food( snakeStartX + 3, snakeStartY);
		food[1] = new Food( snakeStartX + 4, snakeStartY);
		food[2] = new Food( snakeStartX + 5, snakeStartY);

		

		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		
		
		finishes = new Point[6];
		
		finishes[0] = new Point(0,9);
		finishes[1] = new Point(0,10);
		finishes[5] = new Point(0,11);
		finishes[2] = new Point(30,9);
		finishes[3] = new Point(30,10);
		finishes[4] = new Point(30,11);
		
		teleports = new Teleport[10];
		teleports[0] = new Teleport(13, 7, 5);
		teleports[1] = new Teleport(13, 8, 6);
		teleports[2] = new Teleport(13, 9, 7);
		teleports[3] = new Teleport(13, 10, 8);
		teleports[4] = new Teleport(13, 11, 9);
		teleports[5] = new Teleport(16, 7, 0);
		teleports[6] = new Teleport(16, 8, 1);
		teleports[7] = new Teleport(16, 9, 2);
		teleports[8] = new Teleport(16, 10, 3);
		teleports[9] = new Teleport(16, 11, 4);


		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
