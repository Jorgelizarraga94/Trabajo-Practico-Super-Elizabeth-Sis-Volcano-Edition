package juego;

import java.awt.Image;
import java.util.ArrayList;
import javax.sound.sampled.Clip;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	private Entorno entorno;
	private Bloques bloque;   
	private Bloques [] piso1; 
	private Bloques [] piso2;	
	private Bloques [] piso3;
	private Bloques [] piso4;
	private Princesa princesa; 
	private Tiranosaurio [] tiranosaurios;
	private Tiranosaurio tiranosaurio;
	private Proyectil proyectil;
	private boolean disparoLado;
	private boolean disparoActivo;
	//-----------Variables de teclas--------------//
	private char TECLA_X = 120;
	private char TECLA_C = 99;

	private Image fondo;

	Juego() {
		//--------Inicializa el objeto entorno--------//
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 1000, 700);
		//Inicializar lo que haga falta para el juego
		//--------carga de imagen de fondo--------//
		this.fondo = Herramientas.cargarImagen("lava.png");
		this.bloque = new Bloques(); 
		this.princesa = new Princesa(entorno.ancho()/2 , 557 , 1, 70); 
		this.proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
		this.tiranosaurio = new Tiranosaurio(); 
		//--------Reserva de 20 espacios para bloques--------//
		piso1 = new Bloques[20]; 
		piso2 = new Bloques[20];
		piso3 = new Bloques[20];
		piso4 = new Bloques[20];
		//--------Reserva de 8 espacios para tiranosaurios--------//
		tiranosaurios = new Tiranosaurio[8]; 
		//--------llamada al contructor de bloque y creación de pisos--------//
		bloque.crearPiso(piso1 , 600,50,15); 
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
		//--------FONDO--------//
		entorno.dibujarImagen(fondo, entorno.ancho()/2,entorno.alto()/2,0);
	    //--------DIBUJA LOS BLOQUES DE CADA PISO--------//
		dibujarBloques(piso1 ,piso2 , piso3 , piso4);
		//--------DIBUJA A LA PRINCESA Y LE DA MOVILIDAD--------//
		this.princesa.dibujarDer(this.entorno);
		//--------DIBUJA EL TIRANOSAURIO--------//
		this.tiranosaurio.dibujar(entorno, tiranosaurios);
		//--------MOVIMIENTO IZQUIERDA--------//
		if(this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)&& princesa.getX() > 31) {
			this.princesa.dibujarIzq(this.entorno);
			princesa.movIzq();
			this.disparoLado = true;
		}
		//--------MOVIMIENTO DERECHA--------//
		if(this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) && princesa.getX() < 970) {
			princesa.movDer();
			this.disparoLado = false;
		}
		//--------DISPARO IZQUIERDA--------//
		if(this.entorno.sePresiono(this.TECLA_C) && proyectil != null && disparoLado == true && proyectil.getX() >0 ) {
				disparoActivo = true;
				this.proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
		}
		//--------DISPARO DERECHA--------//
		if(this.entorno.sePresiono(this.TECLA_C) && proyectil != null && disparoLado == false && proyectil.getX() < 1000 ) {
			disparoActivo = true;
			this.proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
		}
		//--------CONTROL DE DIRECCIÓN DEL DISPARO----------//
		if(proyectil!= null && disparoActivo == true && disparoLado == true) {
			proyectil.disparoIzq(entorno);
		}
		if(proyectil!= null && disparoActivo == true && disparoLado == false) {
			proyectil.disparoDer(entorno);
		}
		//--------LUEGO DE DISPARAR, PONEMOS AL PROYECTIL EL NULL Y VOLVEMOS A CREARLO--------//
		if(proyectil != null && this.proyectil.getX() >= 1000 ||proyectil != null && this.proyectil.getX() <= 0) {
			this.proyectil = null;
			disparoActivo = false;
			this.proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
		}
		
		//--------SI PRESIONA LA TECLA X SALTA, ROMPE LOS BLOQUES Y CAMBIA LA GRAVEDAD PARA SIMULAR COLISION--------//
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
	//-----------------------Recibe arrays de pisos y los dibuja------------------------//
	public void dibujarBloques(Bloques [] piso1, Bloques [] piso2 , Bloques [] piso3 , Bloques [] piso4 ) {
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
	}
	//-----------------------Colisión entre el pie de la princesa y la parte superior del bloque------------------------//
	
	public boolean colisionPiesBloque(Bloques bloque) {
		boolean colisionY = colisionPrincesaBloque(bloque) && princesa.getY() + princesa.getAlto()/2 > bloque.getY() - bloque.getAlto()/2
														   && princesa.getY() + princesa.getAlto()/2 < (bloque.getY() - bloque.getAlto()/2)+5;
		System.out.println(colisionY);
		return colisionY;
	}
	//-----------------------Colisión entre la cabeza y la parte inferior del bloque------------------------//
	
	public boolean colisionCabezaBloque(Bloques bloque) {
		boolean colisionY = colisionPrincesaBloque(bloque) && princesa.getY() - princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2
														   && princesa.getY() - princesa.getAlto()/2 > (bloque.getY() + bloque.getAlto()/2)-10;
		return colisionY;
	}
	//-----------------------Colisión general entre la princesa y el bloque------------------------//
	
	public boolean colisionPrincesaBloque(Bloques bloque) {
		if(bloque != null) {
			boolean colisionX = princesa.getX() - princesa.getAncho()/2 > bloque.getX() - bloque.getAncho()/2 && princesa.getX() - princesa.getAncho()/2 < bloque.getX() + bloque.getAncho()/2 ||
								princesa.getX() + princesa.getAncho()/2 > bloque.getX() - bloque.getAncho()/2 && princesa.getX() + princesa.getAncho()/2 < bloque.getX() + bloque.getAncho()/2;
			boolean colisionY = princesa.getY() - princesa.getAlto()/2 > bloque.getY() - bloque.getAlto()/2 && princesa.getY() - princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2 ||
								princesa.getY() + princesa.getAlto()/2 > bloque.getY() - bloque.getAlto()/2 && princesa.getY() + princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2;
			boolean colision = colisionX && colisionY;
			return colision;
		}
		return false;
	}
	//-----------------------Eliminación de bloques luego de la colision------------------------//
	
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
