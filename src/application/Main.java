package application;
	
import Model.DataModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	private Parent root;
	@Override
	public void start(Stage stage) {
		
		DataModel model = new DataModel(); 
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Menu.fxml"));
			loader.setControllerFactory(param -> new MenuController(model));
			root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}





