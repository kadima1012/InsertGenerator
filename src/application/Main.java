package application;
	
import java.io.File;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			FileChooser fileChooser = new FileChooser();

			String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
			fileChooser.setInitialDirectory(new File(currentPath));
			//primaryStage.setMinHeight(250);
			//primaryStage.setMinWidth(420);
			primaryStage.setResizable(false);
			
			primaryStage.setScene(scene);
			ParserSql.readFile(fileChooser.showOpenDialog(primaryStage));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
