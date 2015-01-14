package com.arhimag.games.omnomnom.Maps;


public class Level4RoomsMap extends GameMap
{

	public Level4RoomsMap( )
	{
		super( );
		FlatMap = new char[][]{
				{'R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','_','_','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','_','_','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','~','~',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','~','~',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R','R','R','R','R','R','R','R','R','R','R','R','R','~','~','R','R','~','~','R','R','R','R','R','R','R','R','R','R','R','R','R'},
				{'R','R','R','R','R','R','R','R','R','R','R','R','R','~','~','R','R','~','~','R','R','R','R','R','R','R','R','R','R','R','R','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','~','~',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','~','~',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R'},
				{'R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R','R'}};
		
		mapWidth = FlatMap[0].length;
		mapHeight = FlatMap.length;
		
	}
}

