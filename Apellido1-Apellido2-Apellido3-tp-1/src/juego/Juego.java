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
	private Princesa princesa; //Creación del objeto princesa

	private char TECLA_X = 120;
	//Creación de objetos tipo Imagen
	private Image fondo;

	boolean enElSuelo = true;  // Variable para verificar si el personaje está en el suelo
	double velocidadY = 0;     // Velocidad vertical del personaje
	double gravedad = 0.9; 		// Gravedad del personaje
	//String cancion = "./users/jorge/Desktop/Apellido1-Apellido2-Apellido3-tp-1/camping.mp3";
	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 1000, 700);
		//cargar imagen de fondo
		this.fondo = Herramientas.cargarImagen("lava.png");
		this.bloque = new Bloques(); //Inicialización de Bloque
		this.princesa = new Princesa(entorno.ancho()/2 , 557 , 50, 70); // Inicialización de princesa
		
		// Inicializar lo que haga falta para el juego
		piso1 = new Bloques[20]; //Reserva de 20 espacios para bloques
		piso2 = new Bloques[20];
		piso3 = new Bloques[20];
		piso4 = new Bloques[20];
		 
		bloque.crearPiso(piso1 , 600,50,15); //llamada al contructor de bloque y creación de pisos
		bloque.crearPiso(piso2, 450,50,40);
		bloque.crearPiso(piso3 , 300,50,40);
		bloque.crearPiso(piso4 , 150,50,40);
		
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
		//movimiento izquierda
		if(this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)&& princesa.getX() > 31) {
			princesa.movIzq();			
			this.princesa.dibujarIzq(this.entorno);
		}
		//moviento derecha
		if(this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) && princesa.getX() < 970) {
			princesa.movDer();
		}
		if(this.entorno.estaPresionada(TECLA_X)) {
			 princesa.saltar(); 
			 romperBloque();
			 System.out.println(princesa.getY());
		 }
		princesa.actualizarSalto();
	}

	public void romperBloque() {
	    // Obtener la posición y tamaño de la princesa
	    int princesaX = princesa.getX();
	    int princesaY = princesa.getY();
	    int princesaAncho = princesa.getAncho();
	    int princesaAlto = princesa.getAlto();

	    // Iterar sobre los bloques del piso2
	    for (int i = 0; i < piso2.length; i++) {
	        Bloques bloqueActual = piso2[i];
	        
	        // Verificar si el bloque actual no es nulo y es rompible
	        if (bloqueActual != null && bloqueActual.seRompe) {
	            // Calcular los límites del bloque actual
	            int bloqueX = bloqueActual.getX();
	            int bloqueY = bloqueActual.getY();
	            int bloqueAncho = bloqueActual.getAncho();
	            int bloqueAlto = bloqueActual.getAlto();

	            // Verificar si la princesa está dentro de los límites del bloque actual
	            boolean colisionX = princesaX + princesaAncho >= bloqueX && princesaX <= bloqueX + bloqueAncho;
	            boolean colisionY = princesaY + princesaAlto >= bloqueY && princesaY <= bloqueY + bloqueAlto;

	            if (colisionX && colisionY) {
	                // Eliminar el bloque actual y salir del bucle
	                piso2[i] = null;
	                break;
	            }
	        }
	    }
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
