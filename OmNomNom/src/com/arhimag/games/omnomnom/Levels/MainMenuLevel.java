package com.arhimag.games.omnomnom.Levels;

import com.arhimag.games.omnomnom.Food;
import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.SnakePart;
import com.arhimag.games.omnomnom.AI.BFSAISnake;
import com.arhimag.games.omnomnom.Maps.GameMap;

public class MainMenuLevel extends GameLevel
{

	public MainMenuLevel(  GameMap map )
	{
		super(map);
		int snakeStartX = 17;
		int snakeStartY = 3;
		this.playerSnake = -1;
		pauseable = false;
		snakes = new Snake[2];
		moved = new boolean[2];
//		snakes[0] = new Deykstra2AISnake(this,snakeStartX, snakeStartY);
		snakes[0] = new BFSAISnake(this,snakeStartX, snakeStartY,23);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX - 2, snakeStartY));
		snakes[0].lastx = snakeStartX - 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 10000;
		
		snakes[1] = new BFSAISnake(this,29 , snakeStartY, 29);
		snakes[1].parts.add(new SnakePart(30, snakeStartY));
		snakes[1].parts.add(new SnakePart(31, snakeStartY));
		snakes[1].lastx = 32;
		snakes[1].lasty = snakeStartY;
		snakes[1].direction = Snake.LEFT;
		snakes[1].finishSize = 10000;  
		
		
		food = new Food[10];
		food[0] = new Food( snakeStartX, snakeStartY);
		food[1] = new Food( snakeStartX, snakeStartY);
		food[2] = new Food( snakeStartX, snakeStartY);
		food[3] = new Food( snakeStartX, snakeStartY);
		food[4] = new Food( snakeStartX, snakeStartY);
		food[5] = new Food( snakeStartX, snakeStartY);
		food[6] = new Food( snakeStartX, snakeStartY);
		food[7] = new Food( snakeStartX, snakeStartY);
		food[8] = new Food( snakeStartX, snakeStartY);
		food[9] = new Food( snakeStartX, snakeStartY);
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		generateNewFood(4);
		generateNewFood(5);
		generateNewFood(6);
		generateNewFood(7);
		generateNewFood(8);
		generateNewFood(9);

		aqua = false;
		readyScreen = false;
	}
	
	
	public void update(float deltaTime, int snake_id )
	{
		if(!botMoved)
		{
			((BFSAISnake)snakes[0]).nextTurn();
			((BFSAISnake)snakes[1]).nextTurn();
			//((Deykstra2AISnake)snakes[1]).nextTurn();
			
			botMoved = true;
		}
		super.update(deltaTime, snake_id);
	}
}
