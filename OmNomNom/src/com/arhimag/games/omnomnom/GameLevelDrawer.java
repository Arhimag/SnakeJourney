package com.arhimag.games.omnomnom;

import java.util.Random;

import android.graphics.Rect;
import android.util.Log;

import com.arhimag.games.omnomnom.Levels.GameLevel;
import com.arhimag.games.omnomnom.Levels.LevelLevelsList;
import com.arhimag.games.omnomnom.Levels.SettingsLevel;
import com.arhimag.games.omnomnom.Maps.EggsWindowMap;
import com.arhimag.games.omnomnom.Maps.GameMap;
import com.arhimag.games.omnomnom.Maps.LevelsListMap;
import com.arhimag.games.omnomnom.Maps.SettingsMap;
import com.arhimag.games.omnomnom.framework.Graphics;
import com.arhimag.games.omnomnom.framework.Input.TouchEvent;

public class GameLevelDrawer
{
	private GameLevel level;
	
	private int[] mapWallColors = new int[]{0xff53aa14, 0xffdd954d, 0xFFDD954D, 0xFFDD954D, 0xFFDD954D, 0xFF305015, 0xFF000000};
	private int[][] mapColorModificators = new int[3][3];
	
	private int mapPixelSize = 5;
	
	private int mapStartDrawX = 0;
	private int mapStartDrawY = 0;
	
	private int mapBitShift = 32;

	private int foodColor = 0xffaaaaaa;
	private int finishColor = 0xff000000;
//	private int teleportColor = 0xff5555ff;
	private int teleportColor = 0xff7799ff;
	
	private int inactiveSettingsColors = 0xff333333;
	private int activeSettingsColors = 0xffdd5555;
	
	private int snakeHeadSize;
	private int snakeBodySize;
	private int foodSize;
	
	private int screenHeight;
	private int screenWidth;
	
	private int paddingTop = 0;
	private int paddingBottom = 0;
	private int paddingLeft = 0;
	private int paddingRight = 0;
	
	private long levelsSlideAnimationStartTime = 0;
	private int levelsSlideAnimationStartX = 0;
	
	private long levelsArrowsAnimationTime = 0;
	private int levelsArrowsAnimationSlide = 0;
	
	private int previousHeadX = -1;
	private int previousHeadY = -1;
	
	private int eggShown = 0;
	private long eggShowStart = 0;
	private Graphics graphics;
	
	public GameLevelDrawer( GameLevel level, int screenWidth, int screenHeight, Graphics graphics )
	{
		
		this.level = level;
		
		Random rand = new Random();
		
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		
		mapPixelSize = java.lang.Math.min((screenHeight - paddingTop - paddingBottom)/level.getMap().getMapHeight(), (screenWidth - paddingRight - paddingLeft)/level.getMap().getMapWidth() );
		mapStartDrawX = paddingLeft + (screenWidth  - paddingRight - paddingLeft - mapPixelSize*level.getMap().getMapWidth()) / 2 ;
		mapStartDrawY = paddingTop + (screenHeight - paddingTop - paddingBottom - level.getMap().getMapHeight()*mapPixelSize ) /2;
		
		this.foodSize = (3 * mapPixelSize) / 4;
		this.snakeBodySize = (3 * mapPixelSize) / 4;
		this.snakeHeadSize = mapPixelSize;
		
		this.graphics = graphics;
		this.mapColorModificators[0][0] = 0;
		this.mapColorModificators[1][1] = (0 | rand.nextInt(mapBitShift) << 16 | rand.nextInt(mapBitShift) << 8 | rand.nextInt(mapBitShift));
		this.mapColorModificators[1][0] = (0 | rand.nextInt(mapBitShift) << 16 | rand.nextInt(mapBitShift) << 8 | rand.nextInt(mapBitShift));
		this.mapColorModificators[2][0] = (0 | rand.nextInt(mapBitShift) << 16 | rand.nextInt(mapBitShift) << 8 | rand.nextInt(mapBitShift));
		this.mapColorModificators[0][1] = (0 | rand.nextInt(mapBitShift) << 16 | rand.nextInt(mapBitShift) << 8 | rand.nextInt(mapBitShift));
		this.mapColorModificators[2][1] = (0 | rand.nextInt(mapBitShift) << 16 | rand.nextInt(mapBitShift) << 8 | rand.nextInt(mapBitShift));
		this.mapColorModificators[0][2] = (0 | rand.nextInt(mapBitShift) << 16 | rand.nextInt(mapBitShift) << 8 | rand.nextInt(mapBitShift));
		this.mapColorModificators[1][2] = (0 | rand.nextInt(mapBitShift) << 16 | rand.nextInt(mapBitShift) << 8 | rand.nextInt(mapBitShift));
		this.mapColorModificators[2][2] = (0 | rand.nextInt(mapBitShift) << 16 | rand.nextInt(mapBitShift) << 8 | rand.nextInt(mapBitShift));
		
	}
	
	private int getColor(char cellValue)
	{
		return getColor( cellValue, 0, 0);
	}
	private int getColor(char cellValue, int i, int j  )
	{
		switch( cellValue )
		{
			case '#':
				return mapWallColors[0] + mapColorModificators[i % 3][j % 3];
			case '_':
				return 0xFF000000;
			case 'W':
				return mapWallColors[1] + mapColorModificators[i % 3][j % 3];
			case 'S':
				return inactiveSettingsColors + mapColorModificators[i % 3][j % 3];
			case 'Q':
				return mapWallColors[2] + mapColorModificators[i % 3][j % 3];
			case 'R':
				return mapWallColors[3] + mapColorModificators[i % 3][j % 3];
			case 'G':
				return mapWallColors[4] + mapColorModificators[i % 3][j % 3];
			case 'P':
//				return 0xFF03C03C + mapColorModificators[i % 3][j % 3];
//				return 0xFFE0E0E0 + mapColorModificators[i % 3][j % 3];
//				return 0xFF1AAE1A + mapColorModificators[i % 3][j % 3];
				return mapWallColors[3] + mapColorModificators[i % 3][j % 3];
			case 'A':
				return 0xFFDAA520 + ( mapColorModificators[i % 3][j % 3] & 0xFF00FFFF );
			case 's':
				//return 0xFFBB1B5C + mapColorModificators[i % 3][j % 3];
				return 0xFFDAC320 + mapColorModificators[i % 3][j % 3];
			case 'L':
				//return 0xFF7AC61D + mapColorModificators[i % 3][j % 3];
				return 0xFF4B2295 + mapColorModificators[i * 5 % 3][j * 5 % 3];
			case 'H':
				//return 0xFF263696 + mapColorModificators[i % 3][j % 3];
				return 0xFF263696 + mapColorModificators[i % 3][j % 3];
			case 'T': //����� ������
				return 0xFF7F7F7F + mapColorModificators[i % 3][j % 3];
			case 't': //����� ������
				return 0xFF858DA0 + mapColorModificators[i % 3][j % 3];
			case 'E': //�� �������� ����
				return 0xFFC0C0C0 + mapColorModificators[i % 3][j % 3];
			case 'e': //�������� ����
				return 0xFFF0F0F0;
			case 'Y':
				return 0xFFBEBEBE + mapColorModificators[i % 3][j % 3];
			case 'M':
				return 0xFFC2C2C2 + mapColorModificators[i % 3][j % 3];
			case 'C':
				return 0xFFFFFFFF + mapColorModificators[i % 3][j % 3];
			case 'B':
				return 0xFF9A7F76 + mapColorModificators[i % 3][j % 3];
			default:
				return mapWallColors[mapWallColors.length - 1];
		}
	}
	
