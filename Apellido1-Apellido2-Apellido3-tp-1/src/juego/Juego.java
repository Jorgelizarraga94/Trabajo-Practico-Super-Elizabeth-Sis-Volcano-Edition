package juego;

import java.awt.Image;
import java.util.ArrayList;
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
	private Tiranosaurio [] tiranosaurios;
	private Tiranosaurio tiranosaurio;
	private Proyectil proyectil;
	
	
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
		this.princesa = new Princesa(entorno.ancho()/2 , 557 , 10, 50); 
		this.proyectil = new Proyectil(princesa.getX() , princesa.getY() , 20, 20);
		this.tiranosaurio = new Tiranosaurio(); 
		Random numeroAleatorio = new Random();
		//--------Reserva de 20 espacios para bloques--------//
		piso1 = new Bloques[20]; 
		piso2 = new Bloques[20];
		piso3 = new Bloques[20];
		piso4 = new Bloques[20];
		//--------Reserva de 8 espacios para tiranosaurios--------//
		tiranosaurios = new Tiranosaurio[8]; 
		//--------Inicialización de la lista de Bloques y creación de pisos--------//
		todosLosPisos = new ArrayList<Bloques>();
		bloque.crearPiso(todosLosPisos,piso1 , 600,50,50); 
		bloque.crearPiso(todosLosPisos,piso2, 450,50,50);
		bloque.crearPiso(todosLosPisos,piso3 , 300,50,50);
		bloque.crearPiso(todosLosPisos,piso4 , 150,50,50);
		//--------Inicialización de la lista de Tiranosaurio y creación de Tiranosaurios--------//
		listaTiranosaurio = new ArrayList<Tiranosaurio>();
		bombaDino = new ArrayList <Bomba>();
		tiranosaurios[0] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900), 540 , 20 , 20);
		tiranosaurios[1] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 540 , 20 , 20);
		tiranosaurios[2] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 400 , 20 , 20);
		tiranosaurios[3] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 400 , 20 , 20);
		tiranosaurios[4] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 240 , 20 , 20);
		tiranosaurios[5] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 240 , 20 , 20);
		tiranosaurios[6] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 95 , 20 , 20);
		tiranosaurios[7] = new Tiranosaurio(numeroAleatorio.nextInt(50, 900) , 95 , 20 , 20);
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
					princesa.setPiso(540);
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
					princesa.setPiso(princesa.getY());
				}
				else {
					princesa.setEnElSuelo(false);
					princesa.setPiso(540);
					if(colisionPiesBloque(todosLosPisos.get(i))){
						princesa.setPiso(princesa.getY());
					}	
				}
			}	
		}
		//--------DISPARO IZQUIERDA--------//
		if(this.entorno.estaPresionada(this.TECLA_C) && proyectil != null && princesa.getDisparoLadoPrincesa() == true && proyectil.getX() >0 ) {
				proyectil.setDisparoActivo(true);			
		}
		//--------DISPARO DERECHA--------//
		if(this.entorno.estaPresionada(this.TECLA_C) && proyectil != null && princesa.getDisparoLadoPrincesa() == false && proyectil.getX() < 1000 ) {
			proyectil.setDisparoActivo(true);
		}
		//--------DISPARO PRINCESA------------//
		disparoPrincesa(this.proyectil, princesa.getDisparoLadoPrincesa());

		
		//--------SI PRESIONA LA TECLA X SALTA, ROMPE LOS BLOQUES Y CAMBIA LA GRAVEDAD PARA SIMULAR COLISION--------//
		if(this.entorno.estaPresionada(this.TECLA_X)) {
			 princesa.saltar();
			 for(int i = 0; i < todosLosPisos.size(); i++) {
				 Bloques bloque = todosLosPisos.get(i);
				if(colisionPiesBloque(bloque) && bloque == null) {
					princesa.setVelocidadY(+10);
				}
			 }
			 romperBloque(); 
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
			System.out.println("entra");
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
				System.out.println("entra");
				
				Bomba nuevoProyectil = new Bomba(listaTiranosaurio.get(i).getX() ,listaTiranosaurio.get(i).getY(),20,20);
				proyectil.set(i, nuevoProyectil);
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
	//-----------------------Colisión entre el pie de la princesa y la parte superior del bloque------------------------//
	public boolean colisionPiesBloque(Bloques bloque) {
		boolean colisionY = colisionPrincesaBloque(bloque) && princesa.getY() + ((princesa.getAlto()/2)+3) > bloque.getY() - bloque.getAlto()/2
														   && princesa.getY() + ((princesa.getAlto()/2)+3) < (bloque.getY() + (bloque.getAlto()/2)+10);
		return colisionY;
	}
	//-----------------------Colisión entre la cabeza y la parte inferior del bloque------------------------//
	public boolean colisionCabezaBloque(Bloques bloque) {
		boolean colisionY = colisionPrincesaBloque(bloque) && princesa.getY() - princesa.getAlto()/2 < bloque.getY() + bloque.getAlto()/2
														   && princesa.getY() - princesa.getAlto()/2 > (bloque.getY() - bloque.getAlto()/2);
									   
		return colisionY;
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
