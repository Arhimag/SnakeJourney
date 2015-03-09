package com.arhimag.games.snakejourney;


public class PlayerSnake extends Snake
{
	public PlayerSnake( int x, int y)
	{
		super(x,y);
	}
	
	public void turnLeft()
	{
		direction ++;
		if( direction > RIGHT)
			direction = UP;
	}
	
	public void turnRight()
	{
		direction --;
		if( direction < UP)
			direction = RIGHT;
	}
	
	public int getBodyColor(int part)
	{
		return bodyColorEven;
	}
	
}
