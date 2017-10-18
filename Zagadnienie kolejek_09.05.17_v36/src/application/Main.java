//Symulator kolejki
// version 1.0.1
// Poprawione:
//		zdjecie wzoru dla P(0) w dokumentacji

package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml")); 
			
			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			primaryStage.centerOnScreen();
			primaryStage.setMaximized(true);
			primaryStage.setTitle("Symulator kolejki");

			Main.setIcons(primaryStage);
			
//			primaryStage.setOnShown(e ->  MainController.mainController.postRendering());
			Main.primaryStage = primaryStage;
			Main.primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void setIcons(Stage primaryStage) {
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/graphics/icons/16x16.png")));
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/graphics/icons/32x32.png")));
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/graphics/icons/64x64.png")));
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/graphics/icons/128x128.png")));
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/graphics/icons/256x256.png")));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
