package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Tiranosaurio {
	private double x = 500;
	private double y = 565;
	private double ANCHO = 30;
	private double ALTO = 50;
	private double angulo;
	private double escala;
	Image imagenIzq = Herramientas.cargarImagen("tiranosaurioIzq.png");
	Image imagenDer = Herramientas.cargarImagen("tiranosaurio.png");
	public Tiranosaurio() {}
	public Tiranosaurio(int x, int y, int aNCHO, int aLTO) {
		super();
		this.x = x;
		this.y = y;
		ANCHO = aNCHO;
		ALTO = aLTO;
		angulo = 0;
		escala= 0.4;
	}
	public void movIzq() {
		this.x -= 3;
	}
	public void movDer() {
		this.x += 3;
	}
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
	public double getANCHO() {
		return ANCHO;
	}
	public void setANCHO(int aNCHO) {
		ANCHO = aNCHO;
	}
	public double getALTO() {
		return ALTO;
	}
	public void setALTO(int aLTO) {
		ALTO = aLTO;
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
	public void dibujar(Entorno entorno , Tiranosaurio [] tiranosaurios) {
		for (int i = 0; i < tiranosaurios.length; i++) {
			Tiranosaurio tira = tiranosaurios[i];
			if(i % 2 == 0) {
				entorno.dibujarImagen(imagenIzq, tira.getX(), tira.getY(), tira.getAngulo(), tira.getEscala());
			}
			else {
				entorno.dibujarImagen(imagenDer, tira.getX(), tira.getY(), tira.getAngulo(), tira.getEscala());
			}
			
			
		}
	}
	
	
	
	
}
