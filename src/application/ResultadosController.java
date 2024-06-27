package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import Model.Driven_Adapter;

import Model.IDataModel;

import javafx.event.ActionEvent;

public class ResultadosController implements Initializable {
	   IDataModel data;
	   
	    @FXML
	    private Label acertouLabel;

	    @FXML
	    private Label errouLabel;
	    
	    
	    private Stage stage;
    	private Scene scene;
    	private Parent root;
    	private int acertos;
    	private int erros;
    	Driven_Adapter adaptador = new Driven_Adapter();
	   
	   public ResultadosController(IDataModel data,int contadorAcertos,int contadorErros) {
		   this.data = data;
		   this.erros = contadorErros;
		   this.acertos = contadorAcertos;
	   }
	   
	   
	   public void initialize(URL location, ResourceBundle resources) {
            acertouLabel.setText("Acertou: " + acertos);
	    	errouLabel.setText("Errou: " + erros);
	   }
	   
	
	   @FXML
	    public void voltarMenu(ActionEvent event) throws IOException{
	    	adaptador.saveDisk(data.getListaFlashcards(), "dados.txt");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Menu.fxml"));
			loader.setControllerFactory(param -> new MenuController(data));
			root = loader.load();	
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();	
	    }
}
