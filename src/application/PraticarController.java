package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Container.Factory;
import Model.Flashcard;
import Model.IDataModel;
import Model.OutputPort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;



import javafx.scene.Node;

public class PraticarController  implements Initializable {
    @FXML
    private Label perguntaLabel;

    @FXML
    private Label respostaLabel;
    

    @FXML
    private Button botaoPassar;

    private Stage stage;
	private Scene scene;
	private Parent root;
	
	private int tamanho;
	
	private int contador;
	private int contadorAcertos = 0;
	private int sentinelaResposta = -1;
	private int sentinelaVirar = -1;
	private int contadorErros = 0;
	private int sentinelaPassar = -1;
	
	private ObservableList<Flashcard> listaDeFlashcards = FXCollections.observableArrayList();
	
	OutputPort portaSaida;
	

    public void initialize(URL location, ResourceBundle resources) {
            perguntaLabel.setText("Resposta: ");
           
            mostrarPrimeiraPergunta(data.getPerguntaFlashcardPratica(data.getListaFlashcards(),data.getContador()));
    }
    
    IDataModel data;
    public PraticarController(IDataModel data) {
    	this.data = data;
    	listaDeFlashcards = data.getListaFlashcards();
    	tamanho = data.getListaFlashcards().size();
    	contador = data.getContador();
    	Factory factory = new Factory();
    	OutputPort portaSaida = factory.criarPortaExterna();
    	this.portaSaida = portaSaida;
    	
    }
    
    
    public void irMenu(ActionEvent event) throws IOException {
    	data.atualizarFlashcardList(listaDeFlashcards);
    	
    	portaSaida.saveDisk(data.getListaFlashcards(), "dados.txt");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Menu.fxml"));
		loader.setControllerFactory(param -> new MenuController(data));
		root = loader.load();	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
    }
    
    private void mostrarPrimeiraPergunta(String pergunta) {
		perguntaLabel.setText("Pergunta: " + pergunta);
	}
	
    public void passar(ActionEvent event) throws IOException {
    	if(data.getContador()<tamanho && sentinelaPassar == 0) {
    		listaDeFlashcards = data.getListaFlashcards();
    		contador = data.getContador();
    		perguntaLabel.setText("Pergunta: " +  data.getPerguntaFlashcardPratica(listaDeFlashcards,data.getContador()));
        	respostaLabel.setText("Resposta: ");
        	sentinelaVirar = -1;
        	
    	}
    	else if(data.getContador() >= tamanho) {
    		
    		botaoPassar.setText("Resultados");
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Resultados.fxml"));	
    		loader.setControllerFactory(param -> new ResultadosController(data,contadorAcertos,contadorErros));
    		root = loader.load();		
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();     		
    	}	
    }
    
    public void acertar() {
    	//data.getListaFlashcards()
 	   if(sentinelaResposta == 0 && sentinelaVirar == 0) {
 		   contadorAcertos++;
 		   sentinelaResposta = -1;
 		  
 		   if(listaDeFlashcards.get(data.getContador()-1).caixaLeitner!= "1") {
 			   
 			   Flashcard flashcard = listaDeFlashcards.get(data.getContador()-1);
 			   int numeroConvertido  = Integer.parseInt(flashcard.getCaixaLeitner());
 			   numeroConvertido--;
 			   String numeroStr = String.valueOf(numeroConvertido);
 			   flashcard.caixaLeitner = numeroStr;
 		   }
 		   sentinelaPassar = 0;
 	   }
 	   if(data.getContador()==tamanho) {
 		   botaoPassar.setText("Resultados");
 	   }
    }
    
    
    
    public void errar() {
 	   if(sentinelaResposta == 0 && sentinelaVirar == 0) { 
 		   contadorErros++;
 		   sentinelaResposta = -1;
 		   if(!listaDeFlashcards.get(data.getContador()-1).caixaLeitner.equals("3")) {
 			  
 			   Flashcard flashcard = listaDeFlashcards.get(data.getContador()-1);
 			   int numeroConvertido  = Integer.parseInt(flashcard.getCaixaLeitner());
 			   numeroConvertido++;
			   String numeroStr = String.valueOf(numeroConvertido);
			   flashcard.caixaLeitner = numeroStr;
 		   }
 		   sentinelaPassar = 0;
 	   }
 	   if(data.getContador()==tamanho) {
 		   
 		   botaoPassar.setText("Resultados");
 	   }
    }
    
    public void virar(ActionEvent event) throws IOException {  
 	   if(contador<tamanho && sentinelaVirar == -1) {
 		   respostaLabel.setText("Resposta: " + listaDeFlashcards.get(data.getContador()).getResposta());
     	 	data.addContador();
     	 	
            sentinelaVirar = 0;
            sentinelaResposta = 0;
 	   }
     }
    
    
    
}
