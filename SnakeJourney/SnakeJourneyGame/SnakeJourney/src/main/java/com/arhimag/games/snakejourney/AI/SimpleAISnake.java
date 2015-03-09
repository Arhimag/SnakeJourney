package com.arhimag.games.snakejourney.AI;

import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.Levels.GameLevel;

public class SimpleAISnake extends Snake 
{
	GameLevel level;
	
	public SimpleAISnake(GameLevel level, int x, int y)
	{
		super(x,y);
		this.level = level;
	}
	
	
	public int chooseFood()
	{
		int minlength;
		int minfood = 0;
		if(level.getFoodLength() >= 0)
			minlength = (parts.get(0).x - level.getFood(0).x) *(parts.get(0).x - level.getFood(0).x) + ( parts.get(0).y - level.getFood(0).y) * (parts.get(0).y - level.getFood(0).y);
		else 
			return -1;
		
		for(int i = 1; i < level.getFoodLength(); i++ )
			if( (parts.get(0).x - level.getFood(i).x) * (parts.get(0).x - level.getFood(i).x) + (parts.get(0).y - level.getFood(i).y) * (parts.get(0).y - level.getFood(i).y) < minlength )
			{
				minlength = (parts.get(0).x - level.getFood(i).x) * (parts.get(0).x - level.getFood(i).x) + (parts.get(0).y - level.getFood(i).y) * (parts.get(0).y - level.getFood(i).y);
				minfood = i;
			}
		return minfood;
	}
	
	public int chooseDirection()
	{
		int prefferedVertical; // ������������ �������� �����, ������������ - ����
		int prefferedHorizontal; // ������������ ���� ��� ������
		
		boolean upLocked = false;
		boolean downLocked = false;
		boolean rightLocked = false;
		boolean leftLocked = false;
		
		if( this.parts.size() > this.finishSize )
		{
			if( level.getFinishY(0) > parts.get(0).y )
				prefferedVertical = -1;
			else if( level.getFinishY(0) < parts.get(0).y )
				prefferedVertical = 1;
			else
				prefferedVertical = 0;
			
			if( level.getFinishX(0) > parts.get(0).x )
				prefferedHorizontal = 1;
			else if( level.getFinishX(0) < parts.get(0).x )
				prefferedHorizontal = - 1;
			else
				prefferedHorizontal = 0;
		}
		else
		{
			int wishfood = chooseFood();
		
			if( level.getFood(wishfood).y > parts.get(0).y )
				prefferedVertical = -1;
			else if( level.getFood(wishfood).y < parts.get(0).y )
				prefferedVertical = 1;
			else
				prefferedVertical = 0;
			
			if( level.getFood(wishfood).x > parts.get(0).x )
				prefferedHorizontal = 1;
			else if( level.getFood(wishfood).x < parts.get(0).x )
				prefferedHorizontal = - 1;
			else
				prefferedHorizontal = 0;
		}

		
		
		if( parts.get(0).x == 0 )
			leftLocked = true;
		if( parts.get(0).y == 0 )
			upLocked = true;
		if( parts.get(0).x == level.getMap().getMapWidth() - 1 )
			rightLocked = true;
		if( parts.get(0).y == level.getMap().getMapHeight() - 1 )
			downLocked = true;
		
		if( !leftLocked && level.getMap().getFlatMap(parts.get(0).x - 1,parts.get(0).y) != ' '  && !(this.parts.size() > this.finishSize))
			leftLocked = true;
		if( !rightLocked && level.getMap().getFlatMap(parts.get(0).x + 1,parts.get(0).y) != ' ' && !(this.parts.size() > this.finishSize))
			rightLocked = true;
		if( !upLocked && level.getMap().getFlatMap(parts.get(0).x,parts.get(0).y - 1) != ' ' && !(this.parts.size() > this.finishSize))
			upLocked = true;
		if( !downLocked && level.getMap().getFlatMap(parts.get(0).x,parts.get(0).y + 1) != ' ' && !(this.parts.size() > this.finishSize))
			downLocked = true;
		
		if( !leftLocked )
			for( int i = 0; i < parts.size(); i++)
				if ( ( parts.get(0).x - 1 == parts.get(i).x ) && ( parts.get(0).y == parts.get(i).y ) )
					leftLocked = true;
		if( !rightLocked )
			for( int i = 0; i < parts.size(); i++)
				if ( ( parts.get(0).x + 1 == parts.get(i).x ) && ( parts.get(0).y == parts.get(i).y ) )
					rightLocked = true;
		if( !upLocked )
			for( int i = 0; i < parts.size(); i++)
				if ( ( parts.get(0).x == parts.get(i).x ) && ( parts.get(0).y - 1 == parts.get(i).y ) )
					upLocked = true;
		if( !downLocked )
			for( int i = 0; i < parts.size(); i++)
				if ( ( parts.get(0).x == parts.get(i).x ) && ( parts.get(0).y + 1 == parts.get(i).y ) )
					downLocked = true;
		
		if( prefferedVertical > 0 )
		{
			if( !upLocked )
			{
				return Snake.UP;
			}
			else
			{
				if( prefferedHorizontal > 0 )
				{
					if( !rightLocked )
						return Snake.RIGHT;
					else if( !leftLocked )
						return Snake.LEFT;
					else
						return Snake.DOWN;
				}
				else if ( prefferedHorizontal < 0 )
				{
					if( !leftLocked )
						return Snake.LEFT;
					else if( !rightLocked )
						return Snake.RIGHT;
					else
						return Snake.DOWN;
				}
				else
				{
					if( !leftLocked )
						return Snake.LEFT;
					else if( !rightLocked )
						return Snake.RIGHT;
					else
						return Snake.DOWN;
				}
			}
		}
		else if( prefferedVertical < 0 )
		{
			if( !downLocked )
			{
				return Snake.DOWN;
			}
			else
			{
				if( prefferedHorizontal > 0 )
				{
					if( !rightLocked )
						return Snake.RIGHT;
					else if( !leftLocked )
						return Snake.LEFT;
					else
						return Snake.UP;
				}
				else if ( prefferedHorizontal < 0 )
				{
					if( !leftLocked )
						return Snake.LEFT;
					else if( !rightLocked )
						return Snake.RIGHT;
					else
						return Snake.UP;
				}
				else
				{
					if( !leftLocked )
						return Snake.LEFT;
					else if( !rightLocked )
						return Snake.RIGHT;
					else 
						return Snake.UP;
				}
			}
		}
		else
		{
			if( prefferedHorizontal > 0 )
			{
				if( !rightLocked )
					return Snake.RIGHT;
				else if( !leftLocked )
					return Snake.LEFT;
				else if ( !upLocked )
					return Snake.UP;
				else
					return Snake.DOWN;
			}
			else if ( prefferedHorizontal < 0 )
			{
				if( !leftLocked )
					return Snake.LEFT;
				else if( !rightLocked )
					return Snake.RIGHT;
				else if ( !upLocked )
					return Snake.UP;
				else
					return Snake.DOWN;
			}
			else
			{
				if( !leftLocked )
					return Snake.LEFT;
				else if( !rightLocked )
					return Snake.RIGHT;
				else if ( !upLocked )
					return Snake.UP;
				else
					return Snake.DOWN;
			}
		}
	}

	public void nextTurn()
	{
		direction = chooseDirection();
	}
}
