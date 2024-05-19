package juego;



import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Princesa {
	public int x;
	public int y;
	private int ancho;
	private int alto;
	private Image imagen;
	private double angulo;
	private double escala;
	private Entorno entorno;
	//Variables de salto
	boolean enElSuelo = true;  // Variable para verificar si el personaje está en el suelo
	double velocidadY = 0;     // Velocidad vertical del personaje
	double gravedad = 0.9; 		// Gravedad del personaje
	
	
	public Princesa(int x, int y, int ancho, int alto) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.imagen = Herramientas.cargarImagen("princesa.png");
		this.angulo = 0;
		this.escala = 0.06;
	}
	public void dibujar(Entorno entorno) {
		//entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.blue);
		entorno.dibujarImagen(imagen, x, y, angulo, escala);
	}
	public void movIzq() {
		this.x -= 2;
		
	}
	public void movDer() {
		this.x += 2;
	}
	 public void saltar() {
	        // Detectar si se presionó la tecla de salto
	        if (this.enElSuelo) {
	            velocidadY = -10; // Ajusta este valor para cambiar la fuerza del salto
	            this.enElSuelo = false;
	        }
	    }
    public void actualizar() {
        // Aplicar gravedad en cada frame
        if (!enElSuelo) {
            velocidadY += gravedad;
            this.y += velocidadY;
        }
        // Verificar si el personaje está tocando el suelo
        if (this.y >= 550) { // Suponiendo que 550 es la posición del suelo
            this.y = 550;
            this.velocidadY = 0;
            this.enElSuelo = true;
        }
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

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
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
	
	
	
	
}
