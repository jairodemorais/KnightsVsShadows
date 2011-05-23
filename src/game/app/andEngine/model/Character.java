package game.app.andEngine.model;

import game.app.andEngine.LevelController;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public abstract class Character extends AnimatedSprite{
	LevelController levelController;
	int frameCount;
	protected int powerAttack;
	protected int defence;
	
	public Character(float pX, float pY, float pTileWidth, float pTileHeight,
			TiledTextureRegion pTiledTextureRegion, LevelController levelController, int powerAttack, int defence) {
		super(pX, pY, pTileWidth, pTileHeight, pTiledTextureRegion);
		frameCount = 0;
		this.powerAttack = powerAttack;
		this.defence = defence;
		this.levelController = levelController;
		levelController.scene.registerUpdateHandler(new IUpdateHandler(){
			@Override
			public void onUpdate(float pSecondsElapsed) {
				// TODO Auto-generated method stub			
			}
			@Override
			public void reset() {
				// TODO Auto-generated method stub
			}		
		});
	}	
	
	protected abstract boolean onBeforePositionChanged();

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		onBeforePositionChanged();
	}
	public int getPowerAttack(){
		return this.powerAttack;
	}
	public int getDefense(){
		return this.defence;
	}
}