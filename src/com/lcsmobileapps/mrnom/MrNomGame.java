package com.lcsmobileapps.mrnom;


import android.os.Bundle;

import com.lcsmobileapps.framework.Screen;
import com.lcsmobileapps.framework.impl.AndroidGame;


public class MrNomGame extends AndroidGame {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        
    }
	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}
}
