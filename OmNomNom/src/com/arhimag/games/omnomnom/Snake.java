package com.arhimag.games.omnomnom;

import java.util.ArrayList;
import java.util.List;

public class Snake
{
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	
	public List<SnakePart> parts = new ArrayList<SnakePart>();
	public int direction;

	public int headColor = 0xffffffff;
	protected int bodyColorEven = Settings.getSnakeEvenColor();
	protected int bodyColorOdd = Settings.getSnakeOddColor();
	
	public int lastx;
	public int lasty;
	
	public int finishSize = 15;
	
	public Snake(int x, int y)
	{
		direction = UP;
		parts.add(new SnakePart(x, y));
	}

	public void eat()
	{
		SnakePart end = parts.get(parts.size() - 1);
		parts.add( new SnakePart( end.x, end.y));
	}
	
	public void advance()
	{
		SnakePart head = parts.get(0);
		
		int len = parts.size() - 1;
		
		lastx = parts.get(len).x;
		lasty = parts.get(len).y;
		
		for(int i = len; i > 0; i--)
		{
			SnakePart before = parts.get(i-1);
			SnakePart part = parts.get(i);
			part.x = before.x;
			part.y = before.y;
		}
		
		if( direction == UP )
			head.y -= 1;
		if( direction == LEFT )
			head.x -= 1;
		if( direction == DOWN )
			head.y += 1;
		if( direction == RIGHT)
			head.x += 1; 
	}
	
	public void turnLeft()
	{
	}

	public void turnRight()
	{
	}
	
	public int getBodyColor()
	{
		return bodyColorEven;
	}
	
	public void setBodyColorEven( int color )
	{
		bodyColorEven = color;
	}
	
	public void setBodyColorOdd( int color )
	{
		bodyColorOdd = color;
	}
	
	public int getBodyColor(int part)
	{
		if( part % 2 == 1)
			return bodyColorEven;
		else
			return bodyColorOdd;
	}
	
	public void setBodyColor(int color)
	{
		bodyColorEven = color;
		bodyColorOdd = bodyColorEven - 0x002f2f2f;
	}
}

