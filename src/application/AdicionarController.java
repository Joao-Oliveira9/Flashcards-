package application;



import java.io.IOException;


import Model.IDataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AdicionarController {
	
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	
	
	
    @FXML
    private TextField pergunta;

    @FXML
    private TextField resposta;
	
    IDataModel data;
	public AdicionarController(IDataModel data) {
		this.data = data;
		
	}
	
	
	public void adicionarFlashcard(ActionEvent event) throws IOException{
	
		String perguntaString = pergunta.getText();
		String respostaString = resposta.getText();
		data.addListaFlashcards(perguntaString,respostaString);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Gerenciar.fxml"));	
		loader.setControllerFactory(param -> new GerenciarController(data));
		root = loader.load();		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
