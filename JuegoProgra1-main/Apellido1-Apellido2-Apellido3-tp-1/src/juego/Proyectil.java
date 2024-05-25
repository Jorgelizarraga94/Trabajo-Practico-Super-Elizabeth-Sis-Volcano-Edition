package juego;

import java.awt.Color;

import entorno.Entorno;

public class Proyectil {
	private int x;
	private int y;
	private int ANCHO = 10;
	private int ALTO = 10;
	private boolean disparoActivo = false;
	private Entorno entorno;
	//----------------Constructores-----------------//
	public Proyectil() {}
	public Proyectil(int x, int y, int ANCHO, int ALTO) {
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
	public boolean getDisparoActivo() {
		return disparoActivo;
	}
	public void setDisparoActivo(boolean disparoActivo) {
		this.disparoActivo = disparoActivo;
	}
	//----------------Metodos-----------------//
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
