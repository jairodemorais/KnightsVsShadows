package game.app.andEngine.model;

import game.app.andEngine.LevelController;

import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class Arrow extends Character{
	private float initialX;
	public Arrow(float pX, float pY, float pTileWidth, float pTileHeight,
			TiledTextureRegion pTiledTextureRegion,
			LevelController levelController, int powerAttack, int defence) {
		super(pX, pY, pTileWidth, pTileHeight, pTiledTextureRegion, levelController, powerAttack, defence);
		this.initialX = pX;
		this.registerEntityModifier(new MoveModifier(10, pX,levelController.mCameraWidth, pY,pY));
	}

	@Override
	protected boolean onBeforePositionChanged() {
		int shadowListSize = levelController.getShadows().size();
		for(int i = 0; i < shadowListSize; i++){
			if(this.collidesWith(levelController.getShadows().get(i)))
			{
				this.clearEntityModifiers();
				this.registerEntityModifier(new MoveModifier(10, initialX,levelController.mCameraWidth, mY,mY));
				return false;
			}
		}
		return true;
	}

}
