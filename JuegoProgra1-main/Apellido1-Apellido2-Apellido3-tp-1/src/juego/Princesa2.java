package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Princesa2 {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private Image imagenDer;
	private Image imagenIzq;
	private double angulo;
	private double escala;
	private Entorno entorno;
	private Bloques bloque;
	//Variables de salto
	boolean enElSuelo = true;  // Variable para verificar si el personaje está en el suelo
	double velocidadY = 0;     // Velocidad vertical del personaje
	double gravedad = 0.3; 		// Gravedad del personaje
	
	
	public Princesa2(int x, int y, int ancho, int alto) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.imagenDer = Herramientas.cargarImagen("princesaDer.png");
		this.imagenIzq = Herramientas.cargarImagen("princesaIzq.png");
		this.angulo = 0;
		this.escala = 0.04;
	}
	public void dibujarDer(Entorno entorno) {
		//entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.blue);
		entorno.dibujarImagen(imagenDer, x, y, angulo, escala);
	}
	public void dibujarIzq(Entorno entorno) {
		//entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.blue);
		entorno.dibujarImagen(imagenIzq, x, y, angulo, escala);
	}
	public void movIzq() {
		this.x -= 3;
	}
	public void movDer() {
		this.x += 3;
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

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public double getEscala() {
		return escala;
	}

	public void setEscala(double escala) {
		this.escala = escala;
	}
	public double getGravedad() {
		return gravedad;
	}
	public void setGravedad(double gravedad) {
		this.gravedad = gravedad;
	}
	
	
	
	
}
