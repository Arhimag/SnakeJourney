package com.arhimag.games.SnakeJourney.framework;

import com.arhimag.games.SnakeJourney.framework.Graphics.PixmapFormat;

public interface Pixmap 
{
	public int getWidth();
	public int getHeight();
	public PixmapFormat getFormat();
	public void dispose();
}