	public void drawPauseButton()
	{
		int playerSnakeColor_need = 0xff8888aa;
		int playerSnakeColor_done = 0xff3333aa;
		
		int botSnakeColor_done = 0xffaa3333;
		int botSnakeColor_need = 0xffaa8888;

		int startx = level.getMap().getMapWidth() / 2 - 1;
		int starty = 0;
		
		int playerWinPercent = 0;
		int maxBotWinPercent = 0;
		
		boolean isAnyBot = false;
		
		if( level.getPlayerSnake() >= 0 && level.getPlayerSnake() < level.getSnakesCount() )
			playerWinPercent = level.getSnake(level.getPlayerSnake()).parts.size() * 100 / level.getSnake(level.getPlayerSnake()).finishSize; 
		playerWinPercent = ( playerWinPercent > 100 )?100:playerWinPercent;
		
		for( int i = 0; i < level.getSnakesCount(); i++)
			if( ( i != level.getPlayerSnake() ) && (level.getSnake(i).parts.size() * 100 / level.getSnake(i).finishSize > maxBotWinPercent ) )
			{
				maxBotWinPercent = level.getSnake(i).parts.size() * 100 / level.getSnake(i).finishSize;
				isAnyBot = true;
			}
		maxBotWinPercent = ( maxBotWinPercent > 100 )?100:maxBotWinPercent;
		
		if( isAnyBot )
		{
			if( playerWinPercent >= 50 )
			{
				graphics.drawRect(mapStartDrawX + startx * mapPixelSize + Math.round(mapPixelSize * (float)1 / (float)4) , this.mapStartDrawY + starty * mapPixelSize, mapPixelSize / 2, Math.round(mapPixelSize * ((float)100 - playerWinPercent) / (float)50 + (float)1), playerSnakeColor_need);
				graphics.drawRect(mapStartDrawX + startx * mapPixelSize + Math.round(mapPixelSize * (float)1 / (float)4) , this.mapStartDrawY + starty * mapPixelSize + Math.round(mapPixelSize * ((float)100 - playerWinPercent) / (float)50), mapPixelSize / 2, mapPixelSize * ( playerWinPercent - 50 ) / 50 , playerSnakeColor_done);
				graphics.drawRect(mapStartDrawX + startx * mapPixelSize + Math.round(mapPixelSize * (float)1 / (float)4) , this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize / 2, mapPixelSize , playerSnakeColor_done);
			}
			else
			{
				graphics.drawRect(mapStartDrawX + startx * mapPixelSize + Math.round(mapPixelSize * (float)1 /(float) 4) , this.mapStartDrawY + starty * mapPixelSize, mapPixelSize / 2, mapPixelSize, playerSnakeColor_need);
				graphics.drawRect(mapStartDrawX + startx * mapPixelSize + Math.round(mapPixelSize * (float)1 /(float) 4) , this.mapStartDrawY + (starty + 1)* mapPixelSize, mapPixelSize / 2, Math.round(mapPixelSize * ((float)50 - playerWinPercent) / (float)50 + (float)1) , playerSnakeColor_need);
				graphics.drawRect(mapStartDrawX + startx * mapPixelSize + Math.round(mapPixelSize * (float)1 /(float) 4) , this.mapStartDrawY + (starty + 1)* mapPixelSize + Math.round(mapPixelSize * ((float)50 - playerWinPercent) / (float)50), mapPixelSize / 2, Math.round(mapPixelSize * ( playerWinPercent ) / (float)50) , playerSnakeColor_done);
			}
			
			if( maxBotWinPercent >= 50 )
			{
				graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize + Math.round(mapPixelSize * (float)1 / (float)4) , this.mapStartDrawY + starty * mapPixelSize, mapPixelSize / 2, Math.round(mapPixelSize * ((float)100 - maxBotWinPercent) / (float)50 + (float)1), botSnakeColor_need);
				graphics.drawRect(mapStartDrawX + (startx + 1)* mapPixelSize + Math.round(mapPixelSize * (float)1 / (float)4) , this.mapStartDrawY + starty * mapPixelSize + Math.round(mapPixelSize * ((float)100 - maxBotWinPercent) / (float)50), mapPixelSize / 2, mapPixelSize * ( maxBotWinPercent - 50 ) / 50 , botSnakeColor_done);
				graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize + Math.round(mapPixelSize * (float)1 / (float)4) , this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize / 2, mapPixelSize , botSnakeColor_done);
			}
			else
			{
				graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize + Math.round(mapPixelSize * (float)1 /(float) 4) , this.mapStartDrawY + starty * mapPixelSize, mapPixelSize / 2, mapPixelSize, botSnakeColor_need);
				graphics.drawRect(mapStartDrawX + (startx + 1)* mapPixelSize + Math.round(mapPixelSize * (float)1 /(float) 4) , this.mapStartDrawY + (starty + 1)* mapPixelSize, mapPixelSize / 2, Math.round(mapPixelSize * ((float)50 - maxBotWinPercent) / (float)50 + (float)1) , botSnakeColor_need);
				graphics.drawRect(mapStartDrawX + (startx + 1)* mapPixelSize + Math.round(mapPixelSize * (float)1 /(float) 4) , this.mapStartDrawY + (starty + 1)* mapPixelSize + Math.round(mapPixelSize * ((float)50 - maxBotWinPercent) / (float)50), mapPixelSize / 2, Math.round(mapPixelSize * ( maxBotWinPercent ) / (float)50) , botSnakeColor_done);
			}
		}
		else
		{
			if( playerWinPercent >= 50 )
			{
				graphics.drawRect(mapStartDrawX + startx * mapPixelSize + Math.round(mapPixelSize * (float)2 / (float)3) , this.mapStartDrawY + starty * mapPixelSize, 2 * mapPixelSize / 3, Math.round(mapPixelSize * ((float)100 - playerWinPercent) / (float)50 + (float)1), playerSnakeColor_need);
				graphics.drawRect(mapStartDrawX + startx * mapPixelSize + Math.round(mapPixelSize * (float)2 / (float)3) , this.mapStartDrawY + starty * mapPixelSize + Math.round(mapPixelSize * ((float)100 - playerWinPercent) / (float)50), 2 * mapPixelSize / 3, mapPixelSize * ( playerWinPercent - 50 ) / 50 , playerSnakeColor_done);
				graphics.drawRect(mapStartDrawX + startx * mapPixelSize + Math.round(mapPixelSize * (float)2 / (float)3) , this.mapStartDrawY + (starty + 1) * mapPixelSize, 2 * mapPixelSize / 3, mapPixelSize , playerSnakeColor_done);
			}
			else
			{
				graphics.drawRect(mapStartDrawX + startx * mapPixelSize + Math.round(mapPixelSize * (float)2 /(float) 3) , this.mapStartDrawY + starty * mapPixelSize, 2 * mapPixelSize / 3, mapPixelSize, playerSnakeColor_need);
				graphics.drawRect(mapStartDrawX + startx * mapPixelSize + Math.round(mapPixelSize * (float)2 /(float) 3) , this.mapStartDrawY + (starty + 1)* mapPixelSize, 2 * mapPixelSize / 3, Math.round(mapPixelSize * ((float)50 - playerWinPercent) / (float)50 + (float)1) , playerSnakeColor_need);
				graphics.drawRect(mapStartDrawX + startx * mapPixelSize + Math.round(mapPixelSize * (float)2 /(float) 3) , this.mapStartDrawY + (starty + 1)* mapPixelSize + Math.round(mapPixelSize * ((float)50 - playerWinPercent) / (float)50), 2 * mapPixelSize / 3, Math.round(mapPixelSize * ( playerWinPercent ) / (float)50) , playerSnakeColor_done);
			}
		}
	}
	public void drawAquaControl()
	{
		
		/************************************************************
		 * 						����������:
		 * 
		 * ---------------------------------------------------------
		 * |  ^			                                        ^  |
		 * | /|\                                               /|\ |
		 * |  |                                                 |  |
		 * |-------------------------------------------------------|
		 * |                                                       |
		 * | <-                                                 -> |
		 * |                                                       |  
		 * |-------------------------------------------------------|
		 * |  |                                                 |  | 
		 * | \|/                                               \|/ |
		 * |  V                                                 V  | 
		 * |--------------------------------------------------------
		 ************************************************************/
		int aquaColor = 0x20ffffff;
		
		if( Settings.getControl() == 4 )
		{
			int startx = 2;
			int starty = level.getMap().getMapHeight() / 2;
			
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty - 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty - 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			startx = level.getMap().getMapWidth() - 1 - 2;
			starty = level.getMap().getMapHeight() / 2;
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 1) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 1) * mapPixelSize, this.mapStartDrawY + (starty - 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 1)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 2) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 2) * mapPixelSize, this.mapStartDrawY + (starty - 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 2)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 3)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 4)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			startx = 4;
			starty = 2;
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize, 						2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize - 1 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize + 1 * 2 * mapPixelSize / 3,	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize + 1 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize + 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize + 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize - 2 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize + 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize + 2 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize + 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize + 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize + 3 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 4 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			
			startx = level.getMap().getMapWidth() - 1 - 4;
			starty = 2;
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize, 						2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize - 1 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize + 1 * 2 * mapPixelSize / 3,	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize + 1 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize + 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize + 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize - 2 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize + 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize + 2 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize + 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize + 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize + 3 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 4 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			
			startx = 4;
			starty = level.getMap().getMapHeight() - 1 - 2;
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize, 						2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize - 1 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize - 1 * 2 * mapPixelSize / 3,	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize + 1 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize - 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize - 2 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize - 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize + 2 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize - 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 3 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 4 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			
			startx = level.getMap().getMapWidth() - 1 - 4;;
			starty = level.getMap().getMapHeight() - 1 - 2;
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize, 						2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize - 1 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize - 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize + 1 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize - 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize - 2 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize - 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize + 2 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize - 2 * 2 * mapPixelSize / 3,	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 3 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 4 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
		}
		else if( Settings.getControl() == 0 )
		{
		
			/************************************************************
			 * 						����������:
			 * 
			 * ---------------------------------------------------------
			 * |    O   O    O  OO    O
			 * |   O O  O       O O  O O
			 * |   O    O    O  O O  O
			 * |    O   O    O  O O   O
			 * |     O  O    O  O O  O
			 * |   O O  O O  O  O O  O O
			 * |    O   OOO  O  OO    O
			 * |--------------------------------------------------------
			 ************************************************************/
			
			int startxs = level.getMap().getMapWidth()/2 - 10;
			int startys = level.getMap().getMapHeight()/2 - 4;
			
			graphics.drawRect(mapStartDrawX + (startxs + 1 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 5 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 10 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 13 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 14 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 19 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			graphics.drawRect(mapStartDrawX + (startxs) * mapPixelSize, 	this.mapStartDrawY + (startys + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 2) * mapPixelSize, 	this.mapStartDrawY + (startys + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 5 ) * mapPixelSize, this.mapStartDrawY + (startys + 1 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 13 ) * mapPixelSize, this.mapStartDrawY + (startys + 1 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 15 ) * mapPixelSize, this.mapStartDrawY + (startys + 1 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 18 ) * mapPixelSize, this.mapStartDrawY + (startys + 1 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 20 ) * mapPixelSize, this.mapStartDrawY + (startys + 1 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			graphics.drawRect(mapStartDrawX + (startxs) * mapPixelSize, 	this.mapStartDrawY + (startys + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 10) * mapPixelSize, 	this.mapStartDrawY + (startys + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 5 ) * mapPixelSize, this.mapStartDrawY + (startys + 2 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 13 ) * mapPixelSize, this.mapStartDrawY + (startys + 2 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 15 ) * mapPixelSize, this.mapStartDrawY + (startys + 2 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 18 ) * mapPixelSize, this.mapStartDrawY + (startys + 2 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			graphics.drawRect(mapStartDrawX + (startxs + 1) * mapPixelSize, 	this.mapStartDrawY + (startys + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 5 ) * mapPixelSize, this.mapStartDrawY + (startys + 3 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 10) * mapPixelSize, 	this.mapStartDrawY + (startys + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 13 ) * mapPixelSize, this.mapStartDrawY + (startys + 3 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 15 ) * mapPixelSize, this.mapStartDrawY + (startys + 3 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 19 ) * mapPixelSize, this.mapStartDrawY + (startys + 3 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			graphics.drawRect(mapStartDrawX + (startxs + 2) * mapPixelSize, 	this.mapStartDrawY + (startys + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 10) * mapPixelSize, 	this.mapStartDrawY + (startys + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 5 ) * mapPixelSize, this.mapStartDrawY + (startys + 4 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 13 ) * mapPixelSize, this.mapStartDrawY + (startys + 4 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 15 ) * mapPixelSize, this.mapStartDrawY + (startys + 4 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 18 ) * mapPixelSize, this.mapStartDrawY + (startys + 4 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
	
			graphics.drawRect(mapStartDrawX + (startxs) * mapPixelSize, 	this.mapStartDrawY + (startys + 5) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 2) * mapPixelSize, 	this.mapStartDrawY + (startys + 5) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 5 ) * mapPixelSize, this.mapStartDrawY + (startys + 5 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 7) * mapPixelSize, 	this.mapStartDrawY + (startys + 5) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 10 ) * mapPixelSize, this.mapStartDrawY + (startys + 5 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 13 ) * mapPixelSize, this.mapStartDrawY + (startys + 5 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 15 ) * mapPixelSize, this.mapStartDrawY + (startys + 5 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 18 ) * mapPixelSize, this.mapStartDrawY + (startys + 5 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 20 ) * mapPixelSize, this.mapStartDrawY + (startys + 5 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			graphics.drawRect(mapStartDrawX + (startxs + 1) * mapPixelSize, 	this.mapStartDrawY + (startys + 6) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 5) * mapPixelSize, 	this.mapStartDrawY + (startys + 6) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 6 ) * mapPixelSize, this.mapStartDrawY + (startys + 6 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 7) * mapPixelSize, 	this.mapStartDrawY + (startys + 6) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 10 ) * mapPixelSize, this.mapStartDrawY + (startys + 6 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 13 ) * mapPixelSize, this.mapStartDrawY + (startys + 6 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 14 ) * mapPixelSize, this.mapStartDrawY + (startys + 6 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 19 ) * mapPixelSize, this.mapStartDrawY + (startys + 6 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
		}
		else if( Settings.getControl() == 1)
		{
			/************************************************************
			 * 						����������:
			 * 
			 * ---------------------------------------------------------
			 * |   OOO O   O O  O  OOO
			 * |    O  O   O O O O  O 
			 * |    O  O   O O O    O 
			 * |    O  O   O O  O   O 
			 * |    O  O O O O   O  O 
			 * |    O  O O O O O O  O 
			 * |    O   O O  O  O   O 
			 * |--------------------------------------------------------
			 ************************************************************/
			
			int startxs = level.getMap().getMapWidth()/2 - 9;
			int startys = level.getMap().getMapHeight()/2 - 4;
			
			graphics.drawRect(mapStartDrawX + (startxs) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 1 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 2 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 4 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 8 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 10 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 13 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 16 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 17 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 18 ) * mapPixelSize, this.mapStartDrawY + (startys ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			graphics.drawRect(mapStartDrawX + (startxs + 1) * mapPixelSize, 	this.mapStartDrawY + (startys + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 4) * mapPixelSize, 	this.mapStartDrawY + (startys + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 8 ) * mapPixelSize, this.mapStartDrawY + (startys + 1 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 12 ) * mapPixelSize, this.mapStartDrawY + (startys + 1 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 14 ) * mapPixelSize, this.mapStartDrawY + (startys + 1 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 17 ) * mapPixelSize, this.mapStartDrawY + (startys + 1 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
					
			graphics.drawRect(mapStartDrawX + (startxs + 1) * mapPixelSize, 	this.mapStartDrawY + (startys + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 4) * mapPixelSize, 	this.mapStartDrawY + (startys + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 8 ) * mapPixelSize, this.mapStartDrawY + (startys + 2 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 10 ) * mapPixelSize, this.mapStartDrawY + (startys + 2 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 12 ) * mapPixelSize, this.mapStartDrawY + (startys + 2 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 17 ) * mapPixelSize, this.mapStartDrawY + (startys + 2 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			graphics.drawRect(mapStartDrawX + (startxs + 1) * mapPixelSize, 	this.mapStartDrawY + (startys + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 4 ) * mapPixelSize, this.mapStartDrawY + (startys + 3 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 8) * mapPixelSize, 	this.mapStartDrawY + (startys + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 10 ) * mapPixelSize, this.mapStartDrawY + (startys + 3 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 13 ) * mapPixelSize, this.mapStartDrawY + (startys + 3 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 17 ) * mapPixelSize, this.mapStartDrawY + (startys + 3 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			graphics.drawRect(mapStartDrawX + (startxs + 1) * mapPixelSize, 	this.mapStartDrawY + (startys + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 4) * mapPixelSize, 	this.mapStartDrawY + (startys + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 8 ) * mapPixelSize, this.mapStartDrawY + (startys + 4 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 10 ) * mapPixelSize, this.mapStartDrawY + (startys + 4 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 14 ) * mapPixelSize, this.mapStartDrawY + (startys + 4 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 17 ) * mapPixelSize, this.mapStartDrawY + (startys + 4 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
	
			graphics.drawRect(mapStartDrawX + (startxs + 1) * mapPixelSize, 	this.mapStartDrawY + (startys + 5) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 4) * mapPixelSize, 	this.mapStartDrawY + (startys + 5) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 6 ) * mapPixelSize, this.mapStartDrawY + (startys + 5 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 8) * mapPixelSize, 	this.mapStartDrawY + (startys + 5) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 10 ) * mapPixelSize, this.mapStartDrawY + (startys + 5 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 12 ) * mapPixelSize, this.mapStartDrawY + (startys + 5 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 14 ) * mapPixelSize, this.mapStartDrawY + (startys + 5 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 17 ) * mapPixelSize, this.mapStartDrawY + (startys + 5 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			graphics.drawRect(mapStartDrawX + (startxs + 1) * mapPixelSize, 	this.mapStartDrawY + (startys + 6) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 5) * mapPixelSize, 	this.mapStartDrawY + (startys + 6) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 7 ) * mapPixelSize, this.mapStartDrawY + (startys + 6 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 10) * mapPixelSize, 	this.mapStartDrawY + (startys + 6) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 13) * mapPixelSize, this.mapStartDrawY + (startys + 6 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startxs + 17 ) * mapPixelSize, this.mapStartDrawY + (startys + 6 ) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
		}
		else if ( Settings.getControl() == 2 )
		{
			/************************************************************
			 * 						����������:
			 * 
			 * ---------------------------------------------------------
			 * |   			                                           |
			 * |                                                       |
			 * |                                                       |
			 * | 	                                                   |
			 * |                                                       |
			 * | <--                                               --> |
			 * |   |                                               |   |  
			 * |                                                       |
			 * |                                                       | 
			 * |                                                       |
			 * |                                                       | 
			 * |--------------------------------------------------------
			 ************************************************************/
			
			int startx = 2;
			int starty = level.getMap().getMapHeight() / 2 - 1;
	    	graphics.drawRect(mapStartDrawX + startx * mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty - 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty - 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 5)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 5)* mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 5)* mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 5)* mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			startx = level.getMap().getMapWidth() - 1 - 2;
			starty = level.getMap().getMapHeight() / 2 - 1;
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 1) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 1) * mapPixelSize, this.mapStartDrawY + (starty - 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 1)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 2) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 2) * mapPixelSize, this.mapStartDrawY + (starty - 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 2)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 3)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 4)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 5)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 5)* mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 5)* mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 5)* mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
		}
		else if( Settings.getControl() == 3)
		{
		
			/************************************************************
			 * 						����������:
			 * 
			 * ---------------------------------------------------------
			 * |                |          ^		|                  |
			 * |                |         /|\       |                  |
			 * |                |          |        |                  |
			 * |                |                   |                  |
			 * |                |                   |                  |
			 * | <-             |-------------------|               -> |
			 * |                |                   |                  |  
			 * |                |                   |                  |
			 * |                |          |        |                  | 
			 * |                |         \|/       |                  |
			 * |                |          V        |                  | 
			 * |--------------------------------------------------------
			 ************************************************************/
			int startx = 2;
			int starty = level.getMap().getMapHeight() / 2;
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty - 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty - 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			startx = level.getMap().getMapWidth() - 1 - 2;
			starty = level.getMap().getMapHeight() / 2;
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 1) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 1) * mapPixelSize, this.mapStartDrawY + (starty - 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 1)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 2) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 2) * mapPixelSize, this.mapStartDrawY + (starty - 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 2)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 3)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx - 4)* mapPixelSize, this.mapStartDrawY + starty * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			startx = level.getMap().getMapWidth() / 2 - 1;
			starty = 2;
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize, 						2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize - 1 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize + 1 * 2 * mapPixelSize / 3,	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize + 1 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize + 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize + 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize - 2 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize + 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize + 2 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize + 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize + 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize + 3 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 4 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			
			startx = level.getMap().getMapWidth() / 2 - 1;
			starty = level.getMap().getMapHeight() - 1 - 2;
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize, 						2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize - 1 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize - 1 * 2 * mapPixelSize / 3,	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize + 1 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize - 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 1 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize - 2 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize - 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize + 2 * 2 * mapPixelSize / 3, this.mapStartDrawY + starty * mapPixelSize - 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 2 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 3 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
			graphics.drawRect(mapStartDrawX + startx * mapPixelSize, 						this.mapStartDrawY + starty * mapPixelSize - 4 * 2 * mapPixelSize / 3, 	2 * mapPixelSize / 3, 2 * mapPixelSize / 3, aquaColor);
		}
		else if( Settings.getControl() == 5)
		{
		
			/************************************************************
			 * 						����������:
			 * 
			 * ---------------------------------------------------------
			 * |                                                  ###  |
			 * |                                                 ## ## |
			 * |                                                 ##  # |
			 * |                                                 ##### |
			 * |                                                  ###  |
			 * |                                                       |
			 * |  ###                                                  |  
			 * | #####                                                 |
			 * | #  ##                                                 | 
			 * | ## ##                                                 |
			 * |  ###                                                  | 
			 * |--------------------------------------------------------
			 ************************************************************/
			
			int startx = level.getMap().getMapWidth() - 8;
			int starty = 2;
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx ) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);

			startx = 2;
			starty = level.getMap().getMapHeight() - 8;
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx ) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
		} 
		else if( Settings.getControl() == 6)
		{
		
			/************************************************************
			 * 						����������:
			 * 
			 * ---------------------------------------------------------
			 * |  ###                                                  |
			 * | ## ##                                                 |
			 * | #  ##                                                 |
			 * | #####                                                 |
			 * |  ###                                                  |
			 * |                                                       |
			 * |                                                  ###  |  
			 * |                                                 ##### |
			 * |                                                 ##  # | 
			 * |                                                 ## ## |
			 * |                                                  ###  | 
			 * |--------------------------------------------------------
			 ************************************************************/
			int startx = 2;
			int starty = 2;

			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx ) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
			
			startx = level.getMap().getMapWidth() - 8;
			starty = level.getMap().getMapHeight() - 8;
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4) * mapPixelSize, this.mapStartDrawY + (starty + 1) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx ) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4) * mapPixelSize, this.mapStartDrawY + (starty + 2) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 4) * mapPixelSize, this.mapStartDrawY + (starty + 3) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 1) * mapPixelSize, this.mapStartDrawY + (starty + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 2) * mapPixelSize, this.mapStartDrawY + (starty + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			graphics.drawRect(mapStartDrawX + (startx + 3) * mapPixelSize, this.mapStartDrawY + (starty + 4) * mapPixelSize, mapPixelSize, mapPixelSize, aquaColor);
			
		
		}
	}
	
	
	public void  drawMapMiniature(GameMap map, int mapStartDrawX, int mapStartDrawY, int width, int height)
	{	
		int mapPixelSize = java.lang.Math.min((height)/map.getMapHeight(), (width)/map.getMapWidth() );
		mapStartDrawX += (width - map.getMapWidth() * mapPixelSize) / 2;
		mapStartDrawY += (height - map.getMapHeight() * mapPixelSize) / 2;
		for(int i = 0; i < map.getMapWidth() ; i++ )
			for(int j=0; j < map.getMapHeight(); j++)
				graphics.drawRect(mapStartDrawX + i * mapPixelSize, mapStartDrawY + j * mapPixelSize, mapPixelSize, mapPixelSize, getColor(map.getFlatMap(i, j),i,j));
	}

	public void  drawMapMiniature(GameMap map, int mapStartDrawX, int mapStartDrawY, int pixelSize)
	{	
		for(int i = 0; i < map.getMapWidth() ; i++ )
			for(int j=0; j < map.getMapHeight(); j++)
				graphics.drawRect(mapStartDrawX + i * pixelSize, mapStartDrawY + j * pixelSize, pixelSize, pixelSize, getColor(map.getFlatMap(i, j),i,j));
	}
	
	public void drawMap()
	{
		for(int i = 0; i < level.getMap().getMapWidth() ; i++ )
			for(int j=0; j < level.getMap().getMapHeight(); j++)
				graphics.drawRect(mapStartDrawX + i * mapPixelSize, this.mapStartDrawY + j * mapPixelSize, mapPixelSize, mapPixelSize, getColor(level.getMap().getFlatMap(i, j),i,j));
	}
	
	private void drawCenterRect( int x, int y, int side, int color)
	{
		graphics.drawRect( x - side/2, y - side/2, side, side, color);
	}
	
	private void drawSnake( float deltaTime, int snakeid )
	{
		float x, y;
		if( ! this.level.getMoved(snakeid))
		{
			deltaTime = this.level.getTickTime() + 1.0f;
		}
		
		for( int i = this.level.getSnake(snakeid).parts.size() - 2; i > 0; i --)
		{
			SnakePart curr = this.level.getSnake(snakeid).parts.get(i);
			SnakePart prev = this.level.getSnake(snakeid).parts.get(i + 1);
	
			if( deltaTime > this.level.getTickTime() )
			{
				x = mapStartDrawX + curr.x * mapPixelSize + (float)mapPixelSize / 2.0f;
				y = mapStartDrawY + curr.y * mapPixelSize + (float)mapPixelSize / 2.0f;
			}
			else
			{
				x = mapStartDrawX + prev.x * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)curr.x - (float)prev.x );
				y = mapStartDrawY + prev.y * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)curr.y - (float)prev.y );
			}
	
			drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y),snakeBodySize,this.level.getSnake(snakeid).getBodyColor(i));
		}
		
		if( this.level.getSnake(snakeid).parts.size() == 1 )
		{
			if( deltaTime > this.level.getTickTime() )
			{
				x = mapStartDrawX + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(0).x ;
				y = mapStartDrawY + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(0).y ;
			}
			else
			{
				x = mapStartDrawX + this.level.getSnake(snakeid).lastx * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)this.level.getSnake(snakeid).parts.get(0).x - (float)this.level.getSnake(snakeid).lastx );
				y = mapStartDrawY + this.level.getSnake(snakeid).lasty * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)this.level.getSnake(snakeid).parts.get(0).y - (float)this.level.getSnake(snakeid).lasty );
			}
			drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y),snakeHeadSize,this.level.getSnake(snakeid).headColor);
		} 
		else
		{
			if( deltaTime > this.level.getTickTime() )
			{
				x = mapStartDrawX + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(this.level.getSnake(snakeid).parts.size()-1).x;
				y = mapStartDrawY + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(this.level.getSnake(snakeid).parts.size()-1).y;
			}
			else
			{
				x = mapStartDrawX + this.level.getSnake(snakeid).lastx * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)this.level.getSnake(snakeid).parts.get(this.level.getSnake(snakeid).parts.size()-1).x - (float)this.level.getSnake(snakeid).lastx );
				y = mapStartDrawY + this.level.getSnake(snakeid).lasty * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)this.level.getSnake(snakeid).parts.get(this.level.getSnake(snakeid).parts.size()-1).y - (float)this.level.getSnake(snakeid).lasty );
			}
			
			drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y),snakeBodySize,this.level.getSnake(snakeid).getBodyColor(this.level.getSnake(snakeid).parts.size()+1));
	
			if( deltaTime > this.level.getTickTime() )
			{	
				x = mapStartDrawX + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(0).x;
				y = mapStartDrawY + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(0).y;
			}
			else
			{
				x = mapStartDrawX + this.level.getSnake(snakeid).parts.get(1).x * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)this.level.getSnake(snakeid).parts.get(0).x - (float)this.level.getSnake(snakeid).parts.get(1).x );
				y = mapStartDrawY + this.level.getSnake(snakeid).parts.get(1).y * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)this.level.getSnake(snakeid).parts.get(0).y - (float)this.level.getSnake(snakeid).parts.get(1).y );
			}
			
			drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y),snakeHeadSize,this.level.getSnake(snakeid).headColor);
		}
	}

	private void drawSnakeFuture( float deltaTime, int snakeid )
	{
		float x, y;
		if( ! this.level.getMovedDirection(this.level.getSnake(snakeid).direction,snakeid))
		{
			deltaTime = 0;
		}
		
		for( int i = 1; i < this.level.getSnake(snakeid).parts.size(); i ++)
		{
			SnakePart curr = this.level.getSnake(snakeid).parts.get(i -1);
			SnakePart prev = this.level.getSnake(snakeid).parts.get(i);
	
			if( deltaTime > this.level.getTickTime() )
			{
				x = mapStartDrawX + curr.x * mapPixelSize + (float)mapPixelSize / 2.0f;
				y = mapStartDrawY + curr.y * mapPixelSize + (float)mapPixelSize / 2.0f;
			}
			else
			{
				x = mapStartDrawX + prev.x * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)curr.x - (float)prev.x );
				y = mapStartDrawY + prev.y * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)curr.y - (float)prev.y );
			}
	
			drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y),snakeBodySize,this.level.getSnake(snakeid).getBodyColor(i));
		}
		
		
		switch( this.level.getSnake(snakeid).direction )
		{
			case Snake.DOWN:
				if( deltaTime > this.level.getTickTime() )
				{
					x = mapStartDrawX + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(0).x ;
					y = mapStartDrawY + (float)mapPixelSize / 2.0f + (float)mapPixelSize * ( (float)this.level.getSnake(snakeid).parts.get(0).y + 1) ;
				}
				else
				{
					x = (mapStartDrawX + this.level.getSnake(snakeid).parts.get(0).x * mapPixelSize + (float)mapPixelSize / 2.0f + previousHeadX) / 2;
					y = (mapStartDrawY + this.level.getSnake(snakeid).parts.get(0).y * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime() + previousHeadY) / 2;
				}
				
				previousHeadX = java.lang.Math.round(x);
				previousHeadY = java.lang.Math.round(y);
				
				drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y),snakeHeadSize,this.level.getSnake(snakeid).headColor);
				break;
			case Snake.UP:
				if( deltaTime > this.level.getTickTime() )
				{
					x = mapStartDrawX + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(0).x ;
					y = mapStartDrawY + (float)mapPixelSize / 2.0f + (float)mapPixelSize * ( (float)this.level.getSnake(snakeid).parts.get(0).y - 1) ;
				}
				else
				{
					x = (mapStartDrawX + this.level.getSnake(snakeid).parts.get(0).x * mapPixelSize + (float)mapPixelSize / 2.0f + previousHeadX) / 2;
					y = (mapStartDrawY + this.level.getSnake(snakeid).parts.get(0).y * mapPixelSize + (float)mapPixelSize / 2.0f - deltaTime * (float)mapPixelSize/this.level.getTickTime() + previousHeadY) / 2;
				}
				
				previousHeadX = java.lang.Math.round(x);
				previousHeadY = java.lang.Math.round(y);
				
				drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y),snakeHeadSize,this.level.getSnake(snakeid).headColor);
				break;
			case Snake.LEFT:
				if( deltaTime > this.level.getTickTime() )
				{
					x = mapStartDrawX + (float)mapPixelSize / 2.0f + (float)mapPixelSize * ( (float)this.level.getSnake(snakeid).parts.get(0).x - 1) ;
					y = mapStartDrawY + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(0).y ;
				}
				else
				{
					x = (mapStartDrawX + this.level.getSnake(snakeid).parts.get(0).x * mapPixelSize + (float)mapPixelSize / 2.0f - deltaTime * (float)mapPixelSize/this.level.getTickTime() + previousHeadX) / 2;
					y = (mapStartDrawY + this.level.getSnake(snakeid).parts.get(0).y * mapPixelSize + (float)mapPixelSize / 2.0f + previousHeadY)/2;
				}
				
				previousHeadX = java.lang.Math.round(x);
				previousHeadY = java.lang.Math.round(y);
				
				drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y),snakeHeadSize,this.level.getSnake(snakeid).headColor);
				break;
			case Snake.RIGHT:
				if( deltaTime > this.level.getTickTime() )
				{
					x = mapStartDrawX + (float)mapPixelSize / 2.0f + (float)mapPixelSize * ( (float)this.level.getSnake(snakeid).parts.get(0).x + 1);
					y = mapStartDrawY + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(0).y ;
				}
				else
				{
					x = (mapStartDrawX + this.level.getSnake(snakeid).parts.get(0).x * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime() + previousHeadX) / 2;
					y = (mapStartDrawY + this.level.getSnake(snakeid).parts.get(0).y * mapPixelSize + (float)mapPixelSize / 2.0f + previousHeadY)/2;
				}
				
				previousHeadX = java.lang.Math.round(x);
				previousHeadY = java.lang.Math.round(y);
				
				drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y),snakeHeadSize,this.level.getSnake(snakeid).headColor);
				break;
		}
