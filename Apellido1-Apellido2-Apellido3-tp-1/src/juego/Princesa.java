package juego;



import java.awt.Color;

import entorno.Entorno;

public class Princesa {
	int x = 500;
	int y = 565;
	int ANCHO = 30;
	int ALTO = 50;
	public Princesa() {}
	
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
	public int getAncho() {
		return ANCHO;
	}
	public void setAncho(int ancho) {
		this.ANCHO = ancho;
	}
	public int getLargo() {
		return ALTO;
	}
	public void setLargo(int largo) {
		this.ALTO = largo;
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ANCHO, this.ALTO, 0, Color.blue);
	}
	
	
	
}
