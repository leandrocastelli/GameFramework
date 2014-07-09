package com.lcsmobileapps.framework.impl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.lcsmobileapps.framework.Audio;
import com.lcsmobileapps.framework.FileIO;
import com.lcsmobileapps.framework.Game;
import com.lcsmobileapps.framework.Graphics;
import com.lcsmobileapps.framework.Input;
import com.lcsmobileapps.framework.Screen;
import com.lcsmobileapps.mrnom.LoadingScreen;

public abstract class AndroidGame extends Activity implements Game {

	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.RGB_565);
		float scaleX;
		float scaleY;
		
		if (VERSION.SDK_INT >= 13) {
			Point screenSize = new Point();
			getWindowManager().getDefaultDisplay().getSize(screenSize);
			scaleX = (float) frameBufferWidth /  screenSize.x;
			scaleY = (float) frameBufferHeight /  screenSize.y;
		}
		else {
			 scaleX = (float) frameBufferWidth
		                / getWindowManager().getDefaultDisplay().getWidth();
		     scaleY = (float) frameBufferHeight
		                / getWindowManager().getDefaultDisplay().getHeight();

		}
		renderView = new AndroidFastRenderView(this, frameBuffer);
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(this);
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		screen = getStartScreen();
		setContentView(renderView);
		
		PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
		
		
	}
	@Override
	public Input getInput() {
		return input;
	}

	@Override
	public FileIO getFileIO() {
		return fileIO;
	}

	@Override
	public Graphics getGraphics() {
		return graphics;
	}

	@Override
	public Audio getAudio() {
		return audio;
	}

	@Override
	public void setScreen(Screen screen) {
		if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
	}

	@Override
	public Screen getCurrentScreen() {
		return screen;
	}

	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}
	
	@Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }
	 @Override
	    public void onPause() {
	        super.onPause();
	        wakeLock.release();
	        renderView.pause();
	        screen.pause();

	        if (isFinishing())
	            screen.dispose();
	    }

}
