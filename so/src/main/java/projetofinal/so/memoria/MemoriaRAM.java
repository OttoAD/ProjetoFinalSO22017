package projetofinal.so.memoria;

public interface MemoriaRAM {

	/* Procura uma a primeira sequencia de X blocos livres sequenciais na memoria
	 * Retorna a posição incial da sequencia no vetor de memoria */
	public int encontraMemoria (int quantidadeBlocos, int prioridade);
	
	/* Aloca 'tamanhoBloco' espacos de memoria a partir de uma posicao inicial do vetor
	 * Tais espacos serao atribuidos ao processoID */
	public void alocaMemoria (int processoID, int posicaoInicial, int tamanhoBloco, int prioridade);
	
	/* Realiza amostragem na tela dos vetores de blocos de memória (real e de usuario) */
	public void mostrarMemoria();
}



