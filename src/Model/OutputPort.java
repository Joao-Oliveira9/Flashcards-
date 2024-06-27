package Model;

import java.io.IOException;
import java.util.List;

import javafx.collections.ObservableList;

public interface OutputPort {
	void saveDisk(ObservableList<Flashcard> list, String nomeArq);
    
    List<String> lerLinhas(String filePath) throws IOException;
    
    List<String> removeLinhasTexto(List<String> lines, String textoEncontrado);
    
    void escreverNoArquivo(String caminhoArq, List<String> lines) throws IOException;
    
    void carregarDadosArq(ObservableList<Flashcard> list);
    
    void criarArquivo(String pergunta, String resposta, String caixaLeitner);
    
    ObservableList<Flashcard> lerFlashcardsArquivo(String nomeArquivo) throws IOException;
}
