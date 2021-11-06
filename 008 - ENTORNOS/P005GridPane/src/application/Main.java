package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//creamos los botones
			Button button1 = new Button("Button 1");
			Button button2 = new Button("Button 2");
			Button button3 = new Button("Button 3");
			Button button4 = new Button("Button 4");
			Button button5 = new Button("Button 5");
			Button button6 = new Button("Button 6");
			
			//creamos el GridPane
			GridPane gridPane = new GridPane();
			
			//num col - num fil - tot col - tot fil
			gridPane.add(button1, 0, 0, 1, 1);
			gridPane.add(button2, 1, 0, 1, 1);
			gridPane.add(button3, 2, 0, 1, 1);
			gridPane.add(button4, 0, 1, 1, 1);
			gridPane.add(button5, 1, 1, 1, 1);
			gridPane.add(button6, 2, 1, 1, 1);
			
			//mostramos la escena
			Scene scene = new Scene(gridPane,240,100);
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
