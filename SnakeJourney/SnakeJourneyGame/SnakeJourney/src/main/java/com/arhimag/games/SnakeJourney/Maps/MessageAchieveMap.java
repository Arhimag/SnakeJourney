package com.arhimag.games.SnakeJourney.Maps;


public class MessageAchieveMap extends GameMap
{

	public MessageAchieveMap()
	{
		super( );
		FlatMap = new char[][]{
                {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','#','.','.','.','#','#','.','#','.','#','.','#','.',' ','#','#','.','#','.','#','.','.','#','#','.','.','.'},
                {'.','.','#','.','#','.','#','.','.','.','#','.','#','.','#','.','#','.','#','.','#','.','#','.','#','.','#','.','.','.'},
                {'.','.','#','#','#','.','#','.','.','.','#','#','#','.','#','.','#','#','.','.','#','.','#','.','#','#','.','.','.','.'},
                {'.','.','#','.','#','.','.','#','#','.','#','.','#','.','#','.',' ','#','#','.','.','#','.','.','.','#','#','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','#','#','#','#','#','#','#','#','#','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','#','#','#','#','#','#','#','#','#','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','#','#','#','#','#','#','#','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','#','#','#','#','#','#','#','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','.','#','#','#','#','#','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','.','.','#','#','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','#','#','#','#','#','#','#','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'}
        };
		
		mapWidth = FlatMap[0].length;
		mapHeight = FlatMap.length;
		
	}
}

