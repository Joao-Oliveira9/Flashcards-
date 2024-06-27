package application;

import java.io.IOException;
import java.util.List;


import Model.Driven_Adapter;
import Model.Flashcard;
import Model.IDataModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TrocarController {
    @FXML
    private TextField nomeDoArquivo;
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;

	
	IDataModel data;
	Driven_Adapter  adptadorSecundario;
	ObservableList<Flashcard> flashcardSelecionado;
	public TrocarController(IDataModel data, ObservableList<Flashcard> flashcardSelecionado) {
		this.data = data;
		this.flashcardSelecionado = flashcardSelecionado;
		adptadorSecundario = new  Driven_Adapter(); 
		
	}
	
	public void efetuarTroca(ActionEvent event) throws IOException{
		adptadorSecundario.criarArquivo(flashcardSelecionado.get(0).pergunta,flashcardSelecionado.get(0).resposta,flashcardSelecionado.get(0).caixaLeitner);
		
		List<String> lines = adptadorSecundario.lerLinhas("dados.txt");
		    
		lines = adptadorSecundario.removeLinhasTexto(lines, flashcardSelecionado.get(0).pergunta);
		
		
		adptadorSecundario.escreverNoArquivo("dados.txt",lines);
		flashcardSelecionado.forEach(data.getListaFlashcards()::remove);
		
		
		data.addFlashcardList(adptadorSecundario.lerFlashcardsArquivo(nomeDoArquivo.getText()));
		
		adptadorSecundario.saveDisk(data.getListaFlashcards(),"dados.txt");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Menu.fxml"));	
		loader.setControllerFactory(param -> new MenuController(data));
		root = loader.load();		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	
	public void irMenu(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Menu.fxml"));	
		loader.setControllerFactory(param -> new MenuController(data));
		root = loader.load();		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void irGerenciar(ActionEvent event) throws IOException{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Gerenciar.fxml"));	
		loader.setControllerFactory(param -> new GerenciarController(data));
		root = loader.load();		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
}
	
	 