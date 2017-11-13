package projetofinal.so.memoria;

public class GerenciaMemoria implements MemoriaRAM {
	
	private Memoria memoria; //Verificar como a memoria eh tratada pelo gerenciador; Primeiro palpite: objeto private do gerenciador
	
	/*Mostra na tela a representacao dos vetores de memoria*/
	
	public GerenciaMemoria() {
		memoria = new Memoria();
	}
	
	public void mostrarMemoria () {
		int[] memReal = memoria.getMemReal();
		int[] memUser = memoria.getMemUsuario();
		String mostrarReal = "[";
		String mostrarUsuario = "[";
		
		for (int i : memReal) {
			mostrarReal += (i+"|");
		}
		mostrarReal = mostrarReal.substring(0, mostrarReal.length()-1); //remover ultimo char
		mostrarReal += "]";
		System.out.println("Memoria de tempo real");
		System.out.println(mostrarReal);
		
		for (int i : memUser) {
			mostrarUsuario += (i+"|");
		}
		mostrarUsuario = mostrarUsuario.substring(0, (mostrarUsuario.length()-1)); //remover ultimo char
		mostrarUsuario += "]";
		System.out.println("Memoria de usuário");
		System.out.println(mostrarUsuario);
	}
	
	/* Procura uma a primeira sequencia de X blocos livres sequenciais na memoria
	 * Retorna a posição incial da sequencia no vetor de memoria */
	public int encontraMemoria (int quantidadeBlocos, int prioridade) throws MemoriaInsuficienteException{
		
		int[] memAtual = null;
		if (prioridade == 0) { //processo de prioridade 0 define processo de tempo real
			if (quantidadeBlocos > 64) { //usa mais que o disponivel na memoria de tempo real
				throw new MemoriaInsuficienteException("Nao há memória de tempo real suficiente em hardware para o processo.");
			}
			memAtual = memoria.getMemReal(); //obtem o estado atual da memoria de tempo real
		}
		else { //processo de usuario
			if (quantidadeBlocos > 960) { //usa mais que o disponivel na memoria de usuario
				throw new MemoriaInsuficienteException("Nao há memória de usuário suficiente em hardware para o processo.");
			}
			memAtual = memoria.getMemUsuario(); //obtem o estado atual da memoria de usuario
		}
		
		int blCorrente = 0;
		int nVazios = 0;
		while (blCorrente < memAtual.length) { //percorre toda a memoria buscando alocacao
			if (memAtual[blCorrente] == 0) { //bloco de memoria livre
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
		
		/*Retornei um inteiro em vez de lancar uma exception
		Fiz isso pois essa situação não é motivo para encerrar o programa
		throw new MemoriaIndisponivelException("Nao ha memoria suficiente para o processo no momento");*/
		return -1;
	}
	
	/* Aloca 'tamanhoBloco' espacos de memoria a partir de uma posicao inicial do vetor
	 * Tais espacos serao atribuidos ao processoID */
	public void alocaMemoria (int processoID, int posicaoInicial, int tamanhoBloco, int prioridade) { 
		
		if (prioridade == 0) { //processo de prioridade 0 define processo de tempo real
			memoria.setMemRealRange(processoID, posicaoInicial, posicaoInicial+tamanhoBloco-1);
		}
		else { //processo de usuario
			memoria.setMemUsuarioRange(processoID, posicaoInicial, posicaoInicial+tamanhoBloco);
		}
	}
	
	
	public boolean desalocarProcesso(int processoID, int prioridade) {
		
		int[] memAtual = null;
		if (prioridade == 0) { //processo de prioridade 0 define processo de tempo real
			memAtual = memoria.getMemReal(); //obtem o estado atual da memoria de tempo real
			for (int i = 0; i < memAtual.length; i++) { //percorre toda a memoria
				if (memAtual[i] == processoID)
					memoria.setMemReal(0, i); //Define processo 'processoID' para os 'quantidadeBlocos' blocos de memoria, onde o bloco corrente eh o ultimo deles
			}
			return true;
		}
		else { //processo de usuario
			memAtual = memoria.getMemUsuario(); //obtem o estado atual da memoria de usuario
			for (int i = 0; i < memAtual.length; i++) { //percorre toda a memoria
				if (memAtual[i] == processoID)
					memoria.setMemUsuario(0, i); //Define processo 'processoID' para os 'quantidadeBlocos' blocos de memoria, onde o bloco corrente eh o ultimo deles
			}
			return true;
		}
	}
}