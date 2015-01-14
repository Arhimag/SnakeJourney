package com.arhimag.games.omnomnom.Achievements;


public class TryAllControls extends GameAchievement {

	public TryAllControls() 
	{
		super();
		
		text = "TRY ALL CONTROLS";
		
		color1 = 0xffaaaaaa;
		color2 = 0xffaaaaaa - 0x002f2f2f;
		
		initGameIcon();
		initListIcon();
	}

}
