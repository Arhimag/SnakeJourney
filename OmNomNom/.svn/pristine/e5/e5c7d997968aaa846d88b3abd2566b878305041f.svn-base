package com.arhimag.games.omnomnom.AI;

import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.Levels.GameLevel;

public class Deykstra2AISnake extends Snake 
{
	GameLevel level;
	
	int infinity;
	/* Пирамида с весами вершин хранится в массиве.
	 * В ячейке с номером 0 всегда содержится корень пирамиды */
	int[] vertexWayWeight;
	/* Размер пирамиды */
	int heapsize;
	/* Соответствие номера вершины и номера в пирамиде
	 * Так вес вершины i находится в vertexWayWeight[vertexPosition[i]] */
	int[] vertexPosition;
	/* Соответствие номера в пирамиде и номера вершины  
	 * Так id позиции  i в пирамиде находится в vertexInvPosition[i] */
	int[] vertexInvPosition;
	/* Предыдущая вершина по пути к этой */
	int[] vertexPreviousPath;

	//Deykstra
	int current;
	int neightboor;
	int neightboorId;
	
	int vertexWithMinWeight;
	int tmp;
	int l;
	int r;
	int largest;
	int i;
	int edgeWeight;
	
	
	int newkey;
	int vertex;
	int heapPosition;
	int tmp2;
	
	int i1;
	
	int i_food;
	int foodLength;
	
	int i_finish;
	int finishLength;
	
	int previous_path;
	int current_path;
	int headId;
	
	boolean deykstra_found;
	
	public Deykstra2AISnake(GameLevel level, int x, int y)
	{
		super(x,y);
		this.level = level;
		
		infinity = level.getMap().getMapHeight() * level.getMap().getMapWidth() * 100;
		
		vertexWayWeight = new int[level.getMap().getMapHeight() * level.getMap().getMapWidth()];
		vertexPreviousPath = new int[vertexWayWeight.length];
		vertexPosition = new int[vertexWayWeight.length];
		vertexInvPosition = new int[vertexWayWeight.length];
	}

