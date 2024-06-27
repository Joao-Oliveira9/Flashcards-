package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


//metodos relacionados ao uso de portas externas, a intenção é modificar elementos externos
public class Driven_Adapter implements OutputPort{
	
	public void saveDisk(ObservableList<Flashcard> list,String nomeArq) {
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArq,false))) {
	     	   for(int i = 0; i<list.size();i++) 
	     	   {
	     		   writer.write(list.get(i).pergunta);
		               writer.write("\n");
		               writer.write(list.get(i).resposta);
		               writer.write("\n");
		               writer.write(list.get(i).caixaLeitner);
		               writer.write("\n");
	     	   }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	} 
	
	
	 public List<String> lerLinhas(String filePath) throws IOException {
	        List<String> lines = new ArrayList<>();
	        BufferedReader reader = new BufferedReader(new FileReader(filePath));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            lines.add(line);
	        }
	        reader.close();
	        return lines;
	    }
	
	 public List<String> removeLinhasTexto(List<String> lines, String textoEncontrado) {
	        List<String> linhasModificadas = new ArrayList<>();
	        for (int i = 0; i < lines.size(); i++) {
	            if (lines.get(i).contains(textoEncontrado)) {
	            	//System.out.println("parte do remover" + lines.get(i));
	                i += 2;  // Pular a linha atual e as duas seguintes
	            } else {
	                linhasModificadas.add(lines.get(i));
	            }
	        }
	        return linhasModificadas;
	    }
	 
	public void escreverNoArquivo(String caminhoArq, List<String> lines) throws IOException {
	    	
	        BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArq));
	        for (String line : lines) {
	            writer.write(line);
	            writer.newLine();
	        }
	        writer.close();
	    }
	
	
	public void carregarDadosArq(ObservableList<Flashcard> list) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("CarregarDados.txt",false))) {
			if(list.size() >= 10) {
				 for(int i = 0; i<10;i++) 
		      	   {
		      		   writer.write(list.get(i).getPergunta());
			               writer.write("\n");
			               writer.write(list.get(i).getResposta());
			               writer.write("\n");
			               writer.write(list.get(i).getCaixaLeitner());
			               writer.write("\n");
		      	   }
			 } 
	  } catch (IOException e) {
	             e.printStackTrace();
	         }
	}
	
	
	public void criarArquivo(String pergunta,String resposta,String caixaLeitner) {
    	Random random = new Random();
        
        int randomInt = random.nextInt(1000);
        String numeroAleatorio = String.valueOf(randomInt);
        
        File arquivo = new File("teste1" + numeroAleatorio+ ".txt");

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo))) {
        	escritor.write(pergunta);
        	escritor.write("\n");
        	escritor.write(resposta);
        	escritor.write("\n");
        	escritor.write(caixaLeitner);
        	escritor.write("\n");
        	
            System.out.println("Arquivo criado e conteúdo escrito com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao criar ou escrever no arquivo: " + e.getMessage());
        }
    }
	
	

    public ObservableList<Flashcard> lerFlashcardsArquivo(String nomeArquivo) throws IOException {
    	System.out.println("salve");
		 ObservableList<Flashcard> usuarios = FXCollections.observableArrayList();
	        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
	        	
	            String pergunta, resposta,caixaString;
	            //int caixa;
	            
	            while ((pergunta = reader.readLine()) != null && (resposta = reader.readLine()) != null && (caixaString = reader.readLine()) != null) {
	            	//caixa = Integer.parseInt(caixaString);
	            	Flashcard usuario = new Flashcard(pergunta,resposta,caixaString);
	                usuarios.add(usuario);
	            }
	            
	        } catch (IOException e) {
	        	
	            e.printStackTrace();
	        }
	        return usuarios;
    }
	
	
	
}
