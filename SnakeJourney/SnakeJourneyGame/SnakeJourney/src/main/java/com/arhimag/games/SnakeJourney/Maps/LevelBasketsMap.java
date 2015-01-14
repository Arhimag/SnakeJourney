package com.arhimag.games.SnakeJourney.Maps;


public class LevelBasketsMap extends GameMap
{

	public LevelBasketsMap( )
	{
		super( );
		FlatMap = new char[][]{
				{'R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','_','_','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R'},
				{'R','.','.','.','.','.','.','.','.','.','.','.','.','.','R','_','_','R','.','.','.','.','.','.','.','.','.','.','.','.','.','R'},
				{'R','.','.','.','.','.','.','.','.','.','.','.','.','.','R','R','R','R','.','.','.','.','.','.','.','.',' ',' ','.','.','.','R'},
				{'R','.','.','R',' ',' ',' ',' ','R','.','.','.','.','.','.','.','.','.','.','.','.','.','.','R',' ',' ',' ',' ','R','.','.','R'},
				{'R','.','.','R',' ',' ',' ',' ','R','.','.','.','.','.','.','.','.','.','.','.','.','.','.','R',' ',' ',' ',' ','R','.','.','R'},
				{'R','.','.','R',' ',' ',' ',' ','R','.','.','.','.','.','.','.','.','.','.','.','.','.','.','R',' ',' ',' ',' ','R','.','.','R'},
				{'R','.','.','R',' ',' ',' ',' ','R','.','.','.','.','.','.','.','.','.','.','.','.','.','.','R',' ',' ',' ',' ','R','.','.','R'},
				{'R','.','.','R','R','R','R','R','R','.','.','.','.','.','.','.','.','.','.','.','.','.','.','R','R','R','R','R','R','.','.','R'},
				{'R','.','.','.','.','.','.','.','.','.','.','.','.','R',' ',' ',' ',' ','R','.','.','.','.','.','.','.','.','.','.','.','.','R'},
				{'R','.','.','.','.','.','.','.','.','.','.','.','.','R',' ',' ',' ',' ','R','.','.','.','.','.','.','.','.','.','.','.','.','R'},
				{'R','.','.','.','.','.','.','.','.','.','.','.','.','R',' ',' ',' ',' ','R','.','.','.','.','.','.','.','.','.','.','.','.','R'},
				{'R','.','.','.','.','.','.','.','.','.','.','.','.','R',' ',' ',' ',' ','R','.','.','.','.','.','.','.','.','.','.','.','.','R'},
				{'R','.','.','R',' ',' ',' ',' ','R','.','.','.','.','R','R','R','R','R','R','.','.','.','.','R',' ',' ',' ',' ','R','.','.','R'},
				{'R','.','.','R',' ',' ',' ',' ','R','.','.','.','.','.','.','.','.','.','.','.','.','.','.','R',' ',' ',' ',' ','R','.','.','R'},
				{'R','.','.','R',' ',' ',' ',' ','R','.','.','.','.','.','.','.','.','.','.','.','.','.','.','R',' ',' ',' ',' ','R','.','.','R'},
				{'R','.','.','R',' ',' ',' ',' ','R','.','.','.','.','.','.','.','.','.','.','.','.','.','.','R',' ',' ',' ',' ','R','.','.','R'},
				{'R','.','.','R','R','R','R','R','R','.','.','.','.','.','.','.','.','.','.','.','.','.','.','R','R','R','R','R','R','.','.','R'},
				{'R','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.',' ',' ','.','.','.','R'},
				{'R','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','R'},
				{'R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R'}};
		
		mapWidth = FlatMap[0].length;
		mapHeight = FlatMap.length;
		
	}
}

