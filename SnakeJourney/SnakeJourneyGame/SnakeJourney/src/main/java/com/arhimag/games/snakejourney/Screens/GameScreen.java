package com.arhimag.games.snakejourney.Screens;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import java.util.List;


import com.arhimag.games.snakejourney.Assets;
import com.arhimag.games.snakejourney.GameLevelDrawer;
import com.arhimag.games.snakejourney.LevelSequence;
import com.arhimag.games.snakejourney.Maps.MessageAchieveMap;
import com.arhimag.games.snakejourney.Maps.MessageClickMap;
import com.arhimag.games.snakejourney.Maps.MessageControlMap;
import com.arhimag.games.snakejourney.Maps.MessageEndCommingMap;
import com.arhimag.games.snakejourney.Maps.MessageFinalMap;
import com.arhimag.games.snakejourney.Maps.MessageMoreEggsMap;
import com.arhimag.games.snakejourney.Maps.MessageRateMap;
import com.arhimag.games.snakejourney.Settings;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.Achievements.GameAchievement;
import com.arhimag.games.snakejourney.Achievements.WinWithACCEL;
import com.arhimag.games.snakejourney.Achievements.WinWithLR;
import com.arhimag.games.snakejourney.Achievements.WinWithSLIDE;
import com.arhimag.games.snakejourney.Achievements.WinWithUDLR;
import com.arhimag.games.snakejourney.Levels.GameLevel;
import com.arhimag.games.snakejourney.Maps.EggsWindowMap;
import com.arhimag.games.snakejourney.Social.FacebookRunner;
import com.arhimag.games.snakejourney.Social.VKontakteListener;
import com.arhimag.games.snakejourney.framework.Game;
import com.arhimag.games.snakejourney.framework.Graphics;
import com.arhimag.games.snakejourney.framework.Input.KeyEvent;
import com.arhimag.games.snakejourney.framework.Input.TouchEvent;
import com.arhimag.games.snakejourney.framework.Point;
import com.arhimag.games.snakejourney.framework.Screen;
import com.arhimag.games.snakejourney.framework.impl.AndroidGame;


public class GameScreen extends Screen
{
	GameLevelDrawer levelDrawer;

	Point old[];
//	int level = 0;
    int clickCount = 0;
	int thisSessionLevel = 0;
    protected int viberTimer = 50;
    public static int vkontakteOperationState;
    public static int vkontaktePostingLevel;
    public static int vkontaktePostingEggs;
    public static String vkontaktePostingApp;


	public GameScreen(Game game)
	{
		super(game);

        AndroidGame andrGame = (AndroidGame) game;

        vkontaktePostingApp = andrGame.getApplicationContext().getPackageName();

		old = new Point[10];
		for(int i  = 0; i < 10 ; i++) old[i] = new Point();
		thisSessionLevel = 0;
		//levelDrawer = new GameLevelDrawer( new Level1( new Level1Map()), andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
		//levelDrawer = new GameLevelDrawer( new LevelLabirinth( new LevelLabirinthMap()), andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
		levelDrawer = new GameLevelDrawer( LevelSequence.createLevel(Settings.getLastReachedLevel()), andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
	}

	public GameScreen(Game game, GameLevel levelObject)
	{
		super(game);
		AndroidGame andrGame = (AndroidGame) game;

        vkontaktePostingApp = andrGame.getApplicationContext().getPackageName();

		old = new Point[10];
		for(int i  = 0; i < 10 ; i++) old[i] = new Point();
		
		levelDrawer = new GameLevelDrawer( levelObject, andrGame.getDisplayWidth() , andrGame.getDisplayHeight(), game.getGraphics());
		thisSessionLevel = 0;
		Settings.getControl();
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
					game.setScreen(new PauseMenuScreen(game, levelDrawer.getLevel(), LevelSequence.getLevelNum(levelDrawer.getLevel())));
			}
			
		}
		
