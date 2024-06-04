package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Tiranosaurio {
	//posición del tiranosaurio en pantalla 
	private int x; 
	private int y; 
	//dimensiones del tiranosaurio 
	private int ANCHO; 
	private int ALTO; 
	//booleanos indican si el Tiranosaurio toca algun borde lateral del entorno  
	private boolean tocoPantallaIzq = false; 
	private boolean tocoPantallaDer = false; 
	//booleado indica el lado de disparo del tiranosaurio 
	private boolean disparoLadoTiranosaurios; 
	//piso del tiranosaurio 
	private int piso; 
	//Representan la imagen en formato gif de los Tiranosaurios	  
	private Image imagen = Herramientas.cargarImagen("dino.gif"); 
	private double angulo; 
	private double escala; 
	//----------Variables de salto----------// 
	/*Estas variables las utilizamos para que el Tiranosaurio 
	reconozca el piso y en caso contrario caiga a un piso inferior */ 
	private boolean enElSuelo = true;  
	private double velocidadY = 0;  
	private double gravedad = 0.3; 		 
	
	//----------------Constructores-----------------//
	public Tiranosaurio() {}
	public Tiranosaurio(int x, int y, int ANCHO, int ALTO) {
		this.x = x;
		this.y = y;
		this.ANCHO = ANCHO;
		this.ALTO = ALTO;
		this.imagen = Herramientas.cargarImagen("dino.gif");
		this.angulo = 0;
		this.escala= 1;
		this.piso = this.y + this.ALTO/2;
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
	
	public boolean getDisparoLadoTiranosaurios() {
		return disparoLadoTiranosaurios;
	}
	
	public void setDisparoLadoTiranosaurios(boolean disparoLadoTiranosaurios) {
		this.disparoLadoTiranosaurios = disparoLadoTiranosaurios;
	}
	
	public int getPiso() {
		return piso;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	public boolean getEnElSuelo() {
		return enElSuelo;
	}
	public void setEnElSuelo(boolean enElSuelo) {
		this.enElSuelo = enElSuelo;
	}
	//----------------Metodos-----------------//
	public void movIzq() {
		if(tocoPantallaIzq == false) {
			this.x -= 1;
			disparoLadoTiranosaurios = true;
		}
		else if(tocoPantallaIzq == true){
			this.x = this.x+=1;
			disparoLadoTiranosaurios = false;
		}
	}
	
	public boolean tocoPantallaDinoComienzaSentidoIzq() {
		if(this.x < 31) {
			tocoPantallaIzq = true;
		}
		if(this.x > 975) {
			tocoPantallaIzq = false;
		}
		return tocoPantallaIzq;
	}
	
	public void movDer() {
		if(tocoPantallaDer == false) {
			this.x += 1;
			disparoLadoTiranosaurios = false;
		}
		else if(tocoPantallaDer == true){
			this.x = this.x-=1;
			disparoLadoTiranosaurios = true;
		}
	}
	
	public boolean tocoPantallaDinoComienzaSentidoDer() {
		if(this.x < 31) {
			tocoPantallaDer = false;
		}
		if(this.x > 975) {
			tocoPantallaDer = true;
		}
		return tocoPantallaDer;
	}
	
	public void crearTiranosaurio(ArrayList<Tiranosaurio> tiranosaurios, Tiranosaurio [] tiranosaur ) {
		for (int i = 0; i < tiranosaur.length; i++) {
			tiranosaurios.add(tiranosaur[i]);	
		}
	}
	
	public void dibujar(Entorno entorno , ArrayList<Tiranosaurio> tiranosaurios) {
		for (int i = 0; i < tiranosaurios.size(); i++) {
			Tiranosaurio tira = tiranosaurios.get(i);
				entorno.dibujarImagen(imagen, tira.getX(), tira.getY()-15, tira.getAngulo(), tira.getEscala());
		}
	}

    public void Caida() {
        // Aplicar gravedad en cada frame
        if(!enElSuelo) {
            velocidadY += gravedad;
            this.y += velocidadY;
        }
        if(this.y >= this.piso) {
        	this.setY(this.piso);
            this.velocidadY = 0;
            this.enElSuelo = true;
        }
    }
}
