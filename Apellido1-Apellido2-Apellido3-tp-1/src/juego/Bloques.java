package juego;

import java.awt.Color;

import entorno.Entorno;

public class Bloques {
	int x;
	int y;
	int ancho;
	int alto;
	boolean seRompe;
	public Bloques(int x, int y, int ancho, int alto , boolean seRompe) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.seRompe = seRompe;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getAncho() {
		return ancho;
	}
	public int getAlto() {
		return alto;
	}
	public boolean isSeRompe() {
		return seRompe;
	}
	public void crearPiso(Bloques bloque,Bloques [] conjuntoBloques , int y) {
		int suma = 25;
		for (int i = 0; i < conjuntoBloques.length; i++) {
			conjuntoBloques[i] = new Bloques(suma , y , 50, 30 ,false);
			suma += 50;
		}
	}
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.RED);
	}
	
	



	
	
}
