package game.app;

public class Cell {
	private int x;
	private int width;
	private int y;
	private int height;
	private boolean empty;
	
	public Cell(int x, int width, int y, int height, boolean empty){
		this.x = x;
		this.width = width;
		this.empty = empty;
		this.y = y;
		this.height = height;
	}
	public int getStartX() {
		return x;
	}
	public int getEndX() {
		return x + width;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setEmpty(boolean empty){
		this.empty = empty;
	}
	public boolean isEmpty(){
		return empty;
	}

	public int getStartY() {
		return y;
	}
	public int getEndY() {
		return y + height;
	}
}