/*		
		if( this.level.getSnake(snakeid).parts.size() == 1 )
		{
			if( deltaTime > this.level.getTickTime() )
			{
				x = mapStartDrawX + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(0).x ;
				y = mapStartDrawY + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(0).y ;
			}
			else
			{
				x = mapStartDrawX + this.level.getSnake(snakeid).lastx * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)this.level.getSnake(snakeid).parts.get(0).x - (float)this.level.getSnake(snakeid).lastx );
				y = mapStartDrawY + this.level.getSnake(snakeid).lasty * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)this.level.getSnake(snakeid).parts.get(0).y - (float)this.level.getSnake(snakeid).lasty );
			}
			drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y),snakeHeadSize,this.level.getSnake(snakeid).headColor);
		} 
		else
		{
			if( deltaTime > this.level.getTickTime() )
			{
				x = mapStartDrawX + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(this.level.getSnake(snakeid).parts.size()-1).x;
				y = mapStartDrawY + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(this.level.getSnake(snakeid).parts.size()-1).y;
			}
			else
			{
				x = mapStartDrawX + this.level.getSnake(snakeid).lastx * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)this.level.getSnake(snakeid).parts.get(this.level.getSnake(snakeid).parts.size()-1).x - (float)this.level.getSnake(snakeid).lastx );
				y = mapStartDrawY + this.level.getSnake(snakeid).lasty * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)this.level.getSnake(snakeid).parts.get(this.level.getSnake(snakeid).parts.size()-1).y - (float)this.level.getSnake(snakeid).lasty );
			}
			
			drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y),snakeBodySize,this.level.getSnake(snakeid).getBodyColor(this.level.getSnake(snakeid).parts.size()+1));
	
			if( deltaTime > this.level.getTickTime() )
			{	
				x = mapStartDrawX + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(0).x;
				y = mapStartDrawY + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getSnake(snakeid).parts.get(0).y;
			}
			else
			{
				x = mapStartDrawX + this.level.getSnake(snakeid).parts.get(1).x * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)this.level.getSnake(snakeid).parts.get(0).x - (float)this.level.getSnake(snakeid).parts.get(1).x );
				y = mapStartDrawY + this.level.getSnake(snakeid).parts.get(1).y * mapPixelSize + (float)mapPixelSize / 2.0f + deltaTime * (float)mapPixelSize/this.level.getTickTime()*((float)this.level.getSnake(snakeid).parts.get(0).y - (float)this.level.getSnake(snakeid).parts.get(1).y );
			}
			
			drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y),snakeHeadSize,this.level.getSnake(snakeid).headColor);
		}
		*/
	}


	private void drawFood()
	{
		float x, y;
		
		for( int i = 0; i < this.level.getFoodLength(); i++ )
		{
			x = mapStartDrawX + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getFood(i).x;
			y = mapStartDrawY + (float)mapPixelSize / 2.0f + (float)mapPixelSize * (float)this.level.getFood(i).y;
			drawCenterRect(java.lang.Math.round(x), java.lang.Math.round(y), this.foodSize, this.foodColor);
		}
	}
	
	private void drawFinish()
	{
		for ( int i = 0; i < this.level.getFinishesCount(level.getPlayerSnake()); i++ )
			graphics.drawRect(mapStartDrawX + mapPixelSize * this.level.getFinishX(i), mapStartDrawY + mapPixelSize * this.level.getFinishY(i), mapPixelSize, mapPixelSize, finishColor);
		
		boolean botFinish = false;
		
		for ( int i = 0; i < this.level.getSnakesCount(); i++)
			if( (i != level.getPlayerSnake()) && ( level.getSnake(i).parts.size() > level.getSnake(i).finishSize ))
			{
				botFinish = true;
				break;
			}
		
		if( botFinish)
			for ( int i = 0; i < this.level.getFinishesLength(); i++ )
				graphics.drawRect(mapStartDrawX + mapPixelSize * this.level.getFinishX(i), mapStartDrawY + mapPixelSize * this.level.getFinishY(i), mapPixelSize, mapPixelSize, finishColor - 0x55550000);
			
	}
	
	private void drawTeleports()
	{
		for ( int i = 0; i < this.level.getTeleportesCount(); i++ )
			graphics.drawRect(mapStartDrawX + mapPixelSize * this.level.getTeleportX(i), mapStartDrawY + mapPixelSize * this.level.getTeleportY(i), mapPixelSize, mapPixelSize, teleportColor);
	}
	
	private static final int SOUND_LABEL = 0;
	private static final int SLIDE_LABEL = 1;
	private static final int ACCEL_LABEL = 2;
	private static final int URDL_LABEL = 3;
	private static final int LEFTRIGHT_LABEL = 4;
	private static final int NEXTLIST_LABEL = 5;
	private static final int PREVIOUSLIST_LABEL = 6;

	private static final int NUMBER_0_P = 10;
	private static final int NUMBER_0_A = 20;
	
	private static final int EGG = 30;
	
	public void drawLabel( int label )
	{
		switch(label)
		{
			case SOUND_LABEL:
				for(int i = 0; i < SettingsMap.soundLabel[0].length ; i++ )
					for(int j = 0; j < SettingsMap.soundLabel.length; j++)
						switch (SettingsMap.soundLabel[j][i])
						{
							case 'S':
								graphics.drawRect(mapStartDrawX + ( SettingsMap.getSoundBounds().left + i ) * mapPixelSize, this.mapStartDrawY + ( SettingsMap.getSoundBounds().top + j ) * mapPixelSize, mapPixelSize, mapPixelSize, activeSettingsColors + mapColorModificators[i % 3][j % 3]);
								break;
							default:
								graphics.drawRect(mapStartDrawX + ( SettingsMap.getSoundBounds().left + i ) * mapPixelSize, this.mapStartDrawY + ( SettingsMap.getSoundBounds().top + j ) * mapPixelSize, mapPixelSize, mapPixelSize, mapWallColors[mapWallColors.length - 1]);
						}
				break;
			case SLIDE_LABEL:
				for(int i = 0; i < SettingsMap.slideLabel[0].length ; i++ )
					for(int j = 0; j < SettingsMap.slideLabel.length; j++)
						switch (SettingsMap.slideLabel[j][i])
						{
							case 'S':
								graphics.drawRect(mapStartDrawX + ( SettingsMap.getSlideBounds().left + i ) * mapPixelSize, this.mapStartDrawY + ( SettingsMap.getSlideBounds().top + j ) * mapPixelSize, mapPixelSize, mapPixelSize, activeSettingsColors + mapColorModificators[i % 3][j % 3]);
								break;
							default:
								graphics.drawRect(mapStartDrawX + ( SettingsMap.getSlideBounds().left + i ) * mapPixelSize, this.mapStartDrawY + ( SettingsMap.getSlideBounds().top + j ) * mapPixelSize, mapPixelSize, mapPixelSize, mapWallColors[mapWallColors.length - 1]);
						}

				break;
			case ACCEL_LABEL:
				for(int i = 0; i < SettingsMap.accellabel[0].length ; i++ )
					for(int j = 0; j < SettingsMap.accellabel.length; j++)
						switch (SettingsMap.accellabel[j][i])
						{
							case 'S':
								graphics.drawRect(mapStartDrawX + ( SettingsMap.getAccelBounds().left + i ) * mapPixelSize, this.mapStartDrawY + ( SettingsMap.getAccelBounds().top + j ) * mapPixelSize, mapPixelSize, mapPixelSize, activeSettingsColors + mapColorModificators[i % 3][j % 3]);
								break;
							default:
								graphics.drawRect(mapStartDrawX + ( SettingsMap.getAccelBounds().left + i ) * mapPixelSize, this.mapStartDrawY + ( SettingsMap.getAccelBounds().top + j ) * mapPixelSize, mapPixelSize, mapPixelSize, mapWallColors[mapWallColors.length - 1]);
						}

				break;
			case URDL_LABEL:
				for(int i = 0; i < SettingsMap.uprightdownleftlabel[0].length ; i++ )
					for(int j = 0; j < SettingsMap.uprightdownleftlabel.length; j++)
						switch (SettingsMap.uprightdownleftlabel[j][i])
						{
							case 'S':
								graphics.drawRect(mapStartDrawX + ( SettingsMap.getUprightdownleftBounds().left + i ) * mapPixelSize, this.mapStartDrawY + ( SettingsMap.getUprightdownleftBounds().top + j ) * mapPixelSize, mapPixelSize, mapPixelSize, activeSettingsColors + mapColorModificators[i % 3][j % 3]);
								break;
							default:
								graphics.drawRect(mapStartDrawX + ( SettingsMap.getUprightdownleftBounds().left + i ) * mapPixelSize, this.mapStartDrawY + ( SettingsMap.getUprightdownleftBounds().top + j ) * mapPixelSize, mapPixelSize, mapPixelSize, mapWallColors[mapWallColors.length - 1]);
						}

				break;
			case LEFTRIGHT_LABEL:
				for(int i = 0; i < SettingsMap.leftrightlabel[0].length ; i++ )
					for(int j = 0; j < SettingsMap.leftrightlabel.length; j++)
						switch (SettingsMap.leftrightlabel[j][i])
						{
							case 'S':
								graphics.drawRect(mapStartDrawX + ( SettingsMap.getLeftrightBounds().left + i ) * mapPixelSize, this.mapStartDrawY + ( SettingsMap.getLeftrightBounds().top + j ) * mapPixelSize, mapPixelSize, mapPixelSize, activeSettingsColors + mapColorModificators[i % 3][j % 3]);
								break;
							default:
								graphics.drawRect(mapStartDrawX + ( SettingsMap.getLeftrightBounds().left + i ) * mapPixelSize, this.mapStartDrawY + ( SettingsMap.getLeftrightBounds().top + j ) * mapPixelSize, mapPixelSize, mapPixelSize, mapWallColors[mapWallColors.length - 1]);
						}

				break;			
			case NEXTLIST_LABEL:
				if((System.nanoTime() - levelsArrowsAnimationTime) / 1000000000.0f > 0.15f)
				{
					levelsArrowsAnimationTime = System.nanoTime();
					levelsArrowsAnimationSlide = ( levelsArrowsAnimationSlide + 1 ) % 4; 
				}
				for(int i = 0; i < LevelsListMap.nextlabel[0].length ; i++ )
					for(int j = 0; j < LevelsListMap.nextlabel.length; j++)
						graphics.drawRect(mapStartDrawX + ( LevelsListMap.getNextBounds().left + i - (3*levelsArrowsAnimationSlide*levelsArrowsAnimationSlide + levelsArrowsAnimationSlide - levelsArrowsAnimationSlide*levelsArrowsAnimationSlide*levelsArrowsAnimationSlide)/3 ) * mapPixelSize, this.mapStartDrawY + ( LevelsListMap.getNextBounds().top + j ) * mapPixelSize, mapPixelSize, mapPixelSize, getColor(LevelsListMap.nextlabel[j][i],i,j));
				break;			

			case PREVIOUSLIST_LABEL:
				if((System.nanoTime() - levelsArrowsAnimationTime ) / 1000000000.0f > 0.15f)
				{
					levelsArrowsAnimationTime = System.nanoTime();
					levelsArrowsAnimationSlide = ( levelsArrowsAnimationSlide + 1 ) % 4; 
				}
				for(int i = 0; i < LevelsListMap.previouslabel[0].length ; i++ )
					for(int j = 0; j < LevelsListMap.previouslabel.length; j++)
						graphics.drawRect(mapStartDrawX + ( LevelsListMap.getPreviousBounds().left + i + (3*levelsArrowsAnimationSlide*levelsArrowsAnimationSlide + levelsArrowsAnimationSlide - levelsArrowsAnimationSlide*levelsArrowsAnimationSlide*levelsArrowsAnimationSlide)/3  ) * mapPixelSize, this.mapStartDrawY + ( LevelsListMap.getPreviousBounds().top + j ) * mapPixelSize, mapPixelSize, mapPixelSize, getColor(LevelsListMap.previouslabel[j][i],i,j));
				break;			

		}
	}
	
	public void drawLabel( int label, int x, int y, int pixelsize )
	{
		// ������� �����
		if( label >= NUMBER_0_P  && label <= NUMBER_0_A + 9  )
		{
			for(int i = 0; i < EggsWindowMap.Numbers[(label - NUMBER_0_P) % 10][0].length ; i++ )
				for(int j = 0; j < EggsWindowMap.Numbers[(label - NUMBER_0_P) % 10].length; j++)
					if( label <= NUMBER_0_P + 9)
						graphics.drawRect(x + i * pixelsize, y + j * pixelsize, pixelsize, pixelsize, getColor(Character.toLowerCase(EggsWindowMap.Numbers[(label - NUMBER_0_P) % 10][j][i]),j,i));
					else
						graphics.drawRect(x + i * pixelsize, y + j * pixelsize, pixelsize, pixelsize, getColor(Character.toUpperCase(EggsWindowMap.Numbers[(label - NUMBER_0_P) % 10][j][i]),j,i));
		}
		
		switch( label )
		{
			case EGG:
				for(int i = 0; i < EggsWindowMap.Egg[0].length ; i++ )
					for(int j = 0; j < EggsWindowMap.Egg.length; j++)
						graphics.drawRect(x + i * pixelsize, y + j * pixelsize, pixelsize, pixelsize, getColor(EggsWindowMap.Egg[j][i],j,i));
				break;
		}
	}
	
	private void drawEggWindow()
	{
		if(! this.level.getEggsWindow() )
			return;
		
		int winPixelSize = java.lang.Math.min((screenHeight - paddingTop - paddingBottom)/EggsWindowMap.getMapHeightStatic(), (screenWidth - paddingRight - paddingLeft)/EggsWindowMap.getMapWidthStatic() );
		int winStartDrawX = paddingLeft + (screenWidth  - paddingRight - paddingLeft - winPixelSize * EggsWindowMap.getMapWidthStatic()) / 2 ;
		int winStartDrawY = paddingTop + (screenHeight - paddingTop - paddingBottom - EggsWindowMap.getMapHeightStatic() * winPixelSize ) /2;
		
		graphics.drawRect(winStartDrawX,winStartDrawY,winPixelSize*EggsWindowMap.getMapWidthStatic(),winPixelSize*EggsWindowMap.getMapHeightStatic(),0xFF000000);
		
		// ������ �������� "������" ������������ ������
		for(int i = 0; i < EggsWindowMap.getMapWidthStatic() ; i++ )
			for(int j=0; j < EggsWindowMap.getMapHeightStatic(); j++)
				graphics.drawRect(winStartDrawX + i * winPixelSize, winStartDrawY + j * winPixelSize, winPixelSize, winPixelSize, getColor(EggsWindowMap.getFlatMapStatic(i, j),i,j));
		
		
		// ������ ����� ����
		if( eggShown == 0)
		{
			eggShown = 1;
			eggShowStart = System.nanoTime();
		}
		else if( eggShown < 3 )
		{
			if( System.nanoTime() - eggShowStart > level.getEggTimeout() )
			{
				eggShown ++;
				eggShowStart = System.nanoTime();
			}			
		}
		
		switch( eggShown )
		{
			case 1:
				drawLabel(NUMBER_0_A + (int)level.getEgg1TimeNumber(0),winStartDrawX + EggsWindowMap.ACHIVE_HOUR_1.x * winPixelSize, winStartDrawY + EggsWindowMap.ACHIVE_HOUR_1.y * winPixelSize, winPixelSize);
				drawLabel(NUMBER_0_A + (int)level.getEgg1TimeNumber(1),winStartDrawX + EggsWindowMap.ACHIVE_HOUR_2.x * winPixelSize, winStartDrawY + EggsWindowMap.ACHIVE_HOUR_2.y * winPixelSize, winPixelSize);
				drawLabel(NUMBER_0_A + (int)level.getEgg1TimeNumber(2),winStartDrawX + EggsWindowMap.ACHIVE_MINUTE_1.x * winPixelSize, winStartDrawY + EggsWindowMap.ACHIVE_MINUTE_1.y * winPixelSize, winPixelSize);
				drawLabel(NUMBER_0_A + (int)level.getEgg1TimeNumber(3),winStartDrawX + EggsWindowMap.ACHIVE_MINUTE_2.x * winPixelSize, winStartDrawY + EggsWindowMap.ACHIVE_MINUTE_2.y * winPixelSize, winPixelSize);
				break;
			case 2:
				drawLabel(NUMBER_0_A + (int)level.getEgg2TimeNumber(0),winStartDrawX + EggsWindowMap.ACHIVE_HOUR_1.x * winPixelSize, winStartDrawY + EggsWindowMap.ACHIVE_HOUR_1.y * winPixelSize, winPixelSize);
				drawLabel(NUMBER_0_A + (int)level.getEgg2TimeNumber(1),winStartDrawX + EggsWindowMap.ACHIVE_HOUR_2.x * winPixelSize, winStartDrawY + EggsWindowMap.ACHIVE_HOUR_2.y * winPixelSize, winPixelSize);
				drawLabel(NUMBER_0_A + (int)level.getEgg2TimeNumber(2),winStartDrawX + EggsWindowMap.ACHIVE_MINUTE_1.x * winPixelSize, winStartDrawY + EggsWindowMap.ACHIVE_MINUTE_1.y * winPixelSize, winPixelSize);
				drawLabel(NUMBER_0_A + (int)level.getEgg2TimeNumber(3),winStartDrawX + EggsWindowMap.ACHIVE_MINUTE_2.x * winPixelSize, winStartDrawY + EggsWindowMap.ACHIVE_MINUTE_2.y * winPixelSize, winPixelSize);
				break;
			default://3
				drawLabel(NUMBER_0_A + (int)level.getEgg3TimeNumber(0),winStartDrawX + EggsWindowMap.ACHIVE_HOUR_1.x * winPixelSize, winStartDrawY + EggsWindowMap.ACHIVE_HOUR_1.y * winPixelSize, winPixelSize);
				drawLabel(NUMBER_0_A + (int)level.getEgg3TimeNumber(1),winStartDrawX + EggsWindowMap.ACHIVE_HOUR_2.x * winPixelSize, winStartDrawY + EggsWindowMap.ACHIVE_HOUR_2.y * winPixelSize, winPixelSize);
				drawLabel(NUMBER_0_A + (int)level.getEgg3TimeNumber(2),winStartDrawX + EggsWindowMap.ACHIVE_MINUTE_1.x * winPixelSize, winStartDrawY + EggsWindowMap.ACHIVE_MINUTE_1.y * winPixelSize, winPixelSize);
				drawLabel(NUMBER_0_A + (int)level.getEgg3TimeNumber(3),winStartDrawX + EggsWindowMap.ACHIVE_MINUTE_2.x * winPixelSize, winStartDrawY + EggsWindowMap.ACHIVE_MINUTE_2.y * winPixelSize, winPixelSize);
				break;				
		}
		
		// ������ �������� ����
		if( eggShown > 0 )
		{
			if(level.isFirstEggActive())
				drawLabel(EGG,winStartDrawX + EggsWindowMap.EGG1.x * winPixelSize, winStartDrawY + EggsWindowMap.EGG1.y * winPixelSize, winPixelSize );
		}
		if( eggShown > 1)
		{
			if(level.isSecondEggActive())
				drawLabel(EGG,winStartDrawX + EggsWindowMap.EGG2.x * winPixelSize, winStartDrawY + EggsWindowMap.EGG2.y * winPixelSize, winPixelSize );
		}
		if( eggShown > 2)
		{
			if(level.isThirdEggActive())
				drawLabel(EGG,winStartDrawX + EggsWindowMap.EGG3.x * winPixelSize, winStartDrawY + EggsWindowMap.EGG3.y * winPixelSize, winPixelSize );
		}

		// ������ ����� ������
		drawLabel(NUMBER_0_P + (int)level.getPlayerTimeNumber(0),winStartDrawX + EggsWindowMap.PLAYER_HOUR_1.x * winPixelSize, winStartDrawY + EggsWindowMap.PLAYER_HOUR_1.y * winPixelSize, winPixelSize);
		drawLabel(NUMBER_0_P + (int)level.getPlayerTimeNumber(1),winStartDrawX + EggsWindowMap.PLAYER_HOUR_2.x * winPixelSize, winStartDrawY + EggsWindowMap.PLAYER_HOUR_2.y * winPixelSize, winPixelSize);
		drawLabel(NUMBER_0_P + (int)level.getPlayerTimeNumber(2),winStartDrawX + EggsWindowMap.PLAYER_MINUTE_1.x * winPixelSize, winStartDrawY + EggsWindowMap.PLAYER_MINUTE_1.y * winPixelSize, winPixelSize);
		drawLabel(NUMBER_0_P + (int)level.getPlayerTimeNumber(3),winStartDrawX + EggsWindowMap.PLAYER_MINUTE_2.x * winPixelSize, winStartDrawY + EggsWindowMap.PLAYER_MINUTE_2.y * winPixelSize, winPixelSize);
	}
	
	public void draw( float deltaTime )
	{	
		if( level instanceof SettingsLevel )
		{
			drawMap();
			if(Settings.isSoundEnabled())
				drawLabel(SOUND_LABEL);
			switch( Settings.getControl() )
			{
				case 4:
					drawLabel(URDL_LABEL);
					break;
				case 0:
					drawLabel(SLIDE_LABEL);
					break;
				case 1:
					drawLabel(ACCEL_LABEL);
					break;
				case 2:
					drawLabel(LEFTRIGHT_LABEL);
					break;
			}
		}
		else if( level instanceof LevelLevelsList)
		{
			int minimapWidth = ((LevelLevelsList)level).getMinimapWidth() * mapPixelSize;
			int minimapHeight= ((LevelLevelsList)level).getMinimapHeight() * mapPixelSize;
			int ltmapX  = paddingLeft + ((LevelLevelsList)level).getLtmapX() * mapPixelSize;
			int ltmapY  = paddingTop + ((LevelLevelsList)level).getLtmapY() * mapPixelSize;
			int rtmapX  = paddingLeft + ((LevelLevelsList)level).getRtmapX() * mapPixelSize;
			int rtmapY  = paddingTop + ((LevelLevelsList)level).getRtmapY() * mapPixelSize;
			int lbmapX  = paddingLeft + ((LevelLevelsList)level).getLbmapX() * mapPixelSize;
			int lbmapY  = paddingTop + ((LevelLevelsList)level).getLbmapY() * mapPixelSize;
			int rbmapX  = paddingLeft + ((LevelLevelsList)level).getRbmapX() * mapPixelSize;
			int rbmapY  = paddingTop + ((LevelLevelsList)level).getRbmapY() * mapPixelSize;
			//int minimapXDiff = 5 * mapPixelSize;
			/*int ltmapX  = paddingLeft + mapPixelSize * 6;
			int ltmapY  = paddingTop + 10 * mapPixelSize;
			int rtmapX  = ltmapX + minimapXDiff + minimapWidth;
			int rtmapY  = ltmapY;
			int lbmapX  = ltmapX;
			int lbmapY  = ltmapY + minimapYDiff + minimapHeight;
			int rbmapX  = ltmapX + minimapXDiff + minimapWidth;
			int rbmapY  = ltmapY + minimapYDiff + minimapHeight;*/
			
			drawMap();
			drawFood();
			drawTeleports();
			for( int i = 0; i < this.level.getSnakesCount(); i++)
				if( i != this.level.getPlayerSnake() || !this.level.isReadyScreen()  ||  this.level.getReadyTicks() % 2 == 1 )
					drawSnake(this.level.getTicks(), i);
			if ( ((LevelLevelsList)level).isSlide() )
			{
				if ( ((LevelLevelsList)level).getTopLeft() != null )
					drawMapMiniature( ((LevelLevelsList)level).getTopLeft(), ltmapX - ((LevelLevelsList)level).getSlideStartX() + ((LevelLevelsList)level).getSlideX() , ltmapY, minimapWidth, minimapHeight);
				if ( ((LevelLevelsList)level).getTopRight() != null )
					drawMapMiniature( ((LevelLevelsList)level).getTopRight(), rtmapX - ((LevelLevelsList)level).getSlideStartX() + ((LevelLevelsList)level).getSlideX(), rtmapY, minimapWidth, minimapHeight);
				if ( ((LevelLevelsList)level).getBottomLeft() != null )
					drawMapMiniature( ((LevelLevelsList)level).getBottomLeft(), lbmapX - ((LevelLevelsList)level).getSlideStartX() + ((LevelLevelsList)level).getSlideX(), lbmapY, minimapWidth, minimapHeight);
				if ( ((LevelLevelsList)level).getBottomRight() != null )
					drawMapMiniature( ((LevelLevelsList)level).getBottomRight(), rbmapX - ((LevelLevelsList)level).getSlideStartX() + ((LevelLevelsList)level).getSlideX(), rbmapY, minimapWidth, minimapHeight);
			}
			else if ( ((LevelLevelsList)level).getAnimation() != LevelLevelsList.ANIMATION_STOP ) 
			{
				if( levelsSlideAnimationStartTime == 0 )
				{
					levelsSlideAnimationStartTime = System.nanoTime();
					levelsSlideAnimationStartX = ((LevelLevelsList)level).getSlideX() - ((LevelLevelsList)level).getSlideStartX(); 
				}
				
				if (((LevelLevelsList)level).getAnimation() == LevelLevelsList.ANIMATION_LEFT || ((LevelLevelsList)level).getAnimation() == LevelLevelsList.ANIMATION_RIGHT) 
				{
					int move = java.lang.Math.round(((float)((System.nanoTime() - levelsSlideAnimationStartTime))/ 1000000000.0f) * ((LevelLevelsList)level).getAnimationSpeed());
					
					if ( ((LevelLevelsList)level).getTopLeft() != null )
						drawMapMiniature( ((LevelLevelsList)level).getTopLeft(), ltmapX + move + levelsSlideAnimationStartX  , ltmapY, minimapWidth, minimapHeight);
					if ( ((LevelLevelsList)level).getTopRight() != null )
						drawMapMiniature( ((LevelLevelsList)level).getTopRight(), rtmapX + move + levelsSlideAnimationStartX , rtmapY, minimapWidth, minimapHeight);
					if ( ((LevelLevelsList)level).getBottomLeft() != null )
						drawMapMiniature( ((LevelLevelsList)level).getBottomLeft(), lbmapX + move + levelsSlideAnimationStartX , lbmapY, minimapWidth, minimapHeight);
					if ( ((LevelLevelsList)level).getBottomRight() != null )
						drawMapMiniature( ((LevelLevelsList)level).getBottomRight(), rbmapX + move + levelsSlideAnimationStartX , rbmapY, minimapWidth, minimapHeight);
					
					if (( rtmapX + minimapWidth + move + levelsSlideAnimationStartX < 0) || ( ltmapX + move + levelsSlideAnimationStartX > screenWidth ))
					{
						Log.d("OmNomNom", "Move:" + Integer.toString(move) );
						Log.d("OmNomNom", "levelsSlideAnimationStartX:" + Integer.toString(levelsSlideAnimationStartX) );
						levelsSlideAnimationStartTime = System.nanoTime();
						switch(((LevelLevelsList)level).getAnimation())
						{
							case LevelLevelsList.ANIMATION_LEFT:
								((LevelLevelsList)level).decListNumber();
								((LevelLevelsList)level).setAnimation(LevelLevelsList.ANIMATION_PREVIOUS);
								levelsSlideAnimationStartX = - rtmapX - minimapWidth;
								break;
							case LevelLevelsList.ANIMATION_RIGHT:
								((LevelLevelsList)level).incListNumber();
								((LevelLevelsList)level).setAnimation(LevelLevelsList.ANIMATION_NEXT);
								levelsSlideAnimationStartX = screenWidth - ltmapX;
								break;
						}
					}
				}
				else
				{
					int move = java.lang.Math.round((((float)(System.nanoTime() - levelsSlideAnimationStartTime))/ 1000000000.0f) * ((LevelLevelsList)level).getAnimationSpeed());
					
					if ( ((LevelLevelsList)level).getTopLeft() != null )
						drawMapMiniature( ((LevelLevelsList)level).getTopLeft(), ltmapX + move + levelsSlideAnimationStartX  , ltmapY, minimapWidth, minimapHeight);
					if ( ((LevelLevelsList)level).getTopRight() != null )
						drawMapMiniature( ((LevelLevelsList)level).getTopRight(), rtmapX + move + levelsSlideAnimationStartX , rtmapY, minimapWidth, minimapHeight);
					if ( ((LevelLevelsList)level).getBottomLeft() != null )
						drawMapMiniature( ((LevelLevelsList)level).getBottomLeft(), lbmapX + move + levelsSlideAnimationStartX , lbmapY, minimapWidth, minimapHeight);
					if ( ((LevelLevelsList)level).getBottomRight() != null )
						drawMapMiniature( ((LevelLevelsList)level).getBottomRight(), rbmapX + move + levelsSlideAnimationStartX , rbmapY, minimapWidth, minimapHeight);
					
					if ((( rtmapX + move + levelsSlideAnimationStartX < rtmapX) && (((LevelLevelsList)level).getAnimation() ==LevelLevelsList.ANIMATION_NEXT )) || (( rtmapX + move + levelsSlideAnimationStartX > rtmapX) && (((LevelLevelsList)level).getAnimation() ==LevelLevelsList.ANIMATION_PREVIOUS )) )
					{
						((LevelLevelsList)level).setAnimation(LevelLevelsList.ANIMATION_STOP);
						levelsSlideAnimationStartTime = 0;
					}
				}
			}
			else
			{
				if ( ((LevelLevelsList)level).getTopLeft() != null )
					drawMapMiniature( ((LevelLevelsList)level).getTopLeft(), ltmapX, ltmapY, minimapWidth, minimapHeight);
				if ( ((LevelLevelsList)level).getTopRight() != null )
					drawMapMiniature( ((LevelLevelsList)level).getTopRight(), rtmapX, rtmapY, minimapWidth, minimapHeight);
				if ( ((LevelLevelsList)level).getBottomLeft() != null )
					drawMapMiniature( ((LevelLevelsList)level).getBottomLeft(), lbmapX, lbmapY, minimapWidth, minimapHeight);
				if ( ((LevelLevelsList)level).getBottomRight() != null )
					drawMapMiniature( ((LevelLevelsList)level).getBottomRight(), rbmapX, rbmapY, minimapWidth, minimapHeight);
				
				if( ((LevelLevelsList)level).isListAvalible(((LevelLevelsList)level).getListNumber() + 1) )
					drawLabel(NEXTLIST_LABEL);
				if( ((LevelLevelsList)level).isListAvalible(((LevelLevelsList)level).getListNumber() - 1) )
					drawLabel(PREVIOUSLIST_LABEL);
			}
		}
		else 
		{
			drawMap();
			
			if( level.getAqua() )
				drawAquaControl();
			if( level.getPauseButton())
				if( !this.level.isReadyScreen()  || ( this.level.getReadyTicks() % 2 == 1))
					drawPauseButton();
			drawFood();
			drawFinish();
			drawTeleports();
			if( ! level.getEggsWindow() )
			{
				if( Settings.getIsFutureMovement() == 1)
				{
					for( int i = 0; i < this.level.getSnakesCount(); i++)
						if( i != this.level.getPlayerSnake() || !this.level.isReadyScreen()  ||  this.level.getReadyTicks() % 2 == 1 )
							drawSnakeFuture(this.level.getTicks(), i);
				}
				else
				{
					for( int i = 0; i < this.level.getSnakesCount(); i++)
						if( i != this.level.getPlayerSnake() || !this.level.isReadyScreen()  ||  this.level.getReadyTicks() % 2 == 1 )
							drawSnake(this.level.getTicks(), i);
				}
			}
			
			if( level.getEggsWindow())
				drawEggWindow();
		}
	}
	
		
	public GameLevel getLevel()
	{
		return level;
	}
	
	public void setLevel( GameLevel level)
	{
		this.level = level;
		
		mapPixelSize = java.lang.Math.min((screenHeight - paddingTop - paddingBottom)/level.getMap().getMapHeight(), (screenWidth - paddingRight - paddingLeft)/level.getMap().getMapWidth() );
		mapStartDrawX = paddingLeft + (screenWidth  - paddingRight - paddingLeft - mapPixelSize*level.getMap().getMapWidth()) / 2 ;
		mapStartDrawY = paddingTop + (screenHeight - paddingTop - paddingBottom - level.getMap().getMapHeight()*mapPixelSize ) /2;
		
		this.foodSize = (3 * mapPixelSize) / 4;
		this.snakeBodySize = (3 * mapPixelSize) / 4;
		this.snakeHeadSize = mapPixelSize;
		
		eggShown = 0;
		eggShowStart = 0;
	}
	
	public boolean inBounds( TouchEvent event, Rect rct)
	{
		if ( ( event.x > paddingLeft + rct.left * mapPixelSize + mapStartDrawX) &&
				( event.x <  paddingLeft + rct.right * mapPixelSize + mapStartDrawX  ) &&
				( event.y > paddingTop + rct.top * mapPixelSize + mapStartDrawY) &&
				( event.y <  paddingTop + rct.bottom * mapPixelSize + mapStartDrawY  ) )
			return true;
		else
			return false;
	}
	
	public boolean inBoundsEgg( TouchEvent event, Rect rct)
	{
		int winPixelSize = java.lang.Math.min((screenHeight - paddingTop - paddingBottom)/EggsWindowMap.getMapHeightStatic(), (screenWidth - paddingRight - paddingLeft)/EggsWindowMap.getMapWidthStatic() );
		int winStartDrawX = paddingLeft + (screenWidth  - paddingRight - paddingLeft - winPixelSize * EggsWindowMap.getMapWidthStatic()) / 2 ;
		int winStartDrawY = paddingTop + (screenHeight - paddingTop - paddingBottom - EggsWindowMap.getMapHeightStatic() * winPixelSize ) /2;
		
		if ( ( event.x > paddingLeft + rct.left * winPixelSize + winStartDrawX) &&
				( event.x <  paddingLeft + rct.right * winPixelSize + winStartDrawX  ) &&
				( event.y > paddingTop + rct.top * winPixelSize + winStartDrawY) &&
				( event.y <  paddingTop + rct.bottom * winPixelSize + winStartDrawY  ) )
			return true;
		else
			return false;
	}
}
