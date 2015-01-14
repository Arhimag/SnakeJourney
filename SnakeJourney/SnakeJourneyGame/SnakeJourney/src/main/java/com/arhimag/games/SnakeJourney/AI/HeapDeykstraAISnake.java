package com.arhimag.games.SnakeJourney.AI;

import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.Levels.GameLevel;

public class HeapDeykstraAISnake extends Snake 
{
	GameLevel level;
	
	int infinity;
	/* �������� � ������ ������ �������� � �������.
	 * � ������ � ������� 0 ������ ���������� ������ �������� */
	int[] vertexWayWeight;
	/* ������ �������� */
	int heapsize;
	/* ������������ ������ ������� � ������ � ��������
	 * ��� ��� ������� i ��������� � vertexWayWeight[vertexPosition[i]] */
	int[] vertexPosition;
	/* ������������ ������ � �������� � ������ �������  
	 * ��� id �������  i � �������� ��������� � vertexInvPosition[i] */
	int[] vertexInvPosition;
	/* ���������� ������� �� ���� � ���� */
	int[] vertexPreviousPath;

	
	public HeapDeykstraAISnake(GameLevel level, int x, int y)
	{
		super(x,y);
		this.level = level;
		
		infinity = level.getMap().getMapHeight() * level.getMap().getMapWidth() * 100;
		
		vertexWayWeight = new int[level.getMap().getMapHeight() * level.getMap().getMapWidth()];
		vertexPreviousPath = new int[vertexWayWeight.length];
		vertexPosition = new int[vertexWayWeight.length];
		vertexInvPosition = new int[vertexWayWeight.length];
	}

	
	private int getVertexWeight(int v)
	{
		return vertexWayWeight[vertexPosition[v]];
	}
	
	private int heapParent(int i)
	{
		return i/2;
	}
	
	private int heapLeft(int i)
	{
		return 2*i;
	}
	
	private int heapRight(int i)
	{
		return 2*i + 1;
	}
	
	@SuppressWarnings("unused")
	private void heapMinHeapify(int i)
	{
		int l;
		int r;
		int largest;
		
		int tmp;
		
		do
		{
			l = heapLeft(i);
			r = heapRight(i);
			
			if( ( l < heapsize) && (vertexWayWeight[l] < vertexWayWeight[i]) )
				largest = l;
			else
				largest = i;
			if( ( r < heapsize) && (vertexWayWeight[r] < vertexWayWeight[largest]) )
				largest = r;
			if( i != largest)
			{
				tmp = vertexWayWeight[i];
				vertexWayWeight[i] = vertexWayWeight[largest];
				vertexWayWeight[largest] = tmp;
				
				tmp = vertexPosition[vertexInvPosition[i]];
				vertexPosition[vertexInvPosition[i]] = vertexPosition[vertexInvPosition[largest]];
				vertexPosition[vertexInvPosition[largest]] = tmp;
				
				tmp = vertexInvPosition[i];
				vertexInvPosition[i] = vertexInvPosition[largest];
				vertexInvPosition[largest] = tmp;
				
				
				i = largest;
			}
			else
				break;
			
		} while( true );
	}
	
	private int heapExtractMin()
	{
		int vertexWithMinWeight;
		int tmp;
		int l;
		int r;
		int largest;
		int i;
		
		if( heapsize < 1 )
			return -1;
		
		vertexWithMinWeight = vertexInvPosition[0];

		heapsize--;
		
		tmp = vertexWayWeight[0];
		vertexWayWeight[0] = vertexWayWeight[heapsize];
		vertexWayWeight[heapsize] = tmp;
			
		tmp = vertexPosition[vertexInvPosition[0]];
		vertexPosition[vertexInvPosition[0]] = vertexPosition[vertexInvPosition[heapsize]];
		vertexPosition[vertexInvPosition[heapsize]] = tmp;
		
		tmp = vertexInvPosition[0];
		vertexInvPosition[0] = vertexInvPosition[heapsize];
		vertexInvPosition[heapsize] = tmp;

		i = 0;

		do
		{
			l = heapLeft(i);
			r = heapRight(i);
			
			if( ( l < heapsize) && (vertexWayWeight[l] < vertexWayWeight[i]) )
				largest = l;
			else
				largest = i;
			if( ( r < heapsize) && (vertexWayWeight[r] < vertexWayWeight[largest]) )
				largest = r;
			if( i != largest )
			{
				tmp = vertexWayWeight[i];
				vertexWayWeight[i] = vertexWayWeight[largest];
				vertexWayWeight[largest] = tmp;
					
				tmp = vertexPosition[vertexInvPosition[i]];
				vertexPosition[vertexInvPosition[i]] = vertexPosition[vertexInvPosition[largest]];
				vertexPosition[vertexInvPosition[largest]] = tmp;
				
				tmp = vertexInvPosition[i];
				vertexInvPosition[i] = vertexInvPosition[largest];
				vertexInvPosition[largest] = tmp;
				
				i = largest;
			}
			else
				break;
		} while( true);
		
		return vertexWithMinWeight;
	}
	
