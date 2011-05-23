package game.app.andEngine;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import com.badlogic.gdx.math.Vector2;

public class AndEngineView extends BaseGameActivity {
	protected PhysicsWorld mPhysicsWorld;
	public LevelController levelController;
	public TiledTextureRegion knightTextureRegion;
	public TiledTextureRegion bullTextureRegion;
	public TiledTextureRegion fireBallTextureRegion;
	public TextureRegion bloodTextureRegion;
	private Texture knightTexture;
	private Texture bullTexture;
	private Texture fireBallTexture;
	private Texture bloodTexture;
	private Camera camera;
	@Override
	public Engine onLoadEngine() {
		levelController = new LevelController(this);
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		levelController.mCameraWidth = width;
		levelController.mCameraHeight = height;
		camera = new Camera(0, 0, width,height);
		return new Engine(
				new EngineOptions(true, ScreenOrientation.LANDSCAPE,
						new RatioResolutionPolicy(levelController.mCameraWidth, levelController.mCameraHeight),
						camera).setNeedsSound(true));
	}

	@Override
	public void onLoadResources() {	
		this.knightTexture  = new Texture(128, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		knightTextureRegion = TextureRegionFactory.createTiledFromAsset(this.knightTexture, this,
																			"gfx/archery.png",0,0,3,4);
		this.bullTexture  = new Texture(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		bullTextureRegion = TextureRegionFactory.createTiledFromAsset(this.bullTexture, this,
																			"gfx/bull.png",0,0,3,2);
		fireBallTexture = new Texture(64, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		fireBallTextureRegion = TextureRegionFactory.createTiledFromAsset(this.fireBallTexture, this,
																			"gfx/fireBall.png",0,0,1,1);
		bloodTexture = new Texture(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		bloodTextureRegion = TextureRegionFactory.createFromAsset(this.bloodTexture, this,
																			"gfx/blood.png",0,0);
		this.mEngine.registerUpdateHandler(new FPSLogger());
		this.mEngine.getTextureManager().loadTextures(knightTexture, bullTexture,fireBallTexture,bloodTexture);

	}

	@Override
	public Scene onLoadScene() {
		return newGameLevelScene(1);
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
	}
	
	public Scene newGameLevelScene(int levelId){
		Scene scene = new Scene(2);
		this.mPhysicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0, 0), false);
		levelController.setScene(scene);
		levelController.setmPhysicsWorld(mPhysicsWorld);
		levelController.loadLevel(levelId);
		scene.registerUpdateHandler(this.mPhysicsWorld);
		return scene;
	}
	
	
	
}
