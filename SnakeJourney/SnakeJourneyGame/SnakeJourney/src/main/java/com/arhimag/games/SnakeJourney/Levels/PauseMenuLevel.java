package com.arhimag.games.SnakeJourney.Levels;

import com.arhimag.games.SnakeJourney.Food;
import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.SnakePart;
import com.arhimag.games.SnakeJourney.AI.BFSAISnake;
import com.arhimag.games.SnakeJourney.Maps.GameMap;

public class PauseMenuLevel extends GameLevel
{
	
	private GameLevel tempLevel;
	private int tempLevelNum;
	
	public PauseMenuLevel(  GameMap map )
	{
		super(map);
		pauseable = false;
		playerSnake = -1;
		snakes = new Snake[1];
		moved = new boolean[1];
		
		int snakeStartX = 17;
		int snakeStartY = 3;
		
		snakes[0] = new BFSAISnake(this,snakeStartX, snakeStartY,23);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX - 2, snakeStartY));
		snakes[0].lastx = snakeStartX - 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 10000;
	
		
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
	
	
	public PauseMenuLevel(  GameMap map, GameLevel tmpLevel, int tmpLevelNum )
	{
		super(map);
		tempLevel = tmpLevel;
		tempLevelNum = tmpLevelNum;
		

		pauseable = false;
		playerSnake = -1;
		snakes = new Snake[1];
		moved = new boolean[1];
		
		int snakeStartX = 17;
		int snakeStartY = 3;
		
		snakes[0] = new BFSAISnake(this,snakeStartX, snakeStartY,23);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX - 2, snakeStartY));
		snakes[0].lastx = snakeStartX - 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 10000;
	
		
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
			botMoved = true;
		}
		super.update(deltaTime, snake_id);
	}
	
	public GameLevel getTempLevel()
	{
		return tempLevel;
	}
	
	public int getTempLevelNum()
	{
		return tempLevelNum;
	}
	
}
