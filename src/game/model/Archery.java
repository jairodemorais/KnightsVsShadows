package game.model;

import game.app.GameView;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Archery extends Knight{
	private Arrow arrow;
	private Bitmap arrowImag;
	private GameView gameView;
	
	public Archery(Bitmap bmp, Bitmap arrowImg, GameView gameView, boolean drawable, int x, int y, int rows, int columns) {
		super(bmp, drawable,x ,y,rows, columns);
		this.gameView = gameView;
		this.arrowImag = arrowImg;
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(this.isPlaying){
			if(arrow == null){
				arrow = new Arrow(this.arrowImag, gameView,this.x, this.y,15,1,1);
			}
			arrow.onDraw(canvas);
		}
	}

}