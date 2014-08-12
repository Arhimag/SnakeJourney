package com.arhimag.games.omnomnom;

import com.arhimag.games.omnomnom.Screens.LoadingScreen;
import com.arhimag.games.omnomnom.framework.Screen;
import com.arhimag.games.omnomnom.framework.impl.AndroidGame;

public class SnakeJourney extends AndroidGame
{
	@Override
	public Screen getStartScreen()
	{
		return new LoadingScreen(this);
	}
}
