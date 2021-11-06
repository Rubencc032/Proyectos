package thread;

public class RunThread {

	public static void main(String[] args) {
		
		HelloThread hilo = new HelloThread(); //se crea un nuevo hilo de ejecucion
		hilo.start();						  //se arranca el hilo creado anteriormente
		System.out.println("Hola desde el hilo principal");
		System.out.println("Proceso acabando");
		
	}

}
