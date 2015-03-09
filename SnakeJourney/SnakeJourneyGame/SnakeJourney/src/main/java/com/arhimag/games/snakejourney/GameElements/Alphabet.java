package com.arhimag.games.snakejourney.GameElements;

import android.util.Log;

import com.arhimag.games.snakejourney.GameLevelDrawer;

public class Alphabet 
{
	//��������� �������� ���������� ����� ��������� 
	private static final int charSpace = 1;
	//��������� �������� ������ �������. ����������� � ����������� ����� ���������
	private static final int wordSpace = 2;
	//��������� �������� ���������� ����� ��������.
	private static final int lineSpace = 1;
	
	private static final char alphabet[][][] = {
			{
				{'_','#','#','#','_'},
				{'#','_','_','_','#'},
				{'#','#','#','#','#'},
				{'#','_','_','_','#'},
				{'#','_','_','_','#'}
			},
			{                   
				{'#','#','#','#','_'},
				{'#','_','_','_','#'},
				{'#','#','#','#','_'},
				{'#','_','_','_','#'},
				{'#','#','#','#','_'}
			},
			{                   
				{'_','#','#','#','_'},
				{'#','_','_','_','#'},
				{'#','_','_','_','_'},
				{'#','_','_','_','#'},
				{'_','#','#','#','_'}
			},
			{                   
				{'#','#','#','#','_'},
				{'#','_','_','_','#'},
				{'#','_','_','_','#'},
				{'#','_','_','_','#'},
				{'#','#','#','#','_'}
			},
			{                   
				{'_','#','#','#','#'},
				{'#','_','_','_','_'},
				{'_','#','#','_','_'},
				{'#','_','_','_','_'},
				{'_','#','#','#','#'}
			},
			{                   
				{'#','#','#','#','#'},
				{'#','_','_','_','_'},
				{'#','#','#','#','#'},
				{'#','_','_','_','_'},
				{'#','_','_','_','_'}
			},
			{                   
				{'_','#','#','#','#'},
				{'#','_','_','_','_'},
				{'#','_','_','#','#'},
				{'#','_','_','_','#'},
				{'_','#','#','#','_'}
			},
			{                   
				{'#','_','_','_','#'},
				{'#','_','_','_','#'},
				{'#','#','#','#','#'},
				{'#','_','_','_','#'},
				{'#','_','_','_','#'}
			},
			{                   
				{'_','_','#','_','_'},
				{'_','_','#','_','_'},
				{'_','_','#','_','_'},
				{'_','_','#','_','_'},
				{'_','_','#','_','_'}
			},
			{                   
				{'_','_','#','_','_'},
				{'_','_','#','_','_'},
				{'_','_','#','_','_'},
				{'#','_','#','_','_'},
				{'_','#','_','_','_'}
			},
			{                   
				{'#','_','_','_','#'},
				{'#','_','_','#','_'},
				{'#','#','#','_','_'},
				{'#','_','_','#','_'},
				{'#','_','_','_','#'}
			},
			{                   
				{'#','_','_','_','_'},
				{'#','_','_','_','_'},
				{'#','_','_','_','_'},
				{'#','_','_','_','#'},
				{'#','#','#','#','#'}
			},
			{                   
				{'#','_','_','_','#'},
				{'#','#','_','#','#'},
				{'#','_','#','_','#'},
				{'#','_','_','_','#'},
				{'#','_','_','_','#'}
			},
			{                   
				{'#','_','_','_','#'},
				{'#','#','_','_','#'},
				{'#','_','#','_','#'},
				{'#','_','_','#','#'},
				{'#','_','_','_','#'}
			},
			{                   
				{'_','_','#','_','_'},
				{'_','#','_','#','_'},
				{'#','_','_','_','#'},
				{'_','#','_','#','_'},
				{'_','_','#','_','_'}
			},
			{                   
				{'#','#','#','#','_'},
				{'#','_','_','_','#'},
				{'#','#','#','#','_'},
				{'#','_','_','_','_'},
				{'#','_','_','_','_'}
			},
			{                   
				{'_','#','#','_','_'},
				{'#','_','_','#','_'},
				{'#','_','_','#','_'},
				{'#','_','_','#','_'},
				{'_','#','#','_','#'}
			},
			{                   
				{'#','#','#','#','_'},
				{'#','_','_','_','#'},
				{'#','#','#','#','_'},
				{'#','_','_','_','#'},
				{'#','_','_','_','#'}
			},
			{                   
				{'_','#','#','#','#'},
				{'#','_','_','_','_'},
				{'_','#','#','#','_'},
				{'_','_','_','_','#'},
				{'#','#','#','#','_'}
			},
			{                   
				{'#','#','#','#','#'},
				{'_','_','#','_','_'},
				{'_','_','#','_','_'},
				{'_','_','#','_','_'},
				{'_','_','#','_','_'}
			},
			{                   
				{'#','_','_','_','#'},
				{'#','_','_','_','#'},
				{'#','_','_','_','#'},
				{'#','_','_','_','#'},
				{'_','#','#','#','_'}
			},
			{                   
				{'#','_','_','_','#'},
				{'#','_','_','_','#'},
				{'#','_','_','_','#'},
				{'_','#','_','#','_'},
				{'_','_','#','_','_'}
			},
			{                   
				{'#','_','_','_','#'},
				{'#','_','_','_','#'},
				{'#','_','#','_','#'},
				{'#','_','#','_','#'},
				{'_','#','_','#','_'}
			},
			{                   
				{'#','_','_','_','#'},
				{'_','#','_','#','_'},
				{'_','_','#','_','_'},
				{'_','#','_','#','_'},
				{'#','_','_','_','#'}
				},
			{                   
				{'#','_','_','_','#'},
				{'_','#','_','#','_'},
				{'_','_','#','_','_'},
				{'_','_','#','_','_'},
				{'_','_','#','_','_'}
			},
			{                   
				{'#','#','#','#','#'},
				{'#','_','_','#','_'},
				{'_','_','#','_','_'},
				{'_','#','_','_','#'},
				{'#','#','#','#','#'}
			},
			{                   
				{'_','#','#','#','_'},
				{'_','#','_','#','_'},
				{'_','#','_','#','_'},
				{'_','#','_','#','_'},
				{'_','#','#','#','_'}
			},	
			{                   
				{'_','_','#','_','_'},
				{'_','#','#','_','_'},
				{'_','_','#','_','_'},
				{'_','_','#','_','_'},
				{'_','#','#','#','_'}
			},
			{                   
				{'_','#','#','#','_'},
				{'_','_','_','#','_'},
				{'_','_','#','_','_'},
				{'_','#','_','_','_'},
				{'_','#','#','#','_'}
			},
			{                   
				{'_','#','#','#','_'},
				{'_','_','_','#','_'},
				{'_','_','#','_','_'},
				{'_','_','_','#','_'},
				{'_','#','#','#','_'}
			},
			{                   
				{'_','#','_','#','_'},
				{'_','#','_','#','_'},
				{'_','#','#','#','_'},
				{'_','_','_','#','_'},
				{'_','_','_','#','_'}
			},
			{                   
				{'_','#','#','#','_'},
				{'_','#','_','_','_'},
				{'_','#','#','#','_'},
				{'_','_','_','#','_'},
				{'_','#','#','#','_'}
			},
			{                   
				{'_','#','#','#','_'},
				{'_','#','_','_','_'},
				{'_','#','#','#','_'},
				{'_','#','_','#','_'},
				{'_','#','#','#','_'}
			},
			{                   
				{'_','#','#','#','_'},
				{'_','_','_','#','_'},
				{'_','_','#','_','_'},
				{'_','_','#','_','_'},
				{'_','_','#','_','_'}
			},
			{                   
				{'_','#','#','#','_'},
				{'_','#','_','#','_'},
				{'_','_','#','_','_'},
				{'_','#','_','#','_'},
				{'_','#','#','#','_'}
			},
			{                   
				{'_','#','#','#','_'},
				{'_','#','_','#','_'},
				{'_','#','#','#','_'},
				{'_','_','_','#','_'},
				{'_','#','#','#','_'}
			}
		};

