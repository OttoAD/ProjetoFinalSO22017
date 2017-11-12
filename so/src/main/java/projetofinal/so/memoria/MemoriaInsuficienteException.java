package projetofinal.so.memoria;

public class MemoriaInsuficienteException extends Exception {
	
	public MemoriaInsuficienteException(String mensagem) {
		super(mensagem);
	}
	
	public MemoriaInsuficienteException() {
	}
	
	public MemoriaInsuficienteException(String mensagem, Throwable excecao) {
		super(mensagem,excecao);
	}
}
