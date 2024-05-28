package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
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
	private Princesa princesa; 
	private int enemigosEliminados;
	private int puntaje;
	private Tiranosaurio [] tiranosaurios;
	private Tiranosaurio tiranosaurio;
	private Proyectil proyectil;
	Random numeroAleatorio = new Random();
	private Image fondo;

	private int tiempo = 0;
	Juego() {
		tiempo += 1;
		this.enemigosEliminados = 0;
		this.puntaje = 0;
		//--------Inicializa el objeto entorno--------//
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 1000, 850);
		//Inicializar lo que haga falta para el juego
		
		//--------carga de imagen de fondo--------//
		this.fondo = Herramientas.cargarImagen("interiorVolcan.jpeg");
		this.bloque = new Bloques(); 
		this.princesa = new Princesa(entorno.ancho()/2 , 750 , 10, 50); 
		this.proyectil = new Proyectil(princesa.getX(), princesa.getY() + princesa.getAlto()/2 , 20, 20);
		this.tiranosaurio = new Tiranosaurio(); 
		
		//--------Reserva de 20 espacios para bloques--------//
		piso1 = new Bloques[20]; 
		piso2 = new Bloques[20];
		piso3 = new Bloques[20];
		piso4 = new Bloques[20];
		//--------Reserva de 8 espacios para tiranosaurios--------//
		tiranosaurios = new Tiranosaurio[8]; 
		//--------Inicialización de la lista de Bloques y creación de pisos--------//
		todosLosPisos = new ArrayList<Bloques>();
		bloque.crearPiso(todosLosPisos,piso1 , 800,50,50); 
		bloque.crearPiso(todosLosPisos,piso2, 600,50,50);
		bloque.crearPiso(todosLosPisos,piso3 , 400,50,50);
		bloque.crearPiso(todosLosPisos,piso4 , 200,50,50);
		//--------Inicialización de la lista de Tiranosaurio y creación de Tiranosaurios--------//
		listaTiranosaurio = new ArrayList<Tiranosaurio>();
		bombaDino = new ArrayList <Bomba>();
		tiranosaurios[0] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900), 761 , 10 , 30);
		tiranosaurios[1] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 761 , 10 , 30);
		tiranosaurios[2] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 561 , 10 , 30);
		tiranosaurios[3] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 561 , 10 , 30);
		tiranosaurios[4] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 361 , 10 , 30);
		tiranosaurios[5] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 361 , 10 , 30);
		tiranosaurios[6] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 161 , 10 , 30);
		tiranosaurios[7] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 161 , 10 , 30);
		tiranosaurio.crearTiranosaurio(listaTiranosaurio, tiranosaurios);
		//-----------------Proyectil tiranosaurio-----------------//
		for (int i = 0; i < listaTiranosaurio.size(); i++) {
			bombaDino.add(new Bomba(listaTiranosaurio.get(i).getX(),listaTiranosaurio.get(i).getY(),20,20)); 
		}
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
		//--------FONDO--------//
		entorno.dibujarImagen(fondo, entorno.ancho()/2,entorno.alto()/2,0);
		
	    //--------DIBUJA LOS BLOQUES DE CADA PISO--------//
		dibujarBloques(todosLosPisos);
		//Dibuja el puntaje
				this.entorno.cambiarFont("",25, Color.white);
				this.entorno.escribirTexto("Puntaje: "+ this.puntaje, 15,20);
				//Dibuja el puntaje
				this.entorno.cambiarFont("",25, Color.white);
				this.entorno.escribirTexto("Enemigos Eliminados: "+ this.enemigosEliminados, 15,50);

		//--------DIBUJA A LA PRINCESA Y LE DA MOVILIDAD--------//
		this.princesa.dibujarDer(this.entorno);
		//--------DIBUJA EL TIRANOSAURIO--------//
		this.tiranosaurio.dibujar(entorno, listaTiranosaurio);
		//--------MOVIMIENTO IZQUIERDA Y CAIDA SI BLOQUE ES NULL--------//
		if(this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)&& princesa.getX() > 31) {
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
		//--------MOVIMIENTO DERECHA Y CAIDA SI BLOQUE ES NULL--------//
		if(this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) && princesa.getX() < 970) {
			princesa.movDer();
			princesa.setDisparoLadoPrincesa(false);
			for(int i = 0; i < todosLosPisos.size(); i++) {
				if(colisionPrincesaBloque(todosLosPisos.get(i))) {
					//princesa.setPiso(princesa.getY());
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
		//--------DISPARO IZQUIERDA--------//
		if(this.entorno.estaPresionada('c') && proyectil != null && princesa.getDisparoLadoPrincesa() == true && proyectil.getX() >0 ) {
				proyectil.setDisparoActivo(true);			
		}
		//--------DISPARO DERECHA--------//
		if(this.entorno.estaPresionada('c') && proyectil != null && princesa.getDisparoLadoPrincesa() == false && proyectil.getX() < 1000 ) {
			proyectil.setDisparoActivo(true);
		}
		//--------DISPARO PRINCESA------------//
		disparoPrincesa(this.proyectil, princesa.getDisparoLadoPrincesa());
		//--------SI PRESIONA LA TECLA X SALTA, ROMPE LOS BLOQUES Y COLISIONA CONTRA LOS BLOQUES--------//
		if(this.entorno.estaPresionada('x')) {
			 princesa.saltar();
			 for(int i = 0; i < todosLosPisos.size(); i++) {
				 Bloques bloque = todosLosPisos.get(i);
				if(colisionPiesBloque(bloque) && bloque == null) {
					princesa.setVelocidadY(+10);
				}
			 }
			 romperBloque(); 
		}
		//----------VERIFICACIÓN DE COLISION CONSTANTE ENTRE PROYECTIL Y BOMBA-----------------//
		for (int i = 0; i < bombaDino.size(); i++) {
			if(colisionProyectilBomba(proyectil , bombaDino.get(i))) {
				proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
				Bomba nuevoProyectil = new Bomba(listaTiranosaurio.get(i).getX() ,listaTiranosaurio.get(i).getY(),20,20);
				bombaDino.set(i, nuevoProyectil);
			}
		}
		//---------VERIFICACIÓN DE COLISION CONSTANTE ENTRE LOS TIRANOSAURIOS Y LOS BLOQUES-----------//
		for (int i = 0; i < listaTiranosaurio.size(); i++) {
			listaTiranosaurio.get(i).Caida();
		    boolean colisionDetectada = false;
		    for (int j = 0; j < todosLosPisos.size(); j++) {
		        if (colisionPiesTiranosauriosBloque(todosLosPisos.get(j), listaTiranosaurio.get(i))) {
		            colisionDetectada = true;
		            break; // Salimos del bucle interno ya que detectamos una colisión
		        }
		    }
		    if(colisionDetectada) {
		        //System.out.println("Tiranosaurio " + i + " ha colisionado con un bloque.");
		    } else {
		    	listaTiranosaurio.get(i).setEnElSuelo(false);
	        	listaTiranosaurio.get(i).setPiso(762);
	        	  for (int j = 0; j < todosLosPisos.size(); j++) {
					if(colisionTiranosaurioBloque(todosLosPisos.get(j), listaTiranosaurio.get(i))){
						listaTiranosaurio.get(i).setPiso(listaTiranosaurio.get(i).getY()-50);
					}
			        //System.out.println("Tiranosaurio " + i + " no ha colisionado con ningún bloque.");
	        	 }  
		    }
		}
		for (int i = 0; i < listaTiranosaurio.size(); i++) {
			if(colisionTiranosaurioProyectil(proyectil,listaTiranosaurio.get(i))) {
				System.out.println("hay colision");
				Tiranosaurio tiranosaurio = new Tiranosaurio(listaTiranosaurio.get(numeroAleatorio.nextInt(0, 7)).getX(), listaTiranosaurio.get(numeroAleatorio.nextInt(0, 7)).getY() , 10 , 30);
				listaTiranosaurio.set(i, tiranosaurio);
				enemigosEliminados ++;
				puntaje += 2;
			};
		}
		//---------------------COMPROBACIÓN DE COLISIONES ENTRE PRINCESA Y BLOQUES-------//
		comprobaciónDeColisiones();
		//----------------------MOVIMIENTO DE TIRANOSAURIOS----------------------------//
		moverTiranosaurios();
		//----------------------DISPARO TIRANOSAURIO-----------------------------------//
		disparoTiranosaurio(bombaDino);	
		
		princesa.actualizarSalto();
	}
	//----------------------------------------METODOS-------------------------------------------------------------------------------//
	public void disparoPrincesa(Proyectil proyectil , boolean disparoLado) {
	//--------CONTROL DE DIRECCIÓN DEL DISPARO----------//
		if(proyectil!= null && proyectil.getDisparoActivo() == true && disparoLado == true) {
			proyectil.disparoIzq(entorno,30);
		}
		if(proyectil!= null && proyectil.getDisparoActivo() == true && disparoLado == false) {
			proyectil.disparoDer(entorno,30);
		}
		//--------LUEGO DE DISPARAR, PONEMOS AL PROYECTIL EN NULL Y VOLVEMOS A CREARLO--------//
		if(proyectil != null && this.proyectil.getX() >= 1000 ||proyectil != null && this.proyectil.getX() <= 0) {
			this.proyectil = null;
			this.proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
		}	
	}
	
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
	
	public void disparoTiranosaurio(ArrayList<Bomba> proyectil) {
	//--------CONTROL DE DIRECCIÓN DEL DISPARO----------//
		for (int i = 0; i < proyectil.size(); i++) {
			if(proyectil.get(i)!= null && listaTiranosaurio.get(i).getDisparoLadoTiranosaurios() == true) {
				proyectil.get(i).disparoIzq(entorno, 3);
			}
			if(proyectil.get(i)!= null && listaTiranosaurio.get(i).getDisparoLadoTiranosaurios() == false ) {
				proyectil.get(i).disparoDer(entorno,3);
			}
			//--------LUEGO DE DISPARAR, PONEMOS AL PROYECTIL EN NULL Y VOLVEMOS A CREARLO--------//
			if(proyectil != null && proyectil.get(i).getX() >= 1000 ||proyectil.get(i) != null && proyectil.get(i).getX() <= 0) {
				Bomba nuevoProyectil = new Bomba(listaTiranosaurio.get(i).getX() ,listaTiranosaurio.get(i).getY(),20,20);
				proyectil.set(i, nuevoProyectil);
				//System.out.println(proyectil.get(1).getX()); 
			}
			if(colisionPrincesaBomba(proyectil.get(i))){
				//System.out.println("colision");
				//finaliza el juego, pedirle codigo a Marcelo
			}
		}	
	}
	//-----------------------Recibe arrays de pisos y los dibuja------------------------//
	public void dibujarBloques(ArrayList<Bloques> todosLosPisos) {
		for (int i = 0; i < todosLosPisos.size(); i++) {
	        if (todosLosPisos.get(i) != null) {
	            todosLosPisos.get(i).dibujar(entorno, todosLosPisos);
	        }
	    }
	}
	
	public void comprobaciónDeColisiones() {
		for(int i = 0; i < todosLosPisos.size(); i++) {
			 Bloques bloque = todosLosPisos.get(i); 
				if(colisionCabezaBloque(bloque)) {
					princesa.setVelocidadY(+10);
				}
				if(colisionPiesBloque(bloque)){
					princesa.setPiso(princesa.getY());
				}
		}
	}
	public boolean colisionPiesTiranosauriosBloque(Bloques bloque, Tiranosaurio tiranosaurio) {
	    boolean colisionY = colisionTiranosaurioBloque(bloque, tiranosaurio) &&
	                        tiranosaurio.getY() + ((tiranosaurio.getALTO() / 2) + 3) > bloque.getY() - bloque.getAlto() / 2 &&
	                        tiranosaurio.getY() + ((tiranosaurio.getALTO() / 2) + 3) < bloque.getY() + (bloque.getAlto() / 2);
	    return colisionY;
	}
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
	//-----------------------Colisión entre el pie de la princesa y la parte superior del bloque------------------------//
	public boolean colisionPiesBloque(Bloques bloque) {
		boolean colisionY = colisionPrincesaBloque(bloque) && princesa.getY() + ((princesa.getAlto()/2)) > bloque.getY() - bloque.getAlto()/2
														   && princesa.getY() + ((princesa.getAlto()/2)) < (bloque.getY() - (bloque.getAlto()/2)+10);
		return colisionY;
	}
	//-----------------------Colisión entre la cabeza y la parte inferior del bloque------------------------//
	public boolean colisionCabezaBloque(Bloques bloque) {
		boolean colisionY = colisionPrincesaBloque(bloque) && princesa.getY() - princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2
														   && princesa.getY() - princesa.getAlto()/2 > (bloque.getY() - bloque.getAlto()/2);
									   
		return colisionY;
	}   
	public boolean colisionPrincesaTiranosaurio(Tiranosaurio tiranosaurio) {
		if(tiranosaurio != null) {
			boolean colisionX = princesa.getX() - princesa.getAncho()/2 < tiranosaurio.getX() + tiranosaurio.getANCHO()/2 && princesa.getX() - princesa.getAncho()/2 > tiranosaurio.getX() - tiranosaurio.getANCHO()/2 ||
								princesa.getX() + princesa.getAncho()/2 > tiranosaurio.getX() - tiranosaurio.getANCHO()/2 && princesa.getX() + princesa.getAncho()/2 < tiranosaurio.getX() + tiranosaurio.getANCHO()/2;
			boolean colisionY = tiranosaurio.getY() - tiranosaurio.getALTO()/2 > princesa.getY() - princesa.getAlto()/2 && tiranosaurio.getY() - tiranosaurio.getALTO()/2 < princesa.getY() + princesa.getAlto()/2 ||
								tiranosaurio.getY() + tiranosaurio.getALTO()/2 > princesa.getY() - princesa.getAlto()/2 && tiranosaurio.getY() + tiranosaurio.getALTO()/2 <= princesa.getY() + princesa.getAlto()/2;
			return colisionX && colisionY;
		}
		else {
			return false;
		}
	}
	public boolean colisionProyectilBomba(Proyectil proyectil , Bomba bomba) {
		if(proyectil != null && bomba != null) {
			boolean colisionX = bomba.getX() - bomba.getANCHO()/2 < proyectil.getX() + proyectil.getANCHO()/2 && bomba.getX() - bomba.getANCHO()/2 > proyectil.getX() - proyectil.getANCHO()/2 ||
								bomba.getX() + bomba.getANCHO()/2 > proyectil.getX() - proyectil.getANCHO()/2 && bomba.getX() + bomba.getANCHO()/2 < proyectil.getX() + proyectil.getANCHO()/2;
			boolean colisionY = bomba.getY() - bomba.getALTO()/2 > proyectil.getY() - proyectil.getALTO()/2 && bomba.getY() - bomba.getALTO()/2 < proyectil.getY() + proyectil.getALTO()/2 ||
								bomba.getY() + bomba.getALTO()/2 > proyectil.getY() - proyectil.getALTO()/2 && bomba.getY() + bomba.getALTO()/2 <= proyectil.getY() + proyectil.getALTO()/2;
			return colisionX && colisionY;
		}
		else {
			return false;
		}
	}
	public boolean colisionPrincesaBomba(Bomba bomba) {
		if(bomba != null) {
			boolean colisionX = princesa.getX() - princesa.getAncho()/2 < bomba.getX() + bomba.getANCHO()/2 && princesa.getX() - princesa.getAncho()/2 > bomba.getX() - bomba.getANCHO()/2 ||
								princesa.getX() + princesa.getAncho()/2 > bomba.getX() - bomba.getANCHO()/2 && princesa.getX() + princesa.getAncho()/2 < bomba.getX() + bomba.getANCHO()/2;
			boolean colisionY = bomba.getY() - bomba.getALTO()/2 > princesa.getY() - princesa.getAlto()/2 && bomba.getY() - bomba.getALTO()/2 < princesa.getY() + princesa.getAlto()/2 ||
								bomba.getY() + bomba.getALTO()/2 > princesa.getY() - princesa.getAlto()/2 && bomba.getY() + bomba.getALTO()/2 <= princesa.getY() + princesa.getAlto()/2;
			return colisionX && colisionY;
		}
		else {
			return false;
		}
	}
	//-----------------------Colisión general entre la princesa y el bloque------------------------//
	public boolean colisionPrincesaBloque(Bloques bloque) {
		if(bloque != null) {
			boolean colisionX = princesa.getX() - princesa.getAncho()/2 > bloque.getX() + bloque.getAncho()/2 && princesa.getX() - princesa.getAncho()/2 < bloque.getX() - bloque.getAncho()/2 ||
								princesa.getX() + princesa.getAncho()/2 > bloque.getX() - bloque.getAncho()/2 && princesa.getX() + princesa.getAncho()/2 < bloque.getX() + bloque.getAncho()/2;
			boolean colisionY = princesa.getY() - princesa.getAlto()/2 > bloque.getY() - bloque.getAlto()/2 && princesa.getY() - princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2 ||
								princesa.getY() + princesa.getAlto()/2 > bloque.getY() - bloque.getAlto()/2 && princesa.getY() + princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2;
			boolean colision = colisionX && colisionY;
			return colision;
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
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
