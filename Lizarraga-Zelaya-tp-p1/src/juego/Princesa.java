package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Princesa {
	//posicion de la princesa en pantalla  
	private double x; 
	private double y; 
	//dimensiones constantes de la princesa 
	private int ANCHO; 
	private int ALTO; 
	//posicion(del piso inferior) inicial donde nace la princesa  
	private double piso = 750; 
	/*Se le asigna una imagen cuando se desplaza hacia la izquierda  
	y otra cuando lo hace hacia la derecha */ 
	private Image imagenDer; 
	private Image imagenIzq; 
	private double angulo; 
	private double escala; 
	//booleano para diferenciar los lados de disparo de la princesa 
	private boolean disparoLadoPrincesa; 
	//----------Variables de salto----------// 
	// Variable para verificar si el personaje está en el suelo 
	private boolean enElSuelo = true; 
	// Velocidad vertical del personaje,se inicializa en 0 hasta que detecte la tecla "X" 
	private double velocidadY = 0;  
	//Representa la velocidad de caida cuando no detecte un bloque  
	private double gravedad = 0.1; 	

	//----------constructor----------//
	public Princesa(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ANCHO = ancho;
		this.ALTO = alto;
		this.imagenDer = Herramientas.cargarImagen("princesaDer.png");
		this.imagenIzq = Herramientas.cargarImagen("princesaIzq.png");
		this.angulo = 0;
		this.escala = 0.03;
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
		return ANCHO;
	}

	public void setAncho(int ancho) {
		this.ANCHO = ancho;
	}

	public int getAlto() {
		return ALTO;
	}

	public void setAlto(int alto) {
		this.ALTO = alto;
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
            velocidadY = -7; //fuerza del salto
            this.enElSuelo = false;
        }
    }
	
    public void caidaSalto() {
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
