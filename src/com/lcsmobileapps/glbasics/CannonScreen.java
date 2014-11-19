package com.lcsmobileapps.glbasics;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.lcsmobileapps.framework.Game;
import com.lcsmobileapps.framework.Input.TouchEvent;
import com.lcsmobileapps.framework.Screen;
import com.lcsmobileapps.framework.impl.GLGame;
import com.lcsmobileapps.framework.impl.GLGraphics;
import com.lcsmobileapps.framework.impl.gl.Vertices;
import com.lcsmobileapps.framework.math.Vector2;

public class CannonScreen extends Screen{

	float FRUSTUM_WIDTH = 4.8f;
	float FRUSTUM_HEIGHT = 3.2f;
	
	GLGraphics glGraphics;
	
	Vertices vertices;
	Vector2 cannonPos = new Vector2(2.4f, 0.5f);
	float cannonAngle = 0;
	Vector2 touchPos = new Vector2();
	
	public CannonScreen(Game game) {
		super(game);
		glGraphics = ((GLGame)game).getGLGraphics();
		vertices = new Vertices(glGraphics, 3, 0, false,false);
		vertices.setVertices(new float[] {-0.5f, -0.5f,
										0.5f, 0.0f,
										-0.5f, 0.5f	}, 0, 6);
		
	}

	@Override
	public void update(float deltatime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			
			touchPos.x = (event.x / (float)glGraphics.getWidth()) * FRUSTUM_WIDTH;
			touchPos.y = (1 - event.y / (float)glGraphics.getHeigth()) * FRUSTUM_HEIGHT;
			cannonAngle = touchPos.sub(cannonPos).angle();
			
		}
		
	}

	@Override
	public void present(float deltatime) {
		GL10 gl = glGraphics.getGL();
		gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeigth());
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(0, FRUSTUM_WIDTH, 0, FRUSTUM_HEIGHT, 1, -1);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		gl.glTranslatef(cannonPos.x, cannonPos.y, 0);
		gl.glRotatef(cannonAngle, 0, 0, 1);
		vertices.bind();
		vertices.draw(GL10.GL_TRIANGLES, 0, 3);
		vertices.unbind();
		
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
