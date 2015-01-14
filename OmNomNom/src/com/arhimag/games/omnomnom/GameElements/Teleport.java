package com.arhimag.games.omnomnom.GameElements;

import com.arhimag.games.omnomnom.framework.Point;

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
