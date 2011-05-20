package game.app.andEngine;

import game.app.Cell;
import game.app.Row;
import game.app.andEngine.model.Knight;
import game.app.andEngine.model.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class LevelController {
	public Scene scene;
	private PhysicsWorld mPhysicsWorld;
	private AndEngineView viewController;
	private int VIEW_COLUMNS = 10;
	private int VIEW_ROWS = 3;
	private List<Row> rows = new ArrayList<Row>(VIEW_ROWS);
	//Game Config Settings
	private List<Shape> knights;
	private List<Shape> shadows;
	private int level;
	private Knight mPlayer;
	private boolean isGameFinished;
	public int mCameraWidth;
	public int mCameraHeight;
	
	public LevelController(AndEngineView view){
		level= 0;
		this.viewController = view;
	}
	
	public void CreateCharacter(TiledTextureRegion characterTextureRegion, int x, int y){
		mPlayer = new Knight(x, y, 60,60,characterTextureRegion, this);
		
		FixtureDef FIXTURE = PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
		Body body = PhysicsFactory.createBoxBody(mPhysicsWorld, mPlayer, BodyType.DynamicBody, FIXTURE);
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(mPlayer, body, true, false));
		scene.getLastChild().attachChild(mPlayer);
		knights.add(mPlayer);
	}
	public void addShadow(Path path, int speed, TiledTextureRegion shadowTextureRegion){
		Shadow enemy = new Shadow(path,speed, 150, 150, shadowTextureRegion, this);
		scene.getLastChild().attachChild(enemy);
		shadows.add(enemy);
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public void loadLevel(int levelId){
		isGameFinished = false;
		this.setCurrentLevel(levelId);
		
		if(levelId == 1)
			createGrid();
			loadLevel1();
		//else if(levelId == 2)
			//loadLevel2();
		//else if(levelId == 3)
			//loadLevel3();
		//else if(levelId == 4)
			//loadLevel4();
		//else if(levelId == 5)
			//loadLevel5();
		//else if(levelId == 6)
			//loadLevel6();
		//else if(levelId == 7)
			//loadLevel7();
		//else if(levelId == 8)
			//loadLevel8();
		//else if(levelId == 9)
			//loadLevel9();
	}
	
	
	private void setCurrentLevel(int levelId) {
		// TODO Auto-generated method stub
	}
	public void loadLevel1(){
		shadows = new ArrayList<Shape>();
		knights = new ArrayList<Shape>();
		Random ran = new Random();
		int y1 = rows.get(ran.nextInt(VIEW_ROWS)).getStartY();
		CreateCharacter(viewController.knightTextureRegion, 90, y1);
		Path path = new Path(2).to(500, y1).to(0, y1);
		addShadow(path,5,viewController.bullTextureRegion);
		/*int y2 = rows.get(ran.nextInt(VIEW_ROWS)).getStartY();
		Path path2 = new Path(2).to(500, y2).to(0, y2);
		addShadow(path2,3,viewController.bullTextureRegion);
		int y3 = rows.get(ran.nextInt(VIEW_ROWS)).getStartY();
		Path path3 = new Path(2).to(500, y3).to(0, y3);
		addShadow(path3,8,viewController.bullTextureRegion);*/
	}
	
	public List<Shape> getKnights(){
	 return this.knights;
	}
	public List<Shape> getShadows(){
	 return this.shadows;
	}
	public void setmPhysicsWorld(PhysicsWorld mPhysicsWorld) {
		this.mPhysicsWorld = mPhysicsWorld;
	}
	public void createFrame() {
		// TODO Auto-generated method stub
	}
	private void createGrid() {
		int rowHeight = mCameraHeight / VIEW_ROWS;
		int cellWidth = mCameraWidth / VIEW_COLUMNS;
		for (int i = 0; i <= VIEW_ROWS; i++) {
			Row row = new Row(i * rowHeight, rowHeight);
			for (int j = 0; j <= VIEW_COLUMNS; j++) {
				row.addCell(new Cell(j * cellWidth, cellWidth, i * rowHeight,
						rowHeight, true));
			}
			this.rows.add(row);
		}
	}

	public void callbackCollisionKnights(int knightIndex) {
		final Shape knight = this.knights.get(knightIndex);
		
	}
	public void callbackCollisionShadows(int shadowIndex){
		final Shape shadow = this.shadows.get(shadowIndex);
		this.viewController.getEngine().runOnUpdateThread(new Runnable() {
            @Override
            public void run() {
            	//scene.getLastChild().detachChild(shadow);
            }
		});
		this.shadows.remove(shadowIndex);
	}
}
