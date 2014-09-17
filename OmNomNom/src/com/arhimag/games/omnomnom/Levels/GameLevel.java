package com.arhimag.games.omnomnom.Levels;

import java.util.Calendar;
import java.util.Random;

import android.util.Log;

import com.arhimag.games.omnomnom.Food;
import com.arhimag.games.omnomnom.LevelSequence;
import com.arhimag.games.omnomnom.Settings;
import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.Achievements.GameAchievement;
import com.arhimag.games.omnomnom.Achievements.Get30WithAccel;
import com.arhimag.games.omnomnom.Achievements.GetLength40;
import com.arhimag.games.omnomnom.Achievements.UseTeleport;
import com.arhimag.games.omnomnom.GameElements.Teleport;
import com.arhimag.games.omnomnom.Maps.GameMap;
import com.arhimag.games.omnomnom.framework.Point;

public abstract class GameLevel
{
	protected GameMap map;
	//protected Snake snake;
	protected Snake[] snakes;
	protected Food[] food; 
	protected boolean aqua = false;
	protected boolean pauseButton = false;
	protected float ticksEverySecs = 0.2f;
	protected float timeShift = 0.075f;
	protected float speedupCount = 3;
	protected float timeFromTick;
	protected Point finishes[];
	protected boolean nextLevel = false;
	protected boolean eggsWindow = false;
	protected int winLength = 5;
	protected boolean moved[];
	protected boolean movedDirection[][];
	protected boolean eat = false;
	protected Teleport teleports[];  
	protected int playerSnake = 0;
	protected long levelStartTime = 0;
	protected long levelEndTime = 0;
	protected long egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
	protected long egg2 = (long)300 * (long)1000000000; // 5 �����
	protected long egg3 = (long)60 * (long)1000000000; // 1 ������
	protected long eggTimeOut = (long)1 * (long)1000000000; // 1 �������
	protected boolean gameOver = false;
	
	protected boolean movementHelp = false;
	protected int movementHelpDirection;
	protected boolean movementHelpDone = false;
	
	private static long pauseStart = 0;
	private static long pauseDuration = 0;
	
	protected java.util.Date tempEggDate = new java.util.Date();
	protected java.util.Calendar tempEggCalendar = java.util.Calendar.getInstance();
	
	public static final int WALL = -2;
	public static final int TELEPORT = -3;
	public static final int SNAKE = -7;
	public static final int FOOD = -11;
	public static final int FINISH = -13;
    protected int[][] intTempMap;
	
    //	protected char[][] tempMap; ������ �������� ��� ��������, ������� BFS
	protected boolean tempMapBuild;
	
	protected boolean readyScreen = true;
	protected int readyTicksLeft = 5;
	
	protected boolean pause = false;
	protected boolean pauseable = true;
	
	protected boolean ticked = true;
	protected boolean botMoved = false;
	
	//����������� ������ �������� ������
	private int i_getTickTime;
	private float minTickTime;
	private float tmp_getTickTime;
	
	protected Random rand = new Random();
	
	public GameLevel( GameMap map )
	{
		this.map = map;
		this.snakes = new Snake[0];
		moved = new boolean[0];
		food = new Food[0];
		finishes = new Point[0];
		teleports = new Teleport[0];
		//tempMap = new char[map.getMapWidth()][map.getMapHeight()];
		intTempMap = new int[map.getMapWidth()][map.getMapHeight()];
		tempMapBuild = false;
		buildTempMap();
		
		pauseable = (LevelSequence.getMapNum(map) >= 0);
		
		if(pauseable)
			GameLevel.clearPause();
		levelStartTime = System.nanoTime();
	}
	
	public int getMapVertexId(int x, int y)
	{
		return y * map.getMapWidth() + x;
	}
	
	public int getMapVertexX( int v )
	{
		return v % map.getMapWidth();
	}
	
	public int getMapVertexY( int v )
	{
		return v / map.getMapWidth();
	}

	public void markIntTempMap( int id, int value )
	{
		if( intTempMap[getMapVertexX(id)][getMapVertexY(id)] >= 0 )
			intTempMap[getMapVertexX(id)][getMapVertexY(id)] = value;
	}
	
	public int getIntTempMap( int id)
	{
		return intTempMap[getMapVertexX(id)][getMapVertexY(id)];
	}
	
	protected void buildTempMap()
	{
		for(int x = 0; x < map.getMapWidth(); x++)
			for(int y = 0; y < map.getMapHeight(); y++)
				intTempMap[x][y] = ((map.getFlatMap(x, y) == ' ') || (map.getFlatMap(x, y) == '.'))  ? 0 : WALL;
//				tempMap[x][y] = map.getFlatMap(x, y);
		for(int j = 0; j < this.snakes.length; j++)
		{
			if(snakes[j] != null)
				for(int i = 0; i < snakes[j].parts.size(); i++)
//					tempMap[snakes[j].parts.get(i).x][snakes[j].parts.get(i).y] = 'S';
					intTempMap[snakes[j].parts.get(i).x][snakes[j].parts.get(i).y] = SNAKE;
		}
		if( teleports != null)
			for(int i = 0; i < teleports.length; i++  )
//				tempMap[teleports[i].x][teleports[i].y] = 'T';
				intTempMap[teleports[i].x][teleports[i].y] = TELEPORT;
		if( food != null )
			for( int i = 0; i < food.length; i++ )
				intTempMap[food[i].x][food[i].y] = FOOD;
		tempMapBuild = true;
	}
	
