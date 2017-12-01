package projetofinal.so;

public class GerenciaException extends Exception {
	public GerenciaException(String mensagem) {
		super(mensagem);
	}
	
	public GerenciaException() {
	}
	
	public GerenciaException(String mensagem, Throwable excecao) {
		super(mensagem,excecao);
	}
}
