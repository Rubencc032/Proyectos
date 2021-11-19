package hilos;

public class Hilo implements Runnable {
	
	private static int espera = 3000;
	 
	public static void main(String[] args) {
		 
		 //creamos el Runnable class
		Hilo fil = new Hilo();
		 
		//creamos los objetos hilo
		 Thread hilo1 = new Thread(fil);
		 Thread hilo2 = new Thread(fil);
		 
		 //ponemos en marcga 
		 hilo1.start();
		 hilo2.start();
		 
		 //el hilo principal para 4 segundos
		 try {
			 System.out.println("Soy el " + Thread.currentThread().getName());
			Thread.currentThread().sleep(100000);
		 } catch (InterruptedException e) {
			 System.out.println(Thread.currentThread().getName() + " interrumpido. ");
			 return;
		}
	}

			 
	 
	 
	 
	
	 // Método que contiene las acciones que hará el hilo cuando se ejecute.
	 @Override
	 public void run() {
		 String nombre = Thread.currentThread().getName();
		 System.out.println("Soy el hilo "+ nombre +" y he iniciado mi ejecución.");
		 System.out.println("Soy el hilo "+ nombre +" y voy a parar mi ejecución "+ espera +" ms.");
		 
		 try {
			 Thread.sleep(espera);
			 espera = espera + 2000; 
		 } catch (InterruptedException e) {
			 System.err.println("Soy el hilo "+ nombre +" y me han interrumpido.");
		 }
		 
		 System.out.println("Soy el hilo "+ nombre +" y continúo mi ejecución.");
		 System.out.println("Soy el hilo "+ nombre +" y he finalizado mi ejecución.");
	 	}
	}

