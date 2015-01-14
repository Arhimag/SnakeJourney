package com.arhimag.games.omnomnom.Levels;

import com.arhimag.games.omnomnom.Food;
import com.arhimag.games.omnomnom.PlayerSnake;
import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.SnakePart;
import com.arhimag.games.omnomnom.GameElements.Teleport;
import com.arhimag.games.omnomnom.Maps.GameMap;
import com.arhimag.games.omnomnom.framework.Point;

public class LevelForest extends GameLevel
{
	public LevelForest(  GameMap map )
	{
		super(map);
		
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)600 * (long)1000000000; // 10 �����
		egg3 = (long)120 * (long)1000000000; // 2 ������
		
		int snakeStartX = 14;
		int snakeStartY = 8;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX, snakeStartY - 1));
		snakes[0].lastx = snakeStartX;
		snakes[0].lasty = snakeStartY - 2;
		snakes[0].direction = Snake.DOWN;
		snakes[0].finishSize = 16;
		
		food = new Food[4];
		food[0] = new Food( 14, 14 );
		food[1] = new Food( 15, 14 );
		food[2] = new Food( 14, 13 );
		food[3] = new Food( 15, 13 );
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		finishes = new Point[4];
		
		finishes[0] = new Point(14,19);
		finishes[1] = new Point(13,19);
		finishes[2] = new Point(17,19);
		finishes[3] = new Point(18,19);
		
		teleports = new Teleport[10];
		teleports[0] = new Teleport(0,19,5);
		teleports[1] = new Teleport(0,18,8);
		teleports[2] = new Teleport(0,17,9);
		teleports[3] = new Teleport(1,19,6);
		teleports[4] = new Teleport(2,19,7);
		teleports[5] = new Teleport(31,0,0);
		teleports[6] = new Teleport(30,0,3);
		teleports[7] = new Teleport(29,0,4);
		teleports[8] = new Teleport(31,1,1);
		teleports[9] = new Teleport(31,2,2);
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}