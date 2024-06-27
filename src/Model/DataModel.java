package Model;



import Container.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel implements IDataModel {
	
	
	
	private ObservableList<Flashcard> listaDeFlashcards = FXCollections.observableArrayList();
	private int contador;
	Driven_Adapter adptadorSaida;
	OutputPort portaSaida;
	
	
	public DataModel() {
		Factory factory = new Factory();
		InputPort portaEntrada = factory.criarPortaEntrada();
		OutputPort portaSaida = factory.criarPortaExterna();
		this.portaSaida = portaSaida;
		
		listaDeFlashcards = portaEntrada.readDisk();
		contador = 0;
	}
	
	public ObservableList<Flashcard> getListaFlashcards(){
		return listaDeFlashcards;
	}
	
	public void addListaFlashcards(String pergunta,String resposta) {
		Flashcard flashcard = new Flashcard(pergunta,resposta,"3");
		listaDeFlashcards.add(flashcard);
		
		portaSaida.saveDisk(listaDeFlashcards,"dados.txt");
	}
	
	public void removeFlashcards(Flashcard flashcard) {
		listaDeFlashcards.add(flashcard);
	}
	
	public void addContador() {
		contador = contador + 1;
		
	}
	
	public void subContador() {
		contador = contador - 1;
	
	}
	
	public void addFlashcardList(ObservableList<Flashcard> listaDeFlashcardsExterna) {
		listaDeFlashcards.addAll(listaDeFlashcardsExterna);
		
	}
	
	public void atualizarFlashcardList(ObservableList<Flashcard> listaDeFlashcardsExterna) {
		listaDeFlashcards = listaDeFlashcardsExterna;
	}
	
	public int getContador() {
		return contador;
	}
	
	public String getPerguntaFlashcardPratica(ObservableList<Flashcard> listaDeFlashcardsExterna,int contador) {
		Flashcard flashcardLido= listaDeFlashcards.get(contador);
		String perguntaFlashcard = flashcardLido.getPergunta();
		return perguntaFlashcard;
		
	}
}
	
	
	
 
	
	
	




