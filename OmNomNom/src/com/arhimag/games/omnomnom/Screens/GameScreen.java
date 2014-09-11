package com.arhimag.games.omnomnom.Screens;

import java.util.List;


import com.arhimag.games.omnomnom.Assets;
import com.arhimag.games.omnomnom.GameLevelDrawer;
import com.arhimag.games.omnomnom.LevelSequence;
import com.arhimag.games.omnomnom.Settings;
import com.arhimag.games.omnomnom.Snake;
import com.arhimag.games.omnomnom.Levels.GameLevel;
import com.arhimag.games.omnomnom.Maps.EggsWindowMap;
import com.arhimag.games.omnomnom.framework.Game;
import com.arhimag.games.omnomnom.framework.Graphics;
import com.arhimag.games.omnomnom.framework.Input.KeyEvent;
import com.arhimag.games.omnomnom.framework.Input.TouchEvent;
import com.arhimag.games.omnomnom.framework.Point;
import com.arhimag.games.omnomnom.framework.Screen;
import com.arhimag.games.omnomnom.framework.impl.AndroidGame;


public class GameScreen extends Screen
{
	GameLevelDrawer levelDrawer;

	Point old[];
	int level = 0;
	int controltype;
	int thisSessionLevel = 0; 
	
	public GameScreen(Game game)
	{
		super(game);
		AndroidGame andrGame = (AndroidGame) game;

		old = new Point[10];
		for(int i  = 0; i < 10 ; i++) old[i] = new Point();
		thisSessionLevel = 0;
		//levelDrawer = new GameLevelDrawer( new Level1( new Level1Map()), andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
		//levelDrawer = new GameLevelDrawer( new LevelLabirinth( new LevelLabirinthMap()), andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
		levelDrawer = new GameLevelDrawer( LevelSequence.createLevel(Settings.getLastReachedLevel()), andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
		controltype = Settings.getControl();
	}

	public GameScreen(Game game, GameLevel levelObject, int levelNum)
	{
		super(game);
		AndroidGame andrGame = (AndroidGame) game;

		old = new Point[10];
		for(int i  = 0; i < 10 ; i++) old[i] = new Point();
		
		level = levelNum;
		levelDrawer = new GameLevelDrawer( levelObject, andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
		thisSessionLevel = 0;
		//levelDrawer = new GameLevelDrawer( new LevelLabirinth( new LevelLabirinthMap()), andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
		controltype = Settings.getControl();
	}
	
	private void updateAvalibleLevel( int newLevel )
	{
		if(Settings.getLastReachedLevel() < newLevel )
			Settings.setLastReachedLevel(newLevel);
	}
	
	@Override
	public void update(float deltaTime)
	{
		//Log.d("OmNomNomTrace","Update start");
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		List<KeyEvent> keyEvents = game.getInput().getKeyEvents();
		
		int len = keyEvents.size();
		
		for(int i = 0; i < len; i++)
		{
			KeyEvent event = keyEvents.get(i);
			if( event.type == KeyEvent.KEY_UP )
			{
				if( event.keyCode == android.view.KeyEvent.KEYCODE_BACK)
					game.setScreen(new PauseMenuScreen(game, levelDrawer.getLevel(), level));
			}
			
		}
		
		if( this.levelDrawer.getLevel().getEggsWindow())
		{
			Settings.updateEggs(level, (levelDrawer.getLevel().isFirstEggActive()?1:0) + (levelDrawer.getLevel().isSecondEggActive()?1:0) + (levelDrawer.getLevel().isThirdEggActive()?1:0) );
			len = touchEvents.size();
			for(int i = 0; i < len; i++)
			{
				TouchEvent event = touchEvents.get(i);
				
				if(event.type == TouchEvent.TOUCH_UP)
				{
					if( levelDrawer.inBoundsEgg(event, EggsWindowMap.getMenuBounds()))
					{
						game.setScreen(new MainMenuScreen(game));
						if( Settings.isSoundEnabled())
							Assets.eat.play(1);
						return;
					}
					if( levelDrawer.inBoundsEgg(event, EggsWindowMap.getRetryBounds()))
					{
						levelDrawer.setLevel(LevelSequence.createSameLevel(levelDrawer.getLevel()));
						if( Settings.isSoundEnabled())
							Assets.eat.play(1);
						return;
					}
					if( levelDrawer.inBoundsEgg(event, EggsWindowMap.getNextBounds()))
					{
						this.levelDrawer.getLevel().setNextLevel();
						if( Settings.isSoundEnabled())
							Assets.eat.play(1);
						return;
					}
				}
			}
		}
		
		if( controltype == 1 )
		{
			if( game.getInput().getAccelY() < -2.5f )
				this.levelDrawer.getLevel().goLEFT(this.levelDrawer.getLevel().getPlayerSnake());
			else if( game.getInput().getAccelY() > 2.5f )
				this.levelDrawer.getLevel().goRIGHT(this.levelDrawer.getLevel().getPlayerSnake());
		
			if( game.getInput().getAccelX() < -2.5f )
				this.levelDrawer.getLevel().goUP(this.levelDrawer.getLevel().getPlayerSnake());
			else if( game.getInput().getAccelX() > 2.5f )
				this.levelDrawer.getLevel().goDOWN(this.levelDrawer.getLevel().getPlayerSnake());
		}
		
		if( this.levelDrawer.getLevel().nextLevel() )
		{
			
			if( level < LevelSequence.getLevelsCount() - 1)
			{
				level++;
				thisSessionLevel++;
				updateAvalibleLevel(level);
				levelDrawer.setLevel(LevelSequence.createNextLevel(levelDrawer.getLevel()));
				levelDrawer.getLevel().setAqua(false);
			}
			else
			{
				game.setScreen(new MainMenuScreen(game));
			}
		}
		else if( this.levelDrawer.getLevel().getGameOver())
		{
			game.setScreen(new GameOverScreen(game));
		}
		else
		{
			if( this.levelDrawer.getLevel().getEat() )
				if( Settings.isSoundEnabled() )
				Assets.eat.play(1);
			
			len = touchEvents.size();
			
			for(int i = 0; i < len; i++)
			{
				TouchEvent event = touchEvents.get(i);
			
				if( controltype == 0)
				{
					if(event.type == TouchEvent.TOUCH_UP)
					{
						if( java.lang.Math.abs(event.x - old[event.pointer].x) > java.lang.Math.abs(event.y - old[event.pointer].y) )
						{
							if( event.x - old[event.pointer].x < 0 )
								this.levelDrawer.getLevel().goLEFT(this.levelDrawer.getLevel().getPlayerSnake());
							else
								this.levelDrawer.getLevel().goRIGHT(this.levelDrawer.getLevel().getPlayerSnake());
						}
						else
						{
							if( event.y - old[event.pointer].y < 0 )
								this.levelDrawer.getLevel().goUP(this.levelDrawer.getLevel().getPlayerSnake());
							else
								this.levelDrawer.getLevel().goDOWN(this.levelDrawer.getLevel().getPlayerSnake());
						}
						old[event.pointer].x = -1;
						old[event.pointer].y = -1;
					}
					else if( event.type == TouchEvent.TOUCH_DOWN )
					{
						old[event.pointer].x = event.x;
						old[event.pointer].y = event.y;
					}
/*					else if( event.type == TouchEvent.TOUCH_DRAGGED && levelDrawer.getLevel().getMovementHelp() && levelDrawer.getLevel().isTickNow(deltaTime) && old[event.pointer].x > 0 && old[event.pointer].y > 0)
					{
						Log.d("tocuhTest","TOUCH_DRAGGED: I = " + event.pointer + " (" + event.x + "," + event.y  + ") old[" + event.pointer + "]=(" + old[event.pointer].x + "," + old[event.pointer].y + ")");
						if( java.lang.Math.abs(event.x - old[event.pointer].x) > java.lang.Math.abs(event.y - old[event.pointer].y) && java.lang.Math.abs(event.x - old[event.pointer].x) > levelDrawer.getScreenWidth() / 100)
						{
							if( event.x - old[event.pointer].x < 0 )
								this.levelDrawer.getLevel().helpGoLEFT();
							else
								this.levelDrawer.getLevel().helpGoRIGHT();
						}
						else if( java.lang.Math.abs(event.x - old[event.pointer].x) < java.lang.Math.abs(event.y - old[event.pointer].y) &&  java.lang.Math.abs(event.y - old[event.pointer].y) > levelDrawer.getScreenHeight() / 100 )
						{
							if( event.y - old[event.pointer].y < 0 )
								this.levelDrawer.getLevel().helpGoUP();
							else
								this.levelDrawer.getLevel().helpGoDOWN();
						}
						old[event.pointer].x = -1;
						old[event.pointer].y = -1;
					}*/
				}
				else if( controltype == 2)
				{
					if(event.type == TouchEvent.TOUCH_UP)
					{
						if( event.x > ((AndroidGame)game).getDisplayWidth() / 2 )
							levelDrawer.getLevel().turnRight(this.levelDrawer.getLevel().getPlayerSnake());
						else
							levelDrawer.getLevel().turnLeft(this.levelDrawer.getLevel().getPlayerSnake());
					}
				}
				else if( controltype == 3)
				{
					if( event.type == TouchEvent.TOUCH_UP )
					{
						if( event.x < ((AndroidGame)game).getDisplayWidth() / 3 )
							levelDrawer.getLevel().goLEFT(this.levelDrawer.getLevel().getPlayerSnake());
						else if( event.x > 2 *((AndroidGame)game).getDisplayWidth() / 3 )
							levelDrawer.getLevel().goRIGHT(this.levelDrawer.getLevel().getPlayerSnake());
						else if( event.y > ((AndroidGame)game).getDisplayHeight() /2 )
							levelDrawer.getLevel().goDOWN(this.levelDrawer.getLevel().getPlayerSnake());
						else 
							levelDrawer.getLevel().goUP(this.levelDrawer.getLevel().getPlayerSnake());
					}
						
				}
				else if( controltype == 4)
				{
					if( event.type == TouchEvent.TOUCH_UP )
					{
						if( event.y < ((AndroidGame)game).getDisplayHeight() / 3 )
							levelDrawer.getLevel().goUP(this.levelDrawer.getLevel().getPlayerSnake());
						else if( event.y > 2 *((AndroidGame)game).getDisplayHeight() / 3 )
							levelDrawer.getLevel().goDOWN(this.levelDrawer.getLevel().getPlayerSnake());
						else if( event.x > ((AndroidGame)game).getDisplayWidth() /2 )
							levelDrawer.getLevel().goRIGHT(this.levelDrawer.getLevel().getPlayerSnake());
						else 
							levelDrawer.getLevel().goLEFT(this.levelDrawer.getLevel().getPlayerSnake());
					}
		
				}
				else if( controltype == 5)
				{
					if( event.type == TouchEvent.TOUCH_UP )
					{
						if(( event.y > ((AndroidGame)game).getDisplayHeight() / 2 ) && ( event.x < ((AndroidGame)game).getDisplayWidth() /2 ))
							if( levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.UP || levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.DOWN )
								levelDrawer.getLevel().goLEFT(this.levelDrawer.getLevel().getPlayerSnake());
							else
								levelDrawer.getLevel().goDOWN(this.levelDrawer.getLevel().getPlayerSnake());
						else if(( event.y < ((AndroidGame)game).getDisplayHeight() / 2 ) && ( event.x > ((AndroidGame)game).getDisplayWidth() /2 ))
							if( levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.UP || levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.DOWN )
								levelDrawer.getLevel().goRIGHT(this.levelDrawer.getLevel().getPlayerSnake());
							else
								levelDrawer.getLevel().goUP(this.levelDrawer.getLevel().getPlayerSnake());
					}
				}
				else if( controltype == 6)
				{
					if( event.type == TouchEvent.TOUCH_UP )
					{
						if(( event.y < ((AndroidGame)game).getDisplayHeight() / 2 ) && ( event.x < ((AndroidGame)game).getDisplayWidth() /2 ))
							if( levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.UP || levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.DOWN )
								levelDrawer.getLevel().goLEFT(this.levelDrawer.getLevel().getPlayerSnake());
							else
								levelDrawer.getLevel().goUP(this.levelDrawer.getLevel().getPlayerSnake());
						else if(( event.y > ((AndroidGame)game).getDisplayHeight() / 2 ) && ( event.x > ((AndroidGame)game).getDisplayWidth() /2 ))
								if( levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.UP || levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.DOWN )
									levelDrawer.getLevel().goRIGHT(this.levelDrawer.getLevel().getPlayerSnake());
								else
									levelDrawer.getLevel().goDOWN(this.levelDrawer.getLevel().getPlayerSnake());
					}
				}
				else if( controltype == 7)
				{
					if( event.type == TouchEvent.TOUCH_UP )
					{
						if( old[event.pointer].x != 0 || old[event.pointer].y != 0 )
							if( old[event.pointer].x < ((AndroidGame)game).getDisplayWidth() / 2 )
								if( old[event.pointer].y > event.y )
									levelDrawer.getLevel().goUP(levelDrawer.getLevel().getPlayerSnake());
								else
									levelDrawer.getLevel().goDOWN(levelDrawer.getLevel().getPlayerSnake());
							else
								if( old[event.pointer].x > event.x )
									levelDrawer.getLevel().goLEFT(levelDrawer.getLevel().getPlayerSnake());
								else
									levelDrawer.getLevel().goRIGHT(levelDrawer.getLevel().getPlayerSnake());
						old[event.pointer].x = 0;
						old[event.pointer].y = 0;
					}
					else if( event.type == TouchEvent.TOUCH_DOWN )
					{
						old[event.pointer].x = event.x;
						old[event.pointer].y = event.y;
					}
				}
			}
		}
		//Log.d("OmNomNomTrace","Update end");
		this.levelDrawer.getLevel().update(deltaTime,this.levelDrawer.getLevel().getPlayerSnake());
	}

	@Override
	public void present(float deltaTime)
	{
		Graphics g = game.getGraphics();
		g.clear(0);
		levelDrawer.draw(deltaTime);
	}

	@Override
	public void pause()
	{
		Settings.save(game.getFileIO());
		GameLevel.startPauseTimer();
	}

	@Override
	public void resume()
	{
		GameLevel.stopPauseTimer();
	}

	@Override
	public void dispose()
	{

	}

}
