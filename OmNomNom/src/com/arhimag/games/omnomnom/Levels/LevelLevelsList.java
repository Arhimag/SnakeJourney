package com.arhimag.games.omnomnom.Levels;

import android.graphics.Rect;

import com.arhimag.games.omnomnom.LevelSequence;
import com.arhimag.games.omnomnom.Settings;
import com.arhimag.games.omnomnom.Maps.CircleMap;
import com.arhimag.games.omnomnom.Maps.GameMap;
import com.arhimag.games.omnomnom.Maps.Level1Map;
import com.arhimag.games.omnomnom.Maps.Level2Map;
import com.arhimag.games.omnomnom.Maps.Level3Map;
import com.arhimag.games.omnomnom.Maps.Level4Map;
import com.arhimag.games.omnomnom.Maps.LevelCarpetMap;
import com.arhimag.games.omnomnom.Maps.LevelLabirinthMap;
import com.arhimag.games.omnomnom.Maps.MaskMap;
import com.arhimag.games.omnomnom.Maps.MeetAIMap;
import com.arhimag.games.omnomnom.Maps.MeetTeleportMap;
import com.arhimag.games.omnomnom.Maps.SnakeMap;

public class LevelLevelsList extends GameLevel
{
	private int listNumber = 0;
	
	private int slideStartX = 0;
	private long slideStartTime = 0;
	private int slideX = 0;
	private boolean slide = false;
	
	private int slideAnimation = ANIMATION_STOP;
	
	public final static int ANIMATION_STOP = 0;
	public final static int ANIMATION_RIGHT = 1;
	public final static int ANIMATION_LEFT = -1;
	public final static int ANIMATION_NEXT = 2;
	public final static int ANIMATION_PREVIOUS = -2;

	private float animationSpeed;
	
	private GameMap levelTopLeft;
	private GameMap levelTopRight;
	private GameMap levelBottomLeft;
	private GameMap levelBottomRight;
	
	private int levelTopLeftEggs;
	private int levelTopRightEggs;
	private int levelBottomLeftEggs;
	private int levelBottomRightEggs;

	private int minimapXDiff = 5;
	private int minimapYDiff = 3;
	private int minimapWidth = ((getMap().getMapWidth() - 15) / 2 );
	private int minimapHeight = ((getMap().getMapHeight() - 16) / 2 );
	private int ltmapX  = 6;
	private int ltmapY  = 10;
	private int rtmapX  = ltmapX + minimapXDiff + minimapWidth;
	private int rtmapY  = ltmapY;
	private int lbmapX  = ltmapX;
	private int lbmapY  = ltmapY + minimapYDiff + minimapHeight;
	private int rbmapX  = ltmapX + minimapXDiff + minimapWidth;
	private int rbmapY  = ltmapY + minimapYDiff + minimapHeight;
	
	private Rect ltBounds = new Rect(ltmapX, ltmapY, ltmapX + minimapWidth, ltmapY + minimapHeight);
	private Rect rtBounds = new Rect(rtmapX, rtmapY, rtmapX + minimapWidth, rtmapY + minimapHeight);
	private Rect lbBounds = new Rect(lbmapX, lbmapY, lbmapX + minimapWidth, lbmapY + minimapHeight);
	private Rect rbBounds = new Rect(rbmapX, rbmapY, rbmapX + minimapWidth, rbmapY + minimapHeight);

	public LevelLevelsList(  GameMap map )
	{
		super(map);

		pauseable = false;
		/*snakes = new Snake[2];
		moved = new boolean[2];
		
		ticksEverySecs = 0.1f;
		timeShift = 0; 
		int snake1StartX = 24;
		int snake1StartY = 18;
		snakes[0] = new Deykstra2AISnake(this,snake1StartX, snake1StartY);
		snakes[0].parts.add(new SnakePart(snake1StartX - 1, snake1StartY));
		snakes[0].parts.add(new SnakePart(snake1StartX - 2, snake1StartY));
		snakes[0].lastx = snake1StartX - 3;
		snakes[0].lasty = snake1StartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 10000;
		snakes[0].setBodyColor( 0xFF103010 + 0x002f2f2f/2);
		
		int snake2StartX = 25;
		int snake2StartY = 1;
		snakes[1] = new Deykstra2AISnake(this,snake2StartX, snake2StartY);
		snakes[1].parts.add(new SnakePart(snake2StartX , snake2StartY + 1));
		snakes[1].parts.add(new SnakePart(snake2StartX, snake2StartY + 2));
		snakes[1].lastx = snake2StartX;
		snakes[1].lasty = snake2StartY + 3;
		snakes[1].direction = Snake.RIGHT;
		snakes[1].finishSize = 10000;
		snakes[1].setBodyColor( 0xFF274727);
		
		food = new Food[5];
		food[0] = new Food( 5, 3 );
		food[1] = new Food( 24, 3 );
		food[2] = new Food( 5, 13 );
		food[3] = new Food( 24, 13 );
		food[4] = new Food( 24, 13 );
		
		generateNewFood(0);
		generateNewFood(1);
		generateNewFood(2);
		generateNewFood(3);
		generateNewFood(4);
		*/
		fillList();
		aqua = false;
		readyScreen = false;
	}
	
	
	public void update(float deltaTime, int snake_id )
	{
		/*if(!botMoved)
		{
			((Deykstra2AISnake)snakes[0]).nextTurn();
			((Deykstra2AISnake)snakes[1]).nextTurn();
			botMoved = true;
		}*/
		super.update(deltaTime, snake_id);
	}
	
