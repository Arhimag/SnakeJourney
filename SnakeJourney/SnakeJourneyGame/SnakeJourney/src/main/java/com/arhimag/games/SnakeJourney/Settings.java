package com.arhimag.games.SnakeJourney;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.util.Log;

import com.arhimag.games.SnakeJourney.Achievements.CelebrateChristmas;
import com.arhimag.games.SnakeJourney.Achievements.Earn100Eggs;
import com.arhimag.games.SnakeJourney.Achievements.FindVisualDoj;
import com.arhimag.games.SnakeJourney.Achievements.FinishGame;
import com.arhimag.games.SnakeJourney.Achievements.GameAchievement;
import com.arhimag.games.SnakeJourney.Achievements.Get30WithAccel;
import com.arhimag.games.SnakeJourney.Achievements.MeetAI;
import com.arhimag.games.SnakeJourney.Achievements.ReachLevel10;
import com.arhimag.games.SnakeJourney.Achievements.ReachLevel20;
import com.arhimag.games.SnakeJourney.Achievements.ReachLevel30;
import com.arhimag.games.SnakeJourney.Achievements.ReachLevel5;
import com.arhimag.games.SnakeJourney.Achievements.TryAllControls;
import com.arhimag.games.SnakeJourney.Levels.LevelChristmass;
import com.arhimag.games.SnakeJourney.Levels.LevelWelcomeToVisualDoj;
import com.arhimag.games.SnakeJourney.Levels.MeetAILevel;
import com.arhimag.games.SnakeJourney.framework.FileIO;
import com.vk.sdk.VKScope;

public class Settings
{
	/* �������� ����� ��������:
	 * 1.	������ �����.(��������, � ������ ������������ �������� � 
	 * 		������� � ������ ���� ���������� ����� �������� � ���������)
	 * 2.	������� �� ����
	 * 3.	Id ������ ����������
	 * 4.	��������� ����������� �������.
	 * 5.	���������� ������� ��� ������� ���� ���������� ��� ����.
	 * 6.	���� ������ 1
	 * 7.	���� ������ 2
	 * 8.	...
	 * 9. 	���� ���������� ������ � ������� ���� ���������� ��� ����.
	 * 10.	��������� ������ ������
	 * 11.	��������� ������ ������
	 * 12.	...
	 * 13. 	��������� ��������� ������.
	 * 14.	-1 �� ������.(������� � ������� �������� ��� � ������ �����������
	 * 		���, ��� ���������� � ����� - �������� ������������� ����������� 
	 * 		������ � ������� �� ������������ �� �������. ���������� �� ������
	 * 		����� ���� �������� �������������.)
	 */
	private static boolean soundEnabled = true;
	private static int control = 2;
	private static final int programmVersion = 44;
	private static int currentVersion = programmVersion;
	private static int lastReachedLevel = 32;
//	private static int isFutureMovement = 1;
	private static int eggsCount = 0;
	private static int levelsEggs[];
	private static int evenSnakeColor = 0xffaaaaaa;
	private static int oddSnakeColor = 0xffaaaaaa - 0x002f2f2f;
	private static final int CODE_SOUND = 0;
	private static final int CODE_CONTROL = 1;
	private static final int CODE_PROGRAMMVERSION = 2;
	private static final int CODE_LASTREACHEDLEVEL = 3;
	private static final int CODE_EGG_VALUE = 4;
	private static final int CODE_ACHIEVEMENT = 5;
	private static final int CODE_EVEN_SNAKE = 6;
	private static final int CODE_ODD_SNAKE = 7;
    private static final int CODE_VKONTAKTE_TOKEN = 8;
    public static final int URDL_CONTROL =  4;
    public static final int SLIDE_CONTROL = 0;
    public static final int ACCEL_CONTROL = 1;
    public static final int LEFTRIGHT_CONTROL = 2;

    public static final String vkontakteTokenFileName = ".SnakeJourneyVKToken";
    public static final String vkontakteApplicationId = "4717821";
    public static final String[] vkontaktePrivates = new String[] {
        VKScope.FRIENDS,
        VKScope.WALL,
        VKScope.PHOTOS,
        VKScope.NOHTTPS
    };

	public static GameAchievement[] achievementsStatus;
	
	public static void load(FileIO  files)
	{
		BufferedReader in = null;
		try
		{
			in = new BufferedReader( new InputStreamReader( files.readFile(".snakejourney")));
			setToDefault();
			while(readBlock(in));
		}
		catch (IOException e)
		{
		    setToDefault();
		}
		finally
		{
			try
			{
				if( in != null )
					in.close();
			}
			catch( IOException e)
			{
			    setToDefault();
			}
		}
	}

