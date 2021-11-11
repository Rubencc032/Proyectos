/*
 * Repaso de como representar distintos valores literales de las variables y constantes
 */
package literales;

/**
 * @author jvandreu
 * @version 1
 * @since 07nov2021
 *
 */
public class Literales {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//variable boolean
		boolean verdadero = true;
		
		//variable caracter
		char letra = 'c';
		
		//cadena de caracteres
		String saludo = "Hola";
		
		//numero 26 decimal
		int decimal = 26;
		
		//numero 26 binario
		int binario = 0b11010;
		
		//numero 26 octal
		int octal = 032;
		
		//numero 26 hexadecimal
		int hexadecimal = 0x1a;
		
		//delimitar numeros largos con guiones bajos. Sufijo L para indicar que se representa un Long
		long tarjetaCredito = 1234_5678_9012_3456L;

	}

}
