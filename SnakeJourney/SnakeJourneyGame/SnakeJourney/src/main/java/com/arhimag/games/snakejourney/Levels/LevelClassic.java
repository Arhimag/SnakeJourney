package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.GameElements.Teleport;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.SnakePart;
import com.arhimag.games.snakejourney.framework.Point;

public class LevelClassic extends GameLevel
{


	public LevelClassic(GameMap map)
	{
		super(map);
		
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)300 * (long)1000000000; // 5 �����
		egg3 = (long)120 * (long)1000000000; // 1 ������
		
		int snakeStartX = 8;
		int snakeStartY = 14;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].lastx = snakeStartX - 2;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 25;
		
		food = new Food[1];
		food[0] = new Food( 14, 14 );

		generateNewFood(0);

		finishes = new Point[8];
		
		finishes[0] = new Point(14,15);
		finishes[1] = new Point(15,15);
		finishes[2] = new Point(13,15);
		finishes[3] = new Point(16,15);
        finishes[4] = new Point(14,16);
        finishes[5] = new Point(15,16);
        finishes[6] = new Point(13,16);
        finishes[7] = new Point(16,16);

		teleports = new Teleport[ (map.getMapHeight() - 6) * 2 + 2 * ( map.getMapWidth() - 4 ) ];

        for( int i = 0; i < map.getMapWidth() - 4 ; i ++ )
        {
            teleports[i] = new Teleport(2 + i, 3, map.getMapHeight() + map.getMapWidth() - 10 + i);
            teleports[map.getMapHeight() + map.getMapWidth() - 10 + i] = new Teleport(2 + i, map.getMapHeight() - 2 , i);
        }
        for( int i = map.getMapWidth() - 4; i < map.getMapHeight() + map.getMapWidth() - 10; i++ )
        {
            teleports[i] = new Teleport(1, i - map.getMapWidth() + 8 , map.getMapHeight() + map.getMapWidth() - 10 + i);
            teleports[map.getMapHeight() + map.getMapWidth() - 10 + i] = new Teleport(map.getMapWidth() - 2, i - map.getMapWidth() + 8 , i);
        }

		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
