package projetofinal.so.arquivos;

public class ArquivoInexistenteException extends Exception {
	public ArquivoInexistenteException(String mensagem) {
		super(mensagem);
	}
	
	public ArquivoInexistenteException() {
	}
	
	public ArquivoInexistenteException(String mensagem, Throwable excecao) {
		super(mensagem,excecao);
	}
}