	public int getListNumber()
	{
		return listNumber;
	}
	
	public boolean isSlide()
	{
		return slide;
	}
	
	public void setSlide(boolean newSlide)
	{
		if( slideAnimation == ANIMATION_STOP )
		{
			slide = newSlide;	
		}
	}
	
	public void activateSlide()
	{
		setSlide(true);
	}
	
	public void setSlideStartX( int newX )
	{
		if( slideAnimation == ANIMATION_STOP )
		{
			slideStartX = newX;
			slideStartTime = System.nanoTime();
		}
	}
	
	public void setSlideX( int newX )
	{
		if( slideAnimation == ANIMATION_STOP )
		{
			slideX = newX;
		}
	}
	
	public int getSlideX()
	{
		return slideX;
	}
	
	public int getSlideStartX()
	{
		return slideStartX;
	}
	
	public void startAnimationRight()
	{
		if(( slideAnimation == ANIMATION_STOP ) && (isListAvalible(listNumber + 1)))
		{
			setSlide(false);
			slideAnimation = ANIMATION_RIGHT;
			animationSpeed = (slideX - slideStartX) / ( (System.nanoTime() - slideStartTime) / 1000000000.0f );
		}
	}
	
	public void startAnimationLeft()
	{
		if(( slideAnimation == ANIMATION_STOP )  && (isListAvalible(listNumber - 1)))
		{
			setSlide(false);
			slideAnimation = ANIMATION_LEFT;
			animationSpeed = (slideX - slideStartX) / ( (System.nanoTime() - slideStartTime) / 1000000000.0f );
		}
	}
	
	public int getAnimation()
	{
		return slideAnimation;
	}
	
	public void setAnimation(int newAnimation)
	{
		slideAnimation = newAnimation;
	}
	
	public float getAnimationSpeed()
	{
		return animationSpeed;
	}
	
	public void activateSlide( int newX )
	{
		setSlideStartX(newX);
		setSlideX(newX);
		activateSlide();
	}
	
	

