package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Bomba {
	//posicion de la bomba en pantalla 
	private double x; 
	private double y; 
	//dimensiones constantes de la bomba 
	private int ANCHO = 10; 
	private int ALTO = 10; 
	//Imagen de la bomba 
	private Image imagen; 
	//----------------Constructores-----------------//
	public Bomba(int x, int y, int ANCHO, int ALTO) {
		this.x = x;
		this.y = y;
		this.ANCHO = ANCHO;
		this.ALTO = ALTO;
		this.imagen = Herramientas.cargarImagen("bombaTiranosaurio.png");
	}
	//----------------Getters y Setters-----------------//
	public double getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public double getY() {
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
	
	public void disparoDer(Entorno entorno ,int velocidadDisparo) {
		//entorno.dibujarRectangulo(this.x, this.y, this.ANCHO, this.ALTO, 0, Color.blue);
		double bala =  this.x += velocidadDisparo;
		this.x = bala;
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.09);
	}
	
	public void disparoIzq(Entorno entorno , int velocidadDisparo) {
		//entorno.dibujarRectangulo(this.x, this.y, this.ANCHO, this.ALTO, 0, Color.red);
		double bala =  this.x -= velocidadDisparo;
		this.x = bala;
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.09);
	}
	
}
