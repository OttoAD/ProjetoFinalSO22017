package projetofinal.so.dados.operacoes;

public class Operacao {

	/*A partir da linha n + 3: cada linha representa uma operação a ser efetivada pelo sistema de arquivos
	 <ID_Processo>, <Código_Operação>, <Nome_arquivo>,<se_operacaoCriar_numero_blocos>*/
	
	private int idProcesso;
	private int codigoOperacao;
	private int nomeArquivo;
	private int numeroBlocos;
	
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
	public int getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(int nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public int getNumeroBlocos() {
		return numeroBlocos;
	}
	public void setNumeroBlocos(int numeroBlocos) {
		this.numeroBlocos = numeroBlocos;
	}
	
}
