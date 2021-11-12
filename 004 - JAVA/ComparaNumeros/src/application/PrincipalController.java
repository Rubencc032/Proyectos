

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class PrincipalController {
	
	@FXML
	private TextField tfNum1;
	
	@FXML
	private TextField tfNum2;
	
	@FXML
	private Button btnComparar;
	
	@FXML
	private Label lbMensajes;
	
	@FXML
	void compararNumeros(ActionEvent event) {
		
		//variables locales
				boolean correcto = false;	//controla que los dos valores sean enteros
				int num1=0;					//almacena el primer numero
				int num2=0;					//almacena el segundo numero
				
				//vemos que los 2 campos esten rellenos.
				if(tfNum1.getLength() == 0 || tfNum2.getLength() == 0) {
					lbMensajes.setText("Rellene los 2 campos.");
				}
				
				//si los dos campos estan rellenos, debemos comparar los numeros
				else {
				
					//comprobamos que los datos sean double o mayor que 0
					try {
						correcto = true; 
						num1 = Integer.parseInt(tfNum1.getText().toString());
						num2 = Integer.parseInt(tfNum2.getText().toString());
						
					//si no son numeros de tipo int lo indicamos y ponemos el booleano en false. 
					} catch (Exception ex) {
						correcto = false;
						lbMensajes.setText("Introduzca un valor correcto.");
					}
					
					//hemos introducido 2 numeros correctos, comprobamos que no sean cero o negativo.
					if (correcto && (num1 <= 0 || num2 <= 0) ) {
						lbMensajes.setText("Introduzca un valor positivo.");
					}
					
					//comprobado que todo es correcto, construimos el objeto e imprimimos su area en el label
					else if(correcto) {
						if(num1 > num2) lbMensajes.setText(num1 + " es mayor que " + num2);
						else if(num1 < num2) lbMensajes.setText(num1 + " es menor que " + num2);
						else lbMensajes.setText("Ambos son iguales");
					}
					
				}
		
	}
	
}
