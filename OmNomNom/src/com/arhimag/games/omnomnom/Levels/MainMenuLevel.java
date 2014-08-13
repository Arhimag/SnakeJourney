package com.arhimag.games.omnomnom.Levels;

import com.arhimag.games.omnomnom.Food;
import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.SnakePart;
import com.arhimag.games.omnomnom.AI.Deykstra2AISnake;
import com.arhimag.games.omnomnom.AI.BFSAISnake;
import com.arhimag.games.omnomnom.Maps.GameMap;

public class MainMenuLevel extends GameLevel
{
	public MainMenuLevel(  GameMap map )
	{
		super(map);
		int snakeStartX = 4;
		int snakeStartY = 1;
		snakes = new Snake[1];
		moved = new boolean[1];
//		snakes[0] = new Deykstra2AISnake(this,snakeStartX, snakeStartY);
		snakes[0] = new BFSAISnake(this,snakeStartX, snakeStartY,23);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].parts.add(new SnakePart(snakeStartX - 2, snakeStartY));
		snakes[0].lastx = snakeStartX - 3;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 10000;
		
		/*snakes[1] = new Deykstra2AISnake(this,this.map.getMapWidth() - snakeStartX - 4 - 1, snakeStartY);
		snakes[1].parts.add(new SnakePart(this.map.getMapWidth() - snakeStartX - 3 - 1, snakeStartY));
		snakes[1].parts.add(new SnakePart(this.map.getMapWidth() - snakeStartX - 2 - 1, snakeStartY));
		snakes[1].lastx = this.map.getMapWidth() - snakeStartX - 1 - 1;
		snakes[1].lasty = snakeStartY;
		snakes[1].direction = Snake.LEFT;
		snakes[1].finishSize = 10000; */
		
		food = new Food[5];
		food[0] = new Food( snakeStartX + 3, snakeStartY);
		food[1] = new Food( snakeStartX + 4, snakeStartY);
		food[2] = new Food( snakeStartX + 5, snakeStartY);
		food[3] = new Food( snakeStartX + 6, snakeStartY);
		food[4] = new Food( snakeStartX + 7, snakeStartY);		
		aqua = false;
		readyScreen = false;
	}
	
	
	public void update(float deltaTime, int snake_id )
	{
		if(!botMoved)
		{
			((BFSAISnake)snakes[0]).nextTurn();
			//((Deykstra2AISnake)snakes[1]).nextTurn();
			botMoved = true;
		}
		super.update(deltaTime, snake_id);
	}
}
