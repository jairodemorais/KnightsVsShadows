package game.model;

import game.app.GameView;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Arrow {
	private int ySpeed;
	private int BMP_ROWS;
	private int BMP_COLUMNS;
	private Bitmap bmp;
	private GameView gameView;
	private int height;
	private int currentFrame;
	private int y;
	private int x;
	
	public Arrow(Bitmap bmp, GameView gameView,int x, int y, int ySpeed, int rows, int columns) {
		this.ySpeed = ySpeed;
		this.bmp = bmp;
		this.BMP_ROWS = rows;
		this.BMP_COLUMNS = columns;
		this.gameView = gameView;
		this.height = bmp.getHeight() / BMP_ROWS;
		this.x = x;
		this.y = y;
	}
	
	public void onDraw(Canvas canvas){
		update();
		canvas.drawBitmap(bmp, this.x, this.y, null);
	}
	private void update(){
		if(this.y < gameView.getHeight() - height - ySpeed){
			this.y =this.y - ySpeed;
		}
		currentFrame = ++currentFrame % BMP_COLUMNS;
	}

}
