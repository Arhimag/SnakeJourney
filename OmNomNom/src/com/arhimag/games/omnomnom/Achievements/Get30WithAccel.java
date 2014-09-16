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
		
		gameIcon = new int[][] {
			{0xFFFFF200, 0xFFFE2030, 0xFFFFF200, 0xFFFE2030, 0xFFFFFFFF},
			{0xFFFE2030, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000},
			{0xFFFFF200, 0xFF000000, 0xFFFFF200, 0xFFFE2030, 0xFFFFF200},
			{0xFFFE2030, 0xFF000000, 0xFF000000, 0xFF000000, 0xFFFE2030},
			{0xFFFFF200, 0xFFFE2030, 0xFFFFF200, 0xFFFE2030, 0xFFFFF200}
		};
		
		listIcon = new int[][] {
				{0xFFFFF200, 0xFFFE2030, 0xFFFFF200, 0xFFFE2030, 0xFFFFFFFF},
				{0xFFFE2030, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000},
				{0xFFFFF200, 0xFFFE2030, 0xFFFFF200, 0xFFFE2030, 0xFFFFF200},
				{0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFFFE2030},
				{0xFFFFF200, 0xFFFE2030, 0xFFFFF200, 0xFFFE2030, 0xFFFFF200},
			};

	}

}
