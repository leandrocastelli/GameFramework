package com.lcsmobileapps.glbasics;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

import com.lcsmobileapps.framework.Game;
import com.lcsmobileapps.framework.Screen;
import com.lcsmobileapps.framework.impl.GLGame;
import com.lcsmobileapps.framework.impl.GLGraphics;
import com.lcsmobileapps.framework.impl.gl.Texture;

public class FirstTriangleTest extends GLGame{
	
	final int VERTEX_SIZE = (2 + 2) * 4;
	
	@Override
	public Screen getStartScreen() {
		return new FirstTriangleScreen(this);
	}
	
	class FirstTriangleScreen extends Screen{
		GLGraphics glGraphics;
		FloatBuffer vertices;
		int textureId;
		ShortBuffer indices;
		Texture texture;
		FirstTriangleScreen(Game game) {
			super(game);
			glGraphics = ((GLGame)game).getGLGraphics();
			
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * VERTEX_SIZE);
			byteBuffer.order(ByteOrder.nativeOrder());
			vertices = byteBuffer.asFloatBuffer();
			
			vertices.put(new float[] {100.0f,100.0f, 0.0f,1.0f,
										228.0f, 100.0f,1.0f,1.0f,
										228.0f,228.0f,1.0f,0.0f,
										100.0f,228.0f,0.0f,0.0f});
			vertices.flip();
			
			byteBuffer = ByteBuffer.allocateDirect(6*2);
			byteBuffer.order(ByteOrder.nativeOrder());
			indices = byteBuffer.asShortBuffer();
			
			indices.put(new short[] {0,1,2,
										2,3,0});
			indices.flip();
			
			
			texture = new Texture((GLGame)game,"bobrgb888.png");
		}
		public int loadTexture(String fileName) {
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(game.getFileIO().readAsset(fileName));
				GL10 gl = glGraphics.getGL();
				int texturesId[] = new int[1];
				gl.glGenTextures(1, texturesId,0);
				int textureId = texturesId[0];
				gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
				GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
				gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
				gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
				gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
				bitmap.recycle();
				return textureId;
			}
			catch (IOException e) {
				Log.d("TexturedTriangleTest", "couldn't load asset 'bobrgb888.png'!");
				throw new RuntimeException("couldn't load asset '" + fileName + "'");
			}
		}
		@Override
		public void update(float deltatime) {
			 game.getInput().getTouchEvents();
	            game.getInput().getKeyEvents();
			
		}
		@Override
		public void present(float deltatime) {
			GL10 gl = glGraphics.getGL();
			
			gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeigth());
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glOrthof(0, 320, 0, 480, 1, -1);
			
			gl.glEnable(GL10.GL_TEXTURE_2D);
	        //gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
			//gl.glColor4f(1, 0, 0, 1);
			texture.bind();
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			vertices.position(0);
			gl.glVertexPointer(2, GL10.GL_FLOAT, VERTEX_SIZE, vertices);
			vertices.position(2);
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, VERTEX_SIZE, vertices);
			gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_SHORT, indices);
			
			
			
			
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
