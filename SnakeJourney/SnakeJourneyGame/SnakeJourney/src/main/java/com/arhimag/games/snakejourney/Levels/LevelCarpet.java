package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.SnakePart;
import com.arhimag.games.snakejourney.GameElements.Teleport;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.framework.Point;

public class LevelCarpet extends GameLevel
{
	public LevelCarpet(  GameMap map )
	{
		super(map);
		
		egg1 = (long)1800 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)180 * (long)1000000000; // 5 �����
		egg3 = (long)90 * (long)1000000000; // 1,5 ������
		
		int snakeStartX = 14;
		int snakeStartY = 5;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX, snakeStartY - 1));
		snakes[0].lastx = snakeStartX;
		snakes[0].lasty = snakeStartY - 2;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 10;
		
		food = new Food[4];
		food[0] = new Food( 5, 3 );
		food[1] = new Food( 24, 3 );
		food[2] = new Food( 5, 13 );
		food[3] = new Food( 24, 13 );
		
		finishes = new Point[4];
		
		finishes[0] = new Point(0,8);
		finishes[1] = new Point(0,9);
		finishes[2] = new Point(30,8);
		finishes[3] = new Point(30,9);
		
		teleports = new Teleport[8];
		teleports[0] = new Teleport(1, 1, 1);
		teleports[1] = new Teleport(14, 8, 0);
		teleports[2] = new Teleport(1, 15, 3);
		teleports[3] = new Teleport(14, 9, 2);
		teleports[4] = new Teleport(28, 1, 5);
		teleports[5] = new Teleport(15, 8, 4);
		teleports[6] = new Teleport(28, 15,7);
		teleports[7] = new Teleport(15, 9, 6);
		
		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
