/**
 * Crea un algoritmo llamado comparar que lea dos números
 * enteros positivos n1 y n2. Si el primero es mayor (n1 > n2)
 * escribir 1, si el segundo es mayor (n2 > n1) escribir 2 y si son
 * iguales escribir 0.
 */

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * 
 * @author jovian(Jorge Victoria Andreu)
 * @version 1
 * @since 13nov2021
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Principal.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
