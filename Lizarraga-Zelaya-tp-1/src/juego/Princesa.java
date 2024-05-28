package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Princesa {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	private double piso = 750;
	private Image imagenDer;
	private Image imagenIzq;
	private double angulo;
	private double escala;
	private boolean disparoLadoPrincesa;
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
		this.imagenDer = Herramientas.cargarImagen("goten.gif");
		this.imagenIzq = Herramientas.cargarImagen("goten.gif");
		this.angulo = 0;
		this.escala = 1;
	}
	
    //----------Getters y Setters----------//
	public double getVelocidadY() {
		return velocidadY;
	}
	
	public void setVelocidadY(double velocidadY) {
		this.velocidadY = velocidadY;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
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
	
	public double getPiso() {
		return piso;
	}
	
	public void setPiso(double piso) {
		this.piso = piso;
	}
	
	public boolean getEnElSuelo() {
		return enElSuelo;
	}

	public void setEnElSuelo(boolean enElSuelo) {
		this.enElSuelo = enElSuelo;
	}
	
	public boolean getDisparoLadoPrincesa() {
		return disparoLadoPrincesa;
	}

	public void setDisparoLadoPrincesa(boolean disparoLadoPrincesa) {
		this.disparoLadoPrincesa = disparoLadoPrincesa;
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
        if(this.enElSuelo){
            velocidadY = -20; //fuerza del salto
            this.enElSuelo = false;
        }
    }
	
    public void actualizarSalto() {
        // Aplicar gravedad en cada frame
        if (!enElSuelo) {
            velocidadY += gravedad;
            this.y += velocidadY;
        }
        if (this.y >= this.piso) {
        	this.setY(this.piso);
            this.velocidadY = 0;
            this.enElSuelo = true;
        }
    }
}
