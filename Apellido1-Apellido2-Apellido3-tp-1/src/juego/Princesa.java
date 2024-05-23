package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Princesa {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int piso = 557;
	private Image imagenDer;
	private Image imagenIzq;
	private double angulo;
	private double escala;
	private Entorno entorno;
	private Bloques bloque;
	//----------Variables de salto----------//
	private boolean enElSuelo = true;  // Variable para verificar si el personaje está en el suelo
	private double velocidadY = 0;     // Velocidad vertical del personaje
	private double gravedad = 0.3; 		// Gravedad del personaje
	
	//----------constructor----------//
	public Princesa(int x, int y, int ancho, int alto) {
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
	
    //----------Getters y Setters----------//
	public double getVelocidadY() {
		return velocidadY;
	}
	
	public void setVelocidadY(double velocidadY) {
		this.velocidadY = velocidadY;
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
	
	public int getPiso() {
		return piso;
	}
	
	public void setPiso(int piso) {
		this.piso = piso;
	}
	
	//----------Getters y Setters----------//
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
	
	public void saltar() {
        // Detectar si se presionó la tecla de salto
        if(this.enElSuelo) {
            velocidadY = -10; // Ajusta este valor para cambiar la fuerza del salto
            this.enElSuelo = false;
        }
    }
	 
    public void actualizarSalto() {
        // Aplicar gravedad en cada frame
        if (!enElSuelo) {
            velocidadY += gravedad;
            this.y += velocidadY;
        }

        if (this.y >= this.piso) { // Suponiendo que 550 es la posición del suelo
        	this.setY(this.piso);
            this.velocidadY = 0;
            this.enElSuelo = true;
        }
        /*if (this.y == 400) { // Suponiendo que 550 es la posición del suelo
            this.setY(400);
            this.velocidadY = 0;
            this.enElSuelo = true;
        }
        if (this.y == 240) { // Suponiendo que 550 es la posición del suelo
            this.setY(240);
            this.velocidadY = 0;
            this.enElSuelo = true;
        }
        if (this.y == 95) { // Suponiendo que 550 es la posición del suelo
            this.setY(95);
            this.velocidadY = 0;
            this.enElSuelo = true;
        }*/

    }
	
	
	
	
}
