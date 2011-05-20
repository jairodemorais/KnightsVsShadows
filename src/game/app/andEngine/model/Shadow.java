package game.app.andEngine.model;

import game.app.andEngine.LevelController;

import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class Shadow extends Character {

	public boolean attacking = false;
	
	public Shadow(Path path, int speed, float pWidth, float pHeight,
			TiledTextureRegion pTextureRegion, LevelController levelController) {
		super(path.getCoordinatesX()[0], path.getCoordinatesY()[0], pWidth, pHeight, pTextureRegion, levelController);
		this.registerEntityModifier(new MoveModifier(10, path.getCoordinatesX()[0], path.getCoordinatesX()[1], path.getCoordinatesY()[0], path.getCoordinatesY()[1]));
		this.animate(new long[] { 130,130,130},0,2,true);
	}

	@Override
	protected boolean onBeforePositionChanged() {
		boolean result = true;
		int knightListSize = levelController.getKnights().size();
		for(int i = 0; i < knightListSize; i++)
			if(this.collidesWith(levelController.getKnights().get(i)))
			{
				this.clearEntityModifiers();
				attack();
				//levelController.callbackCollisionKnights(i);
				result = false;
				break;
			}
		return result;
	}
	private void attack(){
		if (attacking) return;
		this.stopAnimation(2);
		attacking = true;
		this.animate(new long[] { 100,100,100},3,5,true);
	}
}
