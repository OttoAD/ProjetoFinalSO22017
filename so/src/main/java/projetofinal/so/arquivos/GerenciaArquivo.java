package projetofinal.so.arquivos;

import java.util.ArrayList;

import projetofinal.so.dados.LeituraArquivoException;
import projetofinal.so.dados.operacoes.LeituraArquivoOperacoes;
import projetofinal.so.dados.operacoes.ListaOperacoes;
import projetofinal.so.dados.operacoes.Operacao;
import projetofinal.so.dados.operacoes.OperacaoInexistenteException;

public class GerenciaArquivo implements Disco {
	
	/*espacoDisco tem que ser um vetor de arquivos para armazenar o dono de cada arquivo no proprio arquivo*/

	public static final String NOMEARQUIVOOPERACOES = "files.txt";
	private int quantidadeBlocosDisco;
	private int quantidadeSegmentosOcupadosDisco;
	private ListaArquivos arquivos;
	private ListaOperacoes operacoes;
	private Arquivo[] espacoDisco; //DESSE JEITO; VAI MUDAR TUDO MESMO
	
	
	
	public GerenciaArquivo() throws LeituraArquivoException{
		LeituraArquivoOperacoes leitura = new LeituraArquivoOperacoes(NOMEARQUIVOOPERACOES);
		int[] dadosIniciais = leitura.lerDadosDisco();
		
		this.quantidadeBlocosDisco = dadosIniciais[0];
		this.quantidadeSegmentosOcupadosDisco = dadosIniciais[1];
		this.arquivos = leitura.lerArquivos();
		this.operacoes = leitura.lerOperacoes();
		this.espacoDisco = new Arquivo[quantidadeBlocosDisco];
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
	
	private void setarDiscoInicial(ListaArquivos arquivos) {
		Arquivo arqCorrente = null;
		int blInicial = -1, tamanho = -1;
		try {
			for (int i = 0; i < arquivos.getQuantidade(); i++) { //itera cada arquivo definido hardcoded
				arqCorrente = arquivos.getArquivoPorIndice(i);
				blInicial = arqCorrente.getNumeroBloco(); //recupera onde o arq comeca
				tamanho = arqCorrente.getTamanho();
				
				for (int blocoCorrente = blInicial; blocoCorrente < (blInicial + tamanho); blocoCorrente++) {
					espacoDisco[blocoCorrente] = arqCorrente; //seta os blocos do arquivo no disco
				}
				
			}
		} catch (ArquivoInexistenteException aie) {}
	}
	
	/* Procura uma a primeira sequencia de X blocos livres sequenciais no disco
	 * Retorna a posição incial da sequencia no vetor de disco 
	 * Retorna -1 se nao encontrar espaco*/	
	private int firstFit (int quantidadeBlocos) {
		int nVazios = 0;
		
		for (int blocoCorrente = 0; blocoCorrente < this.quantidadeBlocosDisco; blocoCorrente++) { //percorre o disco buscando espaço vazio
			if (espacoDisco[blocoCorrente] == null) { //bloco de disco livre 
				nVazios++;
				if (nVazios == quantidadeBlocos) { //encontrou um espaço que cabe o arquivo
					return (blocoCorrente - nVazios + 1); //retorna a posicao onde o novo bloco começa
				}
			}
			else { //bloco ocupado; reiniciar contagem
				nVazios = 0;
			}
		}
		
		return -1;
	}
	
	
	public void criarArquivo(int idProcesso, Arquivo arq) throws EspacoDiscoInsuficienteException {
		int tamanhoArquivo = arq.getTamanho();
		
		arq.setProcessoDono(idProcesso);
		int posicaoAlocacao = firstFit(tamanhoArquivo);
		if (posicaoAlocacao < 0) {
			throw new EspacoDiscoInsuficienteException("Não há espaço em disco suficiente para o arquivo " + arq.getNomeArquivo());
		}
		else {
			arq.setNumeroBloco(posicaoAlocacao); //define a posicao inicial do arquivo
			for (int i = posicaoAlocacao; i < (posicaoAlocacao+tamanhoArquivo); i++) {
				espacoDisco[i] = arq;
			}
		}
	}
	
	public Arquivo buscarArquivoDisco(char nomeArquivo) throws ArquivoInexistenteException{
			for (Arquivo arq : espacoDisco) {
			if(arq != null && arq.getNomeArquivo() == nomeArquivo) {
				return arq;
			}
		}
		
		throw new ArquivoInexistenteException("O arquivo não foi encontrado no disco");
	}
	
	public void removerArquivo(int idProcessoChamador, int prioridadeProcesso, char nomeArquivo) throws PermissaoNegadaException, ArquivoInexistenteException {
		Arquivo arq = buscarArquivoDisco(nomeArquivo);
		arq.getNumeroBloco();
		int bloco = arq.getNumeroBloco();
		int tamanho = arq.getTamanho();
		
		if ((prioridadeProcesso == 0) || (idProcessoChamador == arq.getProcessoDono())) {
			for(int i = bloco ; i < (bloco+tamanho) ; i++) {
				this.espacoDisco[i] = null;
			}
		}else {
			throw new PermissaoNegadaException("O processo "+idProcessoChamador+" nao possui permissão para deletar o arquivo "+nomeArquivo);
		}
	}
	
	//TEM QUE REVER ESSA FUNÇÃO
	public void executaOperacoesProcesso(int idProcesso, int prioridadeProcesso) throws OperacaoInexistenteException {
		ArrayList<Operacao> toExecute = operacoes.getOperacoesProcesso(idProcesso);
		char nomeArquivo;
		
		for (Operacao operacao : toExecute) {
			int acao = operacao.getCodigoOperacao();
			nomeArquivo = operacao.getNomeArquivo();
			if (acao == 0) { //criacao de arquivo
				try {
					criarArquivo(idProcesso, new Arquivo(nomeArquivo,-1, operacao.getTamanho(), idProcesso));
					System.out.println("\nArquivo "+nomeArquivo+" criado com sucesso pelo processo "+idProcesso);
				} catch (EspacoDiscoInsuficienteException e) {
					System.out.println("\nA criação do arquivo "+nomeArquivo+" pelo processo "+idProcesso+" deu erro por falta de espaço em disco");
				}
			}
			else if (acao == 1) { //remocao de arquivo
				try {
					removerArquivo(idProcesso, prioridadeProcesso, nomeArquivo);
				} catch (PermissaoNegadaException e) {
					System.out.println("\nO processo "+idProcesso+" não possui permissão para deletar o arquivo "+nomeArquivo);
				} catch (ArquivoInexistenteException e) {
					System.out.println("\nO arquivo "+nomeArquivo+" não existe em disco: é impossível deletá-lo");
				}
			}
			else {
				throw new OperacaoInexistenteException("O código " + acao + " de operação é inválido");
			}
		}
	}

	public void mostrarDisco() {
		System.out.println("\n---------- BLOCOS DO DISCO ----------");
		System.out.print("[|"); //pra ficar simetrico
		for (Arquivo arq : espacoDisco) {
			if (arq == null)
				System.out.print(" |");
			else
				System.out.print(arq.getNomeArquivo()+"|");
		}
		System.out.println("]");
		
	}
}
