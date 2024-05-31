package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;

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
	private ArrayList <Bloques> todosLosPisos;
	private ArrayList <Tiranosaurio> listaTiranosaurio;
	private ArrayList <Bomba> bombaDino;
	private ArrayList <Integer> pisos;
	private Princesa princesa; 
	private Tiranosaurio [] tiranosaurios;
	private Tiranosaurio tiranosaurio;
	private Proyectil proyectil;
	private Random numeroAleatorio = new Random();
	private Image inicio;
	private Image fondo;
	private int enemigosEliminados;
	private int puntaje;
	private Clip sonidoInicio;
	private Clip sonidoJuego;
	private Clip saltar;
	private boolean pantallaDeInicio = true;
	
	Juego() {
		this.enemigosEliminados = 0;
		this.puntaje = 0;
		//Inicialización del objeto entorno
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 1000, 850);
		//Inicialización  del sonido de inicio
		sonidoInicio = Herramientas.cargarSonido("inicio.wav");
		//Inicialización  del sonido del juego
		sonidoJuego =  Herramientas.cargarSonido("stratosphere.wav");
		//Inicilización del sonido de salto
		saltar =  Herramientas.cargarSonido("jump1.wav");
		
		//Inicializar lo que haga falta para el juego
		this.inicio = Herramientas.cargarImagen("inicio.jpg");
		//carga de imagen de fondo
		this.fondo = Herramientas.cargarImagen("interiorVolcan.jpeg");
		this.bloque = new Bloques(); 
		this.princesa = new Princesa(entorno.ancho()/2 , 750 , 10, 50); 
		this.proyectil = new Proyectil(princesa.getX(), princesa.getY() + princesa.getAlto()/2 , 20, 20);
		this.tiranosaurio = new Tiranosaurio(); 
		
		//Reserva de 20 espacios para bloques
		piso1 = new Bloques[20]; 
		piso2 = new Bloques[20];
		piso3 = new Bloques[20];
		piso4 = new Bloques[20];
		//Reserva de 8 espacios para tiranosaurios
		tiranosaurios = new Tiranosaurio[8]; 
		//Inicialización de la lista de Bloques y creación de pisos
		todosLosPisos = new ArrayList<Bloques>();
		bloque.crearPiso(todosLosPisos,piso1 , 800,50,50); 
		bloque.crearPiso(todosLosPisos,piso2, 600,50,50);
		bloque.crearPiso(todosLosPisos,piso3 , 400,50,50);
		bloque.crearPiso(todosLosPisos,piso4 , 200,50,50);
		pisos = new ArrayList <Integer>();
		//Inicialización de la lista de Tiranosaurio y creación de Tiranosaurios
		listaTiranosaurio = new ArrayList<Tiranosaurio>();
		bombaDino = new ArrayList <Bomba>();
		tiranosaurios[0] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 761 , 10 , 30);
		tiranosaurios[1] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 761 , 10 , 30);
		tiranosaurios[2] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 561 , 10 , 30);
		tiranosaurios[3] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 561 , 10 , 30);
		tiranosaurios[4] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 361 , 10 , 30);
		tiranosaurios[5] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 361 , 10 , 30);
		tiranosaurios[6] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 161 , 10 , 30);
		tiranosaurios[7] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 161 , 10 , 30);
		tiranosaurio.crearTiranosaurio(listaTiranosaurio, tiranosaurios);
		//Proyectil tiranosaurio
		for (int i = 0; i < listaTiranosaurio.size(); i++) {
			bombaDino.add(new Bomba(listaTiranosaurio.get(i).getX(),listaTiranosaurio.get(i).getY(),20,20)); 
		}
		pisos.add(161);
		pisos.add(361);
		pisos.add(561);
		pisos.add(761);
		
		// Inicia el juego!
		this.entorno.iniciar();
	}
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick(){
		if(pantallaDeInicio) {
			mostrarPantallaInicio();
			if(this.entorno.sePresiono((char) KeyEvent.VK_ENTER)) {
				pantallaDeInicio = false;
			}
		}
		else {
			dibujarPantalla();
			actualizarJuego();
		}
	}
	
	public void mostrarPantallaInicio() {
		entorno.dibujarImagen(inicio, entorno.ancho()/2,entorno.alto()/2,0);
		sonidoInicio.loop(1);
	}
	public void dibujarPantalla () {
		//cierre de sonido de inicio
		sonidoInicio.close();
		//apertura de sonido del juego
		sonidoJuego.loop(3);
		//Dibuja el fondo del juego
		entorno.dibujarImagen(fondo, entorno.ancho()/2,entorno.alto()/2,0);
	    //Dibuja los bloques de cada piso
		dibujarBloques(todosLosPisos);
		//Dibuja el puntaje
		this.entorno.cambiarFont("",25, Color.white);
		this.entorno.escribirTexto("Puntaje: "+ this.puntaje, 15,20);
		//Dibuja enemigos eliminados.
		this.entorno.escribirTexto("Enemigos Eliminados: "+ this.enemigosEliminados, 15,50);
		//Dibuja a la princesa
		this.princesa.dibujarDer(this.entorno);
		//Dibuja tiranosaurio
		this.tiranosaurio.dibujar(entorno, listaTiranosaurio);
	}
	
	public void actualizarJuego() {
		//Movimiento izquierda y caida si bloque es null
		moverIzquierdaPrincesa();
		//Movimiento derecha y caida si bloque es null
		moverDerechaPrincesa();
		//Disparo princesa
		disparoPrincesa(this.proyectil, princesa.getDisparoLadoPrincesa());
		//Salto de la princesa
		saltoPrincesa();
		//Verificación de colisión constante entre proyectil-bomba y seteo de las mismas
		verificacionProyectilBomba();
		//verificación de colision constante entre los tiranosaurios y los bloques
		verificacionTiranosauriosBloques();
		//Verificación de colisión constante entre los proyectiles de la princesa y los tiranosaurios
		verificacionProyectilTiranosaurios();
		//Verificación de colisión entre princesa y bloques
		verificacionDeColisionesPrincesaBloque();
		//Automatización de movimientos de los tiranosaurios
		moverTiranosaurios();
		//Disparo tiranosaurios
		disparoTiranosaurio(bombaDino);	
		//Caida del salto de la princesa
		princesa.caidaSalto();
		//Game Over
		gameOver();
	}
	
								//----------------------------------Metodos Princesa---------------------------------//
								//-----------------------------------------------------------------------------------//
	//Movimiento izquierda de la princesa
	public void moverIzquierdaPrincesa() {
		if(this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)&& princesa.getX() > 31) {
			this.proyectil = new Proyectil(princesa.getX(), princesa.getY() + princesa.getAlto()/2 , 20, 20);
			this.princesa.dibujarIzq(this.entorno);
			princesa.movIzq();
			princesa.setDisparoLadoPrincesa(true);
			for(int i = 0; i < todosLosPisos.size(); i++) {
				if(colisionPrincesaBloque(todosLosPisos.get(i))) {
				}
				else {
					princesa.setEnElSuelo(false);
					princesa.setPiso(750);
					if(colisionPiesBloque(todosLosPisos.get(i))){
						princesa.setPiso(princesa.getY());
					}
				}
			}
		}
	}
	//Movimiento hacia la derecha de la princesa
	public void moverDerechaPrincesa() {
		if(this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) && princesa.getX() < 970) {
			this.proyectil = new Proyectil(princesa.getX(), princesa.getY() + princesa.getAlto()/2 , 20, 20);
			princesa.movDer();
			princesa.setDisparoLadoPrincesa(false);
			for(int i = 0; i < todosLosPisos.size(); i++) {
				if(colisionPrincesaBloque(todosLosPisos.get(i))) {
				}
				else {
					princesa.setEnElSuelo(false);
					princesa.setPiso(750);
					if(colisionPiesBloque(todosLosPisos.get(i))){
						princesa.setPiso(princesa.getY());
					}	
				}
			}	
		}
	}
	//Disparo de la princesa
	public void disparoPrincesa(Proyectil proyectil , boolean disparoLado) {
	//Control de dirección del disparo
		//Disparo Izquierda
		if(this.entorno.estaPresionada('c') && proyectil != null && princesa.getDisparoLadoPrincesa() == true && proyectil.getX() >0 ) {
				proyectil.setDisparoActivo(true);			
		}
		//Disparo Derecha
		if(this.entorno.estaPresionada('c') && proyectil != null && princesa.getDisparoLadoPrincesa() == false && proyectil.getX() < 1000 ) {
			proyectil.setDisparoActivo(true);
		}
		if(proyectil!= null && proyectil.getDisparoActivo() == true && disparoLado == true) {
			proyectil.disparoIzq(entorno,30);
		}
		if(proyectil!= null && proyectil.getDisparoActivo() == true && disparoLado == false) {
			proyectil.disparoDer(entorno,30);
		}
		//Luego de disparar, ponemos al proyectil en null y volvemos a crearlo
		if(proyectil != null && this.proyectil.getX() >= 1000 ||proyectil != null && this.proyectil.getX() <= 0) {
			this.proyectil = null;
			this.proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
		}	
	}
	//Salto de la princesa
	public void saltoPrincesa() {
		if(this.entorno.estaPresionada('x')) {
			princesa.saltar();
			saltar.loop(1);
			romperBloque(); 
			for(Bloques bloque:todosLosPisos) {
				if(colisionPiesBloque(bloque) && bloque == null) {
					princesa.setVelocidadY(+10);
				}
			}
		}
	}
	/*Recorremos cada bloque de la lista, y verificamos si hay colisión entre la cabeza y el bloque,
	en caso de dar true la función de colisión cabeza-bloque, pasamos a setear la velocidad de salto
	para que frene en la parte inferior del bloque*/
	public void verificacionDeColisionesPrincesaBloque() {
		for(Bloques bloque:todosLosPisos) {
			if(colisionCabezaBloque(bloque)) {
				princesa.setVelocidadY(+10);
			}
			if(colisionPiesBloque(bloque)){
				princesa.setPiso(princesa.getY());
			}
		}
	}
	//Colisión entre el pie de la princesa y la parte superior del bloque
	public boolean colisionPiesBloque(Bloques bloque) {
		boolean colisionY = colisionPrincesaBloque(bloque) &&
				princesa.getY() + princesa.getAlto()/2 > bloque.getY() - bloque.getAlto()/2 &&
				princesa.getY() + princesa.getAlto()/2 < (bloque.getY() - (bloque.getAlto()/2)+20);
		return colisionY;
	}
	//Colisión entre la cabeza de la princesa y la parte inferior del bloque
	public boolean colisionCabezaBloque(Bloques bloque) {
		boolean colisionY = colisionPrincesaBloque(bloque) && 
				princesa.getY() - princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2 &&
				princesa.getY() - princesa.getAlto()/2 > (bloque.getY() - bloque.getAlto()/2);	   
		return colisionY;
	}   
	//Colisión general entre la princesa y el bloque
		public boolean colisionPrincesaBloque(Bloques bloque) {
			if(bloque != null) {
				boolean colisionX = princesa.getX() - princesa.getAncho()/2 < bloque.getX() + bloque.getAncho()/2 && 
									princesa.getX() - princesa.getAncho()/2 > bloque.getX() - bloque.getAncho()/2 ||
									princesa.getX() + princesa.getAncho()/2 > bloque.getX() - bloque.getAncho()/2 && 
									princesa.getX() + princesa.getAncho()/2 < bloque.getX() + bloque.getAncho()/2;
				boolean colisionY = princesa.getY() - princesa.getAlto()/2 > bloque.getY() - bloque.getAlto()/2 && 
									princesa.getY() - princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2 ||
									princesa.getY() + princesa.getAlto()/2 > bloque.getY() - bloque.getAlto()/2 && 
									princesa.getY() + princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2;
				boolean colision = colisionX && colisionY;
				return colision;
			}
			else {
				return false;
			}
		}
	//Colisión Princesa Tiranosaurio
	public boolean colisionPrincesaTiranosaurio(Tiranosaurio tiranosaurio) {
		if(tiranosaurio != null) {
			boolean colisionX = princesa.getX() - princesa.getAncho()/2 < tiranosaurio.getX() + tiranosaurio.getANCHO()/2 && 
								princesa.getX() - princesa.getAncho()/2 > tiranosaurio.getX() - tiranosaurio.getANCHO()/2 ||
								princesa.getX() + princesa.getAncho()/2 > tiranosaurio.getX() - tiranosaurio.getANCHO()/2 && 
								princesa.getX() + princesa.getAncho()/2 < tiranosaurio.getX() + tiranosaurio.getANCHO()/2;
			boolean colisionY = tiranosaurio.getY() - tiranosaurio.getALTO()/2 > princesa.getY() - princesa.getAlto()/2 && 
								tiranosaurio.getY() - tiranosaurio.getALTO()/2 < princesa.getY() + princesa.getAlto()/2 ||
								tiranosaurio.getY() + tiranosaurio.getALTO()/2 > princesa.getY() - princesa.getAlto()/2 && 
								tiranosaurio.getY() + tiranosaurio.getALTO()/2 <= princesa.getY() + princesa.getAlto()/2;
			return colisionX && colisionY;
		}
		else {
			return false;
		}
	}
	//Colisión Princesa-Bomba
	public boolean colisionPrincesaBomba(Bomba bomba) {
		if(bomba != null) {
			boolean colisionX = princesa.getX() - princesa.getAncho()/2 < bomba.getX() + bomba.getANCHO()/2 && 
								princesa.getX() - princesa.getAncho()/2 > bomba.getX() - bomba.getANCHO()/2 ||
								princesa.getX() + princesa.getAncho()/2 > bomba.getX() - bomba.getANCHO()/2 &&
								princesa.getX() + princesa.getAncho()/2 < bomba.getX() + bomba.getANCHO()/2;
			boolean colisionY = bomba.getY() - bomba.getALTO()/2 > princesa.getY() - princesa.getAlto()/2 && 
								bomba.getY() - bomba.getALTO()/2 < princesa.getY() + princesa.getAlto()/2 ||
								bomba.getY() + bomba.getALTO()/2 > princesa.getY() - princesa.getAlto()/2 && 
								bomba.getY() + bomba.getALTO()/2 <= princesa.getY() + princesa.getAlto()/2;
			return colisionX && colisionY;
		}
		else {
			return false;
		}
	}
	
	//Verificación Proyectil-Tiranosaurio y sumatoria de puntos
	private void verificacionProyectilTiranosaurios() {
		for (int i = 0; i < listaTiranosaurio.size(); i++) {
			if(colisionTiranosaurioProyectil(proyectil,listaTiranosaurio.get(i))) {
				System.out.println("hay colision");
				int contador= numeroAleatorio.nextInt(0,3);
				proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
				Tiranosaurio tiranosaurio = new Tiranosaurio(numeroAleatorio.nextInt(50, 900), pisos.get(contador) , 10 , 30);
				listaTiranosaurio.set(i, tiranosaurio);
				enemigosEliminados ++;
				puntaje += 2;
			}
		}
	}	
								//----------------------------Metodos tiranosaurios--------------------------------------//
								//---------------------------------------------------------------------------------------//
	//Movimiento de los tiranosaurios y colisión con la princesa
	public void moverTiranosaurios() {
		for (int i = 0; i < listaTiranosaurio.size(); i++) {
			if(i % 2 == 0) {
				listaTiranosaurio.get(i).movDer();
			}
			else {
				listaTiranosaurio.get(i).movIzq();
			}
			listaTiranosaurio.get(i).tocoPantallaDinoComienzaSentidoIzq();
			listaTiranosaurio.get(i).tocoPantallaDinoComienzaSentidoDer();
			//colision PrincesaTiranosaurio
			if(colisionPrincesaTiranosaurio(listaTiranosaurio.get(i))) {
				//System.out.println("colision dino princesa");
			}
		}
	}
	//Disparo de bombas del tiranosaurio y colisión entre bombas y princesa
	public void disparoTiranosaurio(ArrayList<Bomba> bomba) {
		//Control de dirección del disparo
		for (int i = 0; i < bomba.size(); i++) {
			if(bomba.get(i)!= null && listaTiranosaurio.get(i).getDisparoLadoTiranosaurios() == true) {
				bomba.get(i).disparoIzq(entorno, 3);
			}
			if(bomba.get(i)!= null && listaTiranosaurio.get(i).getDisparoLadoTiranosaurios() == false ) {
				bomba.get(i).disparoDer(entorno,3);
			}
			//Luego de disparar, ponemos el proyectil en null y volvemos a crearlo
			if(bomba != null && bomba.get(i).getX() >= 1000 ||bomba.get(i) != null && bomba.get(i).getX() <= 0) {
				Bomba nuevoProyectil = new Bomba(listaTiranosaurio.get(i).getX() ,listaTiranosaurio.get(i).getY(),20,20);
				bomba.set(i, nuevoProyectil);
			}
			//Posible fin del juego
			if(colisionPrincesaBomba(bomba.get(i))){
				//finaliza el juego, pedirle codigo a Marcelo
			}
		}	
	}
	//Colisión pies tiranosaurio-bloque
	public boolean colisionPiesTiranosauriosBloque(Bloques bloque, Tiranosaurio tiranosaurio) {
	    boolean colisionY = colisionTiranosaurioBloque(bloque, tiranosaurio) &&
	                        tiranosaurio.getY() + ((tiranosaurio.getALTO() / 2) + 3) > bloque.getY() - bloque.getAlto() / 2 &&
	                        tiranosaurio.getY() + ((tiranosaurio.getALTO() / 2) + 3) < bloque.getY() + (bloque.getAlto() / 2);
	    return colisionY;
	}
	//Colisión tiranosaurio-proyectil
	private boolean colisionTiranosaurioProyectil(Proyectil proyectil, Tiranosaurio tiranosaurio) {
		if(tiranosaurio != null && proyectil != null) {
			boolean colisionX = (tiranosaurio.getX() - tiranosaurio.getANCHO() / 2 < proyectil.getX() + proyectil.getANCHO() / 2 &&
			                     tiranosaurio.getX() - tiranosaurio.getANCHO() / 2 > proyectil.getX() - proyectil.getANCHO() / 2) ||
			                    (tiranosaurio.getX() + tiranosaurio.getANCHO() / 2 > proyectil.getX() - proyectil.getANCHO() / 2 &&
			                     tiranosaurio.getX() + tiranosaurio.getANCHO() / 2 < proyectil.getX() + proyectil.getANCHO() / 2);
			boolean colisionY = (tiranosaurio.getY() - tiranosaurio.getALTO() / 2 > proyectil.getY() - proyectil.getANCHO() / 2 &&
			                    tiranosaurio.getY() - tiranosaurio.getALTO() / 2 < proyectil.getY() + proyectil.getANCHO() / 2) ||
			                   	(tiranosaurio.getY() + tiranosaurio.getALTO() / 2 > proyectil.getY() - proyectil.getANCHO() / 2 &&
			                    tiranosaurio.getY() + tiranosaurio.getALTO() / 2 <= proyectil.getY() + proyectil.getANCHO() / 2);
			return colisionX && colisionY;
		}
		else {
			return false;
		}
	}
	//Colisión tiranosaurio-Bloque
	private boolean colisionTiranosaurioBloque(Bloques bloque, Tiranosaurio tiranosaurio) {
		if(tiranosaurio != null && bloque != null) {
			boolean colisionX = (tiranosaurio.getX() - tiranosaurio.getANCHO() / 2 < bloque.getX() + bloque.getAncho() / 2 &&
			                     tiranosaurio.getX() - tiranosaurio.getANCHO() / 2 > bloque.getX() - bloque.getAncho() / 2) ||
			                    (tiranosaurio.getX() + tiranosaurio.getANCHO() / 2 > bloque.getX() - bloque.getAncho() / 2 &&
			                     tiranosaurio.getX() + tiranosaurio.getANCHO() / 2 < bloque.getX() + bloque.getAncho() / 2);
			boolean colisionY = (tiranosaurio.getY() - tiranosaurio.getALTO() / 2 > bloque.getY() - bloque.getAlto() / 2 &&
			                    tiranosaurio.getY() - tiranosaurio.getALTO() / 2 < bloque.getY() + bloque.getAlto() / 2) ||
			                   	(tiranosaurio.getY() + tiranosaurio.getALTO() / 2 > bloque.getY() - bloque.getAlto() / 2 &&
			                    tiranosaurio.getY() + tiranosaurio.getALTO() / 2 <= bloque.getY() + bloque.getAlto() / 2);
			return colisionX && colisionY;
		}
		else {
			return false;
		}
	}
	
	public boolean colisionTiranosaurios(Tiranosaurio tiranosaurio) {
		if(tiranosaurios != null) {
			boolean colisionX = tiranosaurio.getX() - tiranosaurio.getANCHO()/2 > tiranosaurio.getX() + tiranosaurio.getANCHO()/2 && tiranosaurio.getX() - tiranosaurio.getANCHO()/2 < tiranosaurio.getX() - tiranosaurio.getANCHO()/2 ||
								tiranosaurio.getX() + tiranosaurio.getANCHO()/2 > tiranosaurio.getX() - tiranosaurio.getANCHO()/2 && tiranosaurio.getX() + tiranosaurio.getANCHO()/2 < tiranosaurio.getX() + tiranosaurio.getANCHO()/2;
			return colisionX;
		}
		else {
			return false;
		}
	}
	//VERIFICACIÓN DE COLISION CONSTANTE ENTRE LOS TIRANOSAURIOS Y LOS BLOQUES
	//se busca captar el momento en el que los tiranosaurio se encuentran con un bloque null
	public void verificacionTiranosauriosBloques() {
		for (int i = 0; i < listaTiranosaurio.size(); i++) {
			listaTiranosaurio.get(i).Caida();
		    boolean colisionDetectada = false;
		    for (int j = 0; j < todosLosPisos.size(); j++) {
		        if (colisionPiesTiranosauriosBloque(todosLosPisos.get(j), listaTiranosaurio.get(i))) {
		            colisionDetectada = true;
		            break; //Salimos del bucle interno ya que detectamos una colisión
		        }
		    }
		    if(colisionDetectada) {
		    	//si hay colision no hacemos nada
		    } else {
		    	listaTiranosaurio.get(i).setEnElSuelo(false);
	        	listaTiranosaurio.get(i).setPiso(762);
	        	  for (int j = 0; j < todosLosPisos.size(); j++) {
					if(colisionTiranosaurioBloque(todosLosPisos.get(j), listaTiranosaurio.get(i))){
						listaTiranosaurio.get(i).setPiso(listaTiranosaurio.get(i).getY()-50);
					}
	        	 }  
		    }
		}
	}
	//Colisión proyectil-bomba
	public boolean colisionProyectilBomba(Proyectil proyectil , Bomba bomba) {
		if(proyectil != null && bomba != null) {
			boolean colisionX = bomba.getX() - bomba.getANCHO()/2 < proyectil.getX() + proyectil.getANCHO()/2 && 
								bomba.getX() - bomba.getANCHO()/2 > proyectil.getX() - proyectil.getANCHO()/2 ||
								bomba.getX() + bomba.getANCHO()/2 > proyectil.getX() - proyectil.getANCHO()/2 && 
								bomba.getX() + bomba.getANCHO()/2 < proyectil.getX() + proyectil.getANCHO()/2;
			boolean colisionY = bomba.getY() - bomba.getALTO()/2 > proyectil.getY() - proyectil.getALTO()/2 && 
								bomba.getY() - bomba.getALTO()/2 < proyectil.getY() + proyectil.getALTO()/2 ||
								bomba.getY() + bomba.getALTO()/2 > proyectil.getY() - proyectil.getALTO()/2 && 
								bomba.getY() + bomba.getALTO()/2 <= proyectil.getY() + proyectil.getALTO()/2;
			return colisionX && colisionY;
		}
		else {
			return false;
		}
	}
	//Verificación constante entre bombas y proyectiles
	public void verificacionProyectilBomba() {
		for (int i = 0; i < bombaDino.size(); i++) {
			if(colisionProyectilBomba(proyectil , bombaDino.get(i))) {
				proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
				Bomba nuevoProyectil = new Bomba(listaTiranosaurio.get(i).getX() ,listaTiranosaurio.get(i).getY(),20,20);
				bombaDino.set(i, nuevoProyectil);
			}
		}
	}	
	//Recibe arrays de pisos y los dibuja--------------------------------//
	public void dibujarBloques(ArrayList<Bloques> todosLosPisos) {
		for (int i = 0; i < todosLosPisos.size(); i++) {
	        if (todosLosPisos.get(i) != null) {
	            todosLosPisos.get(i).dibujar(entorno, todosLosPisos);
	        }
	    }
	}
	//-----------------------Eliminación de bloques luego de la colision------------------------//
	public void romperBloque() {	
		for (int i = 0; i < todosLosPisos.size(); i++) {
			Bloques bloque = todosLosPisos.get(i);
			if(bloque != null && bloque.GetseRompe()){
				if(colisionCabezaBloque(bloque)) {
					princesa.setVelocidadY(+10);
					todosLosPisos.set(i, null);
				}
			}
		}
	}
	public void gameOver() {
		if(princesa.getY() < 162 && princesa.getX() > 970) {
			System.exit(0);
		}
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
