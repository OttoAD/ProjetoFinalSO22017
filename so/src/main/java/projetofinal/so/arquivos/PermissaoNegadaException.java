package projetofinal.so.arquivos;

public class PermissaoNegadaException extends Exception {
	public PermissaoNegadaException(String mensagem) {
		super(mensagem);
	}
	
	public PermissaoNegadaException() {
	}
	
	public PermissaoNegadaException(String mensagem, Throwable excecao) {
		super(mensagem,excecao);
	}
}
