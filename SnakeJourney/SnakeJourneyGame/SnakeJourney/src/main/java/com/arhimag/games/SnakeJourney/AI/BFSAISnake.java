package com.arhimag.games.SnakeJourney.AI;

import com.arhimag.games.SnakeJourney.Snake;
import com.arhimag.games.SnakeJourney.Levels.GameLevel;

public class BFSAISnake extends Snake 
{
	GameLevel level;
	private int primeNumber; // ������������ ������� ����� ����������� ������, ��� ���������� ��������� ����� �� ����� ����������� BFS
	private int mapWidth;
	private int mapHeight;
	
	private int queque[]; //������ ��������� �������� �������
	private int quequeStart; //������ ������� ��������
	private int quequeEnd; //������ ���� ���������� ����� �������
	private int quequeSize;//������ �������
	private final int rndSequence[] = {3,2,3,2,0,0,3,1,0,0,2,1,2,0,0,1,0,2,0,2,3,2,0,2,3,2,1,2,2,3,1,1,0,1,0,3,1,2,1,0,2,0,1,3,2,3,1,2,3,1,1,0,1,1,3,2,3,1,2,0,3,3,3,2,2,0,0,0,3,0,2,1,0,0,1,1,3,2,1,3,2,1,3,0,2,1,2,1,0,2,1,3,3,3,2,2,3,1,3,2}; //��������� ������������������ ���������
	private int rndPointer = 3;

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *                                                             *  
	 * ������������ ������� ��������.                              *
	 * queque - ������ ��������� �������� �������.                 *
	 * quequeStart - ������ ������� ��������                       *  
	 * quequeEnd - ������ ���� ���������� ����� �������            *
	 * quequeSize - ������ �������. �� �� ���������. � ������      * 
	 * �������� ����� ���������� �������� ������� ������� �        * 
	 * ������������ ��������� �������, ��� ����� ������������      * 
	 * ����� ������ ���������, ������� ����� ����� ���������       *
	 * ����������� ������ ���������� �������, ����� ��� �� �����   *
	 * ����� ������, �� � ������� ����� � 90% �������.             *
	 *                                                             *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/* ������������� �������. 
	 * ��������� ������ � ������������ � ������ ����� ����, ��� �����
	 * ������ ������� �����. � ���������� mapWidth*mapHeight 
	 */
	private void initQueque()
	{
		quequeSize = mapWidth*mapHeight;
		queque = new int[quequeSize];
		quequeStart = 0;
		quequeEnd = 0;
	}
	
	/* ��������� ������� � ����� �������
	 * ! ��� �������� ����� ������ ������� ����������� !
	 */
	private void pushQueque(int vertex )
	{
		if( quequeEnd == quequeSize )
		{
			// ������-�� ���������� java.lang.OutOfMemory, ������� ���� ����� � ����������� ���������
			return;
/*			int temp[];
			
			temp = new int[quequeSize * 2];
			java.lang.System.arraycopy(queque, 0, temp, 0, quequeSize);
			queque = temp;
			temp = null;
			
			quequeSize *= 2; */
		}
		
		queque[quequeEnd] = vertex;
//		Log.d("BFS","Vertex (" + level.getMapVertexX(vertex) + "," + level.getMapVertexY(vertex) + ") added " + quequeEnd );
		quequeEnd++;		
	}
	
	/* ����� �� �������?
	 */
	private boolean isEmptyQueque()
	{
		return quequeStart >= quequeEnd;
	}
	
	/* ������ ������ ������� ������� ������ ���.
	 */
	private int popQueque()
	{
		return queque[quequeStart++];
	}

	/* ������� �������
	 */
	private void clearQueque()
	{
		quequeStart = 0;
		quequeEnd = 0;
	}

	/* * * * * * * * * * * * * *
	 * ����� �������� �������  *
	 * * * * * * * * * * * * * */
	
	public BFSAISnake(GameLevel level, int x, int y, int primeNmbr)
	{
		super(x,y);
		this.level = level;
		primeNumber = primeNmbr;
		mapWidth = level.getMap().getMapWidth();
		mapHeight = level.getMap().getMapHeight();
		initQueque();
		//Log.d("OmNomNom", "test");
	}
	
	

