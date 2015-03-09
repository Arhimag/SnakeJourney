package com.arhimag.games.snakejourney.Levels;

import com.arhimag.games.snakejourney.Food;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.PlayerSnake;
import com.arhimag.games.snakejourney.Settings;
import com.arhimag.games.snakejourney.Snake;
import com.arhimag.games.snakejourney.framework.Point;

public class LevelBottle extends GameLevel
{
    public static final int NO_ACTION = 0;
    //SLIDE
    public static final int SLIDE_UP = 1;
    public static final int SLIDE_DOWN = 2;
    public static final int SLIDE_RIGHT = 3;
    public static final int SLIDE_LEFT = 4;
    //LR
    public static final int TURN_RIGHT = 5;
    public static final int TURN_LEFT = 6;
    //UDRL
    public static final int CLICK_UP = 7;
    public static final int CLICK_DOWN = 8;
    public static final int CLICK_RIGHT = 9;
    public static final int CLICK_LEFT = 10;
    //ACCEL
    public static final int ACCEL_RIGHT = 11;
    public static final int ACCEL_LEFT = 12;
    public static final int ACCEL_UP = 13;
    public static final int ACCEL_DOWN = 14;

    int snakeStartX = 12;
    int snakeStartY = 6;

	public LevelBottle(GameMap map)
	{
		super(map);
		
		egg1 = (long)3600 * (long)1000000000 - 1; // 1 ���
		egg2 = (long)300 * (long)1000000000; // 5 �����
		egg3 = (long)180 * (long)1000000000; // 1 ������
		
		snakes = new Snake[1];
		moved = new boolean[1];

		snakes[0] = new PlayerSnake(snakeStartX, snakeStartY);
		snakes[0].lastx = snakeStartX - 1;
		snakes[0].lasty = snakeStartY;
		snakes[0].direction = Snake.RIGHT;
		snakes[0].finishSize = 1;
		
		food = new Food[2];
		food[0] = new Food( 14, 3 );
		food[1] = new Food( 15, 3 );

		finishes = new Point[2];
		
		finishes[0] = new Point(14,2);
		finishes[1] = new Point(15,2);

		aqua = false;
		pauseButton = true;
		winLength = 1;
	}
	
	public void update(float deltaTime, int snake_id )
	{
		super.update(deltaTime, snake_id);
	}

    @Override
    protected void generateNewFood(int i)
    {
        if ( i == 0 )
        {
            food[i].y = 15;
            if( food[i].y == 3 )
                food[i].y = 5;
            else
                food[i].y = 3;
        }
        else if ( i == 1 )
        {
            food[i].x = 14;
            if( food[i].y == 3 )
                food[i].y = 5;
            else
                food[i].y = 3;
        }
    }

    public int getSnakeDirection()
    {
        return snakes[0].direction;
    }

    public int getFieldDirection(int x, int y )
    {
        if ( ( snakes[0].parts.get(0).x == snakeStartX ) && ( snakes[0].parts.get(0).y <= snakeStartY + 8 ) )
            return Snake.DOWN;
        if ( ( snakes[0].parts.get(0).y == snakeStartY + 9 )  && ( snakes[0].parts.get(0).x <= snakeStartX + 4 ) )
            return Snake.RIGHT;
        if ( ( ( snakes[0].parts.get(0).y >= snakeStartY + 1 )  && ( snakes[0].parts.get(0).x == snakeStartX + 5 ) ) ||
                ( ( snakes[0].parts.get(0).y <= snakeStartY  )  && ( snakes[0].parts.get(0).x == snakeStartX + 2 ) ) ||
                ( ( snakes[0].parts.get(0).y <= snakeStartY  )  && ( snakes[0].parts.get(0).x == snakeStartX + 3 ) ) )
            return Snake.UP;
        else
            return Snake.LEFT;
    }

    public int getNeedDirection()
    {
        return getFieldDirection(snakes[0].parts.get(0).x, snakes[0].parts.get(0).y );
    }

