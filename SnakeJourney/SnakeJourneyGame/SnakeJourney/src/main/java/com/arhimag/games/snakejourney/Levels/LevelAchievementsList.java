package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Achievements.GameAchievement;
import com.arhimag.games.snakejourney.Maps.GameMap;

public class LevelAchievementsList extends GameLevel
{
	private int slideStartY = 0;
	private long slideStartTime = 0;
	private int slideY = 0;
	private boolean slide = false;
	
	private int slideAnimation = ANIMATION_STOP;
	
	public final static int ANIMATION_STOP = 0;
	public final static int ANIMATION_DOWN = 1;
	public final static int ANIMATION_UP = -1;
	public final static int ANIMATION_NEXT = 2;
	public final static int ANIMATION_PREVIOUS = -2;

	public static final int achievementsPerList = 5;
	
	private float animationSpeed;
	
	public static int achievementId;
	public static int achievementPosition;
	
	public LevelAchievementsList(  GameMap map )
	{
		super(map);

		pauseable = false;
		playerSnake = -1;	
		aqua = false;
		readyScreen = false;
	}
	
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}
	
	public int getAchievemntId()
	{
		return achievementId;
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
	
	public void setSlideStartY( int newY )
	{
		if( slideAnimation == ANIMATION_STOP )
		{
			slideStartY = newY;
			slideStartTime = System.nanoTime();
		}
	}
	
	public void setSlideY( int newY)
	{
		if( slideAnimation == ANIMATION_STOP )
		{
			slideY = newY;
		}
	}
	
	public int getSlideY()
	{
		return slideY;
	}
	
	public int getSlideStartY()
	{
		return slideStartY;
	}
	
	public void startAnimationUP()
	{
		if(( slideAnimation == ANIMATION_STOP ) && (isNextAvalible()))
		{
			setSlide(false);
			slideAnimation = ANIMATION_UP;
			animationSpeed = (slideY - slideStartY) / ( (System.nanoTime() - slideStartTime) / 1000000000.0f );
		}
	}
	
	public void startAnimationDown()
	{
		if(( slideAnimation == ANIMATION_STOP )  && (isPreviousAvalible()))
		{
			setSlide(false);
			slideAnimation = ANIMATION_DOWN;
			animationSpeed = (slideY - slideStartY) / ( (System.nanoTime() - slideStartTime) / 1000000000.0f );
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
	
	public void activateSlide( int newY )
	{
		setSlideStartY(newY);
		setSlideY(newY);
		activateSlide();
	}
	
	
	public boolean isNextAvalible()
	{
		return achievementId < GameAchievement.achievementsList.length - achievementsPerList;
	}
	
	public boolean isPreviousAvalible()
	{
		return achievementId >= achievementsPerList;
	}
	
	public void incAchievement()
	{
		
		if (achievementId < GameAchievement.achievementsList.length - achievementsPerList ) 
			achievementId+=achievementsPerList;
	}
	
	public void decAchievement()
	{
		if (achievementId >= achievementsPerList ) 
			achievementId -= achievementsPerList;
	}	
}

