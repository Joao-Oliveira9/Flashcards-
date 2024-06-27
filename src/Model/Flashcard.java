package Model;

public class Flashcard {
	public String caixaLeitner;
	public String pergunta;
	public String resposta;
	
	public Flashcard(String pergunta,String resposta,String caixaLeitner){
		this.pergunta = pergunta;
		this.resposta = resposta;
		this.caixaLeitner =  caixaLeitner;
	}
	

	public String getPergunta() {
		return pergunta;
	}
	
	public String getResposta() {
		return resposta;
	}
	
	public String getCaixaLeitner() {
		return caixaLeitner;
	}
}
