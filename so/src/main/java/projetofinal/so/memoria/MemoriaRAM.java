package projetofinal.so.memoria;

public interface MemoriaRAM {

	/* Procura uma a primeira sequencia de X blocos livres sequenciais na memoria
	 * Retorna a posição incial da sequencia no vetor de memoria
	 * Lança Exception caso o processo solicite uma memoria impossivel
	*/
	public int encontraMemoria (int quantidadeBlocos, int prioridade) throws MemoriaInsuficienteException;
	
	/* Aloca 'tamanhoBloco' espacos de memoria a partir de uma posicao inicial do vetor
	 * Tais espacos serao atribuidos ao processoID */
	public void alocaMemoria (int i, int posicaoInicial, int tamanhoBloco, int prioridade);
	
	/*
	 * desaloca todos os blocos de memoria alocados ao processo de um ID especificado
	 * A prioridade do processo define se o processo de desalocacao deve ser feito na memoria de usuario ou de tempo real*/
	public boolean desalocarProcesso(int i, int prioridade);
	
	/* Realiza amostragem na tela dos vetores de blocos de memória (real e de usuario) */
	public void mostrarMemoria();
}



