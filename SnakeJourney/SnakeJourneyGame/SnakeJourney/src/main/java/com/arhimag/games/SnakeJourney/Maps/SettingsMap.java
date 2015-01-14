package com.arhimag.games.SnakeJourney.Maps;

import android.graphics.Rect;
import android.util.Log;

public class SettingsMap extends GameMap
{

	private static Rect soundBounds = new Rect(4,1,4+40,1+9);
	private static Rect slideBounds = new Rect(0,11,0+21,11+8);
	private static Rect accelBounds = new Rect(25,11,25+21,11+8);
	private static Rect uprightdownleftBounds = new Rect(0,19,0+21,19+8);
	private static Rect leftrightBounds = new Rect(25,19,25+21,19+8);
	private static Rect closeBounds = new Rect(45,0,50,5);
	
	public static final char[][] soundLabel = 
		{{' ','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S',' '},
		{'S',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ','S','S',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S','S',' ',' ','S',' ',' ','S',' ','S',' ','S',' ',' ','S',' ','S','S',' ',' ','S'},
		{'S',' ','S','S','S','S',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ','S',' ','S',' ','S',' ','S',' ','S','S',' ','S',' ','S',' ','S',' ','S'},
		{'S',' ','S','S','S','S',' ','S','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ','S',' ','S',' ','S',' ','S',' ','S',' ','S','S',' ','S',' ','S',' ','S'},
		{'S',' ','S','S','S','S',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ','S',' ','S',' ','S',' ','S',' ','S',' ',' ','S',' ','S',' ','S',' ','S'},
		{'S',' ',' ',' ','S','S',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ','S','S',' ',' ',' ','S',' ',' ',' ','S','S',' ','S',' ',' ','S',' ','S','S',' ',' ','S'},
		{'S',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{' ','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S',' '}};
	
	public static final char[][] slideLabel =
		{
		{' ','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S',' '},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ','S','S',' ','S',' ',' ',' ','S',' ','S','S',' ',' ','S','S','S',' ','S'},
		{'S',' ','S',' ',' ',' ','S',' ',' ',' ','S',' ','S',' ','S',' ','S',' ',' ',' ','S'},
		{'S',' ',' ','S',' ',' ','S',' ',' ',' ','S',' ','S',' ','S',' ','S','S',' ',' ','S'},
		{'S',' ',' ',' ','S',' ','S',' ',' ',' ','S',' ','S',' ','S',' ','S',' ',' ',' ','S'},
		{'S',' ','S','S',' ',' ','S','S','S',' ','S',' ','S','S',' ',' ','S','S','S',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{' ','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S',' '}
		};
	
	public static final char[][] accellabel = 
		{
		{' ','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S',' '},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ','S',' ',' ',' ','S',' ',' ',' ','S',' ',' ','S','S','S',' ','S',' ',' ',' ','S'},
		{'S',' ','S',' ','S',' ','S',' ','S',' ','S',' ','S',' ','S',' ',' ',' ','S',' ',' ',' ','S'},
		{'S',' ','S',' ','S',' ','S',' ',' ',' ','S',' ',' ',' ','S','S',' ',' ','S',' ',' ',' ','S'},
		{'S',' ','S','S','S',' ','S',' ','S',' ','S',' ','S',' ','S',' ',' ',' ','S',' ',' ',' ','S'},
		{'S',' ','S',' ','S',' ',' ','S',' ',' ',' ','S',' ',' ','S','S','S',' ','S','S','S',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{' ','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S',' '}
		};
	
	public static final char[][] uprightdownleftlabel = 
		{
		{' ','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S',' '},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ','S','S','S',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ','S',' ',' ','S',' ',' ','S',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ','S','S','S','S',' ','S','S','S','S',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ','S',' ',' ','S',' ',' ','S',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ','S','S','S',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{' ','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S',' '}
		};
	
	public static final char[][] leftrightlabel = 
		{
		{' ','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S',' '},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ','S','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S','S',' ',' ',' ',' ','S'},
		{'S',' ',' ','S','S','S','S','S','S','S',' ',' ',' ','S','S','S','S','S','S',' ',' ',' ','S'},
		{'S',' ',' ',' ','S','S',' ',' ',' ','S',' ',' ',' ','S',' ',' ','S','S',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ','S',' ',' ',' ','S',' ',' ',' ','S',' ',' ','S',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{'S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S'},
		{' ','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S','S',' '}
		};
	
	public SettingsMap( )
	{
		super( );

		FlatMap = new char[][]{
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ','S'},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ','S'},
				{' ',' ',' ',' ',' ',' ',' ',' ','S','S',' ',' ','S',' ',' ','S',' ',' ','S',' ',' ',' ',' ','S','S',' ',' ','S',' ',' ','S',' ','S',' ','S',' ',' ','S',' ','S','S',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ','S','S','S','S',' ',' ','S','S',' ','S',' ','S',' ','S',' ',' ','S',' ',' ',' ','S',' ','S',' ','S',' ','S',' ','S','S',' ','S',' ','S',' ','S',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ','S','S','S','S',' ',' ','S',' ','S','S',' ','S',' ','S',' ',' ',' ','S',' ',' ','S',' ','S',' ','S',' ','S',' ','S',' ','S','S',' ','S',' ','S',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ','S','S','S','S',' ',' ','S',' ',' ','S',' ','S',' ','S',' ',' ',' ',' ','S',' ','S',' ','S',' ','S',' ','S',' ','S',' ',' ','S',' ','S',' ','S',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ','S','S',' ',' ','S',' ',' ','S',' ',' ','S',' ',' ',' ','S','S',' ',' ',' ','S',' ',' ',' ','S','S',' ','S',' ',' ','S',' ','S','S',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ','S','S',' ','S',' ',' ',' ','S',' ','S','S',' ',' ','S','S','S',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ','S',' ',' ',' ','S',' ',' ','S','S','S',' ','S',' ',' ',' ',' '},
				{' ',' ','S',' ',' ',' ','S',' ',' ',' ','S',' ','S',' ','S',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ','S',' ','S',' ','S',' ','S',' ','S',' ','S',' ',' ',' ','S',' ',' ',' ',' '},
				{' ',' ',' ','S',' ',' ','S',' ',' ',' ','S',' ','S',' ','S',' ','S','S',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ','S',' ','S',' ',' ',' ','S',' ',' ',' ','S','S',' ',' ','S',' ',' ',' ',' '},
				{' ',' ',' ',' ','S',' ','S',' ',' ',' ','S',' ','S',' ','S',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S','S','S',' ','S',' ','S',' ','S',' ','S',' ','S',' ',' ',' ','S',' ',' ',' ',' '},
				{' ',' ','S','S',' ',' ','S','S','S',' ','S',' ','S','S',' ',' ','S','S','S',' ',' ',' ',' ',' ',' ',' ',' ','S',' ','S',' ',' ','S',' ',' ',' ','S',' ',' ','S','S','S',' ','S','S','S',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ','S','S','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S','S',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ','S',' ',' ','S',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S','S','S','S','S','S','S',' ',' ',' ','S','S','S','S','S','S',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ','S','S','S','S',' ','S','S','S','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S','S',' ',' ',' ','S',' ',' ',' ','S',' ',' ','S','S',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ','S',' ',' ','S',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ','S',' ',' ',' ','S',' ',' ','S',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ','S','S','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};
		
		this.mapHeight = this.FlatMap.length;
		this.mapWidth = this.FlatMap[0].length;
		Log.d("OmNomNomTrace",Integer.toString(mapWidth));
	}
	
	public static Rect getSoundBounds()
	{
		return soundBounds;
	}
	
	public static Rect getSlideBounds()
	{
		return slideBounds;
	}
	
	public static Rect getAccelBounds()
	{
		return accelBounds;
	}
	
	public static Rect getUprightdownleftBounds()
	{
		return uprightdownleftBounds;
	}
	
	public static Rect getLeftrightBounds()
	{
		return leftrightBounds;
	}
	
	public static Rect getCloseBounds()
	{
		return closeBounds;
	}
	
	
	
}

