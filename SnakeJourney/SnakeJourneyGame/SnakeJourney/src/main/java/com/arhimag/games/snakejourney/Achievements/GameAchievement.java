package com.arhimag.games.snakejourney.Achievements;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import android.util.Log;

import com.arhimag.games.snakejourney.Assets;
import com.arhimag.games.snakejourney.Settings;
import com.arhimag.games.snakejourney.framework.impl.AndroidGame;
import com.google.android.gms.games.Games;


public class GameAchievement {

	private int id; //���������� id ������.
	private int status; // ������� ���������� ������ � ��������� �� ������ �� 100
	protected String text; // ����� ������ �������������� � ���� ����� ������. �� ����� ������������ �� ������ ������ ������� ����� � �������.
	protected int[][] listIcon; // ������ ������. ���� ����� ������. ������������ � ������ ������
	protected int[][] gameIcon; // ������ ������. ����� ������. ���������� � ���� ��� ���������� ������.
    protected String PLAY_ACHIEVEMENT_ID;
	public static final int listIconWidth = 5; //������ ������ ���������
	public static final int listIconHeight = 5; //������ ������ ���������
	public static final int gameIconWidth = 9; //������ ������ ���������
	public static final int gameIconHeight = 9; //������ ������ ���������
	public static int lastReachedAchievement = -1;
	public static long reachAchievementTime = -1;
	public static long showAchievementTime = 1000000000;
	public int color1 = 0xFFFFFFFF;
	public int color2 = 0xFFFFFFFF;
	protected int borderColor = 0xFFFFD700;

	public static final Class<?>[] achievementsList = {TryAllControls.class, WinWithACCEL.class, WinWithLR.class, WinWithSLIDE.class, WinWithUDLR.class, ReachLevel5.class, ReachLevel10.class, ReachLevel20.class, ReachLevel30.class, UseTeleport.class, MeetAI.class, CelebrateChristmas.class, Earn100Eggs.class, GetLength40.class, FinishGame.class,Get30WithAccel.class, FindVisualDoj.class}; // �����-������ ������. ����� � ���� ������� � �������� id ������.

	/* ������� ���������� id ������ �� �� ������ */
	public static final int getAchievementId( Class<?> target )
	{
		for( int i = 0; i < achievementsList.length; i++ )
			if( achievementsList[i] == target )
				return i;
		return -1;
	}
	
	/* ������� ��������� ������ �� ����� ��������. � ����� ���� ������ ������
	 * ���������� ������ � id ������ -1. ����������� ������.
	 * ������� ������ ��������� �� 2 ������. id, status
	 */
	public static GameAchievement readAchievement(BufferedReader in)
	{
		int achievementId;
		int rStatus;
		GameAchievement result = null;
		try
		{
			achievementId = Integer.parseInt(in.readLine());
			rStatus = Integer.parseInt(in.readLine());
		}
		catch( Exception ex )
		{
			achievementId = -1;
			rStatus = -1;
			Log.d("Achievement", "can not read id");
		}
		
		if(achievementId == -1) 
			return null;
		else
			if(achievementId >= achievementsList.length )
				return null;
			else
			{
				try
				{
					result = (GameAchievement) achievementsList[achievementId].getConstructor((Class<?>[])null).newInstance((Object[])null);
					result.setStatus(rStatus);
				}
				catch(Exception ex)
				{
					Log.d("Achievement", "can not get constructor " + ex.getMessage());
				}
			}
		return result;
	}

	/* �������� ������� ������ �� �������
	 * � ���������� ������� ���������� ������ ������������������ ������
	 * ���������� � ������ ������ �����
	 */
	
