package com.arhimag.games.omnomnom.Achievements;


public class ReachLevel30 extends GameAchievement {

	public ReachLevel30() 
	{
		super();
		
		text = "Reach Level 30";
		
		color1 = 0xFFFF00FF;
		color2 = 0xFFFF00FF;
		
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
