package com.lcsmobileapps.mrnom;

import com.lcsmobileapps.framework.Game;
import com.lcsmobileapps.framework.Graphics;
import com.lcsmobileapps.framework.Graphics.PixmapFormat;
import com.lcsmobileapps.framework.Screen;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);
		
	}

	@Override
	public void update(float deltatime) {
		Graphics g = game.getGraphics();
		Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
		Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
		Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
		Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
		Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
		Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
		Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
		Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
		Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
		Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
		Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
		Assets.headUp = g.newPixmap("headup.png", PixmapFormat.ARGB4444);
		Assets.headLeft = g.newPixmap("headleft.png", PixmapFormat.ARGB4444);
		Assets.headRight = g.newPixmap("headright.png", PixmapFormat.ARGB4444);
		Assets.headDown = g.newPixmap("headdown.png", PixmapFormat.ARGB4444);
		Assets.headUp_eat = g.newPixmap("headup_eat.png", PixmapFormat.ARGB4444);
		Assets.headLeft_eat = g.newPixmap("headleft_eat.png", PixmapFormat.ARGB4444);
		Assets.headRight_eat = g.newPixmap("headright_eat.png", PixmapFormat.ARGB4444);
		Assets.headDown_eat = g.newPixmap("headdown_eat.png", PixmapFormat.ARGB4444);
		Assets.tail = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
		Assets.stain1 = g.newPixmap("stain1.png", PixmapFormat.ARGB4444);
		Assets.stain2 = g.newPixmap("stain2.png", PixmapFormat.ARGB4444);
		Assets.stain3 = g.newPixmap("stain3.png", PixmapFormat.ARGB4444);
		Assets.click = game.getAudio().newSound("click.ogg");
		Assets.eat = game.getAudio().newSound("eat.ogg");
		Assets.bitten = game.getAudio().newSound("bitten.ogg");
		Settings.load(game.getFileIO());
		game.setScreen(new MainMenuScreen(game));
				
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
