package game.model;

import game.app.GameView;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Arrow {
	private int xSpeed;
	private int BMP_ROWS;
	private int BMP_COLUMNS;
	private Bitmap bmp;
	private GameView gameView;
	private int width;
	private int currentFrame;
	private int y;
	private int x;
	
	public Arrow(Bitmap bmp, GameView gameView,int x, int y, int xSpeed, int rows, int columns) {
		this.xSpeed = xSpeed;
		this.bmp = bmp;
		this.BMP_ROWS = rows;
		this.BMP_COLUMNS = columns;
		this.gameView = gameView;
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.x = x;
		this.y = y;
	}
	
	public void onDraw(Canvas canvas){
		update();
		canvas.drawBitmap(bmp, this.x, this.y, null);
	}
	private void update(){
		if(this.x < gameView.getHeight() - width - xSpeed){
			this.x = this.x + xSpeed;
		}
		currentFrame = ++currentFrame % BMP_COLUMNS;
	}

}
