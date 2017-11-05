package projetofinal.so.processos;

public class ProcessoInexistenteException extends Exception {
	
	public ProcessoInexistenteException(String mensagem) {
		super(mensagem);
	}
	
	public ProcessoInexistenteException() {
	}
	
	public ProcessoInexistenteException(String mensagem, Throwable excecao) {
		super(mensagem,excecao);
	}
}
