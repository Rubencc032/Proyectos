package sleep;

import java.util.Random;

public class Hilo implements Runnable {
	
	//variables mimebro
	 private int espera;				//un tiempo de espera al azar entre 0 y 10 segundos.
	 
	 public static void main(String[] args) {
		 
		 // Lanzamos dos hilos de forma concurrente que duren un tiempo aleatorio:
		 Random aleatorio = new Random(1725);
		 
		 for (int i=0; i<2; i++) {
			 // Un hilo tendr� un tiempo de ejecuci�n comprendido entre los 0 y 10 segundos.
			 //creamos los objeto hilo y los iniciamos
			 new Thread(new Hilo(aleatorio.nextInt(5000))).start();
		 }
		 
	 }
	 
	 //constructor
	 public Hilo(int espera) {
		 this.espera = espera;
	 }
	 
	
	 // M�todo que contiene las acciones que har� el hilo cuando se ejecute.
	 @Override
	 public void run() {
		 String nombre = Thread.currentThread().getName();
		 System.out.println("Soy el hilo "+ nombre +" y he iniciado mi ejecuci�n.");
		 System.out.println("Soy el hilo "+ nombre +" y voy a parar mi ejecuci�n "+ espera +" ms.");
		 
		 try {
			 Thread.sleep(espera);
		 } catch (InterruptedException e) {
			 System.err.println("Soy el hilo "+ nombre +" y me han interrumpido.");
		 }
		 
		 System.out.println("Soy el hilo "+ nombre +" y contin�o mi ejecuci�n.");
		 System.out.println("Soy el hilo "+ nombre +" y he finalizado mi ejecuci�n.");
	 	}
	}

