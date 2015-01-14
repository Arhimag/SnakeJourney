package com.arhimag.games.omnomnom.Maps;

public class Level2Map extends GameMap
{
	public Level2Map( )
	{
		super( );
		mapWidth = 47;
		mapHeight = 29;
		FlatMap = new char[][]{
				{'R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R',' ',' ',' ','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R',' ',' ',' ','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R',' ',' ',' ','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R',' ',' ',' ','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R',' ',' ',' ','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R',' ',' ',' ','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R',' ',' ',' ','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R',' ',' ',' ','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R',' ',' ',' ','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R',' ',' ',' ','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R'} };
	}
}