	public void refreshFoodAndTeleportsOnTempMap()
	{
		if( teleports != null)
			for(int i = 0; i < teleports.length; i++  )
//				tempMap[teleports[i].x][teleports[i].y] = 'T';
				intTempMap[teleports[i].x][teleports[i].y] = TELEPORT;
		if( food != null )
			for( int i = 0; i < food.length; i++ )
				intTempMap[food[i].x][food[i].y] = FOOD;	
	}
	// ���������� i���� ������ ������� v. ���� ������ ������ ���, �� ���������� -1 
	public int getMapGraphNeighbours( int v, int i  )
	{
		int counter = -1;
		int x = getMapVertexX(v);
		int y = getMapVertexY(v);
		
		if( x > 0 )
		{
			counter++;
			if( counter == i )
			{
				if( intTempMap[x-1][y] == TELEPORT)
					for( int j = 0; j < teleports.length; j++ )
						if ( (teleports[j].x == x-1 ) && (teleports[j].y == y) )
						{
							counter++;
							if( counter == i )
								return getMapVertexId(teleports[teleports[j].getNextPort()].x, teleports[teleports[j].getNextPort()].y );
						}
				return getMapVertexId(x - 1, y);
			}
		}
		if( y > 0 )
		{
			counter++;
			if( counter == i )
			{
				if( intTempMap[x][y-1] == TELEPORT)
					for( int j = 0; j < teleports.length; j++ )
						if ( (teleports[j].x == x ) && (teleports[j].y == y-1) )
						{
							counter++;
							if( counter == i )
								return getMapVertexId(teleports[teleports[j].getNextPort()].x, teleports[teleports[j].getNextPort()].y );
						}
				return getMapVertexId(x, y - 1);
			}
		}
		if( x < map.getMapWidth() - 1 )
		{
			counter++;
			if( counter == i )
			{
				if( intTempMap[x+1][y] == TELEPORT)
					for( int j = 0; j < teleports.length; j++ )
						if ( (teleports[j].x == x + 1 ) && (teleports[j].y == y) )
						{
							counter++;
							if( counter == i )
								return getMapVertexId(teleports[teleports[j].getNextPort()].x, teleports[teleports[j].getNextPort()].y );
						}
				return getMapVertexId(x + 1, y);
			}
		}
		if( y < map.getMapHeight() - 1 )
		{
			counter++;
			if( counter == i )
			{
				if( intTempMap[x][y+1] == TELEPORT)
					for( int j = 0; j < teleports.length; j++ )
						if ( (teleports[j].x == x ) && (teleports[j].y == y+1) )
						{
							counter++;
							if( counter == i )
								return getMapVertexId(teleports[teleports[j].getNextPort()].x, teleports[teleports[j].getNextPort()].y );
						}
				return getMapVertexId(x, y + 1);
			}
		}
		//if( tempMap[x][y] == 'T')
		if( intTempMap[x][y] == TELEPORT)
			for( int j = 0; j < teleports.length; j++ )
				if ( (teleports[j].x == x ) && (teleports[j].y == y) )
				{
					counter++;
					if( counter == i )
						return getMapVertexId(teleports[teleports[j].getNextPort()].x, teleports[teleports[j].getNextPort()].y );
				}
		return -1;
	}
	
