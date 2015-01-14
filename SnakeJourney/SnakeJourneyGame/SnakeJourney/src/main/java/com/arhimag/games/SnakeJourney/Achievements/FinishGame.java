package com.arhimag.games.SnakeJourney.Achievements;


public class FinishGame extends GameAchievement {

	public FinishGame() 
	{
		super();
		
		text = "Finish the game";
		
		color1 = 0xFFFFFFFF;
		color2 = 0xFF000000;
		
		initGameIcon();
		initListIcon();
	}

}
