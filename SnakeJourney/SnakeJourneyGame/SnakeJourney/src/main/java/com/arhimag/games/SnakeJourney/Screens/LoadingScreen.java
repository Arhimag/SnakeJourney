package com.arhimag.games.SnakeJourney.Screens;

import com.arhimag.games.SnakeJourney.Assets;
import com.arhimag.games.SnakeJourney.LevelSequence;
import com.arhimag.games.SnakeJourney.Settings;
import com.arhimag.games.SnakeJourney.framework.Game;
import com.arhimag.games.SnakeJourney.framework.Screen;

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
        Assets.gameMusic = game.getAudio().newMusic("lossgame.mp3");
        Assets.gameMusic.setLooping(true);
        Assets.gameMusic.setVolume(0.1f);
        Assets.menuMusic = game.getAudio().newMusic("lossmenu.mp3");
        Assets.menuMusic.setLooping(true);
        Assets.menuMusic.setVolume(0.1f);
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

