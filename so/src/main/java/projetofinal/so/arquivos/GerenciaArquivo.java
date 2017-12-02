package projetofinal.so.arquivos;

import java.util.ArrayList;

import projetofinal.so.dados.LeituraArquivoException;
import projetofinal.so.dados.operacoes.LeituraArquivoOperacoes;
import projetofinal.so.dados.operacoes.ListaOperacoes;
import projetofinal.so.dados.operacoes.Operacao;
import projetofinal.so.dados.operacoes.OperacaoInexistenteException;

public class GerenciaArquivo implements Disco {
	
	/*espacoDisco tem que ser um array de arquivos para armazenar o dono de cada arquivo no proprio arquivo*/

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
		setarDiscoInicial(this.arquivos);
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
	
	public void executaOperacoesProcesso(int idProcesso, int prioridadeProcesso) throws EspacoDiscoInsuficienteException {
		ArrayList<Operacao> toExecute = operacoes.getOperacoesProcesso(idProcesso);
		
		for (Operacao operacao : toExecute) {
			int acao = operacao.getCodigoOperacao();
			
			if (acao == 0) { //criacao de arquivo
				criarArquivo(idProcesso,
						new Arquivo(operacao.getNomeArquivo(),-1, operacao.getTamanho(), idProcesso));
			}
			else if (acao == 1) { //remocao de arquivo
				removerArquivo(idProcesso, prioridadeProcesso, arq);
			}
			else {
				throw new OperacaoInexistenteException("O código da operação é inválido");
			}
		}
	}

	public void removerArquivo(int idProcessoChamador, int prioridadeProcesso, int processoDono, char nomeArquivo) throws PermissaoNegadaException {
		if (prioridadeProcesso != 0 && idProcessoChamador != processoDono) 
			throw new PermissaoNegadaException("O processo "+idProcessoChamador+" nao possui permissão para deletar o arquivo "+nomeArquivo); //processo nao eh de tempo real nem dono do arquivo: nao pode remover
		else {
			/*DELETAR ARQUIVO*/
			for (char i : espacoDisco) {
				if (i == nomeArquivo) //bloco de disco atual pertence ao processo
					this.espacoDisco[i] = 0; //liberar espaco em disco
			}
		}
	}
	
	/* Procura uma a primeira sequencia de X blocos livres sequenciais no disco
	 * Retorna a posição incial da sequencia no vetor de disco 
	 * Retorna -1 se nao encontrar espaco*/
	
	public int espacoEmDisco (int quantidadeBlocos) {
		int nVazios = 0;
		for (int blCorrente : espacoDisco) { //percorre o disco buscando alocacao
			if (espacoDisco[blCorrente] == 0) { //bloco de disco livre
				nVazios++;
				if (nVazios == quantidadeBlocos) { //encontrado o 'First Fit'
					return (blCorrente-nVazios+1); //retorna a posicao inicial do vetor de blocos livres
				}
			}
			else { //bloco ocupado; reiniciar contagem
				nVazios = 0;
			}
			blCorrente++;
		}
		return -1;
	}

	private void setarDiscoInicial(ListaArquivos arquivos) {
		Arquivo arqCorrente = null;
		int blInicial = -1, tamanho = -1;
		try {
			for (int i = 0; i < arquivos.getQuantidade(); i++) { //itera cada arquivo definido hardcoded
				arqCorrente = arquivos.getArquivoPorIndice(i);
				blInicial = arqCorrente.getNumeroBloco(); //recupera onde o arq comeca
				tamanho = arqCorrente.getTamanho();
				for (int j = blInicial; j < blInicial+tamanho; j++) {
					espacoDisco[j] = arqCorrente.getNomeArquivo(); //seta os blocos do arquivo no disco
				}
			}
		} catch (ArquivoInexistenteException e) {			
		}
	}
		

	public void criarArquivo(int idProcesso, Arquivo arq) throws EspacoDiscoInsuficienteException {
		int tamanhoArquivo = arq.getTamanho();
		
		arq.setProcessoDono(idProcesso);
		int posicao = espacoEmDisco(tamanhoArquivo);
		if (posicao < 0) {
			throw new EspacoDiscoInsuficienteException("Não há espaço em disco suficiente para o arquivo " +arq.getNomeArquivo());
		}
		else {
			this.setDiscoRange(arq.getNomeArquivo(), posicao, posicao+tamanhoArquivo); //seta no disco as posicoes preenchidas pelo arquivo
			arq.setNumeroBloco(posicao); //define a posicao inicial do arquivo
		}
	}
	
	public void setDiscoRange(char nomeArquivo, int posicaoinicial, int posicaofinal) { //define um processo para uma sequ�ncia de blocos de memoria de processos em tempo real
		for (int i = posicaoinicial; i < posicaofinal; i++) {
			espacoDisco[i] = nomeArquivo;
		}
	}
	
	public void mostrarDisco() {
		String mostrarDisco = "[";
		
		for (char i : espacoDisco) {
			mostrarDisco += (i+"|");
		}
		mostrarDisco = mostrarDisco.substring(0, mostrarDisco.length()-1); //remover ultimo char
		mostrarDisco += "]";
		System.out.println("Disco do Sistema");
		System.out.println(mostrarDisco);
		
	}
}
