package application;

import java.io.IOException;



import Model.IDataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MenuController {
	   private Stage stage;
	   private Scene scene;
	   private Parent root;
	  
	   private IDataModel data;

	  public MenuController(IDataModel data) {
		        this.data = data;       
		    }

	 public void irGerenciar(ActionEvent event) throws IOException {	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Gerenciar.fxml"));	
			
			loader.setControllerFactory(param -> new GerenciarController(data));
			root = loader.load();		
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	    
	 public void irPraticar(ActionEvent event) throws IOException {	 
		 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Pratica.fxml"));	
			
			loader.setControllerFactory(param -> new PraticarController(data));
			root = loader.load();		
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		 
		}
	 
	 public void irSorteio(ActionEvent event) throws IOException {	 
		 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Sorteio.fxml"));	
			
			loader.setControllerFactory(param -> new SorteioController(data));
			root = loader.load();		
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		 
		}
	 
	
	

}
