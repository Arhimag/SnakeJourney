package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.GameElements.Teleport;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.framework.Point;

public class LevelTrefoil extends GameLevel
{

	
	public LevelTrefoil(  GameMap map )
	{
		super(map);
		
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)300 * (long)1000000000; // 5 �����
		egg3 = (long)60 * (long)1000000000; // 1 ������
		
		int snakeStartX = 7;
		int snakeStartY = 5;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].lastx = snakeStartX - 2;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 20;
		
		food = new Food[4];
		food[0] = new Food( 14, 14 );
		food[1] = new Food( 15, 14 );
		food[2] = new Food( 14, 13 );
		food[3] = new Food( 15, 13 );
		
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		
		finishes = new Point[2];
		
		finishes[0] = new Point(14,13);
		finishes[1] = new Point(15,13);
		
		
		teleports = new Teleport[10]; 

		teleports[0] = new Teleport(14,8,1);
		teleports[1] = new Teleport(15,9,0);
		teleports[2] = new Teleport(14,9,3);
		teleports[3] = new Teleport(15,8,2);
		
		teleports[4] = new Teleport(14,14,4);
		teleports[5] = new Teleport(15,15,5);
		teleports[6] = new Teleport(15,16,6);
		teleports[7] = new Teleport(15,17,7);
		teleports[8] = new Teleport(14,18,8);
		teleports[9] = new Teleport(14,19,9);
		
		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
