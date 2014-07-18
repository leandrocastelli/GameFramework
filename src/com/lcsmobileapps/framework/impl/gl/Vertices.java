package com.lcsmobileapps.framework.impl.gl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import com.lcsmobileapps.framework.impl.GLGraphics;

public class Vertices {

	final GLGraphics glGraphics;
	final boolean hasColor;
	final boolean hasTexCoords;
	final int vertexSize;
	final FloatBuffer vertices;
	final ShortBuffer indices;
	
	public Vertices (GLGraphics glGraphics, int maxVertices, int maxIndices, boolean hasColor,
			boolean hasTexCoord) {
		this.glGraphics = glGraphics;
		this.hasColor = hasColor;
		this.hasTexCoords = hasTexCoord;
		this.vertexSize = (2 + (hasColor?4:0) + (hasTexCoord?2:0) * 4);
		ByteBuffer buffer = ByteBuffer.allocateDirect(maxVertices * vertexSize);
		buffer.order(ByteOrder.nativeOrder());
		
		vertices = buffer.asFloatBuffer();
		
		if (maxIndices > 0) {
			buffer = ByteBuffer.allocateDirect(maxVertices * Short.SIZE / 8);
			buffer.order(ByteOrder.nativeOrder());
			
			indices = buffer.asShortBuffer();
		} else {
			indices = null;
		}
	}
}