	public static int getLetterWidth()
	{
		return alphabet[0][0].length;
	}
	
	public static int getLetterHeight()
	{
		return alphabet[0].length;
	}
	
	public static int getWordSpace() { return wordSpace + charSpace; }

    public static int getLetterPixel(char letter, int x, int y)
	{
		int letterId = ((int)Character.toUpperCase(letter) - (int)'A');
		
		if( letterId > 25)
		{
			letterId = 0;
			Log.d("Alphabet", "Not correct letter access " + letter);
		}
		
		if(( letterId >= ((int)'0' - (int)'A')) && (letterId <= ((int)'9' - (int)'A')))
		{
			letterId = 26 + letterId + (int)'A' - (int)'0';  
		}
				
		if( x < 0 || x >= getLetterWidth())
		{
			x = 0;
			Log.d("Alphabet", "Not correct x  " + x);
		}
		
		if( y < 0 || y >= getLetterHeight())
		{
			y = 0;
			Log.d("Alphabet", "Not correct y  " + y);
		}
		
		return GameLevelDrawer.getColor(alphabet[letterId][y][x], x , y);
	}
	
	private static void writeLetter( char letter, int[][] canvas, int startX, int startY )
	{
		if(canvas.length - startY < getLetterHeight() || canvas[0].length - startX < getLetterWidth() )
		{	
			Log.d("Alphabet", "Not correct parametres (" + startX + " , " + startY + ")");
			return;
		}
		
		for(int x = 0; x < getLetterWidth(); x++ )
			for(int y = 0; y < getLetterHeight(); y++ )
				canvas[startY + y][startX + x] = getLetterPixel(letter, x, y);
	}
	
