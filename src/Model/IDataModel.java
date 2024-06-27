package Model;


import javafx.collections.ObservableList;

public interface IDataModel {
	ObservableList<Flashcard> getListaFlashcards();
    void addListaFlashcards(String resposta, String pergunta);
    void removeFlashcards(Flashcard flashcard);
    void addContador();
    void subContador();
    void addFlashcardList(ObservableList<Flashcard> listaDeFlashcardsExterna);
    void atualizarFlashcardList(ObservableList<Flashcard> listaDeFlashcardsExterna);
    int getContador();
    String getPerguntaFlashcardPratica(ObservableList<Flashcard> listaDeFlashcardsExterna, int contador);
}
