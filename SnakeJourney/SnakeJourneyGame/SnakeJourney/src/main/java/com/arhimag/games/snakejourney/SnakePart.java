package com.arhimag.games.snakejourney;

public class SnakePart
{
	public int x;
	public int y;
	public boolean enabled;
	public static final int TYPICAL = 0;
	public static final int SUPER = 1;
	public static final int DEAD = -1;
	public static final int type = DEAD;
	
	public SnakePart(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
}
