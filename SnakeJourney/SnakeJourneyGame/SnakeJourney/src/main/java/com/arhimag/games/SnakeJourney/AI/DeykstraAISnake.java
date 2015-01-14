package com.arhimag.games.SnakeJourney.AI;

import android.util.Log;

import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.Levels.GameLevel;

public class DeykstraAISnake extends Snake 
{
	GameLevel level;
	
	int[] vertexWayWeight;
	int[] vertexPreviousPath;
	boolean[] vertexCalculated;
	
	private void deykstraInitVertexArrays( int v )
	{
		for(int i = 0; i < vertexWayWeight.length; i++)
		{
			vertexWayWeight[i] = -1;
			vertexPreviousPath[i] = -1;
			vertexCalculated[i] = false;
		}
		vertexWayWeight[v] = 0;
	}
	
	private int deykstraGetNext()
	{
		int min = 0;
		
		boolean found = false;
		for( int i = 0; i < vertexWayWeight.length; i++)
		{
			if( !found )
			{
				if( !vertexCalculated[i] )
				{
					min = i;
					found = true;
				}
			}
			else if( vertexWayWeight[min] == -1 )
			{
				if(  !vertexCalculated[i] && vertexWayWeight[i] != -1 )
					min = i;
			} 
			else
			{
				if(  vertexWayWeight[i] < vertexWayWeight[min] && !vertexCalculated[i] && vertexWayWeight[i] != -1 )
					min = i;
			}
		}
		return min;
	}
	
	private void deykstraRelax(int u, int v)
	{
		if( level.getMapGraphWeight(u, v) != -1 && vertexWayWeight[u] != -1 )
			if( vertexWayWeight[v] == -1 )
			{
				vertexWayWeight[v] = vertexWayWeight[u] + level.getMapGraphWeight(u, v); 
				vertexPreviousPath[v] = u;
			}
			else if ( vertexWayWeight[v] > vertexWayWeight[u] +level.getMapGraphWeight(u, v) )
			{
				vertexWayWeight[v] = vertexWayWeight[u] + level.getMapGraphWeight(u, v); 
				vertexPreviousPath[v] = u;
			}
	}
	
	private void deykstra()
	{
		int calculated = 0;
		int current = -1;
		int neightboor = -1;
		int neightboorId = -1;
		
		deykstraInitVertexArrays(level.getMapVertexId(parts.get(0).x,parts.get(0).y ) );
		
		while (calculated < vertexWayWeight.length )
		{
			current = deykstraGetNext();
			vertexCalculated[current] = true;
			neightboorId = 0;
			while( ( neightboor = level.getMapGraphNeighbours(current, neightboorId++) ) > -1 )
				deykstraRelax(current, neightboor);
			calculated++;
		}
		//for(int i = 0; i < level.getMap().getMapWidth(); i++)
//			Log.d("111info111","Row 1 " + i + " :" + vertexWayWeight[i + 1 * level.getMap().getMapWidth()]);
	}
	
	public DeykstraAISnake(GameLevel level, int x, int y)
	{
		super(x,y);
		this.level = level;
		
		vertexWayWeight = new int[level.getMap().getMapHeight() * level.getMap().getMapWidth()];
		vertexPreviousPath = new int[vertexWayWeight.length];
		vertexCalculated = new boolean[vertexWayWeight.length];
	}
	
	
	public int chooseFood()
	{
		int minlength;
		int minfood = 0;
		if(level.getFoodLength() > 0)
			minlength = vertexWayWeight[level.getMapVertexId(level.getFood(0).x, level.getFood(0).y )];
		else 
			return -1;
		for(int i = 1; i < level.getFoodLength(); i++ )
			if(  minlength == -1 && vertexWayWeight[level.getMapVertexId(level.getFood(i).x, level.getFood(i).y )] != -1 )
			{
				minlength = vertexWayWeight[level.getMapVertexId(level.getFood(i).x, level.getFood(i).y )];
				minfood = i;
			}
			else if( vertexWayWeight[level.getMapVertexId(level.getFood(i).x, level.getFood(i).y )] < minlength && vertexWayWeight[level.getMapVertexId(level.getFood(i).x, level.getFood(i).y )] != -1)
			{
				minlength = vertexWayWeight[level.getMapVertexId(level.getFood(i).x, level.getFood(i).y )];
				minfood = i;
			}
		return minfood;
	}
	
	public int chooseDirection()
	{
		
		int food = chooseFood();
		//Log.d("111info111", "Food: " + food);
		
		int previous = level.getMapVertexId(level.getFood(food).x, level.getFood(food).y );
		//Log.d("111info111", "Previous: (" + level.getMapVertexX(previous) + "," + level.getMapVertexY(previous) + ")" );
		
		int current = vertexPreviousPath[previous];
		//Log.d("111info111", "Current: (" + level.getMapVertexX(current) + "," + level.getMapVertexY(current) + ")" );
		
		while(current != -1 && current != level.getMapVertexId(parts.get(0).x,parts.get(0).y ) )
		{
			previous = current;
			current = vertexPreviousPath[previous];
		}
		
		Log.d("111info111", "Current: " + current);
		
		if(current == -1)
			switch( (int)(Math.random()*4 ))
			{
			case 0: 
				return Snake.UP;
			case 1:
				return Snake.DOWN;
			case 2:
				return Snake.RIGHT;
			case 3:
				return Snake.LEFT;
			default:
				return Snake.UP;
					
			}
		else
		{
			if( parts.get(0).x < level.getMapVertexX(previous) )
				return Snake.RIGHT;
			if( parts.get(0).x > level.getMapVertexX(previous) )
				return Snake.LEFT;
			if( parts.get(0).y < level.getMapVertexY(previous) )
				return Snake.DOWN;
			if( parts.get(0).y > level.getMapVertexY(previous) )
				return Snake.UP;
			switch( (int)(Math.random()*4 ))
			{
			case 0: 
				return Snake.UP;
			case 1:
				return Snake.DOWN;
			case 2:
				return Snake.RIGHT;
			case 3:
				return Snake.LEFT;
			default:
				return Snake.UP;
					
			}
		}
	}

	public int test()
	{
		return vertexWayWeight[6];
	}
	
	public void nextTurn()
	{
		deykstra();
		direction = chooseDirection();
	}
}
