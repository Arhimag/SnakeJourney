package com.arhimag.games.omnomnom.Achievements;


public class TryAllControls extends GameAchievement {

	public TryAllControls() 
	{
		super();
		
		text = "TRY ALL CONTROLS";
		
		color1 = 0xffaaaaaa;
		color2 = 0xffaaaaaa - 0x002f2f2f;
		
		gameIcon = new int[][] {
			{color1, color2, color1, color2, 0xFFFFFFFF},
			{color2, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000},
			{color1, 0xFF000000, color1, color2, color1},
			{color2, 0xFF000000, 0xFF000000, 0xFF000000, color2},
			{color1, color2, color1, color2, color1}
		};
		
		listIcon = new int[][] {
				{color1, color2, color1, color2, 0xFFFFFFFF},
				{color2, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000},
				{color1, color2, color1, color2, color1},
				{0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, color2},
				{color1, color2, color1, color2, color1},
			};
	}

}
