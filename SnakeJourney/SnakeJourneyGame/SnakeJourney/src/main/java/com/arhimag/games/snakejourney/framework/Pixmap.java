package com.arhimag.games.snakejourney.framework;

import com.arhimag.games.snakejourney.framework.Graphics.PixmapFormat;

public interface Pixmap 
{
	public int getWidth();
	public int getHeight();
	public PixmapFormat getFormat();
	public void dispose();
}
