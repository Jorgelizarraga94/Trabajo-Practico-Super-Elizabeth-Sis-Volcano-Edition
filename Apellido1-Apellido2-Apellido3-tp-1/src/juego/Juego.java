package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Bloques bloque; //Creación de Objeto Bloque
	private Bloques [] piso1; //Creación de conjunto de bloques que forman los pisos
	private Bloques [] piso2;	
	private Bloques [] piso3;
	private Bloques [] piso4;
	private Princesa princesa; //Creación del objeto princesa
	
	
	boolean enElSuelo = true;  // Variable para verificar si el personaje está en el suelo
	double velocidadY = 0;     // Velocidad vertical del personaje
	double gravedad = 0.5; 		// Gravedad del personaje
	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 1000, 700);
		this.bloque = new Bloques(); //Inicialización de Bloque
		this.princesa = new Princesa(); // Inicialización de princesa
		
		// Inicializar lo que haga falta para el juego
		piso1 = new Bloques[20]; //Reserva de 20 espacios para bloques
		piso2 = new Bloques[20];
		piso3 = new Bloques[20];
		piso4 = new Bloques[20];
		 
				
		bloque.crearPiso(piso1 , 600 , 50,15); //llamada al contructor de bloque y creación de pisos
		bloque.crearPiso(piso2, 450,40,40);
		bloque.crearPiso(piso3 , 300,40,40);
		bloque.crearPiso(piso4 , 150,40,40);
		
		
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
		// Procesamiento de un instante de tiempo
		// ...
		//Recorremos un piso y dibujamos los primeros 4 con el metodo dibujar
		for (int i = 0; i < piso1.length; i++) {
			this.piso1[i].dibujar(this.entorno, piso1);
			this.piso2[i].dibujar(this.entorno , piso2);
			this.piso3[i].dibujar(this.entorno , piso3);
			this.piso4[i].dibujar(this.entorno , piso4);
		}
		//dibujamos la princesa y le damos movilidad
		this.princesa.dibujar(this.entorno);
		//movimiento izquierda
		movIzq();
		//moviento derecha
		movDer();
		//saltar
		saltar();
		
	}
	public void movIzq() {
		if(this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)) {
			this.princesa.x -= 2;
		}
	}
	public void movDer() {
		if(this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)) {
			this.princesa.x += 2;
		}
	}
	public void saltar() {
	    // Detectar si se presionó la tecla de salto
	    if(this.entorno.sePresiono(this.entorno.TECLA_ARRIBA)) {
	        if(enElSuelo) {
	            velocidadY = -10; // Ajusta este valor para cambiar la fuerza del salto
	            enElSuelo = false;
	        }
	    }

	    // Aplicar gravedad
	    velocidadY += gravedad;
	    this.princesa.y += velocidadY;

	    // Verificamos si el personaje está tocando el suelo
	    if(this.princesa.y >= 550) { // Suponiendo que 550 es la posición del suelo
	        this.princesa.y = 550;
	        velocidadY = 0;
	        enElSuelo = true;
	    }

	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
