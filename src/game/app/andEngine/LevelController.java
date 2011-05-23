package game.app.andEngine;

import game.app.Cell;
import game.app.Row;
import game.app.andEngine.model.Archery;
import game.app.andEngine.model.Arrow;
import game.app.andEngine.model.Knight;
import game.app.andEngine.model.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.BaseSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

public class LevelController {
	public Scene scene;
	private PhysicsWorld mPhysicsWorld;
	private AndEngineView viewController;
	private int VIEW_COLUMNS = 10;
	private int VIEW_ROWS = 3;
	private List<Row> rows = new ArrayList<Row>(VIEW_ROWS);
	//Game Config Settings
	private int level;
	private boolean isGameFinished;
	public int mCameraWidth;
	public int mCameraHeight;
	private List<Knight> knights = new ArrayList<Knight>();
	private List<Arrow> arrows = new ArrayList<Arrow>();
	private List<Shadow> shadows;
	
	public LevelController(AndEngineView view){
		level= 0;
		this.viewController = view;
	}
	public void CreateArrow(float x, float y){
		Arrow mArrow = new Arrow(x,y,45, 20,viewController.fireBallTextureRegion,this,3,1);
		scene.getLastChild().attachChild(mArrow);
		arrows.add(mArrow);
	}
	private void CreateCharacter(int x, int y){
		Knight mPlayer = new Archery(x, y, 80,80,viewController.knightTextureRegion, this, 4, 8);
		scene.getLastChild().attachChild(mPlayer);
		knights.add(mPlayer);
		scene.registerTouchArea(mPlayer);
		scene.setTouchAreaBindingEnabled(true);
	}
	private void addShadow(Path path, int speed){
		Shadow enemy = new Shadow(path,speed, 150, 150, viewController.bullTextureRegion, this, 3,6);
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
		this.level = levelId;
	}
	public void loadLevel1(){
		shadows = new ArrayList<Shadow>(8);
		CreateCharacter(90, 0);
		creteLevelShadowHandler(1);
	}
	
	public List<Knight> getKnights(){
	 return this.knights;
	}
	public List<Shadow> getShadows(){
	 return this.shadows;
	}
	public List<Arrow> getArrows(){
	 return this.arrows;
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

	}
	public void callbackShadowDead(final Shadow shadow){
		this.viewController.getEngine().runOnUpdateThread(new Runnable() {
            @Override
            public void run() {
            	scene.getLastChild().detachChild(shadow);
            	scene.getLastChild().attachChild(new Sprite(shadow.getX(), shadow.getY(),150,150, viewController.bloodTextureRegion));
            }
		});
		this.shadows.remove(shadow);
	}
	
	public void creteLevelShadowHandler(int lvlId){
		int[] shadowInterval = new int[]{4,8,4,2,1};
        final TimerHandler shadowTimeHandler = new TimerHandler(shadowInterval[lvlId], new ITimerCallback() {
                    @Override
                    public void onTimePassed(TimerHandler pTimerHandler) {
                    	Random ran = new Random();
                		int y1 = rows.get(ran.nextInt(VIEW_ROWS)).getStartY();
                    	Path path = new Path(2).to(700, y1).to(0, y1);
                		addShadow(path,5);
                    }
                });
        shadowTimeHandler.setAutoReset(true);
        this.viewController.getEngine().registerUpdateHandler(shadowTimeHandler);
	}
}