	private boolean heapDecreaseKey(int vertex, int newkey)
	{
		int heapPosition = vertexPosition[vertex];
		int tmp;
		
		if( newkey > vertexWayWeight[heapPosition] )
			return false;
		
		vertexWayWeight[heapPosition] = newkey;
		
		while ((heapPosition > 0) && (vertexWayWeight[heapParent(heapPosition)] > vertexWayWeight[heapPosition] ))
		{
			tmp = vertexWayWeight[heapPosition];
			vertexWayWeight[heapPosition] = vertexWayWeight[heapParent(heapPosition)];
			vertexWayWeight[heapParent(heapPosition)] = tmp;
				
			tmp = vertexPosition[vertexInvPosition[heapPosition]];
			vertexPosition[vertexInvPosition[heapPosition]] = vertexPosition[vertexInvPosition[heapParent(heapPosition)]];
			vertexPosition[vertexInvPosition[heapParent(heapPosition)]] = tmp;
			
			tmp = vertexInvPosition[heapPosition];
			vertexInvPosition[heapPosition] = vertexInvPosition[heapParent(heapPosition)];
			vertexInvPosition[heapParent(heapPosition)] = tmp;
					
			heapPosition = heapParent(heapPosition);
		}
		
		return true;
		
	}
	
	private void deykstraInitVertexArrays( int v )
	{
		for(int i = 0; i < vertexWayWeight.length; i++)
		{
			vertexWayWeight[i] = infinity;
			vertexPreviousPath[i] = -1;
			vertexPosition[i] = i;
			vertexInvPosition[i] = i;
		}
		vertexPosition[v] = 0;
		vertexPosition[0] = v;
		vertexWayWeight[0] = 0;
		vertexInvPosition[0] = v;
		vertexInvPosition[v] = 0;
		
		heapsize = vertexWayWeight.length;		
	}
	
	private int deykstraGetNext()
	{
		return heapExtractMin();
	}
	
	private void deykstraRelax(int u, int v)
	{
		int edgeWeight = level.getMapGraphWeight(u, v); 
		if( edgeWeight != -1 && getVertexWeight(v) > getVertexWeight(u) + edgeWeight )
		{
			heapDecreaseKey(v, getVertexWeight(u) + edgeWeight); 
			vertexPreviousPath[v] = u;
		}
	}
	
	private void deykstraRelaxFinish(int u, int v)
	{
		int edgeWeight = level.getMapGraphWeightFinish(u, v); 
		if( edgeWeight != -1 && getVertexWeight(v) > getVertexWeight(u) + edgeWeight )
		{
			heapDecreaseKey(v, getVertexWeight(u) + edgeWeight); 
			vertexPreviousPath[v] = u;
		}
	}
	
	private void deykstra()
	{
		int current = -1;
		int neightboor = -1;
		int neightboorId = -1;
		
		deykstraInitVertexArrays(level.getMapVertexId(parts.get(0).x,parts.get(0).y ) );
		
		while ((current = deykstraGetNext()) >= 0)
		{
			neightboorId = 0;
			while( ( neightboor = level.getMapGraphNeighbours(current, neightboorId++) ) > -1 )
				deykstraRelax(current, neightboor);
		}
	}
	
	private void deykstraFinish()
	{
		int current = -1;
		int neightboor = -1;
		int neightboorId = -1;
		
		deykstraInitVertexArrays(level.getMapVertexId(parts.get(0).x,parts.get(0).y ) );
		
		while ((current = deykstraGetNext()) >= 0)
		{
			neightboorId = 0;
			while( ( neightboor = level.getMapGraphNeighbours(current, neightboorId++) ) > -1 )
				deykstraRelaxFinish(current, neightboor);
		}
	}
	
	
	public int chooseFood()
	{
		int minlength;
		int minfood = 0;
		if(level.getFoodLength() > 0)
			minlength = getVertexWeight(level.getMapVertexId(level.getFood(0).x, level.getFood(0).y ));
		else 
			return -1;
		for(int i = 1; i < level.getFoodLength(); i++ )
			if( getVertexWeight(level.getMapVertexId(level.getFood(i).x, level.getFood(i).y )) < minlength )
			{
				minlength = getVertexWeight(level.getMapVertexId(level.getFood(i).x, level.getFood(i).y ));
				minfood = i;
			}
		return minfood;
	}
	
	public int chooseDirection()
	{
		if( this.parts.size() <= finishSize) 
		{
			int food = chooseFood();
			int previous = level.getMapVertexId(level.getFood(food).x, level.getFood(food).y );
			int current = vertexPreviousPath[previous];
			
			while(current != -1 && current != level.getMapVertexId(parts.get(0).x,parts.get(0).y ) )
			{
				previous = current;
				current = vertexPreviousPath[previous];
			}
			
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
		else
		{
			int previous = level.getMapVertexId(level.getFinishX(0), level.getFinishY(0) );
			int current = vertexPreviousPath[previous];
			
			while(current != -1 && current != level.getMapVertexId(parts.get(0).x,parts.get(0).y ) )
			{
				previous = current;
				current = vertexPreviousPath[previous];
			}
			
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
	}

	public void nextTurn()
	{
		if( parts.size() > finishSize)
			deykstraFinish();
		else
			deykstra();
		direction = chooseDirection();
	}
}
