package com.arhimag.games.snakejourney.framework;

public interface Audio 
{
	public Music newMusic(String filename);
	public Sound newSound(String filename);
    public void vibrate( int  time );
}
