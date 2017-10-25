package projetofinal.so.memoria;

public interface MemoriaRAM {

	/*
	 * Reserva a memória para o processo.
	 * Retorna true se havia memória suficiente,
	 * retorna false caso contrário.
	 */
	public boolean reservarMemoria(int processoID, int quantidadeBlocos, int prioridade);
	
	/*
	 * Realiza amostragem na tela dos vetores de blocos de memória (real e de usuario)
	 */
	public void mostrarMemoria();
}



