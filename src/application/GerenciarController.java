package application;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import Model.Driven_Adapter;
import Model.Flashcard;
import Model.IDataModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GerenciarController implements Initializable {
	
	    private Stage stage;
	    private Scene scene;
	    private Parent root;
	
	
		
	
		@FXML
	    private TableColumn<Flashcard, String> pergunta;

	    @FXML
	    private TableColumn<Flashcard, String> resposta;

	    @FXML
	    private TableColumn<Flashcard, String> caixa;

	    @FXML
	    private TableView<Flashcard> table;
	    
	    Driven_Adapter  adptadorSecundario;
	    
	IDataModel data;
	public GerenciarController(IDataModel data) {
	        this.data = data;
	        adptadorSecundario = new  Driven_Adapter(); 
	    }
	
public void initialize(URL url, ResourceBundle resourceBundle) {
	        pergunta.setCellValueFactory(new PropertyValueFactory<>("pergunta"));
	        resposta.setCellValueFactory(new PropertyValueFactory<>("resposta"));
	        caixa.setCellValueFactory(new PropertyValueFactory<>("caixaLeitner")); 
	        table.setItems(data.getListaFlashcards());
	    }
	
public void criarFlashcard(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Add.fxml"));	
		loader.setControllerFactory(param -> new AdicionarController(data));
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
	
	public void removerFlashcard(ActionEvent event) throws IOException {
	    ObservableList<Flashcard> todosFlashcards,flashcardSelecionado;
	    todosFlashcards = table.getItems();
	    flashcardSelecionado = table.getSelectionModel().getSelectedItems();
	   
	    List<String> lines = adptadorSecundario.lerLinhas("dados.txt");
	    
	    lines = adptadorSecundario.removeLinhasTexto(lines, flashcardSelecionado.get(0).pergunta);
	  
	    adptadorSecundario.escreverNoArquivo("dados.txt",lines);
	    flashcardSelecionado.forEach(todosFlashcards::remove);
	}
	
	public void carregarFlashcard(ActionEvent event) throws IOException{
		
		adptadorSecundario.carregarDadosArq(data.getListaFlashcards());
	}
	
	public void trocarFlashcard(ActionEvent event) throws IOException {
		
		ObservableList<Flashcard> flashcardSelecionado;
	
		flashcardSelecionado = table.getSelectionModel().getSelectedItems();
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Trocar.fxml"));	
		loader.setControllerFactory(param -> new TrocarController(data,flashcardSelecionado));
		root = loader.load();		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
}
