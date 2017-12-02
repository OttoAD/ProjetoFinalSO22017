package projetofinal.so.dados.operacoes;

public class Operacao {

	/*A partir da linha n + 3: cada linha representa uma operação a ser efetivada pelo sistema de arquivos
	 <ID_Processo>, <Código_Operação>, <Nome_arquivo>,<se_operacaoCriar_numero_blocos>*/
	
	private int idProcesso;
	private int codigoOperacao;
	private char nomeArquivo;
	private int tamanho;
	
	public Operacao() {
		this.idProcesso = -1;
		this.codigoOperacao = -1;
		this.nomeArquivo = '\0';
		this.tamanho = 0;
	}
	
	public int getIdProcesso() {
		return idProcesso;
	}
	public void setIdProcesso(int idProcesso) {
		this.idProcesso = idProcesso;
	}
	public int getCodigoOperacao() {
		return codigoOperacao;
	}
	public void setCodigoOperacao(int codigoOperacao) {
		this.codigoOperacao = codigoOperacao;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public char getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(char nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
}
