package game.app.andEngine.model;

import game.app.andEngine.LevelController;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class Knight extends Character{
	public Knight(float pX, float pY, float pTileWidth, float pTileHeight,
			TiledTextureRegion pTiledTextureRegion,	LevelController nLevelController, int powerAttack, int defence) {
		super(pX, pY, pTileWidth, pTileHeight, pTiledTextureRegion,nLevelController, powerAttack, defence);
	}
	@Override
    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
    		final float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			this.setScale(1.5f);
		}
		else if(pSceneTouchEvent.isActionMove()){
			this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
		}
		else if(pSceneTouchEvent.isActionUp()){
			this.setScale(1);
		}
		return true;
    }
	@Override
	protected boolean onBeforePositionChanged() {
		// TODO Auto-generated method stub
		return false;
	};	
}