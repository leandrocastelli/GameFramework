package com.lcsmobileapps.mrnom;

import java.util.Random;

public class World {

	static final int WORLD_WIDTH = 10;
	static final int WORLD_HEIGHT = 13;
	static final int SCORE_INCREMENT = 10;
	static final float TICK_INITIAL = 0.5f;
	static final float TICK_DECREMENT = 0.05f;
	
	public Snake snake;
	public Stain stain;
	
	public boolean gameOver = false;
	public int score = 0;
	
	boolean[][] fields = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
	Random random = new Random();
	float tickTime = 0;
	static float tick = TICK_INITIAL;
	
	public World() {
		snake = new Snake();
		placeStain();
	}
	public void placeStain() {
		for (int x = 0; x < WORLD_WIDTH; x++) {
			for (int y = 0; y < WORLD_HEIGHT; y++) {
				fields[x][y] = false;
			}
		}
		
		int len = snake.parts.size();
		for (int i = 0; i < len; i++) {
			SnakePart part = snake.parts.get(i);
			fields[part.x][part.y] = true;
		}
		int stainX = random.nextInt(WORLD_WIDTH);
		int stainY = random.nextInt(WORLD_HEIGHT);
		
		while (true) {
			if (fields[stainX][stainY] == false) {
				break;
			}
			stainX+=1;
			if (stainX >= WORLD_WIDTH) {
				stainX = 0;
				stainY +=1;
				if (stainY >= WORLD_HEIGHT ) {
					stainY = 0;
				}
			}
		}
		stain = new Stain(stainX, stainY, random.nextInt(3));
	}
	public void update (float deltaTime) {
		if (gameOver)
			return;
		
		tickTime+=deltaTime;
		while (tickTime > tick) {
			tickTime-=tick;
			snake.advance();
			if (snake.checkBitten()) {
				gameOver = true;
				return;
			}
		}
		
		SnakePart head = snake.parts.get(0);
		snake.isEating = nextStain(head);
		if (head.x == stain.x && head.y == stain.y){
			score+= SCORE_INCREMENT;
			snake.eat();
			if (snake.parts.size() == WORLD_WIDTH * WORLD_HEIGHT) {
				gameOver = true;
				return;
			}else {
				placeStain();
			}
			if (score % 100 == 0 && tick - TICK_DECREMENT > 0) {
				tick -= TICK_DECREMENT;
			}
				
			
		}
	}
	public boolean nextStain(SnakePart head) {
		int direction = snake.direction;
		boolean isEating = false;
		if (direction == Snake.UP) {
			if (head.x == stain.x && 
					(((head.y - stain.y) == 1) || head.y == WORLD_HEIGHT && stain.y == 0)) {
				isEating = true;
				
			}
		}
		if (direction == Snake.DOWN) {
			if (head.x == stain.x && 
					(((head.y - stain.y) == -1) || head.y == 0 && stain.y == WORLD_HEIGHT)) {
				isEating = true;
				
			}
		}
		if (direction == Snake.LEFT) {
			if (head.y == stain.y && 
					(((head.x - stain.x) == 1) || head.x == WORLD_WIDTH && stain.x == 0)) {
				isEating = true;
				
			}
		}
		if (direction == Snake.RIGHT) {
			if (head.y == stain.y && 
					(((head.x - stain.x) == -1) || head.x == 0 && stain.x == WORLD_WIDTH)) {
				isEating = true;
				
			}
		}
		return isEating;
	}
}
