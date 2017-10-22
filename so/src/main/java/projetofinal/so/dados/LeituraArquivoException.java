package projetofinal.so.dados;

public class LeituraArquivoException extends Exception {
	
	public LeituraArquivoException(String mensagem,Throwable excecao) {
		super(mensagem,excecao);
	}
	
	public LeituraArquivoException(String mensagem) {
		super(mensagem);
	}
}
