package game.app;

import java.util.ArrayList;
import java.util.List;

public class Row {
	private int y;
	private int height;
	private List<Cell> cells;
	
	public void setY(int y) {
		this.y = y;
	}
	public int getStartY() {
		return y;
	}
	public int getEndY() {
		return y + height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return height;
	}
	public Row(int y, int height){
		this.y = y;
		this.height = height;
		this.cells = new ArrayList<Cell>();
	}
	
	public void addCell(Cell cell){
		this.cells.add(cell);
	}
	
	public Cell getCell(int x){
		Cell activeCell = null;
		for(Cell cell : cells){
			if(x >= cell.getStartX() && x <= cell.getEndX()){
				activeCell = cell;
				break;
			}
		}
		return activeCell;
	}
}
