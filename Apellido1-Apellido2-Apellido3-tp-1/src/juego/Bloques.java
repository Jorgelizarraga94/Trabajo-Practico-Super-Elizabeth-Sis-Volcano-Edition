package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Bloques {
	private Entorno entorno;
	Random random;
	int x;
	int y;
	int ancho;
	int alto;
	boolean seRompe;
	private Image bloqueSeRompe = Herramientas.cargarImagen("bloqueSeRompe.png");
	private Image bloqueNoSeRompe = Herramientas.cargarImagen("bloqueNoSeRompe.png");
	private Image imagen;
	private double angulo;
	private double escala;
	
	public Bloques() {}
	public Bloques(int x, int y, int ancho, int alto , boolean seRompe) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.angulo = 0;
		this.escala = 3;
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
  
    
	public void crearPiso(Bloques [] conjuntoBloques , int y , int ancho, int alto) {
		int suma = 25;
		for (int i = 0; i < conjuntoBloques.length; i++) {
			if(i % 2 == 0 && i % 4 == 0 ) {
				conjuntoBloques[i] = new Bloques(suma , y , ancho, alto , true);
				suma += 50;
			}
			else {
				conjuntoBloques[i] = new Bloques(suma , y , ancho, alto , false);
				suma += 50;
			}
		}
	}
	public void dibujar(Entorno entorno , Bloques [] conjuntoBloques) {
		Color color;
		for (int i = 0; i < conjuntoBloques.length; i++) {
			Bloques bloque = conjuntoBloques[i];
			if(bloque.seRompe) {
				//color = Color.red;
				entorno.dibujarImagen(bloqueSeRompe, bloque.x, bloque.y, this.angulo, this.escala);
				imagen = bloqueSeRompe;
			}
			else {
				//color = Color.BLUE;
				entorno.dibujarImagen(bloqueNoSeRompe, bloque.x, bloque.y, this.angulo, this.escala);
				imagen = bloqueNoSeRompe;
			}
				//entorno.dibujarRectangulo(bloque.x, bloque.y, bloque.ancho, bloque.alto, 0, color);
				entorno.dibujarImagen(imagen, bloque.x, bloque.y, this.angulo, this.escala);
		}
	}
	
}
