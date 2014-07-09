package com.lcsmobileapps.mrnom;

import java.util.List;

import com.lcsmobileapps.framework.Game;
import com.lcsmobileapps.framework.Graphics;
import com.lcsmobileapps.framework.Input.TouchEvent;
import com.lcsmobileapps.framework.Screen;

public class HelpScreen extends Screen{

	public HelpScreen(Game game) {
		super(game);
		// 
	}

	@Override
	public void update(float deltatime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x > 256 && event.y > 416) {
					game.setScreen(new HelpScreen2(game));
					if (Settings.soundEnabled) {
						Assets.click.play(1);
						return;
					}
				}
			}
		}
	}

	@Override
	public void present(float deltatime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.help1, 64, 100);
		g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
		
		
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
