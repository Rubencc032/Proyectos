package areaTriangulo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrincipalController  {
	
	//variables de la escena
	@FXML
	private TextField tfBase;
	
	@FXML
	private TextField tfAltura;
	
	@FXML
	private Label lbArea;
	
	@FXML
	private Button btnCalculo;
	
	@FXML
	private Label lbMensaje;
	
	//objeto triangulo sin inicializar
	private Triangulo triangulo;
	
	//funciones
	/**
	 * funcion listener del boton, para el calculo del area del triangulo
	 * @param event
	 */
	@FXML
	void calcularArea(ActionEvent event) {
		
		//variables locales
		boolean correcto = false;	//controla que los dos valores sean double
		double base=0;				//almacena la base
		double altura=0;			//almacena el triangulo
		
		//vemos que los 2 campos esten rellenos. Si hemos calculado un area antes, hay que limpiar la zona del area
		if(tfBase.getLength() == 0 || tfAltura.getLength() == 0) {
			lbMensaje.setText("Rellene los 2 campos.");
			lbArea.setText("");
		}
		
		//si los dos campos estan rellenos, debemos calcular el area
		else {
		
			//comprobamos que los datos sean double o mayor que 0
			try {
				correcto = true; 
				base = Double.parseDouble(tfBase.getText().toString());
				altura = Double.parseDouble(tfAltura.getText().toString());
				
			//si no son numeros de tipo double lo indicamos y ponemos el booleano en false. Limpiamos el label del area.
			} catch (Exception ex) {
				correcto = false;
				lbMensaje.setText("Introduzca un valor correcto.");
				lbArea.setText("");
			}
			
			//hemos introducido 2 numeros correctos, comprobamos que no sean cero o negativo, para poder calcular el area
			if (correcto && (base <= 0 || altura <= 0) ) {
				lbMensaje.setText("Introduzca un valor positivo.");
				lbArea.setText("");
			}
			
			//comprobado que todo es correcto, construimos el objeto e imprimimos su area en el label
			else if(correcto) {
				triangulo = new Triangulo(base, altura);
				lbArea.setText(String.valueOf(triangulo.getArea()));
				lbMensaje.setText("");
			}
			
		}
		
	}
	
}
