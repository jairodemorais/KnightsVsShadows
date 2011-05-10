package game.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Character {
	protected Bitmap bmp;
	protected int BMP_COLUMNS;
	protected int BMP_ROWS;
	protected int currentFrame;
	protected int width;
	protected int height;
	protected boolean isPlaying;
	protected int x;
	protected int y;
	protected Paint paint;
	protected int prevX;
	protected int prevY;
	
	public Character(Bitmap bmp, int x, int y, int rows,int columns) {
		this.BMP_ROWS = rows;
		this.BMP_COLUMNS = columns;
		paint = new Paint();
		this.bmp = bmp;
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;
		this.x = x;
		this.y = y;
	}

	public void onDraw(Canvas canvas) {
		if (isPlaying) {
			currentFrame = ++currentFrame % BMP_COLUMNS;
		}
		int srcX = currentFrame * width;
		int srcY = getAnimationRow() * height;
		Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
		Rect des = new Rect(x, y, x + width, y + height);
		canvas.drawBitmap(bmp, src, des, null);
	}

	public void play() {
		this.isPlaying = true;
	}

	public void stop() {
		this.isPlaying = false;
	}

	public boolean isCollision(float x2, float y2) {
		return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
	}

	// movement = 0 walk, 1 attack, 2 die
	private int getAnimationRow() {
		return 0;
	}

	public void setCoordinate(int x, int y) {
		this.x = x - getWidth() / 2;
		this.y = y - getHeight() / 2;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	public int getPrevX() {
		return this.prevX;
	}

	public void setPrevX(int prevX) {
		this.prevX = prevX;
	}
	public int getPrevY() {
		return this.prevY;
	}

	public void setPrevY(int prevY) {
		this.prevY = prevY;
	}
}