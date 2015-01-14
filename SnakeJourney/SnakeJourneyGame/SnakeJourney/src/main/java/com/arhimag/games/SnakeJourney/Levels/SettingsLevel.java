package com.arhimag.games.SnakeJourney.Levels;


import com.arhimag.games.SnakeJourney.Maps.GameMap;

public class SettingsLevel extends GameLevel
{
	public SettingsLevel(GameMap map)
	{
		super(map);
		playerSnake = -1;
		pauseable = false;
	}
	
	@Override
	public void update(float deltaTime, int snakeid  )
	{
	}
	
	@Override
	public boolean getMoved( int snakeid)
	{
		return false;
	}
	
}