	public static void save( FileIO files)
	{
		BufferedWriter out = null;
		try
		{
			out = new BufferedWriter( new OutputStreamWriter(files.writeFile(".snakejourney")));
			
			out.write(Integer.toString(CODE_PROGRAMMVERSION));
			out.newLine();
			out.write(Integer.toString(programmVersion));
			out.newLine();
			out.write(Integer.toString(CODE_SOUND));
			out.newLine();
			out.write(Boolean.toString(soundEnabled));
			out.newLine();
			out.write(Integer.toString(CODE_CONTROL));
			out.newLine();
			out.write(Integer.toString(control));
			out.newLine();
			out.write(Integer.toString(CODE_LASTREACHEDLEVEL));
			out.newLine();
			out.write(Integer.toString(lastReachedLevel));
			out.newLine();
			out.write(Integer.toString(CODE_EVEN_SNAKE));
			out.newLine();
			out.write(Integer.toString(evenSnakeColor));
			out.newLine();
			out.write(Integer.toString(CODE_ODD_SNAKE));
			out.newLine();
			out.write(Integer.toString(oddSnakeColor));
			out.newLine();
            for( int i = 0; i < levelsEggs.length; i ++ )
			{
				out.write(Integer.toString(CODE_EGG_VALUE));
				out.newLine();
				out.write(Integer.toString(i));
				out.newLine();
				out.write(Integer.toString(levelsEggs[i]));
				out.newLine();
			}
			for( int i = 0; i < achievementsStatus.length; i ++ )
			{
				out.write(Integer.toString(CODE_ACHIEVEMENT));
				out.newLine();
				out.write(Integer.toString(achievementsStatus[i].getId()));
				out.newLine();
				out.write(Integer.toString(achievementsStatus[i].getStatus()));
				out.newLine();
			}
		}
		catch(IOException e)
		{
		}
		finally
		{
			try
			{
				if( out != null)
					out.close();
			}
			catch(IOException e)
			{
			}
		}
	}
	
	
	public static int getControl()
	{
		return control;
	}
	
