package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.GameElements.Teleport;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.framework.Point;

public class LevelCube extends GameLevel
{
	public LevelCube(GameMap map)
	{
		super(map);
		
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)360 * (long)1000000000; // 5 �����
		egg3 = (long)90 * (long)1000000000; // 1,5 ������
		
		int snakeStartX = 4;
		int snakeStartY = 1;
	
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX - 2, snakeStartY));
		snakes[0].lastx = snakeStartX;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 10;

		food = new Food[3];
		food[0] = new Food( snakeStartX, snakeStartY);
		food[1] = new Food( snakeStartX, snakeStartY);
		food[2] = new Food( snakeStartX, snakeStartY);

		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		
		finishes = new Point[4];
		
		finishes[0] = new Point(18,21);
		finishes[1] = new Point(19,21);
		finishes[2] = new Point(20,21);
		finishes[3] = new Point(21,21);

		teleports = new Teleport[50];
		teleports[0] = new Teleport(11, 3, 0);
		teleports[1] = new Teleport(28, 3, 0);
		teleports[2] = new Teleport(4, 6, 10);
		teleports[3] = new Teleport(5, 6, 11);
		teleports[4] = new Teleport(6, 6, 12);
		teleports[5] = new Teleport(7, 6, 13);
		teleports[6] = new Teleport(18, 6, 18);
		teleports[7] = new Teleport(19, 6, 19);
		teleports[8] = new Teleport(20, 6, 20);
		teleports[9] = new Teleport(21, 6, 21);
        teleports[10] = new Teleport(32, 6, 26);
        teleports[11] = new Teleport(33, 6, 27);
        teleports[12] = new Teleport(34, 6, 28);
        teleports[13] = new Teleport(35, 6, 29);
        teleports[14] = new Teleport(4, 11, 34);
        teleports[15] = new Teleport(5, 11, 35);
        teleports[16] = new Teleport(6, 11, 36);
        teleports[17] = new Teleport(7, 11, 37);
        teleports[18] = new Teleport(18, 11, 2);
        teleports[19] = new Teleport(19, 11, 3);
        teleports[20] = new Teleport(20, 11, 4);
        teleports[21] = new Teleport(21, 11, 5);
        teleports[22] = new Teleport(32, 11, 6);
        teleports[23] = new Teleport(33, 11, 7);
        teleports[24] = new Teleport(34, 11, 8);
        teleports[25] = new Teleport(35, 11, 9);
        teleports[26] = new Teleport(4, 16, 14);
        teleports[27] = new Teleport(5, 16, 15);
        teleports[28] = new Teleport(6, 16, 16);
        teleports[29] = new Teleport(7, 16, 17);
        teleports[30] = new Teleport(18, 16, 22);
        teleports[31] = new Teleport(19, 16, 23);
        teleports[32] = new Teleport(20, 16, 24);
        teleports[33] = new Teleport(21, 16, 25);
        teleports[34] = new Teleport(32, 16, 30);
        teleports[35] = new Teleport(33, 16, 31);
        teleports[36] = new Teleport(34, 16, 32);
        teleports[37] = new Teleport(35, 16, 33);
        teleports[38] = new Teleport(11, 8, 40);
        teleports[39] = new Teleport(11, 9, 41);
        teleports[40] = new Teleport(11, 13, 44);
        teleports[41] = new Teleport(11, 14, 45);
        teleports[42] = new Teleport(11, 18, 48);
        teleports[43] = new Teleport(11, 19, 49);
        teleports[44] = new Teleport(28, 8, 38);
        teleports[45] = new Teleport(28, 9, 39);
        teleports[46] = new Teleport(28, 13, 42);
        teleports[47] = new Teleport(28, 14, 43);
        teleports[48] = new Teleport(28, 18, 46);
        teleports[49] = new Teleport(28, 19, 47);

		aqua = true;
		pauseButton = true;
		winLength = 20;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
