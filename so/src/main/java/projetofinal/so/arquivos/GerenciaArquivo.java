package projetofinal.so.arquivos;

import projetofinal.so.dados.LeituraArquivoException;
import projetofinal.so.dados.operacoes.LeituraArquivoOperacoes;
import projetofinal.so.dados.operacoes.ListaOperacoes;

public class GerenciaArquivo {

	public static final String NOMEARQUIVOOPERACOES = "files.txt";
	private int quantidadeBlocosDisco;
	private int quantidadeSegmentosOcupadosDisco;
	private ListaArquivos arquivos;
	private ListaOperacoes operacoes;
	
	public GerenciaArquivo() throws LeituraArquivoException{
		LeituraArquivoOperacoes leitura = new LeituraArquivoOperacoes(NOMEARQUIVOOPERACOES);
		int[] dadosIniciais = leitura.lerDadosDisco();
		
		this.quantidadeBlocosDisco = dadosIniciais[0];
		this.quantidadeSegmentosOcupadosDisco = dadosIniciais[1];
		this.arquivos = leitura.lerArquivos();
		this.operacoes = leitura.lerOperacoes();
	}
	
	public int getQuantidadeBlocosDisco() {
		return quantidadeBlocosDisco;
	}
	public void setQuantidadeBlocosDisco(int quantidadeBlocosDisco) {
		this.quantidadeBlocosDisco = quantidadeBlocosDisco;
	}
	public int getQuantidadeSegmentosOcupadosDisco() {
		return quantidadeSegmentosOcupadosDisco;
	}
	public void setQuantidadeSegmentosOcupadosDisco(int quantidadeSegmentosOcupadosDisco) {
		this.quantidadeSegmentosOcupadosDisco = quantidadeSegmentosOcupadosDisco;
	}
	
	
	
	
}
