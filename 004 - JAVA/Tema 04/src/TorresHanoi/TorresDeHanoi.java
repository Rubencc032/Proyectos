package TorresHanoi;

import javax.swing.JOptionPane;

public class TorresDeHanoi {
	
	//variables del objeto
	private int numeroDeDiscos; 		//n� de discos
	private int numeroDeMovimientos;	//n� de movimientos
	
	//getters
	public int getNumeroDeDiscos() {
		return numeroDeDiscos;
	}
	
	public int getNumeroDeMovimientos() {
		return numeroDeMovimientos;
	}
	
	//setters
	public void setNumeroDeDiscos(int NumeroDeDiscos) {
		this.numeroDeDiscos = NumeroDeDiscos;
	}

	public void setNumeroDeMovimientos(int NumeroDeMovimientos) {
		this.numeroDeMovimientos = NumeroDeMovimientos;
	}
	
	//subrutinas
	
	public void Captura() {
		numeroDeDiscos = Integer.parseInt(JOptionPane.showInputDialog("�Cuantos discos)"));
	}
	
	public void Intercambio(int NumDiscos, char A, char B, char C) {
		//los parametros se envian a la segunda clase
		/*Tomemos en cuenta que el par�metro A, tomar� el lugar de
		 la torre inicio, la que inicialmente contiene todos los
		 discos; el par�metro B, ser� la torre auxiliar; y el
		 par�metro C ser� la torre destino, donde quedar�n
		 al final de juego todas las fichas */
		
		if(NumDiscos == 1) {
			/* si el n�mero de discos es igual a uno, l�gicamente se
			 mover� el disco de la torre inicio directamente a la
			 de destino*/
			setNumeroDeMovimientos (getNumeroDeMovimientos()+1);
			JOptionPane.showMessageDialog(null, "Mover disco " + NumDiscos + " de la torre " + A + " a la torre " + C + "\nMOVIMIENTOS: " + numeroDeMovimientos);
		}
		else {
			/* se realizar�n mas movimientos y ser�n los siguientes*/
			Intercambio(NumDiscos-1,A, C, B);
			/*... y entonces el m�todo se llama a s� mismo, moviendo
			 primero, el disco de la torre A, inicio, a la torre C,
			 destino */
			setNumeroDeMovimientos(getNumeroDeMovimientos()+1);
			JOptionPane.showMessageDialog(null, "Mover disco " + NumDiscos + " de la torre " + A + " a la torre " + C + "\nMMOVIMIENTOS: " + numeroDeMovimientos);
			Intercambio(NumDiscos-1,B,A,C);
			/*... el m�todo vuelve a llamarse a s� mismo, esta vez
			 los par�metros donde de se encontraba la variable
			 A ahora estar� la variable B, para indicar el siguiente
			 movimiento*/

		}
	}
	
	public void Movimientos() {
		JOptionPane.showMessageDialog(null, "TOTAL DE MOVIMIENTOS: " + numeroDeMovimientos);
	}
}
