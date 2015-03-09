package com.arhimag.games.snakejourney.AI;

import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.Levels.GameLevel;

public class BridgeAISnake extends Snake 
{
	GameLevel level;
	java.util.Random rnd = new java.util.Random(System.currentTimeMillis());
	int snakeId;
	
	public BridgeAISnake(GameLevel level, int x, int y, int snkId)
	{
		super(x,y);
		this.level = level;
		snakeId = snkId;
	}
	
	
	
	public void chooseDirection()
	{
		int decision = rnd.nextInt(3);
		int turn1 = 0;
		int turn2 = 0;
		//���� 0, �� ������������.
		if(decision == 0 )
			if( direction == Snake.UP || direction == Snake.DOWN )
			{
				for( int i = 0; i < parts.size(); i++ )
					if( parts.get(i).x > parts.get(0).x )
						turn1 ++;
					else if( parts.get(i).x < parts.get(0).x )
						turn2 ++;
				if( turn1 > turn2 )
					direction = Snake.LEFT;
				else
					direction = Snake.RIGHT;
			}
			else
			{
				for( int i = 0; i < parts.size(); i++ )
					if( parts.get(i).y > parts.get(0).y )
						turn1 ++;
					else if( parts.get(i).y < parts.get(0).y )
						turn2 ++;
				if( turn1 > turn2 )
					direction = Snake.UP;
				else
					direction = Snake.DOWN;
			}
		if(this.level.getNextSnakeField(snakeId)!=' ')
		{
			direction = Snake.UP;
			if(this.level.getNextSnakeField(snakeId)!=' ')
			{
				direction = Snake.DOWN;
				if(this.level.getNextSnakeField(snakeId)!=' ')
				{
					direction = Snake.RIGHT;
					if(this.level.getNextSnakeField(snakeId)!=' ')
					{
						direction = Snake.LEFT;
					}
				}
			}
		}
	}

	public void nextTurn()
	{
		chooseDirection();
	}
}