	/* 
	 * �������� ������:
	 * ���� �����, � ������� ����� ���������� ������.
	 * �������� ����� � ������ � ��������� ����� �� ��� ���, ���� �� ������ �� ������ ������.
	 * � ������ ������ ������ �� ��������. ��������� ������ - ��� ������ ��������.
	 */
	public int getSnakeTarget()
	{
		int minWayLengthSquare = mapWidth*mapWidth + mapHeight*mapHeight;
		int target = -1;
		if( parts.size() > finishSize )
		{
			 for( int i = 0; i < level.getFinishesLength(); i++ )
				 if( (parts.get(0).x - level.getFinishX(i))*(parts.get(0).x - level.getFinishX(i)) + (parts.get(0).y -level.getFinishY(i))*(parts.get(0).y - level.getFinishY(i)) < minWayLengthSquare )
				 {
					 target = level.getMapVertexId(level.getFinishX(i), level.getFinishY(i));
					 minWayLengthSquare = (parts.get(0).x - level.getFinishX(i))*(parts.get(0).x - level.getFinishX(i)) + (parts.get(0).y -level.getFinishY(i))*(parts.get(0).y - level.getFinishY(i));
				 }
			 //Log.d("BFS","Target is food" + level.getMapVertexX(target) + " " + level.getMapVertexY(target));
			 return target;
		}
		else
		{
			for( int i = 0; i < level.getFoodLength(); i++ )
				 if((parts.get(0).x - level.getFood(i).x)*(parts.get(0).x - level.getFood(i).x) + (parts.get(0).y - level.getFood(i).y)*(parts.get(0).y - level.getFood(i).y) < minWayLengthSquare )
				 {
					 target = level.getMapVertexId(level.getFood(i).x, level.getFood(i).y);
					 minWayLengthSquare = (parts.get(0).x - level.getFood(i).x)*(parts.get(0).x - level.getFood(i).x) + (parts.get(0).y - level.getFood(i).y)*(parts.get(0).y - level.getFood(i).y);

				 }
			return target;
		}
	}
	
	/* ���� ��� �� �������� � ���� � ��������� ����� ���, �� �� �������� 
	 * ���� ��������� �������� � ������� ���� �������. ����������������
	 * ��������� �������� ����������� � ������� �� ���������� ������������. 
	 */
	public int getRandomDirection()
	{
		int rndNumber = rndSequence[rndPointer++ % rndSequence.length];
		int previousLoop = -1;
		while( rndNumber != previousLoop)
		{
			previousLoop = rndNumber;
			if ( level.getIntTempMap(level.getMapVertexId(parts.get(0).x + 1, parts.get(0).y) ) >= 0 || level.getIntTempMap(level.getMapVertexId(parts.get(0).x + 1, parts.get(0).y) ) == GameLevel.TELEPORT ||  level.getIntTempMap(level.getMapVertexId(parts.get(0).x + 1, parts.get(0).y) ) == GameLevel.FOOD)
				if( rndNumber-- == 0)
					return Snake.RIGHT;
			if ( level.getIntTempMap(level.getMapVertexId(parts.get(0).x - 1, parts.get(0).y) ) >= 0 || level.getIntTempMap(level.getMapVertexId(parts.get(0).x - 1, parts.get(0).y) ) == GameLevel.TELEPORT ||  level.getIntTempMap(level.getMapVertexId(parts.get(0).x - 1, parts.get(0).y) ) == GameLevel.FOOD)
				if( rndNumber-- == 0)
					return Snake.LEFT;
			if ( level.getIntTempMap(level.getMapVertexId(parts.get(0).x, parts.get(0).y + 1) ) >= 0 || level.getIntTempMap(level.getMapVertexId(parts.get(0).x, parts.get(0).y + 1) ) == GameLevel.TELEPORT || level.getIntTempMap(level.getMapVertexId(parts.get(0).x, parts.get(0).y + 1) ) == GameLevel.FOOD)
				if( rndNumber-- == 0)
					return Snake.DOWN;
			if ( level.getIntTempMap(level.getMapVertexId(parts.get(0).x, parts.get(0).y - 1) ) >= 0 || level.getIntTempMap(level.getMapVertexId(parts.get(0).x, parts.get(0).y - 1) ) == GameLevel.TELEPORT || level.getIntTempMap(level.getMapVertexId(parts.get(0).x, parts.get(0).y - 1) ) == GameLevel.FOOD)
				if( rndNumber-- == 0)
					return Snake.UP;
		}
		return direction;
	}
	
