package game.model;

import android.graphics.Bitmap;


public class Knight extends Character{
	private boolean draging = true;
	
	public Knight(Bitmap bmp, int x, int y, int rows, int columns) {
		super(bmp, x, y, rows, columns);
	}
	
	@Override
	public void setCoordinate(int x , int y){
		if(draging){
			super.setCoordinate(x, y);
		}
	}
	@Override
	public void play(){
		if(draging){
			super.play();
		}
	}
	public void setDraging(boolean drawable) {
		this.draging = drawable;		
	}
	public void returnPrevPosition() {
		setCoordinate(prevX, prevY);		
	}
}