	//������� ���������� ��� ����� �����-�����
	//���� ��� ����� -1, �� �� ����� �������������
	public int getMapGraphWeight(int u, int v)
	{
		int x1, y1;
		int x2, y2;
		
		x1 = getMapVertexX(u);
		y1 = getMapVertexY(u);
		
		x2 = getMapVertexX(v);
		y2 = getMapVertexY(v);
		
		if( u >= map.getMapWidth() * map.getMapHeight() || v >= map.getMapWidth() * map.getMapHeight() )
		{
			Log.d("SnakeJourney","Incorrect value of vertexes " + u + " " + v);
			throw new RuntimeException();
		}
		
		//if(( tempMap[x2][y2]!=' ') && (tempMap[x2][y2]!='T'))
		if(( intTempMap[x2][y2]<0) && (intTempMap[x2][y2]!=TELEPORT) && (intTempMap[x2][y2]!=FOOD))
			return -1;
		

		
//		if ( (tempMap[x1][y1] == 'T') && (tempMap[x2][y2] == 'T')) 
		if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))
			for( int i = 0; i < teleports.length; i++ )
				if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
						( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
					return 1;
		
		if( x1 > 0 )
			if ( (intTempMap[x1 - 1][y1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 - 1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
							( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 - 1 ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
						return 1;
		
		if( x1 < map.getMapWidth() - 1 )
			if ( (intTempMap[x1 + 1][y1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 + 1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
							( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 + 1 ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
						return 1;
		
		if( x2 > 0 )
			if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2 - 1][y2] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 - 1) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
							( (teleports[i].x == x2 - 1 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1  ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
						return 1;
		
		if( x2 < map.getMapWidth() - 1 )
			if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2 + 1][y2] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 + 1 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
							( (teleports[i].x == x2 + 1 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
						return 1;

		if( y1 > 0 )
			if ( (intTempMap[x1][y1 - 1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1 - 1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
							( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 ) && (teleports[teleports[i].getNextPort()].y == y1 - 1) ) )
						return 1;
		
		if( y1 < map.getMapHeight() - 1 )
			if ( (intTempMap[x1][y1 + 1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1 + 1 ) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
							( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 ) && (teleports[teleports[i].getNextPort()].y == y1 + 1) ) )
						return 1;
		
		if( y2 > 0 )
			if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2][y2 - 1] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2 - 1) ) ||
							( (teleports[i].x == x2  ) && (teleports[i].y == y2 - 1) && (teleports[teleports[i].getNextPort()].x == x1  ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
						return 1;
		
		if( y2 < map.getMapHeight() - 1 )
			if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2][y2 + 1] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2 + 1) ) ||
							( (teleports[i].x == x2 ) && (teleports[i].y == y2 + 1) && (teleports[teleports[i].getNextPort()].x == x1  ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
						return 1;
		
		if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))
			for( int i = 0; i < teleports.length; i++ )
				if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
						( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
					return 1;
		
		if( ( Math.abs(x1 - x2) > 1) || (Math.abs(y1 - y2) > 1) )
			return -1;
		
		return 1;
	}
	
	

	public int getMapGraphWeightFinish(int u, int v)
	{
		int x1, y1;
		int x2, y2;
		
		x1 = getMapVertexX(u);
		y1 = getMapVertexY(u);
		
		x2 = getMapVertexX(v);
		y2 = getMapVertexY(v);
		
		if( u >= map.getMapWidth() * map.getMapHeight() || v >= map.getMapWidth() * map.getMapHeight() )
		{
			Log.d("SnakeJourney","Incorrect value of vertexes " + u + " " + v);
			throw new RuntimeException();
		}
		
				
		
		//if ( (tempMap[x1][y1] == 'T') && (tempMap[x2][y2] == 'T')) 
		if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))			
			for( int i = 0; i < teleports.length; i++ )
				if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[(i + 1)%teleports.length].y == y2) ) ||
						( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
					return 0;

		
		for( int i = 0; i < finishes.length; i++ )
			if ( ( x2 == finishes[i].x ) && ( y2 == finishes[i].y ) ) 
				return 1;
		
		//if(( tempMap[x2][y2]!=' ') && (tempMap[x2][y2]!='T'))
		if(( intTempMap[x2][y2]!=TELEPORT) && (intTempMap[x2][y2]!=TELEPORT))
			return -1;
		if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))
			for( int i = 0; i < teleports.length; i++ )
				if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
						( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
					return 1;
		
		if( x1 > 0 )
			if ( (intTempMap[x1 - 1][y1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 - 1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
							( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 - 1 ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
						return 1;
		
		if( x1 < map.getMapWidth() - 1 )
			if ( (intTempMap[x1 + 1][y1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 + 1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
							( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 + 1 ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
						return 1;
		
		if( x2 > 0 )
			if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2 - 1][y2] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 - 1) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
							( (teleports[i].x == x2 - 1 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1  ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
						return 1;
		
		if( x2 < map.getMapWidth() - 1 )
			if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2 + 1][y2] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 + 1 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
							( (teleports[i].x == x2 + 1 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
						return 1;

		if( y1 > 0 )
			if ( (intTempMap[x1][y1 - 1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1 - 1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
							( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 ) && (teleports[teleports[i].getNextPort()].y == y1 - 1) ) )
						return 1;
		
		if( y1 < map.getMapHeight() - 1 )
			if ( (intTempMap[x1][y1 + 1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1 + 1 ) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
							( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 ) && (teleports[teleports[i].getNextPort()].y == y1 + 1) ) )
						return 1;
		
		if( y2 > 0 )
			if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2][y2 - 1] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2 - 1) ) ||
							( (teleports[i].x == x2  ) && (teleports[i].y == y2 - 1) && (teleports[teleports[i].getNextPort()].x == x1  ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
						return 1;
		
		if( y2 < map.getMapHeight() - 1 )
			if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2][y2 + 1] == TELEPORT))
				for( int i = 0; i < teleports.length; i++ )
					if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2 + 1) ) ||
							( (teleports[i].x == x2 ) && (teleports[i].y == y2 + 1) && (teleports[teleports[i].getNextPort()].x == x1  ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
						return 1;
		
		if ( (intTempMap[x1][y1] == TELEPORT) && (intTempMap[x2][y2] == TELEPORT))
			for( int i = 0; i < teleports.length; i++ )
				if ( ( (teleports[i].x == x1 ) && (teleports[i].y == y1) && (teleports[teleports[i].getNextPort()].x == x2 ) && (teleports[teleports[i].getNextPort()].y == y2) ) ||
						( (teleports[i].x == x2 ) && (teleports[i].y == y2) && (teleports[teleports[i].getNextPort()].x == x1 ) && (teleports[teleports[i].getNextPort()].y == y1) ) )
					return 1;

		
		if( ( Math.abs(x1 - x2) > 1) || (Math.abs(y1 - y2) > 1) )
			return -1;
		
		return 1;
	}
	
	public GameMap getMap()
	{
		return map;
	}
	
	public Snake getSnake( int i )
	{
		return snakes[i];
	}
	
	public void goUP( int i )
	{
		if( snakes[i].parts.size() == 1 || snakes[i].parts.get(1).y != snakes[i].parts.get(0).y - 1 )
		{
			if( ticked )
				snakes[i].direction = Snake.UP;
			ticked = false;
		}
	}
	
	public void goDOWN( int i )
	{
		if( snakes[i].parts.size() == 1 || snakes[i].parts.get(1).y != snakes[i].parts.get(0).y + 1 )
		{
			if( ticked )
				snakes[i].direction = Snake.DOWN;
			ticked = false;
		}
	}
	
	public void goRIGHT( int i )
	{
		if( snakes[i].parts.size() == 1 || snakes[i].parts.get(1).x != snakes[i].parts.get(0).x + 1 )
		{
			if( ticked )
				snakes[i].direction = Snake.RIGHT;
			ticked = false;
		}
	}
	
	public void goLEFT( int i )
	{
		if( snakes[i].parts.size() == 1 || snakes[i].parts.get(1).x != snakes[i].parts.get(0).x - 1 )
		{
			if( ticked )
				snakes[i].direction = Snake.LEFT;
			ticked = false;
		}
	}
	
	public void turnLeft( int i )
	{
		if( ticked )
			snakes[i].turnLeft();
		ticked = false;
	}
	
	public void turnRight( int i )
	{
		if( ticked )
			snakes[i].turnRight();
		ticked = false;
	}

	
	protected boolean isTeleportNear( int x, int y )
	{
		for( int i = 0; i < teleports.length; i++)
			if((java.lang.Math.abs(teleports[i].x - x) <= 1) && (java.lang.Math.abs(teleports[i].y - y) <= 1))
				return true;
		return false;
	}
	
	protected boolean isSnakeNear( int x, int y )
	{
		if( getIntTempMap(getMapVertexId(x,y)) < 0 )
			return true;
		
		for( int i = 0; i < snakes.length; i++ )
		{
			if( snakes[i].lastx == x && snakes[i].lasty == y )
				return true;
			if((java.lang.Math.abs(snakes[i].parts.get(0).x - x) <= 3 ) && (java.lang.Math.abs(snakes[i].parts.get(0).y - y) <= 3))
				return true;
			for( int j = 0; j < snakes[i].parts.size(); j++ )
				if( snakes[i].parts.get(j).x == x && snakes[i].parts.get(j).y == y)
					return true;
		}
		return false;
	}
	
	protected void generateNewFood( int i )
	{
		/* ������ �������� ��������� ���.
		 */ 
		 
		int x = rand.nextInt(this.map.getMapWidth());
		int y = rand.nextInt(this.map.getMapHeight());
		
		while( this.map.getFlatMap(x,y) != ' ' ||  isTeleportNear(x,y)  || isSnakeNear(x,y) )
		{
			x = rand.nextInt(this.map.getMapWidth());
			y = rand.nextInt(this.map.getMapHeight());
		}
		
		this.food[i].x = x;
		this.food[i].y = y;
		
		
		
		
	}
	public boolean isTickNow(float deltaTime)
	{
		return timeFromTick + deltaTime > this.getTickTime();
	}
	
	public void update(float deltaTime, int snake_id )
	{
		
		
		eat = false;
		timeFromTick += deltaTime;
		
		if (timeFromTick > this.getTickTime())
		{
			if( readyScreen )
			{
				if( readyTicksLeft == 0 )
					readyScreen = false;
				else
					readyTicksLeft --;
			}
			else
			{
				tick();
			}
			timeFromTick = 0.0f;
		}
	}
	
	protected void getDirectionsMove()
	{
		if( Settings.getIsFutureMovement() == 1)
		{
			if( movedDirection == null)
				movedDirection = new boolean[4][snakes.length];
			int nextFieldX = -1;
			int nextFieldY = -1;
			char nextFieldValue;
			
			if( moved.length < snakes.length )
				moved = new boolean[snakes.length];
			for(int i = 0; i < moved.length; i++)
				moved[i] = true;
			
			for( int snakeid = 0; snakeid < this.snakes.length; snakeid++ )
			{
	
				movedDirection[Snake.UP][snakeid] = true;
				nextFieldX = snakes[snakeid].parts.get(0).x;
				nextFieldY = snakes[snakeid].parts.get(0).y - 1;
				
				
				if( ( nextFieldX < 0 ) || 
					( nextFieldX >= map.getMapWidth() ) ||
					( nextFieldY < 0 ) ||
					( nextFieldY >= map.getMapHeight() ) )
					{
						Log.d("SnakeJourney", "Incorrect snake position!");
						throw new RuntimeException();
					}
				
				nextFieldValue = map.getFlatMap(nextFieldX,nextFieldY);
				
				// ���� ������ ���� � �����
				if( nextFieldValue != ' ' && nextFieldValue != '.')
				{
					if( snakes[snakeid].parts.size() > 1)
					{
						snakes[snakeid].lastx = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).x;
						snakes[snakeid].lasty = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).y;
					}
					movedDirection[Snake.UP][snakeid] =false;
				}
				else
				{
						// ��������� �� ��������� �� ������������ �� ������
					for( int check_snake = 0; check_snake < snakes.length; check_snake++)
						for( int check_snake_id = 0; check_snake_id < snakes[check_snake].parts.size(); check_snake_id++ )
							if ( ( nextFieldX == snakes[check_snake].parts.get(check_snake_id).x ) && ( nextFieldY == snakes[check_snake].parts.get(check_snake_id).y ) )
								movedDirection[Snake.UP][snakeid] = false;
				}
	
	//-------------------------------------------------
				movedDirection[Snake.DOWN][snakeid] = true; 
				nextFieldX = snakes[snakeid].parts.get(0).x;
				nextFieldY = snakes[snakeid].parts.get(0).y + 1;
		
				if( ( nextFieldX < 0 ) || 
						( nextFieldX >= map.getMapWidth() ) ||
						( nextFieldY < 0 ) ||
						( nextFieldY >= map.getMapHeight() ) )
						{
							Log.d("SnakeJourney", "Incorrect snake position!");
							throw new RuntimeException();
						}
					
				nextFieldValue = map.getFlatMap(nextFieldX,nextFieldY);
				
				// ���� ������ ���� � �����
				if( nextFieldValue != ' ' && nextFieldValue != '.')
				{
					if( snakes[snakeid].parts.size() > 1)
					{
						snakes[snakeid].lastx = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).x;
						snakes[snakeid].lasty = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).y;
					}
					movedDirection[Snake.DOWN][snakeid] =false;
				}
				else
				{
						// ��������� �� ��������� �� ������������ �� ������
					for( int check_snake = 0; check_snake < snakes.length; check_snake++)
						for( int check_snake_id = 0; check_snake_id < snakes[check_snake].parts.size(); check_snake_id++ )
							if ( ( nextFieldX == snakes[check_snake].parts.get(check_snake_id).x ) && ( nextFieldY == snakes[check_snake].parts.get(check_snake_id).y ) )
								movedDirection[Snake.DOWN][snakeid] = false;
				}
				
		//-------		
				
				movedDirection[Snake.LEFT][snakeid] = true;
				nextFieldX = snakes[snakeid].parts.get(0).x - 1;
				nextFieldY = snakes[snakeid].parts.get(0).y ;
				
				if( ( nextFieldX < 0 ) || 
						( nextFieldX >= map.getMapWidth() ) ||
						( nextFieldY < 0 ) ||
						( nextFieldY >= map.getMapHeight() ) )
						{
							Log.d("SnakeJourney", "Incorrect snake position!");
							throw new RuntimeException();
						}
					
				nextFieldValue = map.getFlatMap(nextFieldX,nextFieldY);
				
				// ���� ������ ���� � �����
				if( nextFieldValue != ' ' && nextFieldValue != '.' )
				{
					if( snakes[snakeid].parts.size() > 1)
					{
						snakes[snakeid].lastx = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).x;
						snakes[snakeid].lasty = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).y;
					}
					movedDirection[Snake.LEFT][snakeid] =false;
				}
				else
				{
						// ��������� �� ��������� �� ������������ �� ������
					for( int check_snake = 0; check_snake < snakes.length; check_snake++)
						for( int check_snake_id = 0; check_snake_id < snakes[check_snake].parts.size(); check_snake_id++ )
							if ( ( nextFieldX == snakes[check_snake].parts.get(check_snake_id).x ) && ( nextFieldY == snakes[check_snake].parts.get(check_snake_id).y ) )
								movedDirection[Snake.LEFT][snakeid] = false;
				}
				
				movedDirection[Snake.RIGHT][snakeid] = true;
				nextFieldX = snakes[snakeid].parts.get(0).x + 1;
				nextFieldY = snakes[snakeid].parts.get(0).y;
				if( ( nextFieldX < 0 ) || 
						( nextFieldX >= map.getMapWidth() ) ||
						( nextFieldY < 0 ) ||
						( nextFieldY >= map.getMapHeight() ) )
						{
							Log.d("SnakeJourney", "Incorrect snake position!");
							throw new RuntimeException();
						}
					
				nextFieldValue = map.getFlatMap(nextFieldX,nextFieldY);
				
				// ���� ������ ���� � �����
				if( nextFieldValue != ' ' && nextFieldValue != '.')
				{
					if( snakes[snakeid].parts.size() > 1)
					{
						snakes[snakeid].lastx = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).x;
						snakes[snakeid].lasty = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).y;
					}
					movedDirection[Snake.RIGHT][snakeid] =false;
				}
				else
				{
						// ��������� �� ��������� �� ������������ �� ������

					for( int check_snake = 0; check_snake < snakes.length; check_snake++)
						for( int check_snake_id = 0; check_snake_id < snakes[check_snake].parts.size(); check_snake_id++ )
							if ( ( nextFieldX == snakes[check_snake].parts.get(check_snake_id).x ) && ( nextFieldY == snakes[check_snake].parts.get(check_snake_id).y ) )
								movedDirection[Snake.RIGHT][snakeid] = false;
				}
			}
		}
	}
		
		
	public char getNextSnakeField(int snakeid)
	{
		int nextFieldX = -1;
		int nextFieldY = -1;
		char nextFieldValue;
		boolean tail;
		
		if( snakes[snakeid].direction ==  Snake.UP)
		{
			nextFieldX = snakes[snakeid].parts.get(0).x;
			nextFieldY = snakes[snakeid].parts.get(0).y - 1;
		} 
		else if( snakes[snakeid].direction == Snake.DOWN )
		{
			nextFieldX = snakes[snakeid].parts.get(0).x;
			nextFieldY = snakes[snakeid].parts.get(0).y + 1;
		} 
		else if( snakes[snakeid].direction == Snake.LEFT )
		{
			nextFieldX = snakes[snakeid].parts.get(0).x - 1;
			nextFieldY = snakes[snakeid].parts.get(0).y ;
		} 
		else if( snakes[snakeid].direction == Snake.RIGHT )
		{
			nextFieldX = snakes[snakeid].parts.get(0).x + 1;
			nextFieldY = snakes[snakeid].parts.get(0).y;
		}		
		
		if( ( nextFieldX < 0 ) || 
			( nextFieldX >= map.getMapWidth() ) ||
			( nextFieldY < 0 ) ||
			( nextFieldY >= map.getMapHeight() ) )
			{
				Log.d("SnakeJourney", "Incorrect snake position!");
				throw new RuntimeException();
			}
		
		nextFieldValue = map.getFlatMap(nextFieldX,nextFieldY);
		
		tail = false;
		for( int check_snake = 0; check_snake < snakes.length; check_snake++)
			for( int check_snake_id = 0; check_snake_id < snakes[check_snake].parts.size(); check_snake_id++ )
				if ( ( nextFieldX == snakes[check_snake].parts.get(check_snake_id).x ) && ( nextFieldY == snakes[check_snake].parts.get(check_snake_id).y ) )
					tail = true;
		if( tail )
			return 'R';
		else
			return nextFieldValue;

	}
	
	public void tick()
	{
		int nextFieldX = -1;
		int nextFieldY = -1;
		char nextFieldValue;
		boolean tail;
		boolean moveFinished = false;
		
		if (pauseable )
			GameLevel.stopPauseTimer();
		
		if( eggsWindow )
			return;
		
		if( movementHelp && playerSnake >= 0 && playerSnake < snakes.length && ticked && movementHelpDone)
		{
			snakes[playerSnake].direction = movementHelpDirection;
			movementHelpDone = false;
		}
			
		
		if( moved.length < snakes.length )
			moved = new boolean[snakes.length];
		for(int i = 0; i < moved.length; i++)
			moved[i] = true;
		
		for( int snakeid = 0; snakeid < this.snakes.length; snakeid++ )
		{
			while( !moveFinished )
			{
				moveFinished = true;
				
				if( snakes[snakeid].direction ==  Snake.UP)
				{
					nextFieldX = snakes[snakeid].parts.get(0).x;
					nextFieldY = snakes[snakeid].parts.get(0).y - 1;
				} 
				else if( snakes[snakeid].direction == Snake.DOWN )
				{
					nextFieldX = snakes[snakeid].parts.get(0).x;
					nextFieldY = snakes[snakeid].parts.get(0).y + 1;
				} 
				else if( snakes[snakeid].direction == Snake.LEFT )
				{
					nextFieldX = snakes[snakeid].parts.get(0).x - 1;
					nextFieldY = snakes[snakeid].parts.get(0).y ;
				} 
				else if( snakes[snakeid].direction == Snake.RIGHT )
				{
					nextFieldX = snakes[snakeid].parts.get(0).x + 1;
					nextFieldY = snakes[snakeid].parts.get(0).y;
				}		
				
				if( ( nextFieldX < 0 ) || 
					( nextFieldX >= map.getMapWidth() ) ||
					( nextFieldY < 0 ) ||
					( nextFieldY >= map.getMapHeight() ) )
					{
						Log.d("SnakeJourney", "Incorrect snake position!");
						throw new RuntimeException();
					}
				
				nextFieldValue = map.getFlatMap(nextFieldX,nextFieldY);
				
				boolean finish = false;
				
				// ���������  �� ������������ �� ������
				if( snakes[snakeid].parts.size() > snakes[snakeid].finishSize )
					for( int i = 0; i < finishes.length; i++)
						if(( finishes[i].x == nextFieldX ) && ( finishes[i].y == nextFieldY ))
						{
 
							finish = true;

						}
				
				// ��������� �� ������ �� ������ � ��������
				int teleportId = -1;
				for( int i = 0; i < teleports.length; i++ )
					if((teleports[i].x == nextFieldX ) && (teleports[i].y == nextFieldY))
						teleportId = i;
				
				// ���� ������ ������������
				if( finish )
				{
					
					if( snakeid == this.playerSnake)
					{
						snakes[snakeid].advance();
						levelEndTime = System.nanoTime();
						levelStartTime += GameLevel.getPauseDuration();
						GameLevel.clearPause();
						eggsWindow = true;
						// this.nextLevel = true;
					}
					else
					{
						if(( playerSnake >= 0 ) && ( snakes[playerSnake].parts.size() > 1))
						{
							snakes[playerSnake].lastx = snakes[playerSnake].parts.get(snakes[playerSnake].parts.size() - 1).x;
							snakes[playerSnake].lasty = snakes[playerSnake].parts.get(snakes[playerSnake].parts.size() - 1).y;
							snakes[playerSnake].parts.remove(snakes[playerSnake].parts.size() - 1);
						}
						else
						{
							while( snakes[snakeid].parts.size() > 1)
							{
								snakes[snakeid].lastx = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).x;
								snakes[snakeid].lasty = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).y;
								snakes[snakeid].parts.remove(snakes[snakeid].parts.size() - 1);
							}
						}
						moved[snakeid] = false;
						//this.raiseGameOver();
					}
					moved[snakeid] = true;
				}
				// ���� ������ � �������� �����
				else if ( teleportId >= 0)
				{
					snakes[snakeid].advance();
					snakes[snakeid].parts.get(0).x = teleports[teleports[teleportId].getNextPort()].x;
					snakes[snakeid].parts.get(0).y = teleports[teleports[teleportId].getNextPort()].y;
					moveFinished = false;
					if( snakeid == playerSnake  )
						Settings.achievementsStatus[GameAchievement.getAchievementId(UseTeleport.class)].setStatus(100);
				}
				else
				{
					// ���� ������ ���� � �����
					if( nextFieldValue != ' ' && nextFieldValue != '.')
					{
						if( snakes[snakeid].parts.size() > 1)
						{
							snakes[snakeid].lastx = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).x;
							snakes[snakeid].lasty = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).y;
							snakes[snakeid].parts.remove(snakes[snakeid].parts.size() - 1);
							if(snakeid == playerSnake && !Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].isAchievementReached() && Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].getStatus() >= 0)
								Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].setStatus(Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].getStatus() - 1);
						}		
						moved[snakeid] = false;
					}
					else
					{
	
						// ��������� �� ��������� �� ������������ �� ������
						tail = false;
						for( int check_snake = 0; check_snake < snakes.length; check_snake++)
							for( int check_snake_id = 0; check_snake_id < snakes[check_snake].parts.size(); check_snake_id++ )
								if ( ( nextFieldX == snakes[check_snake].parts.get(check_snake_id).x ) && ( nextFieldY == snakes[check_snake].parts.get(check_snake_id).y ) )
									tail = true;
	
						if( tail )
						{
							if( snakes[snakeid].parts.size() > 1)
							{
								snakes[snakeid].lastx = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).x;
								snakes[snakeid].lasty = snakes[snakeid].parts.get(snakes[snakeid].parts.size() - 1).y;
								snakes[snakeid].parts.remove(snakes[snakeid].parts.size() - 1);
								if(snakeid == playerSnake && !Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].isAchievementReached() && Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].getStatus() >= 0)
									Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].setStatus(Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].getStatus() - 1);
							}
							moved[snakeid] = false;
						}
						else
						{
							for( int i = 0; i < food.length; i++)
							{
								if (( nextFieldX == food[i].x ) && ( nextFieldY == food[i].y ))
								{
									this.snakes[snakeid].eat();
									if( snakeid == playerSnake && snakes[snakeid].parts.size() >= 40 )
										Settings.achievementsStatus[GameAchievement.getAchievementId(GetLength40.class)].setStatus(100);
									
									if(snakeid == playerSnake && !Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].isAchievementReached() && Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].getStatus() >= 0)
									{
										Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].setStatus(Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].getStatus() + 1);
										if(Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].getStatus() == 5)
											Settings.achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].setStatus( 100 );
									}
									
									eat = true;
									this.generateNewFood(i);
								}
							}
							snakes[snakeid].advance();
							moved[snakeid] = true;
						}
					}
				}
			}
			moveFinished = false;
		}
		
		
		getDirectionsMove();
		tempMapBuild = false;
		buildTempMap();
		ticked = true;
		botMoved = false;
	}
	
	public float getTickTime()
	{
		minTickTime = ticksEverySecs;
		for( i_getTickTime = 0; i_getTickTime < this.snakes.length; i_getTickTime++)
		{
			tmp_getTickTime = ticksEverySecs - 0.002f * this.snakes[i_getTickTime].parts.size();
			if( tmp_getTickTime < minTickTime )
				minTickTime = tmp_getTickTime;
		}
		return minTickTime;
	}
	
	public float getTicks()
	{
		return timeFromTick;
	}
	
	public boolean getMoved(int snakeid)
	{
		return moved[snakeid];
	}
	
	public Food getFood(int i)
	{
		return food[i];
	}
	
	public int getFoodLength()
	{
		if( food != null )
			return food.length;
		else
			return 0;
	}
	
	public boolean nextLevel()
	{
		return nextLevel;
	}
	
	public int getFinishesCount( int snake_id )
	{
		if (( snake_id >= 0 ) && ( this.snakes[snake_id].parts.size() > this.snakes[snake_id].finishSize ))
			if( finishes != null )
				return finishes.length;
			else
				return 0;
		else
			return 0;
	}
	
	public int getFinishX(int i)
	{
		return finishes[i].x;
	}
	
	public int getFinishY(int i)
	{
		return finishes[i].y;
	}
	
	public int getTeleportesCount()
	{
		if( teleports != null )
			return teleports.length;
		else
			return 0;
	}
	
	public int getTeleportX(int i)
	{
		return teleports[i].x;
	}
	
	public int getTeleportY(int i)
	{
		return teleports[i].y;
	}
	
	public boolean getEat()
	{
		return eat;
	}
	
	public int getPlayerSnake()
	{
		return this.playerSnake;
	}
	
	public int getSnakesCount()
	{
		return this.snakes.length;
	}
	
	public boolean getGameOver()
	{
		return gameOver;
	}
	
	public void raiseGameOver()
	{
		gameOver = true;
	}
	
	public boolean getAqua()
	{
		return aqua;
	}
	
	public void setAqua(boolean t )
	{
		aqua = t;
	}
	
	public boolean getPauseButton()
	{
		return pauseButton;
	}
	
	public void setPauseButton(boolean t )
	{
		pauseButton = t;
	}
	
	public int getFinishesLength()
	{
		return finishes.length;
	}

	public boolean isReadyScreen()
	{
		return readyScreen;
	}
	
	public int getReadyTicks()
	{
		return readyTicksLeft;
	}
	
	public boolean getMovedDirection(int direction, int snake)
	{
		if( movedDirection == null)
			return false;
		else
			return movedDirection[direction][snake];
	}
	
	public boolean getEggsWindow()
	{
		return eggsWindow;
	}
	
	public boolean isFirstEggActive()
	{
		if( levelEndTime == 0 )
			return false;
		return (levelEndTime - levelStartTime < egg1);
	}
	
	public boolean isSecondEggActive()
	{
		if( levelEndTime == 0 )
			return false;
		return (levelEndTime - levelStartTime < egg2);
	}
	
	public boolean isThirdEggActive()
	{
		if( levelEndTime == 0 )
			return false;
		return (levelEndTime - levelStartTime < egg3);
	}
	
	public long getEggTimeout()
	{
		return eggTimeOut;
	}
	
	public void setNextLevel()
	{
		nextLevel = true;
	}
	
	public long getEgg1TimeNumber(int number) //�0�1:�2�3 
	{
		tempEggCalendar.setTimeInMillis(egg1 / 1000000);
		if( number == 0 )
			return tempEggCalendar.get(Calendar.MINUTE)/10;
		if( number == 1 )
			return tempEggCalendar.get(Calendar.MINUTE)%10;
		if( number == 2 )
			return tempEggCalendar.get(Calendar.SECOND)/10;
		if( number == 3 )
			return tempEggCalendar.get(Calendar.SECOND)%10;
		else return 0;
	}
	
	public long getEgg2TimeNumber(int number) //�0�1:�2�3 
	{
		tempEggCalendar.setTimeInMillis(egg2 / 1000000);
		if( number == 0 )
			return tempEggCalendar.get(Calendar.MINUTE)/10;
		if( number == 1 )
			return tempEggCalendar.get(Calendar.MINUTE)%10;
		if( number == 2 )
			return tempEggCalendar.get(Calendar.SECOND)/10;
		if( number == 3 )
			return tempEggCalendar.get(Calendar.SECOND)%10;
		else return 0;
	}
	
	public long getEgg3TimeNumber(int number) //�0�1:�2�3 
	{
		tempEggCalendar.setTimeInMillis(egg3 / 1000000);
		if( number == 0 )
			return tempEggCalendar.get(Calendar.MINUTE)/10;
		if( number == 1 )
			return tempEggCalendar.get(Calendar.MINUTE)%10;
		if( number == 2 )
			return tempEggCalendar.get(Calendar.SECOND)/10;
		if( number == 3 )
			return tempEggCalendar.get(Calendar.SECOND)%10;
		else return 0;
	}
	
	public long getPlayerTimeNumber(int number) //�0�1:�2�3 
	{
		if( levelEndTime == 0 )
			return 9;
		
		if( levelEndTime - levelStartTime > (long)1000000000 * (long)60 * (long)60  )
			return 9;
		
		tempEggCalendar.setTimeInMillis(( levelEndTime - levelStartTime) / 1000000);
		if( number == 0 )
			return tempEggCalendar.get(Calendar.MINUTE)/10;
		if( number == 1 )
			return tempEggCalendar.get(Calendar.MINUTE)%10;
		if( number == 2 )
			return tempEggCalendar.get(Calendar.SECOND)/10;
		if( number == 3 )
			return tempEggCalendar.get(Calendar.SECOND)%10;
		else return 0;
	}
	
	public boolean getMovementHelp()
	{
		return movementHelp;
	}
	
	public void helpGoUP( )
	{
		if( playerSnake >= 0 && playerSnake < snakes.length )
			if( snakes[playerSnake].parts.size() == 1 || snakes[playerSnake].parts.get(1).x != snakes[playerSnake].parts.get(0).x - 1 )
			{
				movementHelpDirection = Snake.UP;
				movementHelpDone = true;
			}
	}
	
	public void helpGoDOWN( )
	{
		if( playerSnake >= 0 && playerSnake < snakes.length )
			if( snakes[playerSnake].parts.size() == 1 || snakes[playerSnake].parts.get(1).x != snakes[playerSnake].parts.get(0).x - 1 )
			{
				movementHelpDirection = Snake.DOWN;
				movementHelpDone = true;
			}
	}
	
	public void helpGoRIGHT( )
	{
		if( playerSnake >= 0 && playerSnake < snakes.length )
			if( snakes[playerSnake].parts.size() == 1 || snakes[playerSnake].parts.get(1).x != snakes[playerSnake].parts.get(0).x - 1 )
			{
				movementHelpDirection = Snake.RIGHT;
				movementHelpDone = true;
			}
	}
	
	public void helpGoLEFT(  )
	{
		if( playerSnake >= 0 && playerSnake < snakes.length )
			if( snakes[playerSnake].parts.size() == 1 || snakes[playerSnake].parts.get(1).x != snakes[playerSnake].parts.get(0).x - 1 )
			{
				movementHelpDirection = Snake.LEFT;
				movementHelpDone = true;
			}
	}
	
	
	public static void startPauseTimer()
	{
		//Log.d("pauseDebug", "Trying to pause game(pauseStart:" + pauseStart + "; pauseDuration:" + pauseDuration + ")");
		if( pauseStart == 0 )
			pauseStart = System.nanoTime();
	}
	
	public static void stopPauseTimer()
	{
		//Log.d("pauseDebug", "Trying to stopPause game(pauseStart:" + pauseStart + "; pauseDuration:" + pauseDuration + ")");
		if( pauseStart > 0 )
			pauseDuration += System.nanoTime() - pauseStart;
		pauseStart = 0;
	}
	
	public static void clearPause()
	{
		//Log.d("pauseDebug", "Trying to clearPause game(pauseStart:" + pauseStart + "; pauseDuration:" + pauseDuration + ")");
		pauseStart = 0;
		pauseDuration = 0;
	}
	
	public static long getPauseDuration()
	{
		//Log.d("pauseDebug", "Trying to getPause game(pauseStart:" + pauseStart + "; pauseDuration:" + pauseDuration + ")");
		return pauseDuration;
	}
	
	
}
