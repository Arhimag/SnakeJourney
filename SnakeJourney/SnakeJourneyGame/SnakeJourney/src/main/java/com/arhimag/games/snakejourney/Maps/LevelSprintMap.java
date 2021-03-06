package com.arhimag.games.snakejourney.Maps;

public class LevelSprintMap extends GameMap
{

	public LevelSprintMap( )
	{
		super( );
		FlatMap = new char[][]{
				{'R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','_','_','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','_','_','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ','R','R','R','R','R','R','R','R','R',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ','R','R','R','R','R','R','R','R','R','R','R','R','R','R'},
				{'R','R','R','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R','R','R','R','R','R','R','R','R','R','R','R','R',' ',' ','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ','R','R','R','R','R','R','R','R','R','R','R','R','R','R',' ','R','R','R','R','R','R','R','R','R','R','R','R','R','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R','R','R','R','R','R','R','R','R','R','R','R','R',' ',' ','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R',' ','R'},
				{'R','R','R','R','R','R','R','R','R','R','R','R','R',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ','R','R','R','R','R','R','R','R','R','R','R','R','R','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R',' ','R'},
				{'R',' ',' ','R','R','R','R','R','R','R','R','R','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ','R'},
				{'R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R'}};
		
		mapWidth = FlatMap[0].length;
		mapHeight = FlatMap.length;
		
	}
}

