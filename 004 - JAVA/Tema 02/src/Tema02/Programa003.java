package Tema02;

/**
 * Esta clase calcula el dienro obtenido como
 * intereses al invertir 17000 euros a un interes
 * del 0.027 durante un a�o. El interes y la
 * ganancia se imprimen en la salida estandar
 */
public class Programa003 {

	public static void main(String[] args) {
		
		//definicion de variables
		double principal; //capital de dinero a invertir
		double ratio; //el ratio del interes anual
		double interes; //gananacia al a�o debida al interes
		
		//hacer calculos
		principal=17000;
		ratio=0.027;
		interes = principal * ratio; //calcula el interes anual
		principal = principal + interes; //dinero despues de un a�o
		
		//mostrar resultados
		System.out.print("El interes ganado en Euros: ");
		System.out.println(interes);
		System.out.print("Capital despues de un a�o en Euros: ");
		System.out.println(principal);

	}

}