	public static void setControl(int newControl)
	{
		if( control == 1 && newControl != 1 && !achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].isAchievementReached())
			achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].setStatus(-1);			
		if( control != 1 && newControl == 1 && !achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].isAchievementReached())
			achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].setStatus(0);
		switch(newControl)
		{
			case 0:
				if(!achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].isAchievementReached() &&  achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].getStatus() % 2 != 0)
					achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].setStatus(achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].getStatus() *  2);
				break;
			case 1:
				if(!achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].isAchievementReached() &&  achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].getStatus() % 3 != 0)
					achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].setStatus(achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].getStatus() *  3);
				break;
			case 4:
				if(!achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].isAchievementReached() &&  achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].getStatus() % 5 != 0)
					achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].setStatus(achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].getStatus() *  5);
				break;
			case 2:
				if(!achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].isAchievementReached() &&  achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].getStatus() % 7 != 0)
					achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].setStatus(achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].getStatus() *  7);
				break;
		}

		if(!achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].isAchievementReached() &&  achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].getStatus() % 7*5*3*2 == 0)
			achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].setStatus(100);

		control = newControl;
	}
	
	public static boolean isSoundEnabled()
	{
		return soundEnabled;
	}
	public static void setSoundEnabled( boolean newSoundEnabled )
	{
		soundEnabled = newSoundEnabled;
	}
	
	public static int getLastReachedLevel()
	{
		return lastReachedLevel;
	}


    public static void setLastReachedLevel( int newLastReachedLevel )
	{
        Log.d("SnakeJourney", Integer.toString(newLastReachedLevel));
        if ( newLastReachedLevel > lastReachedLevel )
        {
            lastReachedLevel = newLastReachedLevel;
            if (lastReachedLevel >= 4)
                achievementsStatus[GameAchievement.getAchievementId(ReachLevel5.class)].setStatus(100);
            if (lastReachedLevel >= 9)
                achievementsStatus[GameAchievement.getAchievementId(ReachLevel10.class)].setStatus(100);
            if (lastReachedLevel >= 19)
                achievementsStatus[GameAchievement.getAchievementId(ReachLevel20.class)].setStatus(100);
            if (lastReachedLevel >= 29)
                achievementsStatus[GameAchievement.getAchievementId(ReachLevel30.class)].setStatus(100);
            if (lastReachedLevel >= LevelSequence.getLevelTypeNum(LevelChristmass.class))
                achievementsStatus[GameAchievement.getAchievementId(CelebrateChristmas.class)].setStatus(100);
            if (lastReachedLevel == LevelSequence.getLevelsCount() - 1)
                achievementsStatus[GameAchievement.getAchievementId(FinishGame.class)].setStatus(100);
            if (lastReachedLevel >= LevelSequence.getLevelTypeNum(MeetAILevel.class))
                achievementsStatus[GameAchievement.getAchievementId(MeetAI.class)].setStatus(100);
            if (lastReachedLevel >= LevelSequence.getLevelTypeNum(LevelWelcomeToVisualDoj.class))
                achievementsStatus[GameAchievement.getAchievementId(FindVisualDoj.class)].setStatus(100);
        }
	}
	
	public static int getIsFutureMovement()
	{
		//if( control == 2)
		//	return 1;
		//else
			return 0;
	}
	
	public static void updateEggs( int level, int Eggs)
	{
		if( levelsEggs.length > level && Eggs > levelsEggs[level])
		{
			eggsCount -= levelsEggs[level];
			levelsEggs[level] = Eggs;
			eggsCount += Eggs;
			if( eggsCount >= 100 )
				achievementsStatus[GameAchievement.getAchievementId(Earn100Eggs.class)].setStatus(100);
		}
	}
	
	public static int getEggs( int level )
	{
		if( levelsEggs.length > level )
			return levelsEggs[level];
		else 
			return 0;
	}
	public static  void setFutureMovement()
	{
		
	}

	public static GameAchievement getAchievement( int i )
	{
		if( i >= 0 && i <= achievementsStatus.length )
			return achievementsStatus[i];
		else
			return null;
	}
	
	public static int getAchievementsCount()
	{
		return achievementsStatus.length;
	}
	
	
	public static int getSnakeEvenColor()
	{
		return evenSnakeColor;
	}
	
	public static int getSnakeOddColor()
	{
		return oddSnakeColor;
	}

	public static void setSnakeEvenColor(int color)
	{
		evenSnakeColor = color;
	}
	
	public static void setSnakeOddColor(int color)
	{
		oddSnakeColor = color;
	}
	
	private static void setToDefault()
	{
		soundEnabled = true;
		control = 4;
		currentVersion = programmVersion; 
		lastReachedLevel = 32;
		evenSnakeColor = 0xffaaaaaa;
		oddSnakeColor = 0xffaaaaaa - 0x002f2f2f;
		levelsEggs = new int[LevelSequence.getLevelsCount()];
		eggsCount = 0;
		for( int i = 0; i < levelsEggs.length; i++)
			levelsEggs[i] = 0;
		achievementsStatus = new GameAchievement[ GameAchievement.achievementsList.length];
		for( int i = 0; i < achievementsStatus.length; i++ )
			try
			{
				achievementsStatus[i] = (GameAchievement) GameAchievement.achievementsList[i].getConstructor((Class<?>[])null).newInstance((Object[])null);
			}
			catch( Exception ex )
			{
				Log.e("Settings", "Can not init achievement " + i);
			}
		achievementsStatus[GameAchievement.getAchievementId(Get30WithAccel.class)].setStatus(-1);
		achievementsStatus[GameAchievement.getAchievementId(TryAllControls.class)].setStatus(1);
	}
	
	/* ����� ������ ���������� � �������� �������� */
	private static boolean readBlock(BufferedReader in)
	{
		try 
		{
			int code = Integer.parseInt(in.readLine());
			switch(code)
			{
				case CODE_SOUND:
					soundEnabled = Boolean.parseBoolean(in.readLine());
					return true;
				case CODE_CONTROL:
					Settings.setControl(Integer.parseInt(in.readLine()));
					return true;
				case CODE_PROGRAMMVERSION:
					currentVersion = Integer.parseInt( in.readLine() );
					if( currentVersion != programmVersion )
					{
						setToDefault();
						return false;
					}
					else
						return true;
				case CODE_LASTREACHEDLEVEL:
					lastReachedLevel = Integer.parseInt(in.readLine());
					return true;
				case CODE_EGG_VALUE:
					int levelId = Integer.parseInt(in.readLine());
					eggsCount -= levelsEggs[levelId];
					levelsEggs[levelId] = java.lang.Math.max(java.lang.Math.min(Integer.parseInt(in.readLine()),3),0);
					eggsCount += levelsEggs[levelId];
					return true;
				case CODE_ACHIEVEMENT:
					int achievementId = Integer.parseInt(in.readLine());
					achievementsStatus[achievementId].setStatusSilient(Integer.parseInt(in.readLine()));
					return true;
				case CODE_EVEN_SNAKE:
					setSnakeEvenColor(Integer.parseInt(in.readLine()));
					return true;
				case CODE_ODD_SNAKE:
					setSnakeOddColor(Integer.parseInt(in.readLine()));
					return true;
			}
		}
		catch (IOException e)
		{
			return false;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		return false;
	}
}
