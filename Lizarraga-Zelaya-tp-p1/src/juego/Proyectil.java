package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	//posición del proyectil 
	private double x; 
	private double y; 
	//dimensiones constantes del proyectil 
	private int ANCHO = 10; 
	private int ALTO = 10; 
	//Booleano Indica (inicialmente en false) si el disparo del proyectil esta recorriendo 	el entorno 
	private boolean disparoActivo = false; 
	//imagen del proyectil 
	private Image imagen; 
	//----------------Constructores-----------------//
	public Proyectil(double x, double y, int ANCHO, int ALTO) {
		this.x = x;
		this.y = y;
		this.ANCHO = ANCHO;
		this.ALTO = ALTO;
		imagen = Herramientas.cargarImagen("bolaDeFuego.png");
	}
	//----------------Getters y Setters-----------------//
	public double getX() {
		return x;
	}
	
	public void setX(double d) {
		this.x = d;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double d) {
		this.y = d;
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
	public void disparoDer(Entorno entorno ,int velocidadDisparo) {
		//entorno.dibujarRectangulo(this.x, this.y, this.ANCHO, this.ALTO, 0, Color.blue);
		double bala =  this.x += velocidadDisparo;
		this.x = bala;
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.2);
	}
	
	public void disparoIzq(Entorno entorno , int velocidadDisparo) {
		//entorno.dibujarRectangulo(this.x, this.y, this.ANCHO, this.ALTO, 0, Color.red);
		double bala =  this.x -= velocidadDisparo;
		this.x = bala;
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.2);
	}
	
}
