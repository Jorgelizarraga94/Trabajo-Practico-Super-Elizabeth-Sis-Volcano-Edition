package juego;

public class Tiranosaurio {
	int x = 500;
	int y = 565;
	int ANCHO = 30;
	int ALTO = 50;
	public Tiranosaurio() {}
	public Tiranosaurio(int x, int y, int aNCHO, int aLTO) {
		super();
		this.x = x;
		this.y = y;
		ANCHO = aNCHO;
		ALTO = aLTO;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getANCHO() {
		return ANCHO;
	}
	public void setANCHO(int aNCHO) {
		ANCHO = aNCHO;
	}
	public int getALTO() {
		return ALTO;
	}
	public void setALTO(int aLTO) {
		ALTO = aLTO;
	}
	
	
	
	
}
