package game.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Knight {
	protected Bitmap bmp;
	protected Paint paint;
	protected int BMP_COLUMNS;
	protected int BMP_ROWS;
	protected int currentFrame;
	protected int width;
	protected int height;
	protected boolean isPlaying;
	protected int x;
	protected int y;
	protected int xSpeed = 0;
	protected int ySpeed = 0;
	private boolean draging = true;
	private static final int[] DIRECTION_TO_ANIMATON_MAP = {3,1,0,2};
	
	public Knight(Bitmap bmp, boolean drawable, int x, int y, int rows, int columns) {
		this.BMP_ROWS = rows;
		this.BMP_COLUMNS = columns;
		this.draging = drawable;
		this.bmp = bmp;
		paint = new Paint();
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;
		this.x = x;
		this.y = y;
	}

	public void onDraw(Canvas canvas) {
		if(isPlaying) {
			currentFrame = ++currentFrame % BMP_COLUMNS;
		}
		int srcX = currentFrame * width;
		int srcY = 0* height;
		Rect src = new Rect(srcX,srcY, srcX + width, srcY + height);
		Rect des = new Rect(x,y , x+ width, y + height);
		canvas.drawBitmap(bmp, src, des, paint);
	}
	
	public void play(){
		if(draging){
			this.isPlaying = true;
		}
	}
	public void stop()
	{
		this.isPlaying = false;
	}
	public boolean isCollision(float x2, float y2) {
		return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
	}
	//direction = 0 up, 1 left, 2 down, 3 right,
	//animation = 3 back, 1 left, 0 front, 2 right
	private int getAnimationRow(){
		double dirDouble = (Math.atan2(xSpeed, ySpeed)/(Math.PI /2)+2);
		int direction = (int) Math.round(dirDouble) % BMP_ROWS;
		return DIRECTION_TO_ANIMATON_MAP[direction];
	}

	public void setDraging(boolean drawable) {
		this.draging = drawable;		
	}

	public void setCoordinate(int x, int y) {
		if(draging){
			this.x = x;
			this.y = y;
		}
	}

}