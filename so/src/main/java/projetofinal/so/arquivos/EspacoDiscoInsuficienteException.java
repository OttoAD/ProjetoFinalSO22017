package projetofinal.so.arquivos;

public class EspacoDiscoInsuficienteException extends Exception {
	public EspacoDiscoInsuficienteException(String mensagem) {
		super(mensagem);
	}
	
	public EspacoDiscoInsuficienteException() {
	}
	
	public EspacoDiscoInsuficienteException(String mensagem, Throwable excecao) {
		super(mensagem,excecao);
	}
}
