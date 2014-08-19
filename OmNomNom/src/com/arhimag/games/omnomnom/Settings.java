package com.arhimag.games.omnomnom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.arhimag.games.omnomnom.framework.FileIO;

public class Settings
{
	private static boolean soundEnabled = true;
	private static int control = 7;
	private static final int programmVersion = 13; 
	private static int currentVersion = programmVersion;
	private static int lastReachedLevel = 9;
	private static int isFutureMovement = 1;
	
	private static int playButtonColor = 0xFF1AAE1A;
	private static int achievementButtonColor = 0xFFDAA520;
	private static int helpButtonColor = 0xFF263696;
	private static int levelsButtonColor = 0xFF4B2295;
	private static int settingsButtonColor = 0xFFDAC320;
	
	public static void load(FileIO  files)
	{
		BufferedReader in = null;
		try
		{
			in = new BufferedReader( new InputStreamReader( files.readFile(".snakejourney")));
			currentVersion = Integer.parseInt( in.readLine() );
			if( currentVersion == programmVersion )
			{
				soundEnabled = Boolean.parseBoolean(in.readLine());
				control = Integer.parseInt(in.readLine());
				lastReachedLevel = Integer.parseInt(in.readLine());
				isFutureMovement = Integer.parseInt(in.readLine());
			}
		}
		catch (IOException e)
		{
			
		}
		catch (NumberFormatException e)
		{
		
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
					
			}
		}
	}
	
	public static void save( FileIO files)
	{
		BufferedWriter out = null;
		try
		{
			out = new BufferedWriter( new OutputStreamWriter(files.writeFile(".snakejourney")));
			out.write(Integer.toString(programmVersion));
			out.newLine();
			out.write(Boolean.toString(soundEnabled));
			out.newLine();
			out.write(Integer.toString(control));
			out.newLine();
			out.write(Integer.toString(lastReachedLevel));
			out.newLine();
			out.write(Integer.toString(isFutureMovement));
			out.newLine();
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
		lastReachedLevel = newLastReachedLevel;
	}
	
	public static int getIsFutureMovement()
	{
		if( control == 2)
			return 1;
		else
			return 0;
	}
	
	public static  void setFutureMovement()
	{
		
	}
	public static void assScore( int score)
	{
		/*for(int i = 0; i < 5; i++)
			if( highscores[i] < score)
			{
				for ( int j = 4; j > 1;  j--)
					highscores[j] = highscores[j-1];
				highscores[i] = score;
				break;
			} */
	}
}
