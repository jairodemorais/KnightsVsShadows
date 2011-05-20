package game.app.andEngine.model;

import game.app.andEngine.LevelController;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class Knight extends Character{
	public Knight(float pX, float pY, float pTileWidth, float pTileHeight,
			TiledTextureRegion pTiledTextureRegion,	LevelController nLevelController) {
		super(pX, pY, pTileWidth, pTileHeight, pTiledTextureRegion, nLevelController);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean onBeforePositionChanged() {
		//speed up
		if(frameCount < 2){
			frameCount++;
			return true;
		}
		frameCount = 0;
		int enemyListSize = levelController.getShadows().size();
		for(int i = 0; i < enemyListSize; i++){
			if(this.collidesWith(levelController.getShadows().get(i)))
			{
				levelController.callbackCollisionShadows(i);
				return false;
			}
		}
		return true;
	}
	
}