	// 0 - Labirinth
	// 1 - ThreeRooms
	// 2 - Carpet
	// 3 - level1
	// 4 - level2
	// 5 - level3
	// 6 - level4
	private void fillList()
	{
		
		if( Settings.getLastReachedLevel() > listNumber * 4 - 1 )
		{
			levelTopLeft = LevelSequence.createMap(listNumber * 4);
			levelTopLeftEggs = Settings.getEggs(listNumber * 4);
		}
		else
		{
			levelTopLeft = null;
			levelTopLeftEggs = 0;
		}
		
		if( Settings.getLastReachedLevel() > listNumber * 4  )
		{
			levelTopRight = LevelSequence.createMap(listNumber * 4 + 1);
			levelTopRightEggs = Settings.getEggs(listNumber * 4 + 1);
		}
		else
		{
			levelTopRight = null;
			levelTopRightEggs = 0;
		}
		
		if( Settings.getLastReachedLevel() > listNumber * 4 + 1 )
		{
			levelBottomLeft = LevelSequence.createMap(listNumber * 4 + 2);
			levelBottomLeftEggs = Settings.getEggs(listNumber * 4 + 2);
		}
		else
		{
			levelBottomLeft = null;
			levelBottomLeftEggs = 0;
		}
		
		if( Settings.getLastReachedLevel() > listNumber * 4 + 2)
		{
			levelBottomRight = LevelSequence.createMap(listNumber * 4 + 3);
			levelBottomRightEggs = Settings.getEggs(listNumber * 4 + 3);
		}
		else
		{
			levelBottomRight = null;
			levelBottomRightEggs = 0;
		}
		

		/*switch( listNumber )
		{
		case 0:

			
			levelTopLeft = new LevelLabirinthMap();
			if( Settings.getLastReachedLevel() > 0)
				levelTopRight = new MeetTeleportMap();
			else
				levelTopRight = null;
			
			if( Settings.getLastReachedLevel() > 1)
				levelBottomLeft = new MaskMap();
			else
				levelBottomLeft = null;
			
			if( Settings.getLastReachedLevel() > 2)
				levelBottomRight = new LevelCarpetMap();
			else
				levelBottomRight = null;


			break;
			
		case 1:
			
			if( Settings.getLastReachedLevel() > 3)
				levelTopLeft = new MeetAIMap();
			else
				levelTopLeft = null;
			
			if( Settings.getLastReachedLevel() > 4)
				levelTopRight = new CircleMap();
			else
				levelTopRight = null;
			
			if( Settings.getLastReachedLevel() > 5)
				levelBottomLeft = new SnakeMap();
			else
				levelBottomLeft = null;
			
			if( Settings.getLastReachedLevel() > 6)
				levelBottomRight = new Level1Map();
			else
				levelBottomRight = null;

			break;
		case 2:
			
			if( Settings.getLastReachedLevel() > 3)
				levelTopLeft = new Level2Map();
			else
				levelTopLeft = null;
			
			if( Settings.getLastReachedLevel() > 4)
				levelTopRight = new Level3Map();
			else
				levelTopRight = null;
			
			if( Settings.getLastReachedLevel() > 5)
				levelBottomLeft = new Level4Map();
			else
				levelBottomLeft = null;
			
			if( Settings.getLastReachedLevel() > 6)
				levelBottomRight = null;
			else
				levelBottomRight = null;

			
			break;
			
		default:
			levelTopLeft = null;
			levelTopRight = null;
			levelBottomLeft = null;
			levelBottomRight = null;
			break;
			
		} */
	}
	
	
	public boolean isListAvalible( int listId )
	{
		return (listId * 4 <= Settings.getLastReachedLevel()) && (listId >= 0);
	}
	
	public void incListNumber()
	{
		
		if (isListAvalible(listNumber + 1)) 
			listNumber++;
		fillList();
		
	}
	
	public void decListNumber()
	{
		if( isListAvalible( listNumber - 1 ) )
			listNumber--;
		fillList();
	}
	
	public GameMap getTopLeft()
	{
		return levelTopLeft;
	}
	
	public GameMap getTopRight()
	{
		return levelTopRight;
	}
	
	public GameMap getBottomLeft()
	{
		return levelBottomLeft;
	}
	
	public GameMap getBottomRight()
	{
		return levelBottomRight;
	}
	
	public int getMinimapXDiff()
	{
		return minimapXDiff;
	}
	
	public int getMinimapYDiff()
	{
		return minimapYDiff;
	}
	
	public int getMinimapWidth()
	{
		return minimapWidth;
	}
	
	public int getMinimapHeight()
	{
		return minimapHeight;
	}
	
	public int getLtmapX()
	{
		return ltmapX;
	}
	
	public int getLtmapY()
	{
		return ltmapY;
	}
	
	public int getRtmapX()
	{
		return rtmapX;
	}
	
	public int getRtmapY()
	{
		return rtmapY;
	}
	
	public int getLbmapX()
	{
		return lbmapX;
	}
	
	public int getLbmapY()
	{
		return lbmapY;
	}
	
	public int getRbmapX()
	{
		return rbmapX;
	}
	
	public int getRbmapY()
	{
		return rbmapY;
	}
	
	public Rect getLtBounds()
	{
		return ltBounds;
	}

	public Rect getRtBounds()
	{
		return rtBounds;
	}
	
	public Rect getLbBounds()
	{
		return lbBounds;
	}
	
	public Rect getRbBounds()
	{
		return rbBounds;
	}
	
	public int getRTeggs()
	{
		return levelTopRightEggs;
	}
	
	public int getLTeggs()
	{
		return levelTopLeftEggs;
	}
	
	public int getRBeggs()
	{
		return levelBottomRightEggs;
	}
	
	public int getLBeggs()
	{
		return levelBottomLeftEggs;
	}
}

