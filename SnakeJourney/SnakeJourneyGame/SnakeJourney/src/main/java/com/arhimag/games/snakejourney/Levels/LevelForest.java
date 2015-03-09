package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.SnakePart;
import com.arhimag.games.snakejourney.GameElements.Teleport;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.framework.Point;

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
		
		teleports = new Teleport[26];
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

        teleports[10] = new Teleport(3,3,17);
        teleports[11] = new Teleport(3,4,16);
        teleports[12] = new Teleport(4,3,15);
        teleports[13] = new Teleport(4,4,14);
        teleports[14] = new Teleport(27,15,13);
        teleports[15] = new Teleport(27,16,12);
        teleports[16] = new Teleport(28,15,11);
        teleports[17] = new Teleport(28,16,10);

        teleports[18] = new Teleport(11,3,25);
        teleports[19] = new Teleport(11,16,23);
        teleports[20] = new Teleport(20,3,24);
        teleports[21] = new Teleport(20,16,22);
        teleports[22] = new Teleport(15,9,21);
        teleports[23] = new Teleport(16,9,19);
        teleports[24] = new Teleport(15,10,20);
        teleports[25] = new Teleport(16,10,18);

        aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
