package juego;

import java.awt.Color;

import entorno.Entorno;

public class Proyectil {
	int x;
	int y;
	int ANCHO = 10;
	int ALTO = 10;
	private Entorno entorno;
	public Proyectil() {}
	public Proyectil(int x, int y, int aNCHO, int aLTO) {
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
	
	public void disparoDer(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ANCHO, this.ALTO, 0, Color.blue);
		int bala =  this.x += 20;
		this.x = bala;
	}
	public void disparoIzq(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ANCHO, this.ALTO, 0, Color.red);
		int bala =  this.x -= 20;
		this.x = bala;
		
	}
	
	
	
}