	/* ������ �����������, ��� ������������ � ����� - ������������ ��������
	 */
	public int getDirection( int nextVert )
	{
		if( parts.get(0).x < level.getMapVertexX(nextVert) )
			return Snake.RIGHT;
		if( parts.get(0).x > level.getMapVertexX(nextVert) )
			return Snake.LEFT;
		if( parts.get(0).y < level.getMapVertexY(nextVert) )
			return Snake.DOWN;
		else
			return Snake.UP;
	}
	
	public void bfs()
	{
		int neightboor;
		int neightboorId;
		int current;

		clearQueque();
		pushQueque(getSnakeTarget());
		
		/* �������� ���������� ������� ������ �������� � �������, �.�. �� ���
		 * ������ ���������� �� ������ ��� ���. �.�. ���� �� ������� ����� - 
		 * ��� �������� �����, �� ������� � ������� ����� � ��� ���, �.�. ����
		 * ����� ��� - �����.
		 */
		current = popQueque();
		neightboorId = 0;
		
		while( ( neightboor = level.getMapGraphNeighbours(current, neightboorId++) ) > -1 )
		{
    		//Log.d("BFS", "Check for success (" + level.getMapVertexX(neightboor) +  "," +level.getMapVertexY(neightboor) + ") and (" + parts.get(0).x + "," + parts.get(0).y + ")");
			if( level.getMapVertexX(neightboor) == parts.get(0).x && level.getMapVertexY(neightboor) == parts.get(0).y )
			{
				direction = getDirection(current);
				level.refreshFoodAndTeleportsOnTempMap();
				return;
			}
			if( parts.size() >= finishSize )
			{
				if( level.getMapGraphWeightFinish( neightboor, current) >= 0 && level.getIntTempMap(neightboor) != primeNumber && level.getIntTempMap(neightboor) >= 0)
				{
					level.markIntTempMap(neightboor, primeNumber);
					pushQueque(neightboor);
				}
			}
			else
			{
				if( level.getMapGraphWeight(current, neightboor) >= 0 && level.getIntTempMap(neightboor) != primeNumber && level.getIntTempMap(neightboor) >= 0)
				{
					level.markIntTempMap(neightboor, primeNumber);
					pushQueque(neightboor);
				}
			}
		}
		
		while( ! isEmptyQueque() )
		{
			current = popQueque();
			neightboorId = 0;
			//Log.d("BFS","!!!");
			while( ( neightboor = level.getMapGraphNeighbours(current, neightboorId++) ) > -1 )
			{
				//Log.d("BFS", "Check for success (" + level.getMapVertexX(neightboor) +  "," +level.getMapVertexY(neightboor) + ") and (" + parts.get(0).x + "," + parts.get(0).y + ")");
				if( level.getMapVertexX(neightboor) == parts.get(0).x && level.getMapVertexY(neightboor) == parts.get(0).y )
				{
					direction = getDirection(current);
					level.refreshFoodAndTeleportsOnTempMap();
					return;
				}
				
				if( level.getMapGraphWeight(current, neightboor) >= 0 && level.getIntTempMap(neightboor) != primeNumber && level.getIntTempMap(neightboor) >= 0)
				{
					level.markIntTempMap(neightboor, primeNumber);
					pushQueque(neightboor);
				}
			}
		}
		
		direction = getRandomDirection();
		//Log.d("BFS","Way from (" + level.getMapVertexX(getSnakeTarget()) + "," + level.getMapVertexY(getSnakeTarget()) + ") to Snake failed. Direction is " + direction );
		//for(int i = 0; i < queque.length; i++)
			//Log.d("BFS", Integer.toString(queque[i]));
		//Log.d("BFS", "end");
		level.refreshFoodAndTeleportsOnTempMap();
		return;
	}
	
	public void nextTurn()
	{
		//Log.d("BFS", "BfS start");
		bfs();	
	}
}
