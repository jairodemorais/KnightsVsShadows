package game.app.andEngine.model;

import game.app.andEngine.LevelController;

import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class Shadow extends Character {
	public boolean attacking = false;

	public Shadow(Path path, int speed, float pWidth, float pHeight,
			TiledTextureRegion pTextureRegion, LevelController levelController,
			int powerAttack, int defence) {
		super(path.getCoordinatesX()[0], path.getCoordinatesY()[0], pWidth,
				pHeight, pTextureRegion, levelController, powerAttack, defence);
		this.registerEntityModifier(new MoveModifier(10,
				path.getCoordinatesX()[0], path.getCoordinatesX()[1], path
						.getCoordinatesY()[0], path.getCoordinatesY()[1]));
		this.animate(new long[] { 130, 130, 130 }, 0, 2, true);
	}

	@Override
	protected boolean onBeforePositionChanged() {

		boolean result = true;
			int knightListSize = levelController.getKnights().size();
			for (int i = 0; i < knightListSize; i++) {
				Knight knight = levelController.getKnights().get(i);
				if (this.collidesWith(knight)) {
					this.defence -= knight.getPowerAttack();
					if(!this.attacking){
						this.clearEntityModifiers();
						this.attack();
					}
					result = false;
				}
			}
			int arrowListSize = levelController.getArrows().size();
			for (int i = 0; i < arrowListSize; i++) {
				Arrow arrow = levelController.getArrows().get(i);
				if (this.collidesWith(arrow)) {
					this.defence -= arrow.getPowerAttack();
					result = false;
				}
			}
			if (this.defence <= 0) {
				levelController.callbackShadowDead(this);
			}
		return result;
	}

	private void attack() {
		//this.stopAnimation(2);
		attacking = true;
		this.animate(new long[] { 130, 130, 130 }, 3, 5, true);
	}
}
