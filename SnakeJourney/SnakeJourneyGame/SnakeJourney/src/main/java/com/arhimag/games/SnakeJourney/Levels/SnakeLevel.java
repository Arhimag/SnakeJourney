package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.PlayerSnake;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.AI.Deykstra2AISnake;
import com.arhimag.games.SnakeJourney.GameElements.Teleport;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.framework.Point;

public class SnakeLevel extends GameLevel
{
	public SnakeLevel(  GameMap map )
	{
		super(map);
		
		int snakeStartX = 2;
		int snakeStartY = 4;
	
		snakes = new Snake[2];
		moved = new boolean[2];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX, snakeStartY - 1));
		snakes[0].parts.add(new SnakePart(snakeStartX, snakeStartY - 2));
		snakes[0].lastx = snakeStartX;
		snakes[0].lasty = snakeStartY - 3;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 15;
	
		snakes[1] = new Deykstra2AISnake(this, map.getMapWidth() /2, map.getMapHeight() - 4 - 1);
		snakes[1].parts.add(new SnakePart(map.getMapWidth() /2 , map.getMapHeight() - 3 - 1));
		snakes[1].parts.add(new SnakePart(map.getMapWidth() /2 , map.getMapHeight() - 2 - 1));
		snakes[1].lastx = map.getMapWidth() /2;
		snakes[1].lasty = map.getMapHeight() - 1 - 1;
		snakes[1].direction = Snake.UP;
		snakes[1].setBodyColor( 0xffeeeeaa );
		snakes[1].finishSize = 35;
		
		food = new Food[5];
		food[0] = new Food( snakeStartX + 3, snakeStartY);
		food[1] = new Food( snakeStartX + 5, snakeStartY);
		food[2] = new Food( snakeStartX + 7, snakeStartY);
		food[3] = new Food( snakeStartX + 9, snakeStartY);
		food[4] = new Food( snakeStartX + 10, snakeStartY);
		
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		generateNewFood(4);
		
		finishes = new Point[4];
		
		finishes[0] = new Point(this.map.getMapWidth()/2 - 2,map.getMapHeight() - 1);
		finishes[1] = new Point(this.map.getMapWidth()/2 - 1,map.getMapHeight() - 1);
		finishes[2] = new Point(this.map.getMapWidth()/2 ,map.getMapHeight() - 1);
		finishes[3] = new Point(this.map.getMapWidth()/2 + 1,map.getMapHeight() - 1);
		
		teleports = new Teleport[10];
		teleports[0] = new Teleport(0, 1, 5);
		teleports[1] = new Teleport(0, 2, 6);
		teleports[2] = new Teleport(0, 3, 7);
		teleports[3] = new Teleport(0, 4, 8);
		teleports[4] = new Teleport(0, 5, 9);
		teleports[5] = new Teleport(map.getMapWidth() - 1, 1, 0);
		teleports[6] = new Teleport(map.getMapWidth() - 1, 2, 1);
		teleports[7] = new Teleport(map.getMapWidth() - 1, 3, 2);
		teleports[8] = new Teleport(map.getMapWidth() - 1, 4, 3);
		teleports[9] = new Teleport(map.getMapWidth() - 1, 5, 4);
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		if(!botMoved)
		{
			((Deykstra2AISnake)snakes[1]).nextTurn();
			botMoved = true;
		}
		super.update(deltaTime, snake_id);
	}

}
