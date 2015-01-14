package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.GameElements.Teleport;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.framework.Point;

public class LevelMask extends GameLevel
{
	public LevelMask(  GameMap map )
	{
		super(map);
		
		int snakeStartX = 14;
		int snakeStartY = 5;
		
		egg1 = (long)1800 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)150 * (long)1000000000; // 5 �����
		egg3 = (long)60 * (long)1000000000; // 1,5 ������
		
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX, snakeStartY - 1));
		snakes[0].lastx = snakeStartX;
		snakes[0].lasty = snakeStartY - 2;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 10;
		
		food = new Food[3];
		food[0] = new Food( 5, 3 );
		food[1] = new Food( 24, 3 );
		food[2] = new Food( 5, 13 );
		
		
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
				
		finishes = new Point[4];
		
		finishes[0] = new Point(13,14);
		finishes[1] = new Point(14,14);
		finishes[2] = new Point(15,14);
		finishes[3] = new Point(16,14);
		
		teleports = new Teleport[12];
		teleports[0] = new Teleport(9, 4, 9);
		teleports[1] = new Teleport(9, 10, 6);
		teleports[2] = new Teleport(9, 5, 10);
		teleports[3] = new Teleport(9, 11, 7);
		teleports[4] = new Teleport(9, 6, 11);
		teleports[5] = new Teleport(9, 12, 8);
		teleports[6] = new Teleport(20, 10, 0);
		teleports[7] = new Teleport(20, 4, 3);
		teleports[8] = new Teleport(20, 11, 1);
		teleports[9] = new Teleport(20, 5, 4);
		teleports[10] = new Teleport(20, 12, 2);
		teleports[11] = new Teleport(20, 6, 5);
	
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
