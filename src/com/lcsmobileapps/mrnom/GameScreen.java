package com.lcsmobileapps.mrnom;

import java.util.List;

import com.lcsmobileapps.framework.Game;
import com.lcsmobileapps.framework.Input.TouchEvent;
import com.lcsmobileapps.framework.Screen;

public class GameScreen extends Screen{

	enum GameState {
		Ready,
		Running,
		Paused,
		GameOver
	}
	
	GameState state = GameState.Ready;
	World world;
	int oldScore = 0;
	String score = "0";
	public GameScreen(Game game) {
		super(game);
		world = new World();
	}

	@Override
	public void update(float deltatime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		if (state == GameState.Ready) {
			updateReady(touchEvents);
		}
		if (state == GameState.Running) {
			updateRunning(touchEvents, deltatime);
		}
		if (state == GameState.Paused) {
			updatePaused(touchEvents);
		}
		if (state == GameState.GameOver) {
			updateGameOver(touchEvents);
		}
		
	}

	public void updateReady (List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0)
			state = GameState.Running;
	}
	public void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		int len = touchEvents.size();
		
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x < 64 && event.y < 64) {
					if (Settings.soundEnabled) {
						Assets.click.play(1);
					}
					state = GameState.Paused;
					return;
				}
			}
			if (event.type == TouchEvent.TOUCH_DOWN ) {
				if (event.x < 64 && event.y > 416) {
					world.snake.turnLeft();
				}
				if (event.x > 256 && event.y > 416) {
					world.snake.turnRight();
				}
			}
		}
		world.update(deltaTime);
		if (world.gameOver) {
			if (Settings.soundEnabled) {
				Assets.bitten.play(1);
			}
			state = GameState.GameOver;
		}
		if (oldScore != world.score) {
			oldScore = world.score;
			score = ""+oldScore;
			if (Settings.soundEnabled)
				Assets.eat.play(1);
		}
		
	}
	public void updatePaused (List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				 if(event.y > 100 && event.y <= 148) {
                     if(Settings.soundEnabled)
                         Assets.click.play(1);
                     state = GameState.Running;
                     return;
                  }     
                  if(event.y > 148 && event.y < 196) {
                      if(Settings.soundEnabled)
                          Assets.click.play(1);
                      game.setScreen(new MainMenuScreen(game));
                      return;
                  }
			}
		}
	}
	private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
             TouchEvent event = touchEvents.get(i);
             if(event.type == TouchEvent.TOUCH_UP) {
                 if(event.x >= 128 && event.x <= 192 &&
                     event.y >= 200 && event.y <= 264) {
                      if(Settings.soundEnabled)
                          Assets.click.play(1);
                      game.setScreen(new MainMenuScreen(game));
                      return;
                }
            }
        }
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
