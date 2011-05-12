package game.model;

import game.app.GameView;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Shadow extends Character {
	private int xSpeed;
	private GameView gameView;
	private boolean isWalking;

	public Shadow(Bitmap bmp, int x, int y, int rows, int columns, int xSpeed,
			int energy, GameView gameView) {
		super(bmp, x, y, rows, columns, energy);
		this.xSpeed = xSpeed;
		this.isPlaying = true;
		this.gameView = gameView;
		isWalking = true;
	}

	public void onDraw(Canvas canvas) {
		if (isWalking) {
			update();
		}
		
		super.onDraw(canvas);
	}

	private void update() {
		int nextX = this.x + xSpeed;
		if (gameView.getCell(nextX, this.y).isEmpty()) {
			this.x = nextX;
		} else {
			isWalking = false;
		}
	}

	@Override
	protected int getAnimationRow() {
		if(isWalking){
			return 0;
		}else if(energy <= 3){
			return 1;
		}else{
			return 1;
		}	
	}

}
