package game.app;

import game.model.Archery;
import game.model.Knight;
import java.util.ArrayList;
import java.util.List;
import org.games.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	private GameLoopThread gameLoopThread;
	private SurfaceHolder holder;
	private List<Knight> knights = new ArrayList<Knight>();

	public GameView(Context context) {
		super(context);
		gameLoopThread = new GameLoopThread(this);
		holder = getHolder();
		holder.addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
		Bitmap gladiator = BitmapFactory.decodeResource(getResources(),
				R.drawable.gladiator);
		knights.add(new Knight(gladiator, true, 0, 500,4,3));
		Bitmap archery = BitmapFactory.decodeResource(getResources(),
				R.drawable.archery);
		Bitmap arrow = BitmapFactory.decodeResource(getResources(),
				R.drawable.arrow);
		knights.add(new Archery(archery, arrow, this, true, 50, 500,4,3));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		for (Knight knight : knights) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (knight.isCollision(event.getX(), event.getY())) {
					knight.setDraging(true);
				}
				else{
					knight.setDraging(false);
				}
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				knight.setCoordinate((int) event.getX(), (int) event.getY());
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				knight.play();
			}
		}
		return true;
	}

	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		for (int i = 0; i <= knights.size() - 1; i++) {
			knights.get(i).onDraw(canvas);
		}
	}
}