	private static int getPhraseLength( String phrase )
	{
		int phraseLength = 0;
		
		for( int i = 0; i < phrase.length(); i++ )
			phraseLength += ((phrase.charAt(i) == ' ')?(charSpace+wordSpace):(alphabet[0][0].length + charSpace));
		
		//����� ��������� ����� �� ����� �������
		
		return phraseLength - charSpace;
	}
	
	//������� ������� � ������������� �� ������
	private static void writePhraseLine( String phrase, int[][] canvas, int startY)
	{
		int phraseLength = getPhraseLength( phrase );
		int currentX;
		
		if( canvas[0].length < phraseLength )
		{
			Log.d("Alphabet","Not enought length of canvas for phrase :'" + phrase + "'  Length:" + phraseLength + " CanvasLength: " + canvas[0].length);
			return ;
		}
		
		currentX = (canvas[0].length - phraseLength)/2;
		
		for( int i = 0; i < phrase.length(); i++)
		{
			if( phrase.charAt(i) == ' ' )
				currentX += charSpace + wordSpace;
			else
			{
				writeLetter(phrase.charAt(i),canvas,currentX,startY);
				currentX += alphabet[0][0].length + charSpace;
			}
		}
	}
	
	public static void writeText( String text, int[][] canvas)
	{
		int currentSpaceIndex = 0;
		int currentLineStart = 0;
		int currentLine = 0;
		String phrase;
		while( text.indexOf(" ", currentSpaceIndex) > 0)
		{
			while( text.indexOf(" ",currentSpaceIndex) >= 0 && text.indexOf(" ",currentSpaceIndex) - currentLineStart < canvas[0].length )
				currentSpaceIndex = text.indexOf(" ",currentSpaceIndex) + 1;
			if( currentSpaceIndex > currentLineStart )
			{
				phrase = text.substring(currentLineStart, currentSpaceIndex - 1);
				if( canvas.length >= currentLine*(alphabet[0].length + lineSpace) + alphabet[0].length)
				{
					writePhraseLine(phrase, canvas, currentLine*(alphabet[0].length + lineSpace) );
					currentLine++;
					currentLineStart = currentSpaceIndex;
				}
				else
				{
					Log.d("Alphabet", "Canvas is not enought wide! for phrase " + text);
				}
			}
			else
			{
				Log.d("Alphabet","Canvas not anought long for this string: " + text);
				return;
			}	
		}
	}
}