    public int getNeedPlayerAction()
    {
        int current_direction = getSnakeDirection();
        int need_direction = getNeedDirection();

        if ( current_direction == need_direction )
            return NO_ACTION;
        else
        {
            switch (need_direction)
            {
                case Snake.UP:
                    switch (current_direction)
                    {
                        case Snake.DOWN:
                            switch ( Settings.getControl() )
                            {
                                case Settings.ACCEL_CONTROL:
                                    if( snakes[0].parts.size() > 1)
                                        return ACCEL_RIGHT;
                                    else
                                        return ACCEL_UP;
                                case Settings.LEFTRIGHT_CONTROL:
                                    return TURN_LEFT;
                                case Settings.SLIDE_CONTROL:
                                    if( snakes[0].parts.size() > 1)
                                        return SLIDE_LEFT;
                                    else
                                        return SLIDE_UP;
                                case Settings.URDL_CONTROL:
                                    if( snakes[0].parts.size() > 1)
                                        return CLICK_LEFT;
                                    else
                                        return CLICK_UP;
                            }
                            break;
                        case Snake.RIGHT:
                            switch ( Settings.getControl() )
                            {
                                case Settings.ACCEL_CONTROL:
                                    return ACCEL_UP;
                                case Settings.LEFTRIGHT_CONTROL:
                                    return TURN_LEFT;
                                case Settings.SLIDE_CONTROL:
                                    return SLIDE_UP;
                                case Settings.URDL_CONTROL:
                                    return CLICK_UP;
                            }
                            break;
                        case Snake.LEFT:
                            switch ( Settings.getControl() )
                            {
                                case Settings.ACCEL_CONTROL:
                                    return ACCEL_UP;
                                case Settings.LEFTRIGHT_CONTROL:
                                    return TURN_RIGHT;
                                case Settings.SLIDE_CONTROL:
                                    return SLIDE_UP;
                                case Settings.URDL_CONTROL:
                                    return CLICK_UP;
                            }
                            break;
                    }
                    break;
                case Snake.DOWN:
                    switch (current_direction)
                    {
                        case Snake.UP:
                            switch ( Settings.getControl() )
                            {
                                case Settings.ACCEL_CONTROL:
                                    if( snakes[0].parts.size() > 1)
                                        return ACCEL_RIGHT;
                                    else
                                        return ACCEL_DOWN;
                                case Settings.LEFTRIGHT_CONTROL:
                                    return TURN_RIGHT;
                                case Settings.SLIDE_CONTROL:
                                    if( snakes[0].parts.size() > 1)
                                        return SLIDE_RIGHT;
                                    else
                                        return SLIDE_DOWN;
                                case Settings.URDL_CONTROL:
                                    if( snakes[0].parts.size() > 1)
                                        return CLICK_RIGHT;
                                    else
                                        return CLICK_DOWN;
                            }
                            break;
                        case Snake.RIGHT:
                            switch ( Settings.getControl() )
                            {
                                case Settings.ACCEL_CONTROL:
                                    return ACCEL_DOWN;
                                case Settings.LEFTRIGHT_CONTROL:
                                    return TURN_RIGHT;
                                case Settings.SLIDE_CONTROL:
                                    return SLIDE_DOWN;
                                case Settings.URDL_CONTROL:
                                    return CLICK_DOWN;
                            }
                            break;
                        case Snake.LEFT:
                            switch ( Settings.getControl() )
                            {
                                case Settings.ACCEL_CONTROL:
                                    return ACCEL_DOWN;
                                case Settings.LEFTRIGHT_CONTROL:
                                    return TURN_LEFT;
                                case Settings.SLIDE_CONTROL:
                                    return SLIDE_DOWN;
                                case Settings.URDL_CONTROL:
                                    return CLICK_DOWN;
                            }
                            break;
                    }
                    break;
                case Snake.LEFT:
                    switch (current_direction)
                    {
                        case Snake.UP:
                            switch ( Settings.getControl() )
                            {
                                case Settings.ACCEL_CONTROL:
                                    return ACCEL_LEFT;
                                case Settings.LEFTRIGHT_CONTROL:
                                    return TURN_LEFT;
                                case Settings.SLIDE_CONTROL:
                                    return SLIDE_LEFT;
                                case Settings.URDL_CONTROL:
                                    return CLICK_LEFT;
                            }
                            break;
                        case Snake.DOWN:
                            switch ( Settings.getControl() )
                            {
                                case Settings.ACCEL_CONTROL:
                                    return ACCEL_LEFT;
                                case Settings.LEFTRIGHT_CONTROL:
                                    return TURN_RIGHT;
                                case Settings.SLIDE_CONTROL:
                                    return SLIDE_LEFT;
                                case Settings.URDL_CONTROL:
                                    return CLICK_LEFT;
                            }
                            break;
                        case Snake.RIGHT:
                            switch ( Settings.getControl() )
                            {
                                case Settings.ACCEL_CONTROL:
                                    if( snakes[0].parts.size() > 1 )
                                        return ACCEL_DOWN;
                                    else
                                        return ACCEL_RIGHT;
                                case Settings.LEFTRIGHT_CONTROL:
                                    return TURN_LEFT;
                                case Settings.SLIDE_CONTROL:
                                    if( snakes[0].parts.size() > 1 )
                                        return SLIDE_DOWN;
                                    else
                                        return SLIDE_LEFT;
                                case Settings.URDL_CONTROL:
                                    if( snakes[0].parts.size() > 1 )
                                        return CLICK_DOWN;
                                    else
                                        return CLICK_LEFT;
                            }
                            break;
                    }
                    break;
                case Snake.RIGHT:
                    switch (current_direction)
                    {
                        case Snake.UP:
                            switch ( Settings.getControl() )
                            {
                                case Settings.ACCEL_CONTROL:
                                    return ACCEL_RIGHT;
                                case Settings.LEFTRIGHT_CONTROL:
                                    return TURN_RIGHT;
                                case Settings.SLIDE_CONTROL:
                                    return SLIDE_RIGHT;
                                case Settings.URDL_CONTROL:
                                    return CLICK_RIGHT;
                            }
                            break;
                        case Snake.DOWN:
                            switch ( Settings.getControl() )
                            {
                                case Settings.ACCEL_CONTROL:
                                    return ACCEL_RIGHT;
                                case Settings.LEFTRIGHT_CONTROL:
                                    return TURN_LEFT;
                                case Settings.SLIDE_CONTROL:
                                    return SLIDE_RIGHT;
                                case Settings.URDL_CONTROL:
                                    return CLICK_RIGHT;
                            }
                            break;
                        case Snake.LEFT:
                            switch ( Settings.getControl() )
                            {
                                case Settings.ACCEL_CONTROL:
                                    if( snakes[0].parts.size() > 1 )
                                        return ACCEL_UP;
                                    else
                                        return ACCEL_RIGHT;
                                case Settings.LEFTRIGHT_CONTROL:
                                    return TURN_LEFT;
                                case Settings.SLIDE_CONTROL:
                                    if( snakes[0].parts.size() > 1 )
                                        return SLIDE_UP;
                                    else
                                        return SLIDE_RIGHT;
                                case Settings.URDL_CONTROL:
                                    if( snakes[0].parts.size() > 1 )
                                        return CLICK_UP;
                                    else
                                        return CLICK_RIGHT;
                            }
                            break;
                    }
                    break;
            }
        }
        return NO_ACTION;
    }
}
