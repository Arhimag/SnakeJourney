package com.arhimag.games.snakejourney.Achievements;


import android.app.Activity;
import android.content.Context;

import com.arhimag.games.snakejourney.R;

public class CelebrateChristmas extends GameAchievement {

	public CelebrateChristmas() 
	{
		super();

        PLAY_ACHIEVEMENT_ID = "CgkIwJ-mi_ALEAIQDA";

		text = "Happy new year";
		
		color1 = 0xFFFF0000;
		color2 = 0xFF00FF00;
		
		initGameIcon();
		initListIcon();

	}

}
