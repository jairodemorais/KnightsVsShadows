package game.app.andEngine.model;

import game.app.GameView;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Archery extends Knight{
	private Arrow arrow;
	private Bitmap arrowImag;
	private GameView gameView;
	
	public Archery(Bitmap bmp, Bitmap arrowImg, GameView gameView, int x, int y, int rows, int columns, int energy) {
		super(bmp, x ,y,rows, columns, energy);
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
