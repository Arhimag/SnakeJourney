package com.arhimag.games.snakejourney;

import com.arhimag.games.snakejourney.Screens.GameScreen;
import com.arhimag.games.snakejourney.Screens.LoadingScreen;
import com.arhimag.games.snakejourney.framework.Screen;
import com.arhimag.games.snakejourney.framework.impl.AndroidGame;

public class SnakeJourney extends AndroidGame
{
	@Override
	public Screen getStartScreen()
	{
		return new LoadingScreen(this);
	}

    @Override
    public void setScreen(Screen screen)
    {
        Screen oldScreen = this.getCurrentScreen();
        super.setScreen(screen);
        if ( Settings.isSoundEnabled() )
        {
            if ((oldScreen instanceof GameScreen || this.getCurrentScreen() instanceof GameScreen) && (!(oldScreen instanceof GameScreen) || !(this.getCurrentScreen() instanceof GameScreen)) && !(this.getCurrentScreen() instanceof LoadingScreen))
            {
                if (this.getCurrentScreen() instanceof GameScreen)
                {
                    Assets.menuMusic.stop();
                    Assets.gameMusic.play();
                } else
                {
                    Assets.gameMusic.stop();
                    Assets.menuMusic.play();
                }
            }
            else if ((oldScreen instanceof LoadingScreen ) &&  !(this.getCurrentScreen() instanceof LoadingScreen))
            {
                if (this.getCurrentScreen() instanceof GameScreen)
                {
                    Assets.menuMusic.stop();
                    Assets.gameMusic.play();
                } else
                {
                    Assets.gameMusic.stop();
                    Assets.menuMusic.play();
                }
            }

        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if ( Settings.isSoundEnabled() )
        {
            if (this.getCurrentScreen() instanceof GameScreen)
            {
                Assets.menuMusic.stop();
                Assets.gameMusic.play();
            } else if (!( this.getCurrentScreen() instanceof LoadingScreen ))
            {
                Assets.gameMusic.stop();
                Assets.menuMusic.play();
            }
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if( Assets.menuMusic != null )
            Assets.menuMusic.stop();
        if( Assets.gameMusic != null )
            Assets.gameMusic.stop();
    }

}
