package juego;

import java.awt.Image;
import java.util.ArrayList;
import javax.sound.sampled.Clip;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	//private Clip sonido;
	private Bloques bloque; //Creación de Objeto Bloque
	private Bloques [] piso1; //Creación de conjunto de bloques que forman los pisos
	private Bloques [] piso2;	
	private Bloques [] piso3;
	private Bloques [] piso4;
	private Tiranosaurio [] tiranosaurios;
	//creación de Objetos
	private Princesa princesa; 
	private Proyectil proyectil;
	private Tiranosaurio tiranosaurio;
	private boolean disparoLado;
	private boolean disparoActivo;

	private char TECLA_X = 120;
	private char TECLA_C = 99;
	//Creación de objetos tipo Imagen
	private Image fondo;

	//String cancion = "./users/jorge/Desktop/Apellido1-Apellido2-Apellido3-tp-1/camping.mp3";
	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 1000, 700);
		//cargar imagen de fondo
		this.fondo = Herramientas.cargarImagen("lava.png");
		this.bloque = new Bloques(); //Inicialización de Bloque
		this.princesa = new Princesa(entorno.ancho()/2 , 557 , 1, 70); // Inicialización de princesa
		this.proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
		this.tiranosaurio = new Tiranosaurio(); // Inicialización de tiranosaurios
		
		// Inicializar lo que haga falta para el juego
		piso1 = new Bloques[20]; //Reserva de 20 espacios para bloques
		piso2 = new Bloques[20];
		piso3 = new Bloques[20];
		piso4 = new Bloques[20];
		tiranosaurios = new Tiranosaurio[8]; // Reserva de 8 espacios para tiranosaurios
		
		bloque.crearPiso(piso1 , 600,50,15); //llamada al contructor de bloque y creación de pisos
		bloque.crearPiso(piso2, 450,50,50);
		bloque.crearPiso(piso3 , 300,50,50);
		bloque.crearPiso(piso4 , 150,50,50);
		
		tiranosaurios[0] = new Tiranosaurio(100 , 540 , 20 , 20);
		tiranosaurios[1] = new Tiranosaurio(950 , 540 , 20 , 20);
		tiranosaurios[2] = new Tiranosaurio(100 , 400 , 20 , 20);
		tiranosaurios[3] = new Tiranosaurio(950 , 400 , 20 , 20);
		tiranosaurios[4] = new Tiranosaurio(100 , 240 , 20 , 20);
		tiranosaurios[5] = new Tiranosaurio(950 , 240 , 20 , 20);
		tiranosaurios[6] = new Tiranosaurio(100 , 95 , 20 , 20);
		tiranosaurios[7] = new Tiranosaurio(950 , 95 , 20 , 20);



		// Inicia el juego!
		this.entorno.iniciar();
	}
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		entorno.dibujarImagen(fondo, entorno.ancho()/2,entorno.alto()/2,0);
	    
	    // Dibujar los bloques de cada piso
	    for (int i = 0; i < piso1.length; i++) {
	        if (piso1[i] != null) {
	            piso1[i].dibujar(entorno, piso1);
	        }
	    }
	    for (int i = 0; i < piso2.length; i++) {
	        if (piso2[i] != null) {
	            piso2[i].dibujar(entorno, piso2);
	        }
	    }
	    for (int i = 0; i < piso3.length; i++) {
	        if (piso3[i] != null) {
	            piso3[i].dibujar(entorno, piso3);
	        }
	    }
	    for (int i = 0; i < piso4.length; i++) {
	        if (piso4[i] != null) {
	            piso4[i].dibujar(entorno, piso4);
	        }
	    }
		//dibujamos la princesa y le damos movilidad
		this.princesa.dibujarDer(this.entorno);
		this.tiranosaurio.dibujar(entorno, tiranosaurios);
		//movimiento izquierda
		if(this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)&& princesa.getX() > 31) {
			this.princesa.dibujarIzq(this.entorno);
			princesa.movIzq();
			this.disparoLado = true;
		}
		//moviento derecha 
		if(this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) && princesa.getX() < 970) {
			princesa.movDer();
			this.disparoLado = false;
		}
		//disparo izquierda
		if(this.entorno.sePresiono(this.TECLA_C) && proyectil != null && disparoLado == true && proyectil.getX() >0 ) {
				disparoActivo = true;
				this.proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
		}
		//disparo derecha
		if(this.entorno.sePresiono(this.TECLA_C) && proyectil != null && disparoLado == false && proyectil.getX() < 1000 ) {
			disparoActivo = true;
			this.proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
		}
		if(proyectil != null && this.proyectil.getX() >= 1000 ||proyectil != null && this.proyectil.getX() <= 0) {
			this.proyectil = null;
			disparoActivo = false;
			this.proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
		}
		if(proyectil!= null && disparoActivo == true && disparoLado == true) {
			proyectil.disparoIzq(entorno);
		}
		if(proyectil!= null && disparoActivo == true && disparoLado == false) {
			proyectil.disparoDer(entorno);
		}
		//si presiona la tecla X salta, rompe los bloques y cambia la gravedad para simular colision
		if(this.entorno.estaPresionada(this.TECLA_X)) {
			 princesa.saltar(); 
			 romperBloque();
			 
			 for (int i = 0; i < piso2.length; i++) {
				 if(piso2[i] != null){
					 Bloques bloque = piso2[i];
						if(colisionPrincesaBloque(bloque)) {
							princesa.setGravedad(6);
						}
						if(colisionPiesBloque(bloque)) {
							princesa.setPiso(princesa.getY() );
						}
				 }
			 }	 
		 }
		princesa.actualizarSalto();
		princesa.setGravedad(0.3);
	}
	
	public boolean colisionPiesBloque(Bloques bloque) {
		boolean colisionY = colisionPrincesaBloque(bloque) && princesa.getY() + princesa.getAlto()/2 > bloque.getY() - bloque.getAlto()/2
														   && princesa.getY() + princesa.getAlto()/2 < (bloque.getY() - bloque.getAlto()/2)+5;
		System.out.println(colisionY);
		return colisionY;
	}
	public boolean colisionCabezaBloque(Bloques bloque) {
		boolean colisionY = colisionPrincesaBloque(bloque) && princesa.getY() - princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2
														   && princesa.getY() - princesa.getAlto()/2 > (bloque.getY() + bloque.getAlto()/2)-10;
		return colisionY;
	}
	public boolean colisionPrincesaBloque(Bloques bloque) {
		if(bloque != null) {
			boolean colisionX = princesa.getX() - princesa.getAncho()/2 > bloque.getX() - bloque.getAncho()/2 && princesa.getX() - princesa.getAncho()/2 < bloque.getX() + bloque.getAncho()/2 ||
								princesa.getX() + princesa.getAncho()/2 > bloque.getX() - bloque.getAncho()/2 && princesa.getX() + princesa.getAncho()/2 < bloque.getX() + bloque.getAncho()/2;
		//verificamos que el vertice derecho superior de la princesa este dentro del bloque
			boolean colisionY = princesa.getY() - princesa.getAlto()/2 > bloque.getY() - bloque.getAlto()/2 && princesa.getY() - princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2 ||
								princesa.getY() + princesa.getAlto()/2 > bloque.getY() - bloque.getAlto()/2 && princesa.getY() + princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2;
			boolean colision = colisionX && colisionY;
			return colision;
		}
		return false;
	}
	public void romperBloque() {	
		for (int i = 0; i < piso2.length; i++) {
			 if(piso2[i] != null && piso2[i].GetseRompe()) {
				Bloques bloque = piso2[i];
				if(colisionCabezaBloque(bloque)) {
					piso2[i] = null;
				}
			 }	
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
