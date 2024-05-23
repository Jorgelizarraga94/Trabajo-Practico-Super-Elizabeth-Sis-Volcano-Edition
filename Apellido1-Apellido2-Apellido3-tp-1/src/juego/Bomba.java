package juego;

public class Bomba {
	int x;
	int y;
	int ANCHO = 10;
	int ALTO = 10;
	//----------------Constructores-----------------//
	public Bomba(){}
	public Bomba(int x, int y, int aNCHO, int aLTO) {
		super();
		this.x = x;
		this.y = y;
		this.ANCHO = ANCHO;
		this.ALTO = ALTO;
	}
	//----------------Getters y Setters-----------------//
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
	
	public void setANCHO(int ANCHO) {
		this.ANCHO = ANCHO;
	}
	
	public int getALTO() {
		return ALTO;
	}
	
	public void setALTO(int ALTO) {
		this.ALTO = ALTO;
	}
	
}
