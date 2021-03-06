package com.arhimag.games.SnakeJourney.Maps;


public class MessageMoreEggsMap extends GameMap
{

	public MessageMoreEggsMap()
	{
		super( );
		FlatMap = new char[][]{
                {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','#','#','#','.','#','.','.','.','.','#','.','.','#','.','#','.','.','.','#','.','.','#','.','.','.','#','#','.','.'},
                {'.','#','.','#','.','#','.','.','.','#','.','#','.','#','.','#','.','.','#','.','#','.','#','.','.','.','#','.','#','.'},
                {'.','#','#','.','.','#','.','.','.','#','#','#','.','.','#','.','.','.','#','.','#','.','#','.','.','.','#','.','#','.'},
                {'.','#','.','.','.','#','#','#','.','#','.','#','.','.','#','.','.','.','.','#','.','.','#','#','#','.','#','#','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','#','.','.','.','.','#','#','.','.','#','.','#','.','.','#','#','.','#','.','.','.','.','#','.','.','.','.'},
                {'.','.','.','#','.','.','.','#','.','#','.','.','#','.','#','.','#','.','#','.','#','.','.','.','#','.','.','.','.','.'},
                {'.','.','.','#','.','.','.','#','#','.','.','.','#','.','#','.','#','#','.','.','#','.','.','.','.','#','.','.','.','.'},
                {'.','.','.','#','#','#','.','.','#','#','.','.','.','#','.','.','.','#','#','.','#','#','#','.','#','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','#','#','.','.','#','#','.','#','#','#','.','.','.','.','#','#','.','.','#','#','.','.','#','#','.','.','#','.'},
                {'.','#','.','.','.','#','.','#','.','.','#','.','.','.','.','#','.','#','.','#','.','.','.','#','.','.','.','#','.','.'},
                {'.','#','.','#','.','#','#','.','.','.','#','.','.','.','.','#','#','.','.','#','.','#','.','#','.','#','.','.','#','.'},
                {'.','.','#','#','.','.','#','#','.','.','#','.','.','.','.','.','#','#','.','.','#','#','.','.','#','#','.','#','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'}
        };
		
		mapWidth = FlatMap[0].length;
		mapHeight = FlatMap.length;
		
	}
}

