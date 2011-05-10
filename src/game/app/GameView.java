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
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	private GameLoopThread gameLoopThread;
	private SurfaceHolder holder;
	private List<Knight> knights = new ArrayList<Knight>();
	private int VIEW_COLUMNS = 10;
	private int VIEW_ROWS = 4;
	private List<Row> rows = new ArrayList<Row>(VIEW_ROWS);
	private Rect activeCellRec = new Rect();
	private Cell activeCell = null;

	public GameView(Context context) {
		super(context);
		gameLoopThread = new GameLoopThread(this);
		holder = getHolder();
		holder.addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				gameLoopThread.setRunning(false);
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				createGrid();
				creatCharacters();
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		synchronized (getHolder()) {
			for (Knight knight : knights) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (knight.isCollision(event.getX(), event.getY())) {
						knight.setDraging(true);
						knight.setPrevX((int) event.getX());
						knight.setPrevY((int) event.getY());
					} else {
						knight.setDraging(false);
					}
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
					int x = (int) event.getX();
					int y = (int) event.getY();
					
					knight.setCoordinate(x, y);
					if (knight.isCollision(x, y)) {
						this.activeCell = getCell(x,y);
					}
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					this.activeCell = null;
					if (knight.isCollision(event.getX(), event.getY())) {
						reLocateKnight((int) event.getX(), (int) event.getY(),
								knight);
						knight.play();
					}
				}
			}
		}
		return true;
	}

	private void highLightCell(Canvas canvas) {
		if (activeCell == null)
			return;
		this.activeCellRec.left = activeCell.getStartX();
		this.activeCellRec.top = activeCell.getStartY();
		this.activeCellRec.right = activeCell.getEndX();
		this.activeCellRec.bottom = activeCell.getEndY();
		Paint green = new Paint();
		int color = activeCell.isEmpty() ? Color.GREEN : Color.RED;
		green.setColor(color);
		green.setAlpha(80);
		canvas.drawRect(activeCellRec, green);
	}

	private void reLocateKnight(int x, int y, Knight knight) {
		if( x > getWidth() - knight.getWidth())
		{
			x = getWidth() - knight.getWidth();
		}
		if( y > getHeight() - knight.getHeight())
		{
			y = getHeight() - knight.getHeight();
		}
		Cell activeCell = getCell(x, y);
		if (activeCell == null)
			return;

		if (activeCell.isEmpty()) {
			int kWidth = knight.getWidth();
			int newX = activeCell.getStartX()
					+ ((activeCell.getWidth() - kWidth) / 2);
			int kHeight = knight.getHeight();
			int newY = activeCell.getStartY()
					+ ((activeCell.getHeight() - kHeight) / 2);
			
			knight.setCoordinate(newX, newY);
			Cell prevCell = getCell(knight.getPrevX(), knight.getPrevY());
			prevCell.setEmpty(true);
			activeCell.setEmpty(false);
		} else {
			knight.returnPrevPosition();
		}
	}

	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		if (activeCell != null) {
			highLightCell(canvas);
		}
		for (int i = 0; i <= knights.size() - 1; i++) {
			knights.get(i).onDraw(canvas);
		}
	}

	private void createGrid() {
		int rowHeight = getHeight() / VIEW_ROWS;
		int cellWidth = getWidth() / VIEW_COLUMNS;
		for (int i = 0; i <= VIEW_ROWS; i++) {
			Row row = new Row(i * rowHeight, rowHeight);
			for (int j = 0; j <= VIEW_COLUMNS; j++) {
				row.addCell(new Cell(j * cellWidth, cellWidth, i * rowHeight,
						rowHeight, true));
			}
			this.rows.add(row);
		}
	}

	private void creatCharacters() {
		Bitmap gladiator = BitmapFactory.decodeResource(getResources(),
				R.drawable.gladiator);
		knights.add(new Knight(gladiator, 0, 200, 4, 3));
		Bitmap archery = BitmapFactory.decodeResource(getResources(),
				R.drawable.archery);
		Bitmap arrow = BitmapFactory.decodeResource(getResources(),
				R.drawable.arrow);
		knights.add(new Archery(archery, arrow, this, 50, 200, 4, 3));
	}

	private Cell getCell(int x, int y) {
		Cell cell = null;
		for (Row row : this.rows) {
			if (y > row.getStartY() && y < row.getEndY()) {
				cell = row.getCell(x);
				break;
			}
		}
		return cell;
	}
}
