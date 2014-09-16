package com.arhimag.games.omnomnom.Achievements;


public class MeetAI extends GameAchievement {
	
	public MeetAI() 
	{
		super();
		
		text = "MEET ROBOSNAKE";
		
		color1 = 0xFFADFF2F;
		color2 = 0xFF228B22;
		
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
