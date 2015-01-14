package com.arhimag.games.SnakeJourney.Maps;


public class LevelTrefoilMap extends GameMap
{

	public LevelTrefoilMap( )
	{
		super( );
		FlatMap = new char[][]{				
				{'_','_','_','_','_','R','R','R','R','R','R','R','R','R','_','_','R','R','R','R','R','R','R','R','R','_','_','_','_','_'},
				{'_','_','_','_','_','R',' ',' ',' ',' ',' ',' ',' ','R','_','_','R',' ',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_'},
				{'_','_','_','_','_','R',' ',' ',' ',' ',' ',' ',' ','R','R','R','R',' ',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_'},
				{'_','_','_','_','_','R',' ',' ',' ',' ',' ',' ',' ','R','_','_','R',' ',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_'},
				{'_','_','_','_','_','R',' ',' ',' ',' ',' ',' ',' ','R','_','_','R',' ',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_'},
				{'_','_','_','_','_','R',' ',' ',' ',' ',' ',' ',' ','R','R','R','R',' ',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_'},
				{'_','_','_','_','_','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_'},
				{'_','_','_','_','_','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_'},
				{'_','_','_','_','_','R','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R','R','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','R',' ',' ',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','R','R','R','R','R',' ',' ',' ',' ',' ',' ',' ',' ','R','R','R','R','R','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','R',' ',' ',' ',' ',' ',' ','R','R','R','R',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','R',' ',' ',' ',' ',' ',' ','R','_','_','R',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','R',' ',' ',' ',' ',' ',' ','R','_','_','R',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','R',' ',' ',' ',' ',' ',' ','R','_','_','R',' ',' ',' ',' ',' ',' ','R','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','R','R','R','R','R','R','R','R','_','_','R','R','R','R','R','R','R','R','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'}};


		mapWidth = FlatMap[0].length;
		mapHeight = FlatMap.length;
		
	}
}

