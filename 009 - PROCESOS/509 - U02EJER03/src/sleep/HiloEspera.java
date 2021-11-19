package sleep;

public class HiloEspera extends Thread {
	
	public void run() {
		 System.out.println("Soy el "+ Thread.currentThread().getName() +" empezando.");
		 try {
		 //this.sleep(10000);
		 // También se puede hacer así y lo aplicará al hilo actual.
		  Thread.sleep(3000);
		 } catch (InterruptedException e) {
		 System.out.println(Thread.currentThread().getName() +" interrumpido.");
		 return;
		 }
		 System.out.println(Thread.currentThread().getName() +" acabando.");
		 try {
			 //this.sleep(10000);
			 // También se puede hacer así y lo aplicará al hilo actual.
			  Thread.sleep(1000);
			 } catch (InterruptedException e) {
			 System.out.println(Thread.currentThread().getName() +" interrumpido.");
			 return;
			 }
		 System.out.println(Thread.currentThread().getName() +" finalizado.");

	}
}