		if( this.levelDrawer.getLevel().getEggsWindow())
		{
            updateAvalibleLevel(LevelSequence.getLevelNum(levelDrawer.getLevel()) + 1);
			Settings.updateEggs(LevelSequence.getLevelNum(levelDrawer.getLevel()), (levelDrawer.getLevel().isFirstEggActive()?1:0) + (levelDrawer.getLevel().isSecondEggActive()?1:0) + (levelDrawer.getLevel().isThirdEggActive()?1:0) );
			len = touchEvents.size();
			for(int i = 0; i < len; i++)
			{
				TouchEvent event = touchEvents.get(i);
				
				if(event.type == TouchEvent.TOUCH_UP)
				{
					if( levelDrawer.inBoundsEgg(event, EggsWindowMap.getMenuBounds()))
					{
						if( LevelSequence.getLevelNum(levelDrawer.getLevel()) < LevelSequence.getLevelsCount() - 1)
						{
							switch(Settings.getControl())
							{
								case 0:
									if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithSLIDE.class)].isAchievementReached())
										Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithSLIDE.class)].setStatus(100);
									break;
								case 1:
									if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithACCEL.class)].isAchievementReached())
										Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithACCEL.class)].setStatus(100);
									break;
								case 2:
									if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithLR.class)].isAchievementReached())
										Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithLR.class)].setStatus(100);
									break;
								case 4:
									if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithUDLR.class)].isAchievementReached())
										Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithUDLR.class)].setStatus(100);
									break;
							}
							//level++;
							thisSessionLevel++;
							updateAvalibleLevel(LevelSequence.getLevelNum(levelDrawer.getLevel()) + 1);
						}
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
                    if( levelDrawer.inBoundsEgg(event, EggsWindowMap.getVkBounds()))
                    {
                        vkontaktePostingLevel = LevelSequence.getLevelNum(levelDrawer.getLevel());
                        vkontaktePostingEggs = Settings.getEggs(vkontaktePostingLevel);
                        AndroidGame.faceBookActivityLink.runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                new AlertDialog.Builder(AndroidGame.faceBookActivityLink)
                                        .setMessage("Do you really want to POST it to VKontakte?")
                                        .setCancelable(true)
                                        .setNegativeButton("No", new DialogInterface.OnClickListener()
                                        {
                                            public void onClick(DialogInterface dialog, int id)
                                            {
                                                dialog.cancel();
                                            }
                                        })
                                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                (new Thread(VKontakteListener.threadAuth)).start();
                                            }
                                        })
                                        .show();
                            }
                        });

                    }
                    if( levelDrawer.inBoundsEgg(event, EggsWindowMap.getFbBounds()))
                    {
                        vkontaktePostingEggs = LevelSequence.getLevelNum(levelDrawer.getLevel());
                        vkontaktePostingEggs = Settings.getEggs(vkontaktePostingLevel);
//                        AndroidGame.faceBookActivityLink.runOnUiThread(FacebookRunner.fbRunner);
                        FacebookRunner.fbRunner.run();
                    }
				}
			}
		}
		
		if( Settings.getControl() == 1 )
		{
			if( game.getInput().getAccelY() < -2.5f )
            {
                if (this.levelDrawer.getLevel().goLEFT(this.levelDrawer.getLevel().getPlayerSnake()))
                    game.getAudio().vibrate(viberTimer);
            }
            else if( game.getInput().getAccelY() > 2.5f )
            {
                if (this.levelDrawer.getLevel().goRIGHT(this.levelDrawer.getLevel().getPlayerSnake()))
                    game.getAudio().vibrate((viberTimer));
            }
			if( game.getInput().getAccelX() < -2.5f )
            {
                if (this.levelDrawer.getLevel().goUP(this.levelDrawer.getLevel().getPlayerSnake()))
                    game.getAudio().vibrate(viberTimer);
            }
			else if( game.getInput().getAccelX() > 2.5f )
            {
                if ( this.levelDrawer.getLevel().goDOWN(this.levelDrawer.getLevel().getPlayerSnake()) )
                    game.getAudio().vibrate(viberTimer);
            }
		}
		
		if( this.levelDrawer.getLevel().nextLevel() )
		{
		    if( (LevelSequence.getLevelNum(levelDrawer.getLevel()) % 5 == 0 || LevelSequence.getLevelNum(levelDrawer.getLevel()) == LevelSequence.getLevelsCount() - 1 )  && LevelSequence.getLevelNum(levelDrawer.getLevel()) > 0  )
            {
                if( !levelDrawer.getLevel().getMessageScreen() )
                {
                    if (LevelSequence.getLevelNum(levelDrawer.getLevel()) == 5)
                    {
                        levelDrawer.getLevel().setMessageScreen(true);
                        levelDrawer.getLevel().setMessageScreenMap( new MessageAchieveMap());
                    } else if (LevelSequence.getLevelNum(levelDrawer.getLevel()) == 10)
                    {
                        levelDrawer.getLevel().setMessageScreen(true);
                        levelDrawer.getLevel().setMessageScreenMap( new MessageRateMap() );
                    } else if (LevelSequence.getLevelNum(levelDrawer.getLevel()) == 15)
                    {
                        levelDrawer.getLevel().setMessageScreen(true);
                        levelDrawer.getLevel().setMessageScreenMap( new MessageControlMap() );
                    } else if (LevelSequence.getLevelNum(levelDrawer.getLevel()) == 20)
                    {
                        levelDrawer.getLevel().setMessageScreen(true);
                        levelDrawer.getLevel().setMessageScreenMap( new MessageClickMap() );
                    } else if (LevelSequence.getLevelNum(levelDrawer.getLevel()) == 25)
                    {
                        levelDrawer.getLevel().setMessageScreen(true);
                        levelDrawer.getLevel().setMessageScreenMap( new MessageEndCommingMap() );
                    } else if (LevelSequence.getLevelNum(levelDrawer.getLevel()) == 30)
                    {
                        levelDrawer.getLevel().setMessageScreen(true);
                        levelDrawer.getLevel().setMessageScreenMap( new MessageMoreEggsMap() );
                    } else if (LevelSequence.getLevelNum(levelDrawer.getLevel()) == LevelSequence.getLevelsCount() - 1)
                    {
                        clickCount = 0;
                        levelDrawer.getLevel().setMessageScreen(true);
                        levelDrawer.getLevel().setMessageScreenMap( new MessageFinalMap() );
                    }
                }
                else
                {
                    len = touchEvents.size();
                    for(int i = 0; i < len; i++)
                    {
                        TouchEvent event = touchEvents.get(i);

                        if(event.type == TouchEvent.TOUCH_UP)
                        {
                            if (levelDrawer.getLevel().getMessageScreenMap() instanceof MessageRateMap && levelDrawer.inBoundsMessage(event, MessageRateMap.getYesBounds()))
                            {
                                final String appPackageName = ((Activity)game).getPackageName(); // getPackageName() from Context or Activity object
                                try {
                                    ((Activity)game).startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    ((Activity)game).startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                }
                            } else if (levelDrawer.getLevel().getMessageScreenMap() instanceof MessageRateMap && levelDrawer.inBoundsMessage(event, MessageRateMap.getNoBounds()))
                            {
                                levelDrawer.getLevel().setMessageScreen(false);
                                levelDrawer.getLevel().setMessageScreenMap( null );
                                if( LevelSequence.getLevelNum(levelDrawer.getLevel()) < LevelSequence.getLevelsCount() - 1)
                                {
                                    switch(Settings.getControl())
                                    {
                                        case 0:
                                            if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithSLIDE.class)].isAchievementReached())
                                                Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithSLIDE.class)].setStatus(100);
                                            break;
                                        case 1:
                                            if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithACCEL.class)].isAchievementReached())
                                                Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithACCEL.class)].setStatus(100);
                                            break;
                                        case 2:
                                            if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithLR.class)].isAchievementReached())
                                                Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithLR.class)].setStatus(100);
                                            break;
                                        case 4:
                                            if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithUDLR.class)].isAchievementReached())
                                                Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithUDLR.class)].setStatus(100);
                                            break;
                                    }
                                    //level++;
                                    thisSessionLevel++;
                                    updateAvalibleLevel(LevelSequence.getLevelNum(levelDrawer.getLevel()) + 1);
                                    levelDrawer.setLevel(LevelSequence.createNextLevel(levelDrawer.getLevel()));
                                    levelDrawer.getLevel().setAqua(false);
                                }
                                else if ( levelDrawer.getLevel().getMessageScreenMap() instanceof MessageFinalMap  && clickCount < 2 )
                                {
                                    clickCount++;
                                }
                                else if ( !(levelDrawer.getLevel().getMessageScreenMap() instanceof MessageRateMap ) )
                                {
                                    levelDrawer.getLevel().setMessageScreen(false);
                                    levelDrawer.getLevel().setMessageScreenMap( null );
                                    game.setScreen(new MainMenuScreen(game));
                                }
                            }
                            else
                            {
                                levelDrawer.getLevel().setMessageScreen(false);
                                levelDrawer.getLevel().setMessageScreenMap( null );
                                if( LevelSequence.getLevelNum(levelDrawer.getLevel()) < LevelSequence.getLevelsCount() - 1)
                                {
                                    switch(Settings.getControl())
                                    {
                                        case 0:
                                            if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithSLIDE.class)].isAchievementReached())
                                                Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithSLIDE.class)].setStatus(100);
                                            break;
                                        case 1:
                                            if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithACCEL.class)].isAchievementReached())
                                                Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithACCEL.class)].setStatus(100);
                                            break;
                                        case 2:
                                            if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithLR.class)].isAchievementReached())
                                                Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithLR.class)].setStatus(100);
                                            break;
                                        case 4:
                                            if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithUDLR.class)].isAchievementReached())
                                                Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithUDLR.class)].setStatus(100);
                                            break;
                                    }
                                    //level++;
                                    thisSessionLevel++;
                                    updateAvalibleLevel(LevelSequence.getLevelNum(levelDrawer.getLevel()) + 1);
                                    levelDrawer.setLevel(LevelSequence.createNextLevel(levelDrawer.getLevel()));
                                    levelDrawer.getLevel().setAqua(false);
                                }
                                else
                                {
                                    levelDrawer.getLevel().setMessageScreen(false);
                                    levelDrawer.getLevel().setMessageScreenMap( null );
                                    game.setScreen(new MainMenuScreen(game));
                                }
                            }
                        }
                    }

                }
            }
            else if( LevelSequence.getLevelNum(levelDrawer.getLevel()) < LevelSequence.getLevelsCount() - 1)
			{
				switch(Settings.getControl())
				{
					case 0:
						if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithSLIDE.class)].isAchievementReached())
							Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithSLIDE.class)].setStatus(100);
						break;
					case 1:
						if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithACCEL.class)].isAchievementReached())
							Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithACCEL.class)].setStatus(100);
						break;
					case 2:
						if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithLR.class)].isAchievementReached())
							Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithLR.class)].setStatus(100);
						break;
					case 4:
						if(!Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithUDLR.class)].isAchievementReached())
							Settings.achievementsStatus[GameAchievement.getAchievementId(WinWithUDLR.class)].setStatus(100);
						break;
				}
				//level++;
				thisSessionLevel++;
				updateAvalibleLevel(LevelSequence.getLevelNum(levelDrawer.getLevel()) + 1);
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
			
				if(  Settings.getControl() == 0)
				{
					if(event.type == TouchEvent.TOUCH_UP)
					{
						if( java.lang.Math.abs(event.x - old[event.pointer].x) > java.lang.Math.abs(event.y - old[event.pointer].y) )
						{
							if( event.x - old[event.pointer].x < 0 )
                            {
                                if ( this.levelDrawer.getLevel().goLEFT(this.levelDrawer.getLevel().getPlayerSnake()) )
                                    game.getAudio().vibrate(viberTimer);
                            }
                            else
                            {
                                if ( this.levelDrawer.getLevel().goRIGHT(this.levelDrawer.getLevel().getPlayerSnake()) )
                                    game.getAudio().vibrate(viberTimer);
                            }
						}
						else
						{
							if( event.y - old[event.pointer].y < 0 )
                            {
                                if ( this.levelDrawer.getLevel().goUP(this.levelDrawer.getLevel().getPlayerSnake()) )
                                    game.getAudio().vibrate(viberTimer);
                            }
							else
                            {
                                if ( this.levelDrawer.getLevel().goDOWN(this.levelDrawer.getLevel().getPlayerSnake()) )
                                    game.getAudio().vibrate(viberTimer);
                            }
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
				else if(  Settings.getControl() == 2)
				{
					if(event.type == TouchEvent.TOUCH_UP)
					{
						if( event.x > ((AndroidGame)game).getDisplayWidth() / 2 )
                        {
                            if ( levelDrawer.getLevel().turnRight(this.levelDrawer.getLevel().getPlayerSnake()) )
                                game.getAudio().vibrate(viberTimer);
                        }
						else
                        {
                            if ( levelDrawer.getLevel().turnLeft(this.levelDrawer.getLevel().getPlayerSnake()) )
                                game.getAudio().vibrate(viberTimer);
                        }
					}
				}
				else if(  Settings.getControl() == 3)
				{
					if( event.type == TouchEvent.TOUCH_UP )
					{
						if( event.x < ((AndroidGame)game).getDisplayWidth() / 3 )
                        {
                            if ( levelDrawer.getLevel().goLEFT(this.levelDrawer.getLevel().getPlayerSnake()) )
                                game.getAudio().vibrate(viberTimer);
                        }
						else if( event.x > 2 *((AndroidGame)game).getDisplayWidth() / 3 )
                        {
                            if ( levelDrawer.getLevel().goRIGHT(this.levelDrawer.getLevel().getPlayerSnake()) )
                                game.getAudio().vibrate(viberTimer);
                        }
						else if( event.y > ((AndroidGame)game).getDisplayHeight() /2 )
                        {
                            if ( levelDrawer.getLevel().goDOWN(this.levelDrawer.getLevel().getPlayerSnake()) )
                                game.getAudio().vibrate(viberTimer);
                        }
						else
                        {
                            if ( levelDrawer.getLevel().goUP(this.levelDrawer.getLevel().getPlayerSnake()) )
                                game.getAudio().vibrate(viberTimer);
                        }
					}
						
				}
				else if(  Settings.getControl() == 4)
				{
					if( event.type == TouchEvent.TOUCH_UP )
					{
						if( event.y < ((AndroidGame)game).getDisplayHeight() / 3 )
                        {
                            if ( levelDrawer.getLevel().goUP(this.levelDrawer.getLevel().getPlayerSnake()) )
                                game.getAudio().vibrate(viberTimer);
                        }
						else if( event.y > 2 *((AndroidGame)game).getDisplayHeight() / 3 )
                        {
                            if ( levelDrawer.getLevel().goDOWN(this.levelDrawer.getLevel().getPlayerSnake()) )
                                game.getAudio().vibrate(viberTimer);
                        }
						else if( event.x > ((AndroidGame)game).getDisplayWidth() /2 )
                        {
                            if ( levelDrawer.getLevel().goRIGHT(this.levelDrawer.getLevel().getPlayerSnake()) )
                                game.getAudio().vibrate(viberTimer);
                        }
						else
                        {
                            if ( levelDrawer.getLevel().goLEFT(this.levelDrawer.getLevel().getPlayerSnake()) )
                                game.getAudio().vibrate(viberTimer);
                        }
					}
		
				}
				else if(  Settings.getControl() == 5)
				{
					if( event.type == TouchEvent.TOUCH_UP )
					{
						if(( event.y > ((AndroidGame)game).getDisplayHeight() / 2 ) && ( event.x < ((AndroidGame)game).getDisplayWidth() /2 ))
							if( levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.UP || levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.DOWN )
                            {
                                if ( levelDrawer.getLevel().goLEFT(this.levelDrawer.getLevel().getPlayerSnake()) )
                                    game.getAudio().vibrate(viberTimer);
                            }
							else
                            {
                                if ( levelDrawer.getLevel().goDOWN(this.levelDrawer.getLevel().getPlayerSnake()) )
                                    game.getAudio().vibrate(viberTimer);
                            }
						else if(( event.y < ((AndroidGame)game).getDisplayHeight() / 2 ) && ( event.x > ((AndroidGame)game).getDisplayWidth() /2 ))
							if( levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.UP || levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.DOWN )
                            {
                                if ( levelDrawer.getLevel().goRIGHT(this.levelDrawer.getLevel().getPlayerSnake()) )
                                    game.getAudio().vibrate(viberTimer);
                            }
							else
                            {
                                if ( levelDrawer.getLevel().goUP(this.levelDrawer.getLevel().getPlayerSnake()) )
                                    game.getAudio().vibrate(viberTimer);
                            }
					}
				}
				else if(  Settings.getControl() == 6)
				{
					if( event.type == TouchEvent.TOUCH_UP )
					{
						if(( event.y < ((AndroidGame)game).getDisplayHeight() / 2 ) && ( event.x < ((AndroidGame)game).getDisplayWidth() /2 ))
							if( levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.UP || levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.DOWN )
                            {
                                if ( levelDrawer.getLevel().goLEFT(this.levelDrawer.getLevel().getPlayerSnake()) )
                                    game.getAudio().vibrate(viberTimer);
                            }
							else
                            {
                                if ( levelDrawer.getLevel().goUP(this.levelDrawer.getLevel().getPlayerSnake()) )
                                    game.getAudio().vibrate(viberTimer);
                            }
						else if(( event.y > ((AndroidGame)game).getDisplayHeight() / 2 ) && ( event.x > ((AndroidGame)game).getDisplayWidth() /2 ))
								if( levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.UP || levelDrawer.getLevel().getSnake(levelDrawer.getLevel().getPlayerSnake()).direction == Snake.DOWN )
                                {
                                    if ( levelDrawer.getLevel().goRIGHT(this.levelDrawer.getLevel().getPlayerSnake()) )
                                        game.getAudio().vibrate(viberTimer);
                                }
								else
                                {
                                    if ( levelDrawer.getLevel().goDOWN(this.levelDrawer.getLevel().getPlayerSnake()) )
                                        game.getAudio().vibrate(viberTimer);
                                }
					}
				}
				else if(  Settings.getControl() == 7)
				{
					if( event.type == TouchEvent.TOUCH_UP )
					{
						if( old[event.pointer].x != 0 || old[event.pointer].y != 0 )
							if( old[event.pointer].x < ((AndroidGame)game).getDisplayWidth() / 2 )
								if( old[event.pointer].y > event.y )
                                {
                                    if ( levelDrawer.getLevel().goUP(levelDrawer.getLevel().getPlayerSnake()) )
                                        game.getAudio().vibrate(viberTimer);
                                }
                                else
                                {
                                    if ( levelDrawer.getLevel().goDOWN(levelDrawer.getLevel().getPlayerSnake()) )
                                        game.getAudio().vibrate(viberTimer);
                                }
							else
								if( old[event.pointer].x > event.x )
                                {
                                    if ( levelDrawer.getLevel().goLEFT(levelDrawer.getLevel().getPlayerSnake()) )
                                        game.getAudio().vibrate(viberTimer);
                                }
								else
                                {
                                    if ( levelDrawer.getLevel().goRIGHT(levelDrawer.getLevel().getPlayerSnake()) )
                                        game.getAudio().vibrate(viberTimer);
                                }
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
		levelDrawer.draw();
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
