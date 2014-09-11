package com.arhimag.games.omnomnom.Achievements;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import android.util.Log;

public class GameAchievement {

	private int id; //���������� id ������.
	private int status; // ������� ���������� ������ � ��������� �� ������ �� 100
	protected int[][] text; // ����� ������ �������������� � ���� ����� ������. �� ����� ������������ �� ������ ������ ������� ����� � �������.
	protected int[][] listIcon; // ������ ������. ���� ����� ������. ������������ � ������ ������
	protected int[][] gameIcon; // ������ ������. ����� ������. ���������� � ���� ��� ���������� ������.
	public static final int listIconWidth = 5; //������ ������ ���������
	public static final int listIconHeight = 10; //������ ������ ���������
	public static final int gameIconWidth = 5; //������ ������ ���������
	public static final int gameIconHeight = 5; //������ ������ ���������
	public static final int textWidth = 46; // ������ ����� � �������
	public static final int textHeight = 10; // ������ ����� � �������
	

	public static final Class<?>[] achievementsList = {}; // �����-������ ������. ����� � ���� ������� � �������� id ������.

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

	public GameAchievement()
	{
		text = new int[textHeight][textWidth];
		for( int x = 0; x < textWidth; x++ )
			for( int y = 0; y < textHeight; y++ )
				text[y][x] = 0xFF000000;
		
		id = GameAchievement.getAchievementId(this.getClass());
		status = 0;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void setStatus( int newStatus )
	{
		if( newStatus < 0 )
			status = 0;
		else if ( newStatus > 100 )
			status = 100;
		else status = newStatus; 			
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
	
	public int getText(int x, int y)
	{
		return text[y][x];
	}
	
	public int getListIcon(int x, int y)
	{
		return listIcon[y][x];
	}
	
	public int getGameIcon(int x, int y)
	{
		return gameIcon[y][x];
	}
}
