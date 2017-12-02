package projetofinal.so.arquivos;

import java.util.ArrayList;

import projetofinal.so.dados.LeituraArquivoException;
import projetofinal.so.dados.operacoes.LeituraArquivoOperacoes;
import projetofinal.so.dados.operacoes.ListaOperacoes;
import projetofinal.so.dados.operacoes.Operacao;

public class GerenciaArquivo implements Disco{

	public static final String NOMEARQUIVOOPERACOES = "files.txt";
	private int quantidadeBlocosDisco;
	private int quantidadeSegmentosOcupadosDisco;
	private ListaArquivos arquivos;
	private ListaOperacoes operacoes;
	private char[] espacoDisco;
	
	public GerenciaArquivo() throws LeituraArquivoException{
		LeituraArquivoOperacoes leitura = new LeituraArquivoOperacoes(NOMEARQUIVOOPERACOES);
		int[] dadosIniciais = leitura.lerDadosDisco();
		
		this.quantidadeBlocosDisco = dadosIniciais[0];
		this.quantidadeSegmentosOcupadosDisco = dadosIniciais[1];
		this.arquivos = leitura.lerArquivos();
		this.operacoes = leitura.lerOperacoes();
		this.espacoDisco = new char[quantidadeBlocosDisco];
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

	@Override
	public ArrayList<Operacao> getOperacoesProcesso(int idProcesso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removerArquivo(int idProcesso, Arquivo arq) {
		// TODO Auto-generated method stub
	}

	@Override
	public void criarArquivo(int idProcesso, Arquivo arq) {
		// TODO Auto-generated method stub		
	}
	
	public void mostrarDisco() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void buscarArquivo() {
		// TODO Auto-generated method stub
	}
}
