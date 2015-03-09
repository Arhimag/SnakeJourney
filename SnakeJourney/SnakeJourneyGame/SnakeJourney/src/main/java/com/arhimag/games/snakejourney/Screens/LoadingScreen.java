package com.arhimag.games.snakejourney.Screens;

import com.arhimag.games.snakejourney.Assets;
import com.arhimag.games.snakejourney.LevelSequence;
import com.arhimag.games.snakejourney.Settings;
import com.arhimag.games.snakejourney.framework.Game;
import com.arhimag.games.snakejourney.framework.Screen;

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
        Assets.getAchievment = game.getAudio().newSound("Achievement.ogg");
        Assets.getEgg = game.getAudio().newSound("GetEgg.ogg");
        Assets.notGetEgg = game.getAudio().newSound("NotGetEgg.ogg");
        Assets.hurt = game.getAudio().newSound("Hurt.ogg");
        Assets.opponentFinish = game.getAudio().newSound("OpponentFinish.ogg");
        Assets.finish = game.getAudio().newSound("YouFinish.ogg");
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

