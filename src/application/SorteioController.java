package application;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import Model.IDataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SorteioController implements Initializable  {
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	
	
    @FXML
    private Label perguntaLabel;

    @FXML
    private Label respostaLabel;

	
	
	
	IDataModel data;
	public SorteioController(IDataModel data) {
		this.data = data;
	}
	
	
	public void initialize(URL location, ResourceBundle resources) {
		
		Random random = new Random();
    	int randomIndex = random.nextInt(data.getListaFlashcards().size());
		
		perguntaLabel.setText("Resposta: " + data.getListaFlashcards().get(randomIndex).getPergunta());
		respostaLabel.setText("Pergunta: " + data.getListaFlashcards().get(randomIndex).getResposta());
		}
	
	

    @FXML
    void voltarMenu(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Menu.fxml"));	
		loader.setControllerFactory(param -> new MenuController(data));
		root = loader.load();		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
	
}
