package com.lcsmobileapps.framework.impl;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.lcsmobileapps.framework.Audio;
import com.lcsmobileapps.framework.Music;
import com.lcsmobileapps.framework.Sound;

public class AndroidAudio implements Audio{

	AssetManager assets;
	SoundPool soundPool;
	
	public AndroidAudio (Activity activity) {
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}
	@Override
	public Music newMusic(String fileName) {
		try {
			AssetFileDescriptor assetFileDescriptor = assets.openFd(fileName);
			return new AndroidMusic(assetFileDescriptor);
		}
		catch (IOException ex) {
			throw new RuntimeException  ("Couldn't load music "+fileName);
		}
	}

	@Override
	public Sound newSound(String fileName) {
		try {
			AssetFileDescriptor assetFileDescriptor = assets.openFd(fileName);
			int soundId = soundPool.load(assetFileDescriptor, 0);
			return new AndroidSound(soundPool,soundId);
		}
		catch (IOException e) {
			throw new RuntimeException("Couldn't load sound "+fileName);
		}
	}

}
