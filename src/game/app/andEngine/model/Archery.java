package game.app.andEngine.model;

import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import game.app.GameView;
import game.app.andEngine.LevelController;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Archery extends Knight{
	public Archery(float pX, float pY, float pTileWidth, float pTileHeight,
			TiledTextureRegion pTiledTextureRegion,
			LevelController nLevelController, int powerAttack, int defense) {
		super(pX, pY, pTileWidth, pTileHeight, pTiledTextureRegion, nLevelController, powerAttack, defense);
		
	}
	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
    		final float pTouchAreaLocalY) {
		super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
		if(pSceneTouchEvent.isActionUp()){
			this.levelController.CreateArrow(mX + getWidth(), mY + getHeight() / 2);
		}
		return true;
    };	
}
