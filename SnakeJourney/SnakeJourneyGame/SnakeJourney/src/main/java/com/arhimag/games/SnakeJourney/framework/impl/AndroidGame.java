package com.arhimag.games.SnakeJourney.framework.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.arhimag.games.SnakeJourney.Settings;
import com.arhimag.games.SnakeJourney.Social.VKontakteListener;
import com.arhimag.games.SnakeJourney.framework.Audio;
import com.arhimag.games.SnakeJourney.framework.FileIO;
import com.arhimag.games.SnakeJourney.framework.Game;
import com.arhimag.games.SnakeJourney.framework.Graphics;
import com.arhimag.games.SnakeJourney.framework.Input;
import com.arhimag.games.SnakeJourney.framework.Screen;
import com.facebook.AppEventsLogger;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKUIHelper;



public abstract class AndroidGame extends Activity implements Game
{
	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
    VKontakteListener Vklstnr;
    public static UiLifecycleHelper uiHelper;
    public static Activity faceBookActivityLink;
	//WakeLock wakeLock;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

        faceBookActivityLink = this;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON ,  WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
		
		// boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		// ����������  ������ ������� getWidth ���� ����, ����� ������������ 8�� ����������� API
		@SuppressWarnings("deprecation")
		int frameBufferWidth = getWindowManager().getDefaultDisplay().getWidth(); //isLandscape ? 800 : 600;
		// ����������  ������ ������� getHeight ���� ����, ����� ������������ 8�� ����������� API
		@SuppressWarnings("deprecation")
		int frameBufferHeight = getWindowManager().getDefaultDisplay().getHeight(); //isLandscape ? 600 : 800;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,frameBufferHeight,Config.RGB_565);
		// ����������  ������ ������� getWidth ���� ����, ����� ������������ 8�� ����������� API
		@SuppressWarnings("deprecation")
		float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
		// ����������  ������ ������� getHeight ���� ����, ����� ������������ 8�� ����������� API
		@SuppressWarnings("deprecation")
		float scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
		
		renderView = new AndroidFastRenderView( this, frameBuffer);
		graphics = new AndroidGraphics( getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(getAssets());
		audio = new AndroidAudio(this);
		input = new AndroidInput( this, renderView, scaleX, scaleY);
		screen = getStartScreen();
		setContentView(renderView);


        //Adding Vkontakte
        Vklstnr = new VKontakteListener();
        VKUIHelper.onCreate(this);

        VKAccessToken  vkToken = VKAccessToken.tokenFromFile(Settings.vkontakteTokenFileName);


        if( vkToken == null )
        {
            VKSdk.initialize(Vklstnr, Settings.vkontakteApplicationId );
        }
        else
        {
            VKSdk.initialize(Vklstnr, Settings.vkontakteApplicationId, vkToken);
        }

        uiHelper = new UiLifecycleHelper(this, null);
        uiHelper.onCreate(savedInstanceState);

        //if (VKSdk.wakeUpSession()) {
		//PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		//wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
	}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

	@Override
	public void onResume()
	{
		super.onResume();
        VKUIHelper.onResume(this);
        uiHelper.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
        //wakeLock.acquire();
		screen.resume();
		renderView.resume();

	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		//wakeLock.release();
		renderView.pause();
        uiHelper.onPause();
		screen.pause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
		if( isFinishing())
			screen.dispose();
	}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VKUIHelper.onDestroy(this);
        uiHelper.onDestroy();
    }

    @Override
	public Input getInput()
	{
		return input;
	}

	@Override
	public FileIO getFileIO()
	{
		return fileIO;
	}

	@Override
	public Graphics getGraphics()
	{
		return graphics;
	}

	@Override
	public Audio getAudio()
	{
		return audio;
	}

	@Override
	public void setScreen(Screen screen)
	{
		if( screen == null)
			throw new IllegalArgumentException("Screen Must not be null");
		
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}

	@Override
	public Screen getCurrentScreen()
	{
		return screen;
	}
	
	@SuppressWarnings("deprecation")
	public int getDisplayWidth()
	{
		// ����������  ������ ������� getWidth ���� ����, ����� ������������ 8�� ����������� API
		return  getWindowManager().getDefaultDisplay().getWidth();
	}
	
	@SuppressWarnings("deprecation")
	public int getDisplayHeight()
	{
		// ����������  ������ ������� getHeight ���� ����, ����� ������������ 8�� ����������� API
		return  getWindowManager().getDefaultDisplay().getHeight();
	}

	@Override
	public abstract Screen getStartScreen();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        VKUIHelper.onActivityResult(this, requestCode, resultCode, data);

        uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
            @Override
            public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
                Log.e("Activity", String.format("Error: %s", error.toString()));
            }

            @Override
            public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
                Log.i("Activity", "Success!");
            }
        });
    }

}
