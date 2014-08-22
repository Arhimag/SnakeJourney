package com.arhimag.games.omnomnom.Levels;

import com.arhimag.games.omnomnom.Food;
import com.arhimag.games.omnomnom.PlayerSnake;
import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.SnakePart;
import com.arhimag.games.omnomnom.Maps.GameMap;
import com.arhimag.games.omnomnom.framework.Point;

public class LevelSnail extends GameLevel
{
	public LevelSnail(  GameMap map )
	{
		super(map);
		
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)200 * (long)1000000000; // 5 �����
		egg3 = (long)60 * (long)1000000000; // 1 ������
		
		int snakeStartX = 19;
		int snakeStartY = 8;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX + 1, snakeStartY));
		snakes[0].lastx = snakeStartX + 2;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.LEFT;
		snakes[0].finishSize = 8;
		
		food = new Food[25];
		food[0] = new Food( 16, 8 );
		food[1] = new Food( 8, 6 );
		food[2] = new Food( 9, 6 );
		food[3] = new Food( 10, 6 );
		food[4] = new Food( 23, 2 );
		food[5] = new Food( 23, 3 );
		food[6] = new Food( 23, 4 );
		food[7] = new Food( 25, 6 );
		food[8] = new Food( 26, 6 );
		food[9] = new Food( 27, 6 );
		food[10] = new Food( 25, 10 );
		food[11] = new Food( 26, 10 );
		food[12] = new Food( 27, 10 );
		food[13] = new Food( 23, 12 );
		food[14] = new Food( 23, 13 );
		food[15] = new Food( 23, 14 );
		food[16] = new Food( 6, 12 );
		food[17] = new Food( 6, 13 );
		food[18] = new Food( 6, 14 );
		food[19] = new Food( 23, 12 );
		food[20] = new Food( 23, 13 );
		food[21] = new Food( 23, 14 );
		food[22] = new Food( 2, 6 );
		food[23] = new Food( 3, 6 );
		food[24] = new Food( 4, 6 );
		
		finishes = new Point[5];
		
		finishes[0] = new Point(1,0);
		finishes[1] = new Point(2,0);
		finishes[2] = new Point(3,0);
		finishes[3] = new Point(4,0);
		finishes[4] = new Point(5,0);
		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
