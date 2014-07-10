package com.lcsmobileapps.glbasics;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.lcsmobileapps.framework.Game;
import com.lcsmobileapps.framework.Screen;
import com.lcsmobileapps.framework.impl.GLGame;
import com.lcsmobileapps.framework.impl.GLGraphics;

public class GLGameTest extends GLGame{
	
	@Override
	public Screen getStartScreen() {
		return new TestScreen(this);
	}
	
	class TestScreen extends Screen{

		GLGraphics glGraphics;
		Random rand = new Random();

		public TestScreen(Game game) {
			super(game);
			glGraphics = ((GLGame)game).getGLGraphics();
		}

		@Override
		public void update(float deltatime) {
			// TODO Auto-generated method stub

		}

		@Override
		public void present(float deltatime) {
			GL10 gl = glGraphics.getGL();
			gl.glClearColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

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
}
