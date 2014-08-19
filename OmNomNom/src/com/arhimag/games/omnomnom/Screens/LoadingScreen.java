package com.arhimag.games.omnomnom.Screens;

import com.arhimag.games.omnomnom.Assets;
import com.arhimag.games.omnomnom.LevelSequence;
import com.arhimag.games.omnomnom.Settings;
import com.arhimag.games.omnomnom.framework.Game;
import com.arhimag.games.omnomnom.framework.Screen;

public class LoadingScreen extends Screen
{
	public LoadingScreen(Game game)
	{
		super(game);
	}
	
	public void update( float deltaTime)
	{
		Assets.eat = game.getAudio().newSound("eat.ogg");
		Assets.supper = game.getAudio().newSound("super.ogg");
		Settings.load(game.getFileIO());
		LevelSequence.Initialize();
		game.setScreen(new MainMenuScreen(game));
	}
	
	@Override
	public void present(float deltaTime)
	{
		
	}
	
	@Override
	public void pause()
	{
	
	}
	
	@Override
	public void resume()
	{
		
	}
	
	@Override
	public void dispose()
	{
		
	}
}

