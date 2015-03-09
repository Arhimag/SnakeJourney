package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.GameElements.Teleport;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.SnakePart;
import com.arhimag.games.snakejourney.framework.Point;

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

		teleports = new Teleport[84];
		teleports[0] = new Teleport(4, 6, 4);
		teleports[1] = new Teleport(5, 6, 5);
		teleports[2] = new Teleport(6, 6, 6);
		teleports[3] = new Teleport(7, 6, 7);
		teleports[4] = new Teleport(18, 6, 8);
		teleports[5] = new Teleport(19, 6, 9);
		teleports[6] = new Teleport(20, 6, 10);
		teleports[7] = new Teleport(21, 6, 11);
        teleports[8] = new Teleport(32, 6, 12);
        teleports[9] = new Teleport(33, 6, 13);
        teleports[10] = new Teleport(34, 6, 14);
        teleports[11] = new Teleport(35, 6, 15);
        teleports[12] = new Teleport(4, 11, 16);
        teleports[13] = new Teleport(5, 11, 17);
        teleports[14] = new Teleport(6, 11, 18);
        teleports[15] = new Teleport(7, 11, 19);
        teleports[16] = new Teleport(18, 11, 20);
        teleports[17] = new Teleport(19, 11, 21);
        teleports[18] = new Teleport(20, 11, 22);
        teleports[19] = new Teleport(21, 11, 23);
        teleports[20] = new Teleport(32, 11, 24);
        teleports[21] = new Teleport(33, 11, 25);
        teleports[22] = new Teleport(34, 11, 26);
        teleports[23] = new Teleport(35, 11, 27);
        teleports[24] = new Teleport(4, 16, 28);
        teleports[25] = new Teleport(5, 16, 29);
        teleports[26] = new Teleport(6, 16, 30);
        teleports[27] = new Teleport(7, 16, 31);
        teleports[28] = new Teleport(18, 16, 32);
        teleports[29] = new Teleport(19, 16, 33);
        teleports[30] = new Teleport(20, 16, 34);
        teleports[31] = new Teleport(21, 16, 35);
        teleports[32] = new Teleport(32, 16, 0);
        teleports[33] = new Teleport(33, 16, 1);
        teleports[34] = new Teleport(34, 16, 2);
        teleports[35] = new Teleport(35, 16, 3);
        teleports[36] = new Teleport(4, 21, 76);
        teleports[37] = new Teleport(5, 21, 77);
        teleports[38] = new Teleport(6, 21, 78);
        teleports[39] = new Teleport(7, 21, 79);
        teleports[40] = new Teleport(18, 21, 80);
        teleports[41] = new Teleport(19, 21, 81);
        teleports[42] = new Teleport(20, 21, 82);
        teleports[43] = new Teleport(21, 21, 83);
        teleports[44] = new Teleport(32, 21, 72);
        teleports[45] = new Teleport(33, 21, 73);
        teleports[46] = new Teleport(34, 21, 74);
        teleports[47] = new Teleport(35, 21, 75);
        teleports[48] = new Teleport(0, 8, 68);
        teleports[49] = new Teleport(0, 9, 69);
        teleports[50] = new Teleport(0, 13, 70);
        teleports[51] = new Teleport(0, 14, 71);
        teleports[52] = new Teleport(0, 18, 66);
        teleports[53] = new Teleport(0, 19, 67);
        teleports[54] = new Teleport(11, 8, 56);
        teleports[55] = new Teleport(11, 9, 57);
        teleports[56] = new Teleport(11, 13, 58);
        teleports[57] = new Teleport(11, 14, 59);
        teleports[58] = new Teleport(11, 18, 60);
        teleports[59] = new Teleport(11, 19, 61);
        teleports[60] = new Teleport(28, 8, 62);
        teleports[61] = new Teleport(28, 9, 63);
        teleports[62] = new Teleport(28, 13, 64);
        teleports[63] = new Teleport(28, 14, 65);
        teleports[64] = new Teleport(28, 18, 54);
        teleports[65] = new Teleport(28, 19, 55);
        teleports[66] = new Teleport(39, 8, 50);
        teleports[67] = new Teleport(39, 9, 51);
        teleports[68] = new Teleport(39, 13, 52);
        teleports[69] = new Teleport(39, 14, 53);
        teleports[70] = new Teleport(39, 18, 48);
        teleports[71] = new Teleport(39, 19, 49);
        teleports[72] = new Teleport(4, 0, 40);
        teleports[73] = new Teleport(5, 0, 41);
        teleports[74] = new Teleport(6, 0, 42);
        teleports[75] = new Teleport(7, 0, 43);
        teleports[76] = new Teleport(18, 2, 44);
        teleports[77] = new Teleport(19, 2, 45);
        teleports[78] = new Teleport(20, 2, 46);
        teleports[79] = new Teleport(21, 2, 47);
        teleports[80] = new Teleport(32, 0, 36);
        teleports[81] = new Teleport(33, 0, 37);
        teleports[82] = new Teleport(34, 0, 38);
        teleports[83] = new Teleport(35, 0, 39);

		aqua = true;
		pauseButton = true;
		winLength = 20;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

}
