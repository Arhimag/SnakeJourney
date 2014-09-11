package com.arhimag.games.omnomnom.Levels;

import com.arhimag.games.omnomnom.Food;
import com.arhimag.games.omnomnom.PlayerSnake;
import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.SnakePart;
import com.arhimag.games.omnomnom.AI.SimpleAISnake;
import com.arhimag.games.omnomnom.Maps.GameMap;
import com.arhimag.games.omnomnom.framework.Point;

public class CircleLevel extends GameLevel
{
	public CircleLevel(  GameMap map )
	{
		super(map);
		
		int snakeStartX = 7;
		int snakeStartY = 7;
		
		egg1 = (long)1200 * (long)1000000000 - 1; // 1 час
		egg2 = (long)480 * (long)1000000000; // 5 минут
		egg3 = (long)240 * (long)1000000000; // 1,5 минута
		
		snakes = new Snake[2];
		moved = new boolean[2];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].parts.add(new SnakePart(snakeStartX - 1, snakeStartY));
		snakes[0].lastx = snakeStartX - 1;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.DOWN;
		snakes[0].finishSize = 25;

		snakes[1] = new SimpleAISnake(this,this.map.getMapWidth() - snakeStartX - 1 - 1, snakeStartY);
		snakes[1].parts.add(new SnakePart(this.map.getMapWidth() - snakeStartX - 2 - 1, snakeStartY));
		snakes[1].lastx = this.map.getMapWidth() - snakeStartX - 3 - 1;
		snakes[1].lasty = snakeStartY;
		snakes[1].direction = Snake.UP;
		snakes[1].setBodyColor( 0xffaaeeee );
		snakes[1].finishSize = 25;

		food = new Food[5];
		food[0] = new Food( snakeStartX + 3, snakeStartY);
		food[1] = new Food( snakeStartX + 4, snakeStartY);
		food[2] = new Food( snakeStartX + 5, snakeStartY);
		food[3] = new Food( snakeStartX + 3, snakeStartY);
		food[4] = new Food( snakeStartX + 4, snakeStartY);
		
		

		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		generateNewFood(4);

		
		finishes = new Point[3];
		
		finishes[0] = new Point(0,8);
		finishes[1] = new Point(0,9);
		finishes[2] = new Point(0,10);
		
		
		aqua = true;
		pauseButton = true;
		winLength = 30;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		if(!botMoved)
		{
			((SimpleAISnake)snakes[1]).nextTurn();
			botMoved = true;
		}
		super.update(deltaTime, snake_id);
	}

}
