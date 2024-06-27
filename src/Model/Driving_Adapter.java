package Model;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Driving_Adapter implements InputPort{
		
	public ObservableList<Flashcard> readDisk() {
		ObservableList<Flashcard> listaDeFlashcards = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("dados.txt"))) {
            String pergunta, resposta,caixaString;
        
            while ((pergunta = reader.readLine()) != null && (resposta = reader.readLine()) != null && (caixaString = reader.readLine()) != null) {
            	Flashcard fc = new Flashcard(pergunta,resposta,caixaString);
            	listaDeFlashcards.add(fc);
            	
            }  
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaDeFlashcards;
    }
	
}