	public static GameAchievement[] readAchievements(BufferedReader in)
	{
		GameAchievement[] result = new GameAchievement [GameAchievement.achievementsList.length];
		GameAchievement temp;
		int i = 0;
		
		// ��������� ��������� ������ �� �����
		while( i < result.length && ((result[i] = readAchievement(in) ) != null ) )
			if( result[i].getId() >= GameAchievement.achievementsList.length || result[i].getId() < 0 )
				result = null;
		
		// ������������� ������ �� ���� �����.
		for( i = 0; i < result.length; i++ )
			while(result[i] != null && result[i].getId() != i)
			{
				temp = result[i];
				result[i] = result[result[i].getId()];
				result[result[i].getId()] = temp;
			}
		
		// ������� �������� ������, ������� �� ���� � �����
		for( i = 0; i < result.length; i++ )
			if( result[i] == null )
				try
				{
					result[i] = (GameAchievement) achievementsList[i].getConstructor((Class<?>[])null).newInstance((Object[])null);
				}
				catch (Exception ex)
				{
					Log.d("Achievement", "can not get constructor " + ex.getMessage());
				}
		
		return result;
	}		
		
	
	public static void writeAchievements(GameAchievement[] achievements, BufferedWriter out)
	{
		for( int i = 0; i < achievements.length; i++ )
		{
			try
			{
				out.write(Integer.toString(achievements[i].getId()));
				out.newLine();
				out.write(Integer.toString(achievements[i].getStatus()));
				out.newLine();
			}
			catch(Exception ex)
			{
				Log.d("Achievement", "Can not write achievement. " + i + ex.getMessage() );
			}
		}
		try
		{
			out.write(Integer.toString(-1));
			out.newLine();
			out.write(Integer.toString(-1));
			out.newLine();
		}
		catch(Exception ex)
		{
			Log.d("Achievement", "Can not write achievement -1. " + ex.getMessage() );
		}
	}

	protected void initGameIcon()
	{
		gameIcon = new int[][] {
				{0xFF000000, borderColor, borderColor, borderColor, borderColor, borderColor, borderColor, borderColor, 0xFF000000},
				{borderColor, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, borderColor },
				{borderColor, 0xFF000000, color1, color2, color1, color2, 0xFFFFFFFF, 0xFF000000, borderColor},
				{borderColor, 0xFF000000, color2, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, borderColor},
				{borderColor, 0xFF000000, color1, 0xFF000000, color1, color2, color1, 0xFF000000, borderColor},
				{borderColor, 0xFF000000, color2, 0xFF000000, 0xFF000000, 0xFF000000, color2,0xFF000000, borderColor},
				{borderColor, 0xFF000000, color1, color2, color1, color2, color1, 0xFF000000, borderColor},
				{borderColor, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, borderColor },
				{0xFF000000, borderColor, borderColor, borderColor, borderColor, borderColor, borderColor, borderColor, 0xFF000000}
		};
		
	}
	
	protected void initListIcon()
	{
		listIcon = new int[][] {
				{color1, color2, color1, color2, 0xFFFFFFFF},
				{color2, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000},
				{color1, color2, color1, color2, color1},
				{0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, color2},
				{color1, color2, color1, color2, color1},
			};
	}
		
	public GameAchievement()
	{
		text = "";
	
		id = GameAchievement.getAchievementId(this.getClass());
		status = 0;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void setStatus( int newStatus )
	{	
		if( status == 100 ) return;
		status = newStatus;
		if ( newStatus == 100 )
		{
            if( AndroidGame.mGoogleApiClient != null && AndroidGame.mGoogleApiClient.isConnected() )
            {
                Games.Achievements.unlock(AndroidGame.mGoogleApiClient, PLAY_ACHIEVEMENT_ID);
            }
            if (Settings.isSoundEnabled())
                Assets.getAchievment.play(1);
			lastReachedAchievement = id;
			reachAchievementTime = System.nanoTime();
		}
	}
	
	public void setStatusSilient( int newStatus )
	{
		status = newStatus;
        if ( newStatus == 100 )
        {
            if( AndroidGame.mGoogleApiClient != null && AndroidGame.mGoogleApiClient.isConnected() )
            {
                Games.Achievements.unlock(AndroidGame.mGoogleApiClient, PLAY_ACHIEVEMENT_ID);
            }
        }
	}
	
	public int getId()
	{
		return id;
	}
	
	public boolean  isAchievementReached()
	{
		return status >= 100;
	}
	
	public void increaseStatus()
	{
	}
	
	public String getText()
	{
		return text;
	}
	
	public int getListIcon(int x, int y)
	{
		return listIcon[y][x];
	}
	
	public int getGameIcon(int x, int y)
	{
		return gameIcon[y][x];
	}
	
	public int[][] getListIcon()
	{
		return listIcon;
	}
}