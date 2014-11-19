package com.lcsmobileapps.glbasics;

import com.lcsmobileapps.framework.Screen;
import com.lcsmobileapps.framework.impl.GLGame;

public class CannonTest extends GLGame{
	@Override
	public Screen getStartScreen() {
		return new CannonScreen(this);
	}
}
