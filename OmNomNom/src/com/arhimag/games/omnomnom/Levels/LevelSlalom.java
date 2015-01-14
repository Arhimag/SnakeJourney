package com.arhimag.games.omnomnom.Levels;

import com.arhimag.games.omnomnom.Food;
import com.arhimag.games.omnomnom.PlayerSnake;
import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.SnakePart;
import com.arhimag.games.omnomnom.GameElements.Teleport;
import com.arhimag.games.omnomnom.Maps.GameMap;
import com.arhimag.games.omnomnom.framework.Point;

public class LevelSlalom extends GameLevel
{

	
	public LevelSlalom(  GameMap map )
	{
		super(map);
		
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)300 * (long)1000000000; // 5 �����
		egg3 = (long)60 * (long)1000000000; // 1 ������
		
		int snakeStartX = 5;
		int snakeStartY = 5;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].lastx = snakeStartX - 2;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 13;
		
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
		
		finishes[0] = new Point(14,16);
		finishes[1] = new Point(15,16);
		finishes[2] = new Point(13,16);
		finishes[3] = new Point(16,16);
		
		teleports = new Teleport[ ((map.getMapHeight() - 6) * ( map.getMapWidth() - 4 )) / 15 ]; 

		java.util.Random generator = new java.util.Random(System.nanoTime());
		
		for( int i = 0; i < teleports.length; i++ )
			teleports[i] = new Teleport(generator.nextInt( map.getMapWidth() - 4 ) + 2, generator.nextInt( map.getMapHeight() - 6 ) + 4, generator.nextInt( teleports.length ) );

		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}