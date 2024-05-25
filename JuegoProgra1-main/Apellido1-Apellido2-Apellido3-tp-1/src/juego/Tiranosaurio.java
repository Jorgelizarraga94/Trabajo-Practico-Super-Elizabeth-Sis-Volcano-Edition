package juego;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Tiranosaurio {
	private int x = 500;
	private int y = 565;
	private double ANCHO = 30;
	private double ALTO = 50;
	private double angulo;
	private double escala;
	private Image imagenIzq = Herramientas.cargarImagen("tiranosaurioIzq.png");
	private Image imagenDer = Herramientas.cargarImagen("tiranosaurio.png");
	//----------------Constructores-----------------//
	public Tiranosaurio() {}
	public Tiranosaurio(int x, int y, int ANCHO, int ALTO) {
		this.x = x;
		this.y = y;
		this.ANCHO = ANCHO;
		this.ALTO = ALTO;
		this.imagenIzq = Herramientas.cargarImagen("tiranosaurioIzq.png");
		this.imagenDer = Herramientas.cargarImagen("tiranosaurio.png");
		this.angulo = 0;
		this.escala= 0.15;
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
	
	public double getANCHO() {
		return ANCHO;
	}
	
	public void setANCHO(int ANCHO) {
		this.ANCHO = ANCHO;
	}
	
	public double getALTO() {
		return ALTO;
	}
	
	public void setALTO(int ALTO) {
		this.ALTO = ALTO;
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
	
	//----------------Metodos-----------------//
	public void movIzq() {
		this.x -= 3;
	}
	
	public void movDer() {
		this.x += 3;
	}
	public void crearTiranosaurio(ArrayList<Tiranosaurio> tiranosaurios, Tiranosaurio [] tiranosaur ) {
		
		Random aleatorio = new Random();
		for (int i = 0; i < tiranosaur.length; i++) {
			if((aleatorio.nextInt(3)) % 6 == 0 || i == 3 ) {
				tiranosaurios.add(tiranosaur[i]);	
			}
			else {
				tiranosaurios.add(tiranosaur[i]);
			}
		}
	}
	public void dibujar(Entorno entorno , ArrayList<Tiranosaurio> tiranosaurios) {
		for (int i = 0; i < tiranosaurios.size(); i++) {
			Tiranosaurio tira = tiranosaurios.get(i);
			if(i % 2 == 0) {
				entorno.dibujarImagen(imagenIzq, tira.getX(), tira.getY(), tira.getAngulo(), tira.getEscala());
			}
			else {
				entorno.dibujarImagen(imagenDer, tira.getX(), tira.getY(), tira.getAngulo(), tira.getEscala());
			}
		}
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
