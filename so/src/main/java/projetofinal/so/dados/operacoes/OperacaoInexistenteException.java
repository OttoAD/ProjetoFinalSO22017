package projetofinal.so.dados.operacoes;

public class OperacaoInexistenteException extends Exception {
	public OperacaoInexistenteException(String mensagem) {
		super(mensagem);
	}
	
	public OperacaoInexistenteException() {
	}
	
	public OperacaoInexistenteException(String mensagem, Throwable excecao) {
		super(mensagem,excecao);
	}
}
