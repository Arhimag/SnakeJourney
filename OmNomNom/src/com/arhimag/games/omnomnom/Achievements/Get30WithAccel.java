package com.arhimag.games.omnomnom.Achievements;


public class Get30WithAccel extends GameAchievement {
	//TODO Activate achievement Get30WithAccel
	/**
	 * 
	 */
	public Get30WithAccel() 
	{
		super();
		
		text = "size 30 at accel";
		
		color1 = 0xFFFFF68F;
		color2 = 0xFFEEE685;
		
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
