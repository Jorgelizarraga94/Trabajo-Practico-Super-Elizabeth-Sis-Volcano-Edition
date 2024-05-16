package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Bloques {
	Random random;
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
		this.random = new Random();
		for (int i = 0; i < conjuntoBloques.length; i++) {
			conjuntoBloques[i] = new Bloques(suma , y , 50, 30 , random.nextBoolean());
			suma += 50;
			System.out.println(conjuntoBloques[i].seRompe);
		}
	}
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.RED);
	}
	
	



	
	
}
