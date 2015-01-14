package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.AI.BridgeAISnake;
import com.arhimag.games.SnakeJourney.GameElements.Teleport;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.framework.Point;

public class LevelBridge extends GameLevel
{
	public LevelBridge(  GameMap map )
	{
		super(map);
		
		egg1 = (long)1800 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)480 * (long)1000000000; // 8 �����
		egg3 = (long)180 * (long)1000000000; // 3 ������
		
		int snakeStartX = 6;
		int snakeStartY = 2;
	
		snakes = new Snake[2];
		moved = new boolean[2];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX - 2, snakeStartY));
		snakes[0].lastx = snakeStartX - 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 10;
	
		snakes[1] = new BridgeAISnake(this, map.getMapWidth() / 2, map.getMapHeight() - 3, 1);
		snakes[1].parts.add(new SnakePart(map.getMapWidth() / 2 + 1 , map.getMapHeight() - 3));
		snakes[1].parts.add(new SnakePart(map.getMapWidth() / 2 + 2, map.getMapHeight() - 3));
		snakes[1].parts.add(new SnakePart(map.getMapWidth() / 2 + 3, map.getMapHeight() - 3));
		snakes[1].lastx = map.getMapWidth()/2  + 4;
		snakes[1].lasty = map.getMapHeight() - 3;
		snakes[1].direction = Snake.UP;
		snakes[1].setBodyColor( 0xffeeeeaa );
		snakes[1].finishSize = 10000;
		
		food = new Food[4];
		food[0] = new Food( snakeStartX + 3, snakeStartY);
		food[1] = new Food( snakeStartX + 5, snakeStartY);
		food[2] = new Food( snakeStartX + 7, snakeStartY);
		food[3] = new Food( snakeStartX + 9, snakeStartY);
		
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		
		finishes = new Point[4];
		
		finishes[0] = new Point(this.map.getMapWidth()/2 - 2,map.getMapHeight() - 1);
		finishes[1] = new Point(this.map.getMapWidth()/2 - 1,map.getMapHeight() - 1);
		finishes[2] = new Point(this.map.getMapWidth()/2 ,map.getMapHeight() - 1);
		finishes[3] = new Point(this.map.getMapWidth()/2 + 1,map.getMapHeight() - 1);
		
		teleports = new Teleport[4];
		teleports[0] = new Teleport(14,10,1);
		teleports[1] = new Teleport(1,17,0);
		teleports[2] = new Teleport(17,10,3);
		teleports[3] = new Teleport(30,17,2);
		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		if(!botMoved)
		{
			((BridgeAISnake)snakes[1]).nextTurn();
			botMoved = true;
		}
		super.update(deltaTime, snake_id);
	}

}
