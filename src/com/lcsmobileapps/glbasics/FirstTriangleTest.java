package com.lcsmobileapps.glbasics;

import java.nio.FloatBuffer;

import com.lcsmobileapps.framework.Game;
import com.lcsmobileapps.framework.Screen;
import com.lcsmobileapps.framework.impl.GLGame;
import com.lcsmobileapps.framework.impl.GLGraphics;

public class FirstTriangleTest extends GLGame{
	
	@Override
	public Screen getStartScreen() {
		return new FirstTriangleScreen(this);
	}
	
	class FirstTriangleScreen extends Screen{
		GLGraphics glGraphics;
		FloatBuffer vertices;
		FirstTriangleScreen(Game game) {
			super(game);
			glGraphics = ((GLGame)game).getGLGraphics();
		}
		@Override
		public void update(float deltatime) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void present(float deltatime) {
			// TODO Auto-generated method stub
			
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