	private int deykstra()
	{

		
		deykstra_found = false;
		
		current = -1;
		neightboor = -1;
		neightboorId = -1;
	
		foodLength = level.getFoodLength();
		
		for(i1 = 0; i1 < vertexWayWeight.length; i1++)
		{
			vertexWayWeight[i1] = infinity;
			vertexPreviousPath[i1] = -1;
			vertexPosition[i1] = i1;
			vertexInvPosition[i1] = i1;
		}
		
		vertexPosition[level.getMapVertexId(parts.get(0).x,parts.get(0).y )] = 0;
		vertexPosition[0] = level.getMapVertexId(parts.get(0).x,parts.get(0).y );
		vertexWayWeight[0] = 0;
		vertexInvPosition[0] = level.getMapVertexId(parts.get(0).x,parts.get(0).y );
		vertexInvPosition[level.getMapVertexId(parts.get(0).x,parts.get(0).y )] = 0;
		
		heapsize = vertexWayWeight.length;

		if( heapsize < 1 )
			current = -1;
		
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
			l = 2*i;
			r = 2*i+1;
			
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
		
		current = vertexWithMinWeight;
		
		
		while (current >= 0)
		{
			if( vertexWayWeight[vertexPosition[current]] >= infinity )
			{
				current = -current -1;
				break;
			}
			
			for(i_food = 0; i_food < foodLength; i_food++ )
				if( level.getMapVertexId(level.getFood(i_food).x, level.getFood(i_food).y ) == current )
				{
					deykstra_found = true;				
					break;
				}
			
			if( deykstra_found )
				break;
			
			neightboorId = 0;
			while( ( neightboor = level.getMapGraphNeighbours(current, neightboorId++) ) > -1 )
			{
				edgeWeight = level.getMapGraphWeight(current, neightboor); 
				if( edgeWeight != -1 && vertexWayWeight[vertexPosition[neightboor]] > vertexWayWeight[vertexPosition[current]] + edgeWeight )
				{
					newkey = vertexWayWeight[vertexPosition[current]] + edgeWeight;
					vertex = neightboor;
					heapPosition = vertexPosition[vertex];
					
					if(!( newkey > vertexWayWeight[heapPosition] ))
					{
										
						vertexWayWeight[heapPosition] = newkey;
						
						while ((heapPosition > 0) && (vertexWayWeight[heapPosition/2] > vertexWayWeight[heapPosition] ))
						{
							tmp2 = vertexWayWeight[heapPosition];
							vertexWayWeight[heapPosition] = vertexWayWeight[heapPosition/2];
							vertexWayWeight[heapPosition/2] = tmp2;
								
							tmp2 = vertexPosition[vertexInvPosition[heapPosition]];
							vertexPosition[vertexInvPosition[heapPosition]] = vertexPosition[vertexInvPosition[heapPosition/2]];
							vertexPosition[vertexInvPosition[heapPosition/2]] = tmp2;
							
							tmp2 = vertexInvPosition[heapPosition];
							vertexInvPosition[heapPosition] = vertexInvPosition[heapPosition/2];
							vertexInvPosition[heapPosition/2] = tmp2;
									
							heapPosition = heapPosition/2;
						}
					}
					
					vertexPreviousPath[neightboor] = current;
				}
			}

			
			if( heapsize < 1 )
				break;
			
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
				l = 2*i;
				r = 2*i+1;
				
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
			
			current = vertexWithMinWeight;			
		}
		
		if( current < 0 || current == level.getMapVertexId(parts.get(0).x,parts.get(0).y ))
		{
			switch( (-current) % 4 )
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
		else
		{
			previous_path = current;
			current_path = vertexPreviousPath[previous_path];
			headId = level.getMapVertexId(parts.get(0).x,parts.get(0).y );
			
			while(current_path != headId )
			{
				previous_path = current_path;
				current_path = vertexPreviousPath[previous_path];
			}
			
			if( parts.get(0).x < level.getMapVertexX(previous_path) )
				return Snake.RIGHT;
			if( parts.get(0).x > level.getMapVertexX(previous_path) )
				return Snake.LEFT;
			if( parts.get(0).y < level.getMapVertexY(previous_path) )
				return Snake.DOWN;
			if( parts.get(0).y > level.getMapVertexY(previous_path) )
				return Snake.UP;
			switch( (current) % 4 )
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
	
	private int deykstraFinish()
	{

		
		deykstra_found = false;
		
		current = -1;
		neightboor = -1;
		neightboorId = -1;
	
		finishLength = level.getFinishesLength();
		
		for(i1 = 0; i1 < vertexWayWeight.length; i1++)
		{
			vertexWayWeight[i1] = infinity;
			vertexPreviousPath[i1] = -1;
			vertexPosition[i1] = i1;
			vertexInvPosition[i1] = i1;
		}
		
		vertexPosition[level.getMapVertexId(parts.get(0).x,parts.get(0).y )] = 0;
		vertexPosition[0] = level.getMapVertexId(parts.get(0).x,parts.get(0).y );
		vertexWayWeight[0] = 0;
		vertexInvPosition[0] = level.getMapVertexId(parts.get(0).x,parts.get(0).y );
		vertexInvPosition[level.getMapVertexId(parts.get(0).x,parts.get(0).y )] = 0;
		
		heapsize = vertexWayWeight.length;

		if( heapsize < 1 )
			current = -1;
		
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
			l = 2*i;
			r = 2*i+1;
			
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
		
		current = vertexWithMinWeight;
		
		
		while (current >= 0)
		{
			if( vertexWayWeight[vertexPosition[current]] >= infinity )
			{
				current = -current - 1 ;
				break;
			}
			
			for(i_finish = 0; i_finish < finishLength; i_finish++ )
				if( level.getMapVertexId(level.getFinishX(i_finish), level.getFinishY(i_finish) ) == current )
				{
					deykstra_found = true;				
					break;
				}
			
			if( deykstra_found )
				break;
			
			neightboorId = 0;
			while( ( neightboor = level.getMapGraphNeighbours(current, neightboorId++) ) > -1 )
			{
				edgeWeight = level.getMapGraphWeightFinish(current, neightboor); 
				if( edgeWeight != -1 && vertexWayWeight[vertexPosition[neightboor]] > vertexWayWeight[vertexPosition[current]] + edgeWeight )
				{
					newkey = vertexWayWeight[vertexPosition[current]] + edgeWeight;
					vertex = neightboor;
					heapPosition = vertexPosition[vertex];
					
					if(!( newkey > vertexWayWeight[heapPosition] ))
					{
										
						vertexWayWeight[heapPosition] = newkey;
						
						while ((heapPosition > 0) && (vertexWayWeight[heapPosition/2] > vertexWayWeight[heapPosition] ))
						{
							tmp2 = vertexWayWeight[heapPosition];
							vertexWayWeight[heapPosition] = vertexWayWeight[heapPosition/2];
							vertexWayWeight[heapPosition/2] = tmp2;
								
							tmp2 = vertexPosition[vertexInvPosition[heapPosition]];
							vertexPosition[vertexInvPosition[heapPosition]] = vertexPosition[vertexInvPosition[heapPosition/2]];
							vertexPosition[vertexInvPosition[heapPosition/2]] = tmp2;
							
							tmp2 = vertexInvPosition[heapPosition];
							vertexInvPosition[heapPosition] = vertexInvPosition[heapPosition/2];
							vertexInvPosition[heapPosition/2] = tmp2;
									
							heapPosition = heapPosition/2;
						}
					}
					
					vertexPreviousPath[neightboor] = current;
				}
			}

			
			if( heapsize < 1 )
				break;
			
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
				l = 2*i;
				r = 2*i+1;
				
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
			
			current = vertexWithMinWeight;			
		}
		
		if( current < 0 || current == level.getMapVertexId(parts.get(0).x,parts.get(0).y ) )
		{
			switch( (-current) % 4 )
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
		else
		{
			previous_path = current;
			current_path = vertexPreviousPath[previous_path];
			headId = level.getMapVertexId(parts.get(0).x,parts.get(0).y );
			
			while(current_path != headId )
			{
				previous_path = current_path;
				current_path = vertexPreviousPath[previous_path];
			}
			
			if( parts.get(0).x < level.getMapVertexX(previous_path) )
				return Snake.RIGHT;
			if( parts.get(0).x > level.getMapVertexX(previous_path) )
				return Snake.LEFT;
			if( parts.get(0).y < level.getMapVertexY(previous_path) )
				return Snake.DOWN;
			if( parts.get(0).y > level.getMapVertexY(previous_path) )
				return Snake.UP;
			switch( (current) % 4 )
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


	public void nextTurn()
	{
	//	Log.d("OmNomNomTrace","Deikstra start");
		if( parts.size() <= finishSize)
			direction = deykstra();
		else
			direction = deykstraFinish();
		//Log.d("OmNomNomTrace","Deikstra end");
	}
}
