package com.arhimag.games.snakejourney.GameElements;

import com.arhimag.games.snakejourney.framework.Point;

public class Teleport extends Point
{
	private int nextPort = -1;
	
	public Teleport(int x, int y, int nxtPort)
	{
		super(x,y);
		nextPort = nxtPort;
	}
	
	public int getNextPort()
	{
		return nextPort;
	}
}
