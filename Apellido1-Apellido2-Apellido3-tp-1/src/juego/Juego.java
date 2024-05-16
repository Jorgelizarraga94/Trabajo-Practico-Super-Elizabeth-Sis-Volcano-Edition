package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Bloques bloque;
	private Bloques bloque2;
	private Bloques bloque3;
	private Bloques [] conjuntoBloques;
	private Bloques [] piso2;
	private Bloques [] piso3;
	
	
	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		Random rand = new Random();
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 800, 600);
		this.bloque = new Bloques(400,500,50,50,false);
		this.bloque2 = new Bloques(400,300,50,50,false);
		this.bloque3 = new Bloques(400,100,50,50,false);
		
		conjuntoBloques = new Bloques[16];
		piso2 = new Bloques[16];
		piso3 = new Bloques[16];
		bloque.crearPiso(bloque, conjuntoBloques , 500);
		bloque.crearPiso(bloque2, piso2, 300);
		bloque.crearPiso(bloque3, piso3 , 100);
		// Inicializar lo que haga falta para el juego
		// ...
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
		
		for (int i = 0; i < conjuntoBloques.length; i++) {
			this.conjuntoBloques[i].dibujar(this.entorno);
		}
		for (int i = 0; i < piso2.length; i++) {
			this.piso2[i].dibujar(this.entorno);
		}
		for (int i = 0; i < piso3.length; i++) {
			this.piso3[i].dibujar(this.entorno);
